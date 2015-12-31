package kimxu.newsandfm;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Looper;
import android.widget.RemoteViews;
import android.widget.Toast;

import org.litepal.LitePalApplication;

import java.util.List;

import kimxu.newsandfm.model.Audio;
import kimxu.newsandfm.service.PlayMusicService;
import kimxu.newsandfm.utils.DbUtils;
import kimxu.newsandfm.utils.GlobalUtils;
import kimxu.utils.L;
import kimxu.utils.Ts;

/**
 * Application基类
 * Created by kimxu on 15/11/19.
 */
public class KBaseApplication extends LitePalApplication {
    private KBaseApplication mApplication;
    public PlayMusicService mPlayMusicService;
    public NotificationManager mNotificationManager;
    //歌曲列表
    public List<Audio> audios;
    //歌曲播放位置
    public int mPosition;

    public Notification mNotification;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
        DbUtils.initDateBase();
        L.isDebug = BuildConfig.LOG_DEBUG;
        /** 异常自己处理 */
        //Thread.setDefaultUncaughtExceptionHandler(mUncaughtExceptionHandler);
        //LitePalApplication.initialize(this);
        startPlayMusicService();
        bindPlayMusicService();
        mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        // 初始化通知栏播放控件
        initNotificationBar();
    }

    public Audio playPre() {
        if (audios != null) {
            mPosition--;
            if (mPosition >= 0) {
                return audios.get(mPosition);
            } else {
                return null;
            }
        } else return null;
    }

    public Audio playNext() {
        if (audios != null) {
            mPosition++;
            if (mPosition <= audios.size()) {
                return audios.get(mPosition);
            } else {
                return null;
            }
        } else return null;
    }

    public Audio play(){
        if (audios != null) {
            if (mPosition <= audios.size()&&mPosition>=0) {
                return audios.get(mPosition);
            } else {
                return null;
            }
        } else return null;
    }


    private void initNotificationBar() {
        mNotification = new Notification();
        //初始化通知
        mNotification.icon = R.drawable.nf_album_default;
        RemoteViews contentView = new RemoteViews(getPackageName(),
                R.layout.widget_notification_control);
        mNotification.contentView = contentView;

        Intent intentPlay = new Intent("play");//新建意图，并设置action标记为"play"，用于接收广播时过滤意图信息
        PendingIntent pIntentPlay = PendingIntent.getBroadcast(this, 0,
                intentPlay, 0);
        contentView.setOnClickPendingIntent(R.id.ib_notificationControl_playStart, pIntentPlay);//为play控件注册事件

        Intent intentNext = new Intent("next");
        PendingIntent pIntentNext = PendingIntent.getBroadcast(this, 0,
                intentNext, 0);
        contentView.setOnClickPendingIntent(R.id.ib_notificationControl_playNext, pIntentNext);
        //设置通知点击或滑动时不被清除
        mNotification.flags = Notification.FLAG_NO_CLEAR;
        mNotificationManager.notify(NfContant.MUSIC_NOTIFICATION, mNotification);//开启通知
    }


    private void startPlayMusicService() {
        Intent it = new Intent(mApplication, PlayMusicService.class);
        startService(it);
    }

    private void stopPlayMusicService() {
        Intent it = new Intent(mApplication, PlayMusicService.class);
        stopService(it);
    }
    private void bindPlayMusicService() {
        Intent it = new Intent(mApplication, PlayMusicService.class);
        this.bindService(it, new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                if (service instanceof PlayMusicService.ServiceBinder) {
                    PlayMusicService.ServiceBinder binder = (PlayMusicService.ServiceBinder) service;
                    mPlayMusicService = binder.getService();
                    //mPlayMusicService.registerServiceCallback(mPlayManager);
                    mPlayMusicService.setOnPlaybackListener(new PlayMusicService.OnPlaybackListener() {
                        @Override
                        public void onStateChanged(Audio source, PlayMusicService.State state) {

                        }

                        @Override
                        public void onStartProgressChanged(int progress) {

                        }

                        @Override
                        public void onStartProgressDuration(int duration) {

                        }
                    });
                    mPlayMusicService.registerServiceCallback();
                }
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                Ts.showToast(mApplication, "PlayMusicService is Disconnected" + name);
            }
        }, Service.BIND_AUTO_CREATE);
    }


    /**
     * 以下为uncaught exception的处理代码 防止程序崩溃
     */
    private Thread.UncaughtExceptionHandler mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
    private Thread.UncaughtExceptionHandler mUncaughtExceptionHandler = new Thread.UncaughtExceptionHandler() {
        @Override
        public void uncaughtException(Thread thread, Throwable ex) {
            if (!handleException(ex) && mDefaultHandler != null) {
                mDefaultHandler.uncaughtException(thread, ex);
            } else {
                restartApplication();
            }
            System.exit(0);
            android.os.Process.killProcess(android.os.Process.myPid());
        }
    };

    /**
     * 处理异常
     *
     * @param ex
     * @return boolean
     */
    private boolean handleException(Throwable ex) {
        if (ex == null) {
            return false;
        }
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        Looper.prepare();
                        ////传入参数必须为Activity，否则Toast不显示
                        Toast.makeText(KBaseApplication.this, "哎呀出现点小问题", Toast.LENGTH_LONG).show();
                        Looper.loop();
                    }
                }
        ).start();
        return true;
    }

    /**
     * 重启程序
     */
    private void restartApplication() {
        Intent intent = getPackageManager().getLaunchIntentForPackage(getPackageName());
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }


    public void setNotificationStatus(PlayMusicService.State state){
        if (state== PlayMusicService.State.STARTED){
            mNotification.contentView.setBitmap(R.id.ib_notificationControl_playStart,"setImageBitmap", GlobalUtils.drawable2Bitmap(mApplication, R.drawable.nf_player_btn_pause_normal));
            mNotificationManager.notify(NfContant.MUSIC_NOTIFICATION, mNotification);
        }else{
            mNotification.contentView.setBitmap(R.id.ib_notificationControl_playStart,"setImageBitmap", GlobalUtils.drawable2Bitmap(mApplication, R.drawable.nf_player_btn_play_normal));
            mNotificationManager.notify(NfContant.MUSIC_NOTIFICATION, mNotification);
        }
    }
}
