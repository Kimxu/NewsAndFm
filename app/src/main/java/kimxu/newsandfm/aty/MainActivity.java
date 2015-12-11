package kimxu.newsandfm.aty;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.ViewGroup;

import kimxu.mvp.databind.DataBinder;
import kimxu.newsandfm.KBaseActivity;
import kimxu.newsandfm.R;
import kimxu.utils.ScreenUtils;

public class MainActivity extends KBaseActivity<MainDelegate> {
    private static int NAVS_LENGTH;
    private int startPage = 0;
    private float mFrameWidth;
    private float mOffset;
    private float mWindowWidth;
    private int mCursorWidth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //WebActivity.launch(this);
        handleIntent();

    }

    @Override
    public DataBinder getDataBinder() {
        return null;
    }


    @Override
    protected void bindEvenListener() {
        mCursorWidth = BitmapFactory.decodeResource(getResources(),
                R.drawable.nf_cursor).getWidth();
        mWindowWidth = ScreenUtils.getScreenWidth(mActivity);
        NfFragAdapter mAdapter = new NfFragAdapter(getSupportFragmentManager());
        viewDelegate.setAdapter(mAdapter);
        viewDelegate.setCurrentItem(MainDelegate.TAB_ID_MUSIC);
        viewDelegate.setOnPageChangeListener(new NfOnPageChangeListener());
        NAVS_LENGTH = viewDelegate.getNavs().length;
    }

    @Override
    protected Class<MainDelegate> getDelegateClass() {
        return MainDelegate.class;
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus && mFrameWidth == 0) {
            mFrameWidth = viewDelegate.getmFrameLayout().getWidth();
            mOffset = (mFrameWidth / NAVS_LENGTH - mCursorWidth) / 2;
            viewDelegate.updateCurrentTab(mActivity, startPage, true, mOffset, mFrameWidth);
        }
    }

    private void handleIntent() {
        Intent intent = getIntent();
        if (intent != null) {
            startPage = intent.getIntExtra("startPage", 0);
        }
    }


    class NfOnPageChangeListener implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageScrollStateChanged(int state) {
        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int offset) {
            viewDelegate.updateCursorMatrix(position, offset, mFrameWidth, mWindowWidth, mOffset);
        }

        @Override
        public void onPageSelected(int currentTab) {
            viewDelegate.updateCurrentTab(mActivity, currentTab, false, mOffset, mFrameWidth);
        }
    }


    class NfFragAdapter extends FragmentStatePagerAdapter {

        public NfFragAdapter(FragmentManager fm) {
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
