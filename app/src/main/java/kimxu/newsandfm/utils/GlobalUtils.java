package kimxu.newsandfm.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * 工具类
 * Created by kimxu on 15/12/30.
 */

public class GlobalUtils {

    public static Bitmap drawable2Bitmap(Context context, int drawable){
        Resources res=context.getResources();
        return BitmapFactory.decodeResource(res, drawable);
    }
}
