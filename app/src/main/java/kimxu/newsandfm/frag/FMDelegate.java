package kimxu.newsandfm.frag;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import kimxu.mvp.view.AppFragDelegate;
import kimxu.newsandfm.R;

/**
 *
 * Created by kimxu on 15/11/24.
 */
public class FMDelegate extends AppFragDelegate {
    private TextView tvIndex;
    private TextView tvLoad;
    private ViewPager vpContent;
    private List<Fragment> mFrags;
    private TextView[] mNavs;
    private final int NAVS_LENGTH = 2;
    public static final int TAB_ID_INDEX = 0;
    public static final int TAB_ID_LOAD = 1;
    @Override
    public int getRootLayoutId() {
        return R.layout.fragment_fm;
    }

    @Override
    public void initWidget() {
        tvIndex = get(R.id.tView_fragFM_index);
        tvLoad = get(R.id.tView_fragFM_load);
        vpContent = get(R.id.vPager_fragFM_content);
        initFrag();
    }
    private void initFrag() {
        mFrags = new ArrayList<>();
//        mFrags.add(FMIndexFragment.newInstance("",""));
//        mFrags.add(FMIndexFragment.newInstance("",""));
        initBar();
    }

    private void initBar() {
        mNavs = new TextView[NAVS_LENGTH];
        mNavs[TAB_ID_INDEX] = tvIndex;
        mNavs[TAB_ID_LOAD] = tvLoad;
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

}
