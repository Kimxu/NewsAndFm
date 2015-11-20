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
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import kimxu.core.net.model.CQueuedRequest;
import kimxu.newsandfm.R;
import kimxu.newsandfm.frag.FMFragment;
import kimxu.newsandfm.frag.NewsFragment;
import kimxu.utils.ScreenUtils;
import kimxu.utils.T;

public class MainActivity extends KBaseActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private ViewPager mPager;
    private FrameLayout mFrameLayout;
    private float mFrameWidth;
    private ArrayList<Fragment> mFrags;
    private NfFragAdapter mAdapter;
    private TextView[] mNavs;
    private final static int NAVS_LENGTH = 2;
    private ImageView mCursor;
    private int mCursorWidth;
    private float mOffset;

    private float mWindowWidth;

    private int startPage = 0;
    final static int TAB_ID_FM = 0;
    final static int TAB_ID_NEWS = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        handleIntent();
        initView();
        initNav();
        initFrag();
        mHttpService.sendWeiNews("10",mHttpHandler,1);
    }

    @Override
    protected void handleErrorMessage(Message msg) {

    }

    @Override
    protected void handleSuccessMessage(Message msg) {
        CQueuedRequest qr = new CQueuedRequest(msg.obj);

        switch (qr.requestId){
            case 1:
                T.showToast(getApplicationContext(), "接收成功");
               Log.i("tag",(String)qr.result);
                break;
        }
    }

    private void initFrag() {
        mWindowWidth= ScreenUtils.getScreenWidth(mActivity);
        mFrags = new ArrayList<>();
        FMFragment fmFragment = FMFragment.newInstance("", "");
        NewsFragment newsFragment = NewsFragment.newInstance("", "");
        mFrags.add(fmFragment);
        mFrags.add(newsFragment);
        mAdapter = new NfFragAdapter(getSupportFragmentManager());
        mPager.setAdapter(mAdapter);
        mPager.setCurrentItem(TAB_ID_FM);
        mPager.setOnPageChangeListener(new NfOnPageChangeListener());
    }

    private void initNav() {
        mNavs = new TextView[NAVS_LENGTH];
        mNavs[TAB_ID_FM] = (TextView) findViewById(R.id.nf_toolbar_fm);
        mNavs[TAB_ID_NEWS] = (TextView) findViewById(R.id.nf_toolbar_news);
        mCursorWidth = BitmapFactory.decodeResource(getResources(),
                R.drawable.nf_cursor).getWidth();

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus&&mFrameWidth==0){
            mFrameWidth = mFrameLayout.getWidth();
            mOffset = (mFrameWidth / NAVS_LENGTH - mCursorWidth) / 2;
            updateCurrentTab(startPage, true);
            Log.e(TAG, mFrameWidth + "");
        }
    }

    private void initView() {
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.nf_toolbar);
        setSupportActionBar(toolbar);
        mPager = (ViewPager) findViewById(R.id.nf_main_pager);
        mCursor = (ImageView) findViewById(R.id.nf_toolbar_cursor);
        mFrameLayout = (FrameLayout) findViewById(R.id.nf_toolbar_fl);
    }

    private boolean handleIntent() {
        Intent intent = getIntent();
        if (intent != null) {
            startPage = intent.getIntExtra("startPage", 0);
            return true;
        } else {
            return false;
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

    class NfOnPageChangeListener implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageScrollStateChanged(int arg0) {

        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
            updateCursorMatrix(arg0, arg2);
        }

        @Override
        public void onPageSelected(int currentTab) {
            updateCurrentTab(currentTab, false);
            Log.i("yyh", currentTab + "");
        }
    }

    private void updateCursorMatrix(int position, int offset) {
        Log.e(TAG,offset+"offset");
        Log.e(TAG,(mWindowWidth-mFrameWidth)/2+"ooo");
        Matrix matrix = new Matrix();
        //这里滑动的是根据屏幕的宽度。需要进行缩放
        if (mFrameWidth!=0)
        offset =(int)((mFrameWidth/mWindowWidth)*offset);
        matrix.postTranslate(mOffset + mFrameWidth / NAVS_LENGTH * position
                + offset/ NAVS_LENGTH , 0);
        mCursor.setImageMatrix(matrix);
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
            mCursor.setImageMatrix(matrix);
        }
    }
}
