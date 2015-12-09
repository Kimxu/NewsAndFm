package kimxu.newsandfm;

import android.content.Intent;
import android.os.Looper;
import android.widget.Toast;

import org.litepal.LitePalApplication;

import kimxu.newsandfm.utils.DbUtils;
import kimxu.utils.L;

/**
 * Application基类
 * Created by xuzhiguo on 15/11/19.
 */
public class KBaseApplication extends LitePalApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        DbUtils.initDateBase();
        L.isDebug =BuildConfig.LOG_DEBUG;
        /** 异常自己处理 */
        //Thread.setDefaultUncaughtExceptionHandler(mUncaughtExceptionHandler);
        //LitePalApplication.initialize(this);
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
