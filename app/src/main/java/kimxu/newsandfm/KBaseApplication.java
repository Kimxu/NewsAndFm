package kimxu.newsandfm;

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
    //LitePalApplication.initialize(this);
    }
}
