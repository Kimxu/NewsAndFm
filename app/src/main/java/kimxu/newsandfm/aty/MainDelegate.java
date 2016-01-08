package kimxu.newsandfm.aty;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import kimxu.mvp.view.AppAtyDelegate;
import kimxu.newsandfm.R;

/**
 * 视图View
 * Created by xuzhiguo on 15/11/23.
 */
public class MainDelegate extends AppAtyDelegate {
    public ViewPager viewPager;
    public FrameLayout mFrameLayout;
    public ImageView mCursor;
    public TextView[] mNavs;
    public final static int NAVS_LENGTH = 2;
    public final static int TAB_ID_MUSIC = 0;
    public final static int TAB_ID_FM = 1;

    @Override
    public void create(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.create(inflater, container, savedInstanceState);
    }

    @Override
    public int getRootLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initWidget() {
        initBar();
        viewPager = get(R.id.vPager_atyMain_content);
        mCursor = get(R.id.iVew_appMainToolBar_cursor);
        mFrameLayout = get(R.id.fLyout_appMainToolBar);
    }

    private void initBar() {
        mNavs = new TextView[NAVS_LENGTH];
        mNavs[TAB_ID_MUSIC] = get(R.id.tView_appMainToolBar_Music);
        mNavs[TAB_ID_FM] = get(R.id.tView_appMainToolBar_fm);
    }


    @Override
    public void setSupportActionBar(ActionBar actionBar) {
        //TODO 这里是去除toolbar返回键
    }

    @Override
    public Toolbar getToolbar() {
        return get(R.id.tBar_atyMain);
    }

}
