package kimxu.newsandfm;

import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.FragmentActivity;

import kimxu.core.net.HttpConfig;
import kimxu.core.net.HttpHandler;
import kimxu.core.net.HttpService;
import kimxu.core.net.IHandleMessage;
import kimxu.mvp.databind.DataBindFragment;
import kimxu.mvp.view.IDelegate;
import kimxu.utils.Ts;

/**
 * Fragment基类
 * Created by xuzhiguo on 15/11/18.
 */
public abstract class KBaseFragment<T extends IDelegate> extends DataBindFragment<T> implements IHandleMessage{

    protected FragmentActivity mActivity;
    protected KBaseFragment mFragment;

    protected HttpService mHttpService;
    // 处理网络请求
    protected HttpHandler mHttpHandler;


    @Override
    public void handleMessage(Message msg) {
        switch (msg.what) {
            case HttpConfig.MSGCODE_HTTP_ERROR:
                handleErrorMessage(msg);
                break;
            case HttpConfig.MSGCODE_HTTP_RESPONSE:
                handleSuccessMessage(msg);
                break;
            default:
                Ts.showToast(getActivity(), "网络错误");
                break;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        mActivity = getActivity();
        mFragment = this;
        mHttpService = HttpService.getInstance(mActivity);
        mHttpHandler = new HttpHandler(this);
        super.onCreate(savedInstanceState);
    }

    protected abstract void handleErrorMessage(Message msg);
    protected abstract void handleSuccessMessage(Message msg);
}
