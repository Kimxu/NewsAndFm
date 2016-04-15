package kimxu.utils;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


/**
 * Toast工具类
 * Created by xuzhiguo on 15/11/18.
 */
public class Ts {
    private static Context mContext;

    private Ts() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    public static void init(Context context) {
        mContext = context.getApplicationContext();
    }

    private static void showToast(final String message, final int duration) {
        if (Looper.myLooper() != null && Looper.myLooper() == Looper.getMainLooper()) {
            Toast.makeText(mContext, message, duration).show();
        } else {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    showToast(message, duration);
                }
            });
        }
    }

    private static void showToast(int resId, int duration) {
        showToast(mContext.getString(resId), duration);
    }

    public static void showToast(String msg) {
        showToast(msg, Toast.LENGTH_LONG);
    }

    public static void showToast(int resId) {
        showToast(resId, Toast.LENGTH_LONG);
    }
}
