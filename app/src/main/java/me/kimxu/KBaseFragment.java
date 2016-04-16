package me.kimxu;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import kimxu.core.net.ApiService;
import kimxu.mvp.databind.DataBindFragment;
import kimxu.mvp.databind.DataBinder;
import kimxu.mvp.view.IDelegate;
import me.kimxu.aty.MusicActivity;
import me.kimxu.service.PlayService;

/**
 * Fragment基类
 * Created by xuzhiguo on 15/11/18.
 */
public abstract class KBaseFragment<T extends IDelegate> extends DataBindFragment<T>{

    protected FragmentActivity mActivity;
    protected KBaseFragment mFragment;
    protected ApiService mApiService;
    private PlayService mPlayService;
    protected Handler mHandler;
    private boolean mResumed;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        mActivity = getActivity();
        mFragment = this;
        mApiService=ApiService.getInstance();
        mHandler = new Handler();
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        mResumed = true;
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof MusicActivity) {
            mPlayService = ((MusicActivity) activity).getPlayService();
        }
    }
    protected PlayService getPlayService() {
        return mPlayService;
    }
    @Override
    public DataBinder getDataBinder() {
        return null;
    }

    public boolean isResume() {
        return mResumed;
    }
}
