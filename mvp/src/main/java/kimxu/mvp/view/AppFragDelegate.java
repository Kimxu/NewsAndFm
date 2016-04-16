package kimxu.mvp.view;

import android.content.Context;
import android.support.v7.widget.Toolbar;

/**
 * 视图层代理的基类 Fragment
 * Created by kimxu on 15/11/23.
 */
public abstract class AppFragDelegate extends AppAtyDelegate {

    @Override
    public void setContext(Context context) {

    }
    @Override
    public Toolbar getToolbar() {
        return null;
    }
}
