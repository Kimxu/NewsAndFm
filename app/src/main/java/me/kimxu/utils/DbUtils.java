package me.kimxu.utils;

import org.litepal.tablemanager.Connector;

/**
 * 数据库管理类
 * Created by xuzhiguo on 15/11/20.
 */
public class DbUtils {

    public static void initDateBase(){
        //数据库
        Connector.getDatabase();
    }
}
