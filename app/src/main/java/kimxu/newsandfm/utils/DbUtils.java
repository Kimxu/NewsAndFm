package kimxu.newsandfm.utils;

import android.database.sqlite.SQLiteDatabase;

import org.litepal.tablemanager.Connector;

/**
 * 数据库类
 * Created by xuzhiguo on 15/11/19.
 */
public class DbUtils {

    private DbUtils(){
        SQLiteDatabase db = Connector.getDatabase();
    }

}
