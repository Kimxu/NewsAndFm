package kimxu.core.net.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

/**
 * 工具类
 * Created by xuzhiguo on 15/11/18.
 */
public class GUtils {
    private final static String ENCODE_TYPE = "UTF-8";

    // 拼接参数列表
    public static String joinParams(Map<String, String> paramsMap) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String key : paramsMap.keySet()) {
            stringBuilder.append(key);
            stringBuilder.append("=");
            try {
                stringBuilder.append(URLEncoder.encode(paramsMap.get(key), ENCODE_TYPE));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            stringBuilder.append("&");
        }
        return stringBuilder.substring(0, stringBuilder.length() - 1);
    }
}
