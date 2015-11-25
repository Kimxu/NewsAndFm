package kimxu.newsandfm.frag;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import kimxu.mvp.view.AppDelegate;
import kimxu.newsandfm.R;
import kimxu.newsandfm.frag.news.NewsChildFragment;

/**
 * 新闻Fragment视图类
 * Created by xuzhiguo on 15/11/24.
 */
public class NewsFDelegate extends AppDelegate {
    private TextView tvKj;
    private TextView tvSh;
    private TextView tvDz;
    private ViewPager vpContent;
    private List<Fragment> mFrags;
    private TextView[] mNavs;
    private int NAVS_LENGTH = 3;
    public static final int TAB_ID_DZ = 2;
    public static final int TAB_ID_KL = 0;
    public static final int TAB_ID_SH = 1;

    @Override
    public int getRootLayoutId() {
        return R.layout.fragment_news;
    }

    @Override
    public void initWidget() {
        super.initWidget();
        tvKj = get(R.id.tView_fragNews_kj);
        tvSh = get(R.id.tView_fragNews_sh);
        tvDz = get(R.id.tView_fragNews_dz);
        vpContent = get(R.id.vPager_fragNews_content);
        initFrag();
    }

    private void initFrag() {
        mFrags = new ArrayList<>();
        mFrags.add(NewsChildFragment.newInstance(NewsChildFragment.PAGER_KJ));
        mFrags.add(NewsChildFragment.newInstance(NewsChildFragment.PAGER_SH));
        mFrags.add(NewsChildFragment.newInstance(NewsChildFragment.PAGER_DZ));
        initBar();
    }

    private void initBar() {
        mNavs = new TextView[NAVS_LENGTH];
        mNavs[TAB_ID_DZ] = tvDz;
        mNavs[TAB_ID_SH] = tvSh;
        mNavs[TAB_ID_KL] = tvKj;
    }


    public void setOnPageChangeListener(ViewPager.OnPageChangeListener listener) {
        vpContent.setOnPageChangeListener(listener);
    }

    public void setAdapter(FragmentStatePagerAdapter adapter) {
        vpContent.setAdapter(adapter);
    }

    public List<Fragment> getFrags() {
        return mFrags;
    }

    public void updateBarStatus(Activity activity, int index) {
        for (int i = 0; i < mNavs.length; i++) {
            if (i == index) {
                mNavs[i].setTextColor(activity.getResources().getColor(R.color.nf_toolbar_selected));
                mNavs[i].invalidate();
            } else {
                mNavs[i].setTextColor(activity.getResources().getColor(R.color.nf_toolbar_unselected));
                mNavs[i].invalidate();
            }
        }
    }


}
