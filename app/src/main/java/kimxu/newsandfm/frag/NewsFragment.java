package kimxu.newsandfm.frag;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.ViewGroup;

import kimxu.mvp.databind.DataBinder;
import kimxu.newsandfm.KBaseFragment;

/**
 * 新闻
 */
public class NewsFragment extends KBaseFragment<NewsDelegate> {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    public static NewsFragment newInstance(String param1, String param2) {
        NewsFragment fragment = new NewsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void bindEvenListener() {
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        viewDelegate.setAdapter(new NfNewsFragAdapter(mActivity.getSupportFragmentManager()));
        viewDelegate.setOnPageChangeListener(new NfOnPageChangeListener());
    }

    @Override
    protected Class<NewsDelegate> getDelegateClass() {
        return NewsDelegate.class;
    }

    @Override
    public DataBinder getDataBinder() {
        return null;
    }



    class NfOnPageChangeListener implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageScrollStateChanged(int state) {

        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int offset) {

        }

        @Override
        public void onPageSelected(int currentTab) {
            viewDelegate.updateBarStatus(mActivity,currentTab);

        }
    }
    class NfNewsFragAdapter extends FragmentStatePagerAdapter {

        public NfNewsFragAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int arg0) {
            return viewDelegate.getFrags().get(arg0);
        }

        @Override
        public int getCount() {
            return viewDelegate.getFrags().size();
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {

        }
    }
}

