package me.kimxu;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.os.Looper;
import android.support.v4.util.LongSparseArray;
import android.widget.Toast;

import org.litepal.LitePalApplication;

import cn.bmob.v3.Bmob;
import kimxu.utils.SPUtils;
import kimxu.utils.Ts;
import me.kimxu.utils.DbUtils;
import kimxu.utils.L;

/**
 * Application基类
 * Created by kimxu on 15/11/19.
 */
public class KBaseApplication extends LitePalApplication {
    private static KBaseApplication mApplication;

    private LongSparseArray<String> mDownloadList = new LongSparseArray<>();

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
        DbUtils.initDateBase();
        SPUtils.init(mApplication);
        Ts.init(mApplication);
        L.isDebug = BuildConfig.LOG_DEBUG;
        //CrashHandler.getInstance().init();
        Bmob.initialize(mApplication, "5d5332afc7a109703f3fbf77ae0c729b");


    }

    public static KBaseApplication getInstance() {
        return mApplication;
    }

    public static boolean isDebugMode() {
        ApplicationInfo info = mApplication.getApplicationInfo();
        boolean isDebugMode = (info.flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
        L.e(isDebugMode + "");
        return isDebugMode;
    }

    public LongSparseArray<String> getDownloadList() {
        return mDownloadList;
    }
}
