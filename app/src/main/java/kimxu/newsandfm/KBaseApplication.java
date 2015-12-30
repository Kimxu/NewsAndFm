package kimxu.newsandfm;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Looper;
import android.widget.Toast;

import org.litepal.LitePalApplication;

import kimxu.newsandfm.service.PlayMusicService;
import kimxu.newsandfm.utils.DbUtils;
import kimxu.utils.L;
import kimxu.utils.Ts;

/**
 * Application基类
 * Created by xuzhiguo on 15/11/19.
 */
public class KBaseApplication extends LitePalApplication {
    private KBaseApplication mApplication;
    public PlayMusicService mPlayMusicService;
    @Override
    public void onCreate() {
        super.onCreate();
        mApplication=this;
        DbUtils.initDateBase();
        L.isDebug =BuildConfig.LOG_DEBUG;
        /** 异常自己处理 */
        //Thread.setDefaultUncaughtExceptionHandler(mUncaughtExceptionHandler);
        //LitePalApplication.initialize(this);


        startPlayMusicService();
        bindPlayMusicService();
    }

    private void startPlayMusicService() {
        Intent it = new Intent (mApplication, PlayMusicService.class);
        startService(it);
    }

    private void stopPlayMusicService() {
        Intent it = new Intent(mApplication, PlayMusicService.class);
        stopService(it);
    }

    private void bindPlayMusicService() {
        Intent it = new Intent (mApplication, PlayMusicService.class);
        this.bindService(it, new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                if (service instanceof PlayMusicService.ServiceBinder) {
                    PlayMusicService.ServiceBinder binder = (PlayMusicService.ServiceBinder)service;
                    mPlayMusicService = binder.getService();
                    //mPlayMusicService.registerServiceCallback(mPlayManager);
                    mPlayMusicService.registerServiceCallback();
                }
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                Ts.showToast(mApplication,"PlayMusicService is Disconnected"+name);
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
                        Toast.makeText(KBaseApplication.this,"哎呀出现点小问题",Toast.LENGTH_LONG).show();
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
}
