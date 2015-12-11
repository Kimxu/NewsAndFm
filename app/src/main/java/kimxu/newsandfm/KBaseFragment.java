package kimxu.newsandfm;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import org.apache.http.protocol.HttpService;

import kimxu.core.net.ApiService;
import kimxu.mvp.databind.DataBindFragment;
import kimxu.mvp.view.IDelegate;

/**
 * Fragment基类
 * Created by xuzhiguo on 15/11/18.
 */
public abstract class KBaseFragment<T extends IDelegate> extends DataBindFragment<T>{

    protected FragmentActivity mActivity;
    protected KBaseFragment mFragment;
    protected ApiService mApiService;
    protected HttpService mHttpService;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        mActivity = getActivity();
        mFragment = this;
        mApiService=ApiService.getInstance();

        super.onCreate(savedInstanceState);
    }
}
