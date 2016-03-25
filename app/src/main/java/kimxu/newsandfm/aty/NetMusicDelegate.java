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
    TextView[] mNavs;
    ViewPager mViewPager;
    ImageView mCursor;
    FrameLayout mToolbarLayout;
    final int NAVS_LENGTH=3;
    ImageView mIvSearch;

    @Override
    public int getRootLayoutId() {
        return R.layout.activity_net_music;
    }

    @Override
    public void initWidget() {
        mToolbarLayout=get(R.id.fLyout_AppMoreTitleToolBar);
        mNavs =new TextView[3];
        mNavs[0]=get(R.id.tView_appMoreTitleToolBar_1);
        mNavs[1]=get(R.id.tView_appMoreTitleToolBar_2);
        mNavs[2]=get(R.id.tView_appMoreTitleToolBar_3);
        mCursor=get(R.id.iVew_appMoreTitleToolBar_cursor);
        mIvSearch=get(R.id.iV_appMoreTitleToolBar_search);
        mViewPager =get(R.id.vPager_atyNetMusic);
    }

    @Override
    public Toolbar getToolbar() {
        return get(R.id.tBar_atyNetMusic);
    }
}
