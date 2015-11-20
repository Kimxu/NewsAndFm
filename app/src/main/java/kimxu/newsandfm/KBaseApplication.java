package kimxu.newsandfm;

import org.litepal.LitePalApplication;

import kimxu.newsandfm.utils.DbUtils;

/**
 * Application基类
 * Created by xuzhiguo on 15/11/19.
 */
public class KBaseApplication extends LitePalApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        DbUtils.initDateBase();
    //LitePalApplication.initialize(this);
    }
}
