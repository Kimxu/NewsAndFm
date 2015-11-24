package kimxu.newsandfm.aty;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import kimxu.core.net.model.CQueuedRequest;
import kimxu.mvp.databind.DataBinder;
import kimxu.newsandfm.R;
import kimxu.newsandfm.frag.FMFragment;
import kimxu.newsandfm.frag.NewsFragment;
import kimxu.utils.L;
import kimxu.utils.ScreenUtils;
import kimxu.utils.Ts;

public class MainActivity extends KBaseActivity<MainDelegate> {
    private int startPage = 0;
    private float mFrameWidth;
    private float mOffset;
    private final static int NAVS_LENGTH = 2;
    final static int TAB_ID_FM = 0;
    final static int TAB_ID_NEWS = 1;
    private float mWindowWidth;
    private int mCursorWidth;
    private ArrayList<Fragment> mFrags;
    private TextView[] mNavs;
    private NfFragAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        handleIntent();

    }

    @Override
    public DataBinder getDataBinder() {
        return null;
    }


    @Override
    protected void bindEvenListener() {
        mFrags = new ArrayList<>();
        FMFragment fmFragment = FMFragment.newInstance("", "");
        NewsFragment newsFragment = NewsFragment.newInstance("", "");
        mFrags.add(fmFragment);
        mFrags.add(newsFragment);
        mNavs = new TextView[NAVS_LENGTH];
        mNavs[TAB_ID_FM] = viewDelegate.getmToolFm();
        mNavs[TAB_ID_NEWS] = viewDelegate.getmToolNews();
        mCursorWidth = BitmapFactory.decodeResource(getResources(),
                R.drawable.nf_cursor).getWidth();
        mWindowWidth = ScreenUtils.getScreenWidth(mActivity);
        mAdapter = new NfFragAdapter(getSupportFragmentManager());
        viewDelegate.getmPager().setAdapter(mAdapter);
        viewDelegate.getmPager().setCurrentItem(TAB_ID_FM);
        viewDelegate.getmPager().setOnPageChangeListener(new NfOnPageChangeListener());
    }

    @Override
    protected Class<MainDelegate> getDelegateClass() {
        return MainDelegate.class;
    }

    @Override
    protected void handleErrorMessage(Message msg) {

    }

    @Override
    protected void handleSuccessMessage(Message msg) {
        CQueuedRequest qr = new CQueuedRequest(msg.obj);
        switch (qr.requestId) {
            case 1:
                Ts.showToast(getApplicationContext(), "接收成功");
                L.i((String) qr.result);
                break;
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            mFrameWidth = viewDelegate.getmFrameLayout().getWidth();
            mOffset = (mFrameWidth / NAVS_LENGTH - mCursorWidth) / 2;
            updateCurrentTab(startPage, true);
        }
    }

    private void handleIntent() {
        Intent intent = getIntent();
        if (intent != null) {
            startPage = intent.getIntExtra("startPage", 0);
        }
    }

    private void updateCursorMatrix(int position, int offset) {
        Matrix matrix = new Matrix();
        //这里滑动的是根据屏幕的宽度。需要进行缩放
        if (mFrameWidth != 0)
            offset = (int) ((mFrameWidth / mWindowWidth) * offset);
        L.i((mOffset + mFrameWidth / NAVS_LENGTH * position
                + offset / NAVS_LENGTH) + "");
        matrix.postTranslate(mOffset + mFrameWidth / NAVS_LENGTH * position
                + offset / NAVS_LENGTH, 0);
        viewDelegate.getmCursor().setImageMatrix(matrix);
    }

    protected void updateCurrentTab(int index, boolean isSetmatrix) {
        for (int i = 0; i < mNavs.length; i++) {
            if (i == index) {
                mNavs[i].setTextColor(getResources().getColor(R.color.nf_toolbar_selected));
                mNavs[i].invalidate();
            } else {
                mNavs[i].setTextColor(getResources().getColor(R.color.nf_toolbar_unselected));
                mNavs[i].invalidate();
            }
        }
        if (isSetmatrix) {
            Matrix matrix = new Matrix();
            matrix.postTranslate(mOffset + (mFrameWidth / NAVS_LENGTH)
                    * index, 0);
            viewDelegate.getmCursor().setImageMatrix(matrix);
        }
    }

    class NfOnPageChangeListener implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageScrollStateChanged(int arg0) {

        }

        @Override
        public void onPageScrolled(int position, float arg1, int offset) {
            updateCursorMatrix(position, offset);
        }

        @Override
        public void onPageSelected(int currentTab) {
            updateCurrentTab(currentTab, false);

        }
    }


    class NfFragAdapter extends FragmentStatePagerAdapter {

        public NfFragAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int arg0) {
            return mFrags.get(arg0);
        }

        @Override
        public int getCount() {
            return mFrags.size();
        }

        @Override
        public void destroyItem(View container, int position, Object object) {

        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {

        }
    }
}
