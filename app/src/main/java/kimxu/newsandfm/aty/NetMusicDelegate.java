package kimxu.newsandfm.aty;

import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import kimxu.mvp.view.AppAtyDelegate;
import kimxu.newsandfm.R;

/**
 *
 * Created by kimxu on 16/1/8.
 */

public class NetMusicDelegate extends AppAtyDelegate {
    public TextView[] mNavs;
    public ViewPager viewPager;
    public ImageView mCursor;
    public FrameLayout mToolbarLayout;
    public final int NAVS_LENGTH=3;
    @Override
    public int getRootLayoutId() {
        return R.layout.activity_net_music;
    }

    @Override
    public void initWidget() {
        mToolbarLayout=get(R.id.fLyout_appMoreTitleToolBar);
        mNavs =new TextView[3];
        mNavs[0]=get(R.id.tView_appMoreTitleToolBar_1);
        mNavs[1]=get(R.id.tView_appMoreTitleToolBar_2);
        mNavs[2]=get(R.id.tView_appMoreTitleToolBar_3);
        mCursor=get(R.id.iVew_appMoreTitleToolBar_cursor);
        viewPager=get(R.id.vP_atyNetMusic);
    }

    @Override
    public Toolbar getToolbar() {
        return get(R.id.tBar_atyNetMusic);
    }
}
