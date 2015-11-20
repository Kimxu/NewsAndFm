package kimxu.core.net;

import android.os.Message;

/**
 * 网络请求返回接口
 * Created by xuzhiguo on 15/11/18.
 */
public interface IHandleMessage {
    /**
     * 得到网络请求返回结果
     * @param msg 返回信息
     */
    void handleMessage(Message msg);
}
