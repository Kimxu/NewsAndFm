package kimxu.newsandfm.aty;

import android.app.Activity;
import android.graphics.Matrix;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import kimxu.mvp.databind.DataBinder;
import kimxu.newsandfm.R;
import kimxu.newsandfm.frag.FMFragment;
import kimxu.newsandfm.frag.MusicFragment;

/**
 *数据绑定
 * Created by kimxu on 16/1/8.
 */

public class MainDataBinder implements DataBinder<MainDelegate, NetMusicData> {
    private FragmentActivity mActivity;
    private NetMusicData mData;
    private MainDelegate mViewDelegate;
    private List<Fragment> mFragments;

    public MainDataBinder(FragmentActivity mActivity) {
        this.mActivity = mActivity;
    }

    @Override
    public void viewBindModel(MainDelegate viewDelegate, NetMusicData data) {
        mData = data;
        mViewDelegate = viewDelegate;
        mFragments = new ArrayList<>();
        mFragments.add(MusicFragment.newInstance("", ""));
        mFragments.add(FMFragment.newInstance("", ""));
        viewDelegate.viewPager.setAdapter(new NfFragAdapter(mActivity.getSupportFragmentManager()));
        viewDelegate.viewPager.setOnPageChangeListener(new NfOnPageChangeListener());
        viewDelegate.viewPager.setCurrentItem(data.startPage);

    }


    class NfOnPageChangeListener implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageScrollStateChanged(int state) {
        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int offset) {
            updateCursorMatrix(position, offset, mData.mFrameWidth, mData.mWindowWidth, mData.mOffset);
        }

        @Override
        public void onPageSelected(int currentTab) {
            updateCurrentTab(mActivity, currentTab, false, mData.mOffset, mData.mFrameWidth);
        }
    }


    class NfFragAdapter extends FragmentStatePagerAdapter {
        public NfFragAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {

        }
    }


    protected void updateCursorMatrix(int position, int offset, float mFrameWidth, float mWindowWidth, float mOffset) {
        Matrix matrix = new Matrix();
        //这里滑动的是根据屏幕的宽度。需要进行缩放
        if (mFrameWidth != 0)
            offset = (int) ((mFrameWidth / mWindowWidth) * offset);
        matrix.postTranslate(mOffset + mFrameWidth / MainDelegate.NAVS_LENGTH * position
                + offset / MainDelegate.NAVS_LENGTH, 0);
        mViewDelegate.mCursor.setImageMatrix(matrix);
    }

    protected void updateCurrentTab(Activity ac, int index, boolean isSetmatrix, float mOffset, float mFrameWidth) {
        for (int i = 0; i < mViewDelegate.mNavs.length; i++) {
            if (i == index) {
                mViewDelegate.mNavs[i].setTextColor(ac.getResources().getColor(R.color.nf_toolbar_selected));
                mViewDelegate.mNavs[i].invalidate();
            } else {
                mViewDelegate.mNavs[i].setTextColor(ac.getResources().getColor(R.color.nf_toolbar_unselected));
                mViewDelegate.mNavs[i].invalidate();
            }
        }
        if (isSetmatrix) {
            Matrix matrix = new Matrix();
            matrix.postTranslate(mOffset + (mFrameWidth / MainDelegate.NAVS_LENGTH)
                    * index, 0);
            mViewDelegate.mCursor.setImageMatrix(matrix);
        }
    }
}
