package kimxu.utils;

import com.orhanobut.logger.Logger;

/**
 * Log统一管理类
 * Created by xuzhiguo on 15/11/18.
 */
public class L {
    private L()
    {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }
    // 是否需要打印bug，可以在application的onCreate函数里面初始化
    public static boolean isDebug = true;
    private static final String TAG = "Nf";

    // 下面四个是默认tag的函数
    public static void i(String msg)
    {
        if (isDebug)
            Logger.i(msg);
    }

    public static void d(String msg)
    {
        if (isDebug)
            Logger.d(msg);
    }

    public static void e(String msg)
    {
        if (isDebug)
            Logger.e(msg);
    }

    public static void v(String msg)
    {
        if (isDebug)
            Logger.v(msg);
    }
    public static void json(String msg)
    {
        if (isDebug)
            Logger.json(msg);
    }
}
