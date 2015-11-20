package kimxu.newsandfm;

import org.litepal.LitePalApplication;
import org.litepal.tablemanager.Connector;

/**
 * Application基类
 * Created by xuzhiguo on 15/11/19.
 */
public class KBaseApplication extends LitePalApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        //数据库
        Connector.getDatabase();
        //LitePalApplication.initialize(this);
    }
}
