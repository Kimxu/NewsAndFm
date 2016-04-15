package me.kimxu.service;

import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import kimxu.utils.SPUtils;
import me.kimxu.KBaseActivity;
import me.kimxu.enums.PlayModeEnum;
import me.kimxu.model.Music;
import me.kimxu.receiver.NoisyAudioStreamReceiver;
import me.kimxu.utils.Actions;
import me.kimxu.utils.MusicUtils;
import me.kimxu.utils.SystemUtils;

/**
 * 音乐播放后台服务
 * Created by wcy on 2015/11/27.
 */
public class PlayService extends Service implements MediaPlayer.OnCompletionListener, AudioManager.OnAudioFocusChangeListener {
    private static final int NOTIFICATION_ID = 0x111;
    private static final long TIME_UPDATE = 100L;
    private static final long SECOND = 1000L;
    // 本地歌曲列表
    private static final List<Music> sMusicList = new ArrayList<>();
    private static final List<KBaseActivity> sActivityStack = new ArrayList<>();
    private MediaPlayer mPlayer = new MediaPlayer();
    private IntentFilter mNoisyFilter = new IntentFilter(AudioManager.ACTION_AUDIO_BECOMING_NOISY);
    private NoisyAudioStreamReceiver mNoisyReceiver = new NoisyAudioStreamReceiver();
    private Handler mHandler = new Handler();
    private AudioManager mAudioManager;
    private NotificationManager mNotificationManager;
    private OnPlayerEventListener mListener;
    // 正在播放的歌曲[本地|网络]
    private Music mPlayingMusic;
    // 正在播放的本地歌曲的序号
    private int mPlayingPosition;
    private boolean isPause = false;
    private long timerTimeRemain;

    @Override
    public void onCreate() {
        super.onCreate();
        mAudioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        mPlayer.setOnCompletionListener(this);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new PlayBinder();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent == null || intent.getAction() == null) {
            return START_STICKY_COMPATIBILITY;
        }
        switch (intent.getAction()) {
            case Actions.ACTION_MEDIA_PLAY_PAUSE:
                playPause();
                break;
            case Actions.ACTION_MEDIA_NEXT:
                next();
                break;
            case Actions.ACTION_MEDIA_PREVIOUS:
                prev();
                break;
        }
        return START_STICKY_COMPATIBILITY;
    }

    public static List<Music> getMusicList() {
        return sMusicList;
    }

    public static void addToStack(KBaseActivity activity) {
        sActivityStack.add(activity);
    }

    public static void removeFromStack(KBaseActivity activity) {
        sActivityStack.remove(activity);
    }

    /**
     * 每次启动时扫描音乐
     */
    public void updateMusicList() {
        MusicUtils.scanMusic(this, getMusicList());
        if (getMusicList().isEmpty()) {
            return;
        }
        updatePlayingPosition();
        mPlayingMusic = mPlayingMusic == null ? getMusicList().get(mPlayingPosition) : mPlayingMusic;
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        next();
    }

    public void setOnPlayEventListener(OnPlayerEventListener listener) {
        mListener = listener;
    }

    public int play(int position) {
        if (getMusicList().isEmpty()) {
            return -1;
        }

        if (position < 0) {
            position = getMusicList().size() - 1;
        } else if (position >= getMusicList().size()) {
            position = 0;
        }

        mPlayingPosition = position;
        mPlayingMusic = getMusicList().get(mPlayingPosition);

        try {
            mPlayer.reset();
            mPlayer.setDataSource(mPlayingMusic.getUri());
            mPlayer.prepare();
            start();
            if (mListener != null) {
                mListener.onChange(mPlayingMusic);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        SPUtils.saveCurrentSongId(mPlayingMusic.getId());
        return mPlayingPosition;
    }

    public void play(Music music) {
        mPlayingMusic = music;
        try {
            mPlayer.reset();
            mPlayer.setDataSource(mPlayingMusic.getUri());
            mPlayer.prepare();
            start();
            if (mListener != null) {
                mListener.onChange(mPlayingMusic);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void playPause() {
        if (isPlaying()) {
            pause();
        } else if (isPause()) {
            resume();
        } else {
            play(getPlayingPosition());
        }
    }

    private void start() {
        mPlayer.start();
        isPause = false;
        mHandler.post(mBackgroundRunnable);
        updateNotification(mPlayingMusic);
        mAudioManager.requestAudioFocus(this, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN);
        registerReceiver(mNoisyReceiver, mNoisyFilter);
    }

    public int pause() {
        if (!isPlaying()) {
            return -1;
        }
        mPlayer.pause();
        isPause = true;
        mHandler.removeCallbacks(mBackgroundRunnable);
        cancelNotification(mPlayingMusic);
        mAudioManager.abandonAudioFocus(this);
        unregisterReceiver(mNoisyReceiver);
        if (mListener != null) {
            mListener.onPlayerPause();
        }
        return mPlayingPosition;
    }

    public int resume() {
        if (isPlaying()) {
            return -1;
        }
        start();
        if (mListener != null) {
            mListener.onPlayerResume();
        }
        return mPlayingPosition;
    }

    public int next() {
        PlayModeEnum mode = PlayModeEnum.valueOf(SPUtils.getPlayMode(0));
        switch (mode) {
            case LOOP:
                return play(mPlayingPosition + 1);
            case SHUFFLE:
                mPlayingPosition = new Random().nextInt(getMusicList().size());
                return play(mPlayingPosition);
            case ONE:
                return play(mPlayingPosition);
            default:
                return play(mPlayingPosition + 1);
        }
    }

    public int prev() {
        PlayModeEnum mode = PlayModeEnum.valueOf(SPUtils.getPlayMode(0));
        switch (mode) {
            case LOOP:
                return play(mPlayingPosition - 1);
            case SHUFFLE:
                mPlayingPosition = new Random().nextInt(getMusicList().size());
                return play(mPlayingPosition);
            case ONE:
                return play(mPlayingPosition);
            default:
                return play(mPlayingPosition - 1);
        }
    }

    /**
     * 跳转到指定的时间位置
     *
     * @param msec 时间
     */
    public void seekTo(int msec) {
        if (isPlaying() || isPause()) {
            mPlayer.seekTo(msec);
            if (mListener != null) {
                mListener.onPublish(msec);
            }
        }
    }

    public boolean isPlaying() {
        return mPlayer != null && mPlayer.isPlaying();
    }

    public boolean isPause() {
        return mPlayer != null && isPause;
    }

    /**
     * 获取正在播放的本地歌曲的序号
     */
    public int getPlayingPosition() {
        return mPlayingPosition;
    }

    /**
     * 获取正在播放的歌曲[本地|网络]
     */
    public Music getPlayingMusic() {
        return mPlayingMusic;
    }

    /**
     * 删除或下载歌曲后刷新正在播放的本地歌曲的序号
     */
    public void updatePlayingPosition() {
        int position = 0;
        long id = SPUtils.getCurrentSongId(-1);
        for (int i = 0; i < getMusicList().size(); i++) {
            if (getMusicList().get(i).getId() == id) {
                position = i;
                break;
            }
        }
        mPlayingPosition = position;
        SPUtils.saveCurrentSongId(getMusicList().get(mPlayingPosition).getId());
    }

    /**
     * 更新通知栏
     */
    private void updateNotification(Music music) {
        mNotificationManager.cancel(NOTIFICATION_ID);
        startForeground(NOTIFICATION_ID, SystemUtils.createNotification(this, music));
    }

    private void cancelNotification(Music music) {
        stopForeground(true);
        mNotificationManager.notify(NOTIFICATION_ID, SystemUtils.createNotification(this, music));
    }

    private Runnable mBackgroundRunnable = new Runnable() {
        @Override
        public void run() {
            if (isPlaying() && mListener != null) {
                mListener.onPublish(mPlayer.getCurrentPosition());
            }
            mHandler.postDelayed(this, TIME_UPDATE);
        }
    };

    @Override
    public void onAudioFocusChange(int focusChange) {
        switch (focusChange) {
            case AudioManager.AUDIOFOCUS_LOSS:
            case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT:
            case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK:
                if (isPlaying()) {
                    pause();
                }
                break;
        }
    }

    public void startQuitTimer(long milli) {
        stopQuitTimer();
        if (milli > 0) {
            timerTimeRemain = milli + SECOND;
            mHandler.post(mQuitRunnable);
        } else {
            timerTimeRemain = 0;
            if (mListener != null) {
                mListener.onTimer(timerTimeRemain);
            }
        }
    }

    private void stopQuitTimer() {
        mHandler.removeCallbacks(mQuitRunnable);
    }

    private Runnable mQuitRunnable = new Runnable() {
        @Override
        public void run() {
            timerTimeRemain -= SECOND;
            if (timerTimeRemain > 0) {
                if (mListener != null) {
                    mListener.onTimer(timerTimeRemain);
                }
                mHandler.postDelayed(this, SECOND);
            } else {
                SystemUtils.clearStack(sActivityStack);
                stop();
            }
        }
    };

    @Override
    public boolean onUnbind(Intent intent) {
        mListener = null;
        return true;
    }

    public void stop() {
        pause();
        stopQuitTimer();
        mPlayer.reset();
        mPlayer.release();
        mPlayer = null;
        mNotificationManager.cancel(NOTIFICATION_ID);
        stopSelf();
    }

    public class PlayBinder extends Binder {
        public PlayService getService() {
            return PlayService.this;
        }
    }
}
