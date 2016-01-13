package kimxu.newsandfm.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;

import kimxu.newsandfm.R;

/**
 * 工具类
 * Created by kimxu on 15/12/30.
 */

public class GlobalUtils {

    public static Bitmap drawable2Bitmap(Context context, int drawable) {
        Resources res = context.getResources();
        return BitmapFactory.decodeResource(res, drawable);
    }


    public static byte[] getBytesFromStream(InputStream is) throws IOException {
        int len;
        int size = 1024;
        byte[] buf;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        buf = new byte[size];
        while ((len = is.read(buf, 0, size)) != -1) {
            bos.write(buf, 0, len);
        }
        buf = bos.toByteArray();
        return buf;
    }

    public static void saveBytes2File(byte[] bytes, String path) {
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(path);
            fileOutputStream.write(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                assert fileOutputStream != null;
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String getLrcPath(Context context,String name){
        File file =new File(getLrcPath(context)+name+".lrc");
        if (file.exists())
            return getLrcPath(context)+name+".lrc";
        else return "";
    }

    public static String getLrcPath(Context context){
        return context.getFilesDir().getAbsolutePath()+"/";
    }

    /**
     * 根据名称获取ID(反射)
     * @param container
     * @param fieldName
     * @return
     */
    public static View findView(View container, String fieldName){
        View  ret = null;
        if (container != null && fieldName != null) {
            Class<R.id> idClass = R.id.class;
            Field field = null;
            try {
                field = idClass.getDeclaredField(fieldName);
                int id = field.getInt(idClass);
                //通过静态常量，获取int 值，
                ret = container.findViewById(id);
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return ret;
    }
}
