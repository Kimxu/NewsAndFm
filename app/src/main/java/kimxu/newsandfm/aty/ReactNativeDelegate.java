package kimxu.newsandfm.aty;

import android.support.v7.widget.Toolbar;

import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactRootView;

import kimxu.mvp.view.AppAtyDelegate;
/**
 * Created by kimxu on 16/1/26.
 */

public class ReactNativeDelegate extends AppAtyDelegate {
    public ReactRootView mReactRootView;
    public ReactInstanceManager mReactInstanceManager;
    @Override
    public int getRootLayoutId() {
        return 0;
    }

    @Override
    public Toolbar getToolbar() {
        return null;
    }
}
