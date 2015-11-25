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
import kimxu.utils.L;

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
        /*NameValuePair nameValuePair = new BasicNameValuePair("apiVer",
                String.valueOf(API_VER));*/
        nameValuePairs.add(new BasicNameValuePair("platform", "1"));
        nameValuePairs.add(new BasicNameValuePair("infinite", "true"));
        nameValuePairs.add(new BasicNameValuePair("infinite", "true"));
        nameValuePairs.add(new BasicNameValuePair("cstart", "0"));
        nameValuePairs.add(new BasicNameValuePair("cend", "30"));
        nameValuePairs.add(new BasicNameValuePair("appid", "yidian"));
        nameValuePairs.add(new BasicNameValuePair("cv", "3.4.1"));
        nameValuePairs.add(new BasicNameValuePair("refresh", "1"));
        nameValuePairs.add(new BasicNameValuePair("fields", "url"));
        nameValuePairs.add(new BasicNameValuePair("net", "wifi"));
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


    /**
     * 获取热点新闻
     *
     * http://124.243.203.100/Website/channel/news-list-for-hot-channel
     * ?
     * platform=1&
     * infinite=true&
     * cstart=0&
     * cend=30&
     * appid=yidian&
     * cv=3.4.1&
     * refresh=1&
     * fields=docid&
     * fields=date&
     * fields=image&
     * fields=image_urls&
     * fields=like&
     * fields=source&
     * fields=title&
     * fields=url&
     * fields=comment_count&
     * fields=up&
     * fields=down&
     * version=010917&
     * net=wifi
     */
    public void sendYiDian(Handler handler,int requestId) {
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("platform", "1");
        paramMap.put("infinite", "true");
        paramMap.put("cstart", "0");
        paramMap.put("cend", "30");
        paramMap.put("appid", "yidian");
        paramMap.put("cv", "3.4.1");
        paramMap.put("refresh", "1");
        paramMap.put("fields", "url");
        paramMap.put("net", "wifi");
        String url = HttpConfig.URL_YIDIAN_HOT_URL + "?" + GUtils.joinParams(paramMap);
        L.i(url);
        HashMap<String, String> headers = basicYidianHeader();
        httpManager.addApiGetRequest(url,
                handler,headers, requestId);
    }
    /**获取科技新闻
     *
     * http://124.243.203.100/Website/channel/news-list-for-channel?channel_id=1655594078&fields=url
     *
     * */
    public void sendYiDianKeji(Handler handler, int requestId) {
        HashMap<String, String> headers = basicYidianHeader();
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("channel_id", HttpConfig.SITE_KEJI);
        paramMap.put("fields", "url");
        String url = HttpConfig.URL_YIDIAN_URL + "?" + GUtils.joinParams(paramMap)+"&fields=image";
        httpManager.addApiGetRequest(url,
                handler,headers, requestId);
    }

    /**获取社会新闻 */
    public void sendYiDianSheHui(Handler handler, int requestId) {
        HashMap<String, String> headers = basicYidianHeader();
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("channel_id", HttpConfig.SITE_SHEHUI);
        paramMap.put("fields", "url");
        String url = HttpConfig.URL_YIDIAN_URL + "?" + GUtils.joinParams(paramMap)+"&fields=image";
        httpManager.addApiGetRequest(url,
                handler,headers, requestId);
    }

    /**获取段子新闻 */
    public void sendYiDianDuanZi(Handler handler, int requestId) {
        HashMap<String, String> headers = basicYidianHeader();
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("channel_id", HttpConfig.SITE_DUANZI);
        paramMap.put("fields", "url");
        String url = HttpConfig.URL_YIDIAN_URL + "?" + GUtils.joinParams(paramMap)+"&fields=image";
        httpManager.addApiGetRequest(url,
                handler,headers, requestId);
    }


    /**
     * 一点资讯的头部请求
     * @return
     */
    private HashMap<String, String> basicYidianHeader() {
        HashMap<String,String> headers =new HashMap<>();
        headers.put("Accept-Encoding","gzip, deflate");
        headers.put("Cookie","JSESSIONID=4nIW3Q15S5X5QAzzd8MCBw");
        headers.put("User-Agent","Dalvik/1.6.0 (Linux; U; Android 4.4.4; m1 Build/KTU84P)");
        return headers;
    }


}
