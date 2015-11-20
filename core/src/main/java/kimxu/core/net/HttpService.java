package kimxu.core.net;

import android.content.Context;
import android.os.Handler;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kimxu.api.net.HttpManager;
import kimxu.api.net.INet;
import kimxu.core.net.utils.GUtils;

@SuppressWarnings("deprecation")
public class HttpService {
    private static HttpService httpService;
    private static INet httpManager;
    private Context mContext;
    // API版本号
    private final static int API_VER = 1;

    public static HttpService getInstance(Context context) {
        if (httpService == null) {
            httpService = new HttpService(context);
        }
        return httpService;
    }

    private HttpService(Context context) {
        mContext = context;
        httpManager = HttpManager.getInstance(context);
    }

    /**
     * 接口的基本参数
     */
    private void appendBaseParams(List<NameValuePair> nameValuePairs) {
        NameValuePair nameValuePair = new BasicNameValuePair("apiVer",
                String.valueOf(API_VER));
        nameValuePairs.add(nameValuePair);
    }

    /**
     * 获取新闻
     */
    public void sendWeiNews(String num,
                            Handler handler, int requestId) {
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("key", "948bfd98600d28e7ed3d83ad14161368");
        paramMap.put("num", num);
        String url = HttpConfig.URL_WEINEWS_URL + "?" + GUtils.joinParams(paramMap);
        httpManager.addApiGetRequest(url,
                handler, requestId);
    }



}
