package kimxu.api.net;

import android.os.Handler;

import org.apache.http.NameValuePair;

import java.util.List;

/**
 * 网络请求接口
 * Created by xuzhiguo on 15/11/18.
 */
public interface INet {

    /**
     * post请求
     * @param nameValuePairs 请求参数
     * @param apiHost url链接
     * @param handler handler
     * @param requestId 识别码
     */
    void addApiReqest(List<NameValuePair> nameValuePairs,
                             String apiHost, Handler handler, int requestId);

    /**
     * get请求
     * @param apiHost url链接
     * @param handler handler
     * @param requestId 识别码
     */
    void addApiGetRequest(String apiHost, Handler handler, int requestId);
}