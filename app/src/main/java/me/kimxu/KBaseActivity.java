package me.kimxu;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.readystatesoftware.systembartint.SystemBarTintManager;

import kimxu.core.net.ApiService;
import kimxu.mvp.databind.DataBindActivity;
import kimxu.mvp.view.IDelegate;

public abstract class KBaseActivity<T extends IDelegate> extends DataBindActivity<T>{
    protected KBaseActivity mActivity;
    protected KBaseApplication mApplication;
    protected ApiService mApiService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mActivity = this;
        mApiService=ApiService.getInstance();
        mApplication= (KBaseApplication) getApplication();
        setStatusBar();
        super.onCreate(savedInstanceState);

    }

    private void setStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
        }
        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setStatusBarTintResource(R.color.colorPrimaryDark);
    }
    @TargetApi(19)
    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }
}
