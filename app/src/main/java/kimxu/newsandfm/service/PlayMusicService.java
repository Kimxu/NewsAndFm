package kimxu.newsandfm.service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.SystemClock;

import java.io.IOException;

import kimxu.newsandfm.model.Audio;
import kimxu.utils.L;

/**
 * 音乐播放服务类
 * TODO 遗留问题，来消息对音量的控制权处理,拔耳机广播没有测试
 */
public class PlayMusicService extends Service implements MediaPlayer.OnCompletionListener, MediaPlayer.OnErrorListener, MediaPlayer.OnPreparedListener, AudioManager.OnAudioFocusChangeListener {
    private MediaPlayer mPlayer;
    private State mCurrentState;
    private OnPlaybackListener mPlaybackListener;
    private Audio mCurrentAudio;
    private AudioManager mAudioManager;
    private MediaPlayer.OnCompletionListener mCompletionListener;

    private IntentFilter mIntentFilter = new IntentFilter(AudioManager.ACTION_AUDIO_BECOMING_NOISY);
    private NoisyAudioStreamReceiver myNoisyAudioStreamReceiver = new NoisyAudioStreamReceiver();

    private final int RESULT_MUSIC_RUNNING = 0x001;
    private final int RESULT_MUSIC_STOP = 0x002;
    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case RESULT_MUSIC_RUNNING:
                    mPlaybackListener.onStartProgressChanged(msg.arg1);
                    break;
                case RESULT_MUSIC_STOP:
                    mPlaybackListener.onStartProgressChanged(0);
                    break;
            }

            return false;
        }
    });

    public class ServiceBinder extends Binder {
        private PlayMusicService mService = null;

        public ServiceBinder(PlayMusicService service) {
            mService = service;
        }

        public PlayMusicService getService() {
            return mService;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        startPlayback();
        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        requestAudioFocus();
        return new ServiceBinder(this);
    }

    //Android开发的过程中，每次调用startService(Intent)的时候，
    // 都会调用该Service对象的onStartCommand(Intent,int,int)方法，
    // 然后在onStartCommand方法中做一些处理。

    /*

        START_STICKY：如果service进程被kill掉，保留service的状态为开始状态，但不保留递送的intent对象。
    随后系统会尝试重新创建service，由于服务状态为开始状态，所以创建服务后一定会调用
    onStartCommand(Intent,int,int)方法。如果在此期间没有任何启动命令被传递到service，
    那么参数Intent将为null。
        START_NOT_STICKY：“非粘性的”。使用这个返回值时，如果在执行完onStartCommand后，
    服务被异常kill掉，系统将会把它置为started状态，系统不会自动重启该服务，
    直到startService(Intent intent)方法再次被调用;。
        START_REDELIVER_INTENT：重传Intent。使用这个返回值时，如果在执行完onStartCommand后，
    服务被异常kill掉，系统会自动重启该服务，并将Intent的值传入。
        START_STICKY_COMPATIBILITY：START_STICKY的兼容版本，但不保证服务被kill后一定能重启。

     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return Service.START_STICKY;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        stopPlayback();
        return super.onUnbind(intent);
    }

    private void init() {
        if (mPlayer == null) {
            mPlayer = new MediaPlayer();
            //PlayMusicService 要实现这三个接口
            mPlayer.setOnErrorListener(this);
            mPlayer.setOnPreparedListener(this);
            mPlayer.setOnCompletionListener(this);
            changeState(State.IDLE);
        } else {
            if (mCurrentState == State.IDLE || mCurrentState == State.INITIALIZED || mCurrentState == State.PREPARED ||
                    mCurrentState == State.STARTED || mCurrentState == State.PAUSED || mCurrentState == State.STOPPED ||
                    mCurrentState == State.COMPLETED || mCurrentState == State.ERROR) {
                mPlayer.reset();
                changeState(State.IDLE);
            }
        }

    }

    public void start(Audio audio) {
        init();
        try {
            if (mCurrentState == State.IDLE) {
                mCurrentAudio = audio;
                mPlayer.setDataSource(audio.getPath());    //Valid Sates IDLE
            }
            changeState(State.INITIALIZED);
            if (mCurrentState != State.ERROR) {
                mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                //保持屏幕常亮
                mPlayer.setScreenOnWhilePlaying(true);
            }
            if (mCurrentState == State.INITIALIZED || mCurrentState == State.STOPPED) {
                if(!mPlayer.isPlaying())
                mPlayer.prepareAsync();
                else L.d("正在播放。。。");
                changeState(State.PREPARING);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void reStart() {
        if (mPlayer != null) {
            mPlayer.start();
            changeState(State.STARTED);
        }
    }

    public void paused() {
        if (mPlayer != null) {
            mPlayer.pause();
            changeState(State.PAUSED);
        }
    }

    public void stop() {
        if (mPlayer != null) {
            mPlayer.stop();
            changeState(State.STOPPED);
        }
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        mCompletionListener.onCompletion(mp);
    }

    private void doStart() {
        mPlayer.start();
        changeState(State.STARTED);
    }

    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        //L.e("onError" + what + ".." + extra);
        return false;
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        doStart();
    }

    private void changeState(State state) {
        mCurrentState = state;
        //如果是播放的状态
        if (state == State.STARTED) {
            mPlaybackListener.onStartProgressDuration(mPlayer.getDuration());
            new Thread(() -> {
                while (mPlayer.isPlaying()) {
                    Message message = new Message();
                    message.what = RESULT_MUSIC_RUNNING;
                    message.arg1 = mPlayer.getCurrentPosition();
                    mHandler.sendMessage(message);
                    SystemClock.sleep(1000);
                }
            }).start();

        } else if (state == State.STOPPED) {
            mHandler.sendEmptyMessage(RESULT_MUSIC_STOP);
        }
        if (mPlaybackListener != null) {
            mPlaybackListener.onStateChanged(mCurrentAudio, mCurrentState);
        }
    }

    /*这里采用了setOnPlaybackListener的方法，如果有需要，也可以用一个List去保存一个Listener集合，
    只要在适当的时候进行释放，例如在Service的onDestroy方法中，去把这个List清空掉*/
    public void setOnPlaybackListener(OnPlaybackListener listener) {
        mPlaybackListener = listener;
    }

    public void setmCompletionListener(MediaPlayer.OnCompletionListener completionListener) {
        this.mCompletionListener = completionListener;
    }

    /**
     * 播放回调
     */
    public interface OnPlaybackListener {
        /**播放状态改变 */
        void onStateChanged(Audio source, State state);
        /**播放进度改变 */
        void onStartProgressChanged(int progress);
        /**设置播放总长度 */
        void onStartProgressDuration(int duration);
    }

    public void registerServiceCallback() {

    }


    private int requestAudioFocus() {
        //Toast.makeText(mContext, "requestAudioFocus", Toast.LENGTH_SHORT).show();
        return mAudioManager.requestAudioFocus(this, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN);
    }

    private int releaseAudioFocus() {
        //Toast.makeText(mContext, "releaseAudioFocus excuted", Toast.LENGTH_SHORT).show();
        return mAudioManager.abandonAudioFocus(this);
    }

    @Override
    public void onAudioFocusChange(int focusChange) {
        switch (focusChange) {
            //失去了Audio Focus
            case AudioManager.AUDIOFOCUS_LOSS:
                releaseAudioFocus();
                stop();
                L.i("AUDIOFOCUS_LOSS:" + focusChange);
                break;
            //获得了Audio Focus
            case AudioManager.AUDIOFOCUS_GAIN:
                recoverVolume();
                /*if (mService.getState() == MichaelService.State.PAUSED) {
                    mService.resume();
                }*/
                L.i("AUDIOFOCUS_GAIN:" + focusChange);
                break;
            //暂时失去Audio Focus
            case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT:
                paused();
                L.i("AUDIOFOCUS_LOSS_TRANSIENT:" + focusChange);
                break;
            //暂时失去Audio Focus 可以继续播放
            case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK:
                lowerVolume();
                L.i("AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK:" + focusChange);
                break;
            default:
                L.i("default:" + focusChange);
                break;
        }
    }

    public void seekTo(int progress){
        if (mPlayer.isPlaying()){
            mPlayer.seekTo(progress);
        }
    }

    private void recoverVolume() {
        //TODO 获得声音焦点
    }


    private void lowerVolume() {
        mAudioManager.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_LOWER, 0);
    }

    /**
     * 返回当前播放的音乐
     *
     * @return
     */
    public Audio getCurrentAudio() {
        return mCurrentAudio;
    }
    /**
     * 播放状态
     */
    public enum State {
        IDLE,
        INITIALIZED,
        PREPARED,
        PREPARING,
        STARTED,
        PAUSED,
        STOPPED,
        COMPLETED,
        END,
        ERROR

    }


    private void startPlayback() {
        registerReceiver(myNoisyAudioStreamReceiver, mIntentFilter);
    }

    private void stopPlayback() {
        unregisterReceiver(myNoisyAudioStreamReceiver);
    }

    @Override
    public void onDestroy() {
        stopPlayback();
        super.onDestroy();
    }

    //注册拔耳机监听的广播
    private class NoisyAudioStreamReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (AudioManager.ACTION_AUDIO_BECOMING_NOISY.equals(intent.getAction())) {
                // Pause the playback
                paused();
            }
        }
    }

    public MediaPlayer getPlayer(){
        return mPlayer;
    }
}
