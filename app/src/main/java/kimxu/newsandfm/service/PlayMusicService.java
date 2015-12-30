package kimxu.newsandfm.service;

import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;

import java.io.IOException;

import kimxu.newsandfm.model.Audio;
/**
 * 音乐播放服务类
 */
public class PlayMusicService extends Service implements MediaPlayer.OnCompletionListener, MediaPlayer.OnErrorListener, MediaPlayer.OnPreparedListener {
    private MediaPlayer mPlayer;
    private State mCurrentState;
    private OnPlaybackListener mPlaybackListener;
    private Audio mCurrentAudio;

    private MediaPlayer.OnCompletionListener mCompletionListener;
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
        return new ServiceBinder(this);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return Service.START_STICKY;
    }

    private void init() {
        if (mPlayer == null) {
            mPlayer = new MediaPlayer();
            //PlayMusicService 要实现这三个接口
            mPlayer.setOnErrorListener(this);
            mPlayer.setOnPreparedListener(this);
            mPlayer.setOnCompletionListener(this);
            changeState(State.IDLE);
        }else{
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
                mPlayer.setDataSource(audio.getPath());    //Valid Sates IDLE
                mCurrentAudio = audio;
            }
            changeState(State.INITIALIZED);
            if (mCurrentState != State.ERROR) {
                mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                //保持屏幕常亮
                mPlayer.setScreenOnWhilePlaying(true);
            }
            if (mCurrentState == State.INITIALIZED || mCurrentState == State.STOPPED) {
                mPlayer.prepareAsync();
                changeState(State.PREPARING);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void reStart(){
        if (mPlayer!=null) {
            mPlayer.start();
            changeState(State.STARTED);
        }
    }

    public void paused(){
        if (mPlayer!=null){
            mPlayer.pause();
            changeState(State.PAUSED);
        }
    }

    public void stop(){
        if (mPlayer!=null){
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
        return false;
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        doStart();
    }

    private void changeState(State state) {
        mCurrentState = state;
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

    public interface OnPlaybackListener {
        void onStateChanged(Audio source, State state);
    }

    public void registerServiceCallback() {

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

}
