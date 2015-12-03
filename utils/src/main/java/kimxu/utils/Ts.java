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
    private Ts() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }
    private static void showToast(final Context context, final String message, final int duration) {
        if (Looper.myLooper() != null && Looper.myLooper() == Looper.getMainLooper()) {
            Toast.makeText(context,message,duration).show();
        } else {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    showToast(context, message, duration);
                }
            });
        }
    }

    private static void showToast(Context context, int resId, int duration) {
        showToast(context, context.getString(resId), duration);
    }

    public static void showToast(Context context, String msg) {
        showToast(context, msg, Toast.LENGTH_LONG);
    }

    public static void showToast(Context context, int resId) {
        showToast(context, resId, Toast.LENGTH_LONG);
    }
}
