package kimxu.newsandfm.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

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
        boolean isExist = false;
        String[] strings= context.getFilesDir().list();
        for (String string:strings){
           if(string.contains(name)){
               isExist=true;
           }
        }
        if (isExist)
            return context.getFilesDir().getAbsolutePath()+"/"+name+".lrc";
        else
            return "";
    }

    public static String getLrcPath(Context context){
        return context.getFilesDir().getAbsolutePath()+"/";
    }
}
