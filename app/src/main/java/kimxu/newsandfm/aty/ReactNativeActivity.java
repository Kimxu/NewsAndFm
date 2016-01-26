package kimxu.newsandfm.aty;

import android.view.KeyEvent;

import com.facebook.react.BuildConfig;
import com.facebook.react.LifecycleState;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactRootView;
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler;
import com.facebook.react.shell.MainReactPackage;

import kimxu.mvp.databind.DataBinder;
import kimxu.newsandfm.KBaseActivity;

public class ReactNativeActivity extends KBaseActivity<ReactNativeDelegate>  implements DefaultHardwareBackBtnHandler {

    @Override
    protected void bindEvenListener() {
        viewDelegate.mReactRootView = new ReactRootView(this);
        viewDelegate.mReactInstanceManager = ReactInstanceManager.builder()
                .setApplication(getApplication())
                .setBundleAssetName("index.android.bundle")
                .setJSMainModuleName("index.android")
                .addPackage(new MainReactPackage())
                .setUseDeveloperSupport(BuildConfig.DEBUG)
                .setInitialLifecycleState(LifecycleState.RESUMED)
                .build();
        viewDelegate.mReactRootView.startReactApplication(viewDelegate.mReactInstanceManager, "NewsAndFm", null);
        setContentView(viewDelegate.mReactRootView);
    }
    @Override
    public void invokeDefaultOnBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected Class<ReactNativeDelegate> getDelegateClass() {
        return ReactNativeDelegate.class;
    }

    @Override
    public DataBinder getDataBinder() {
        return null;
    }
    @Override
    protected void onPause() {
        super.onPause();

        if (viewDelegate.mReactInstanceManager != null) {
            viewDelegate.mReactInstanceManager.onPause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (viewDelegate.mReactInstanceManager != null) {
            viewDelegate.mReactInstanceManager.onResume(this, this);
        }
    }
    @Override
    public void onBackPressed() {
        if (viewDelegate.mReactInstanceManager != null) {
            viewDelegate.mReactInstanceManager.onBackPressed();
        } else {
            super.onBackPressed();
        }
    }
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_MENU && viewDelegate.mReactInstanceManager != null) {
            viewDelegate.mReactInstanceManager.showDevOptionsDialog();
            return true;
        }
        return super.onKeyUp(keyCode, event);
    }
}
