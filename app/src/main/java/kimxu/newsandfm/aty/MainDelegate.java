package kimxu.newsandfm.aty;

import android.app.Activity;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import kimxu.mvp.view.AppAtyDelegate;
import kimxu.newsandfm.R;
import kimxu.newsandfm.frag.FMFragment;
import kimxu.newsandfm.frag.MusicFragment;

/**
 * 视图View
 * Created by xuzhiguo on 15/11/23.
 */
public class MainDelegate extends AppAtyDelegate {
    private ViewPager mPager;
    private FrameLayout mFrameLayout;
    private ImageView mCursor;
    private TextView mToolFm;
    private TextView mToolMusic;
    private ImageView mCenter;


    private ArrayList<Fragment> mFrags;
    private TextView[] mNavs;
    private final static int NAVS_LENGTH = 2;
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
        super.initWidget();
        mPager = get(R.id.vPager_atyMain_content);
        mCursor = get(R.id.iVew_appMainToolBar_cursor);
        mFrameLayout = get(R.id.fLyout_appMainToolBar);
        mToolFm = get(R.id.tView_appMainToolBar_fm);
        mToolMusic = get(R.id.tView_appMainToolBar_Music);
        mCenter =get(R.id.iView_appMainToolBar_center);
        initFrags();
    }

    private void initFrags() {
        FMFragment fmFragment = FMFragment.newInstance("", "");
        MusicFragment musicFragment = MusicFragment.newInstance("", "");
        mFrags = new ArrayList<>();
        mFrags.add(musicFragment);
        mFrags.add(fmFragment);
        initBar();
    }

    private void initBar() {
        mNavs = new TextView[NAVS_LENGTH];
        mNavs[TAB_ID_MUSIC] = mToolMusic;
        mNavs[TAB_ID_FM] = mToolFm;
    }

    public void setCenterLisenter(View.OnClickListener lisenter){
        mCenter.setOnClickListener(lisenter);
    }

    public FrameLayout getmFrameLayout() {
        return mFrameLayout;
    }

    public void setCurrentItem(int index) {
        mPager.setCurrentItem(index);
    }

    @Override
    public void setSupportActionBar(ActionBar actionBar) {

    }

    @Override
    public Toolbar getToolbar() {
        return get(R.id.tBar_atyMain);
    }

    public void setAdapter(MainActivity.NfFragAdapter adapter) {
        mPager.setAdapter(adapter);
    }

    public void setOnPageChangeListener(MainActivity.NfOnPageChangeListener onPageChangeListener) {
        mPager.setOnPageChangeListener(onPageChangeListener);
    }

    public TextView[] getNavs() {
        return mNavs;
    }

    protected void updateCursorMatrix(int position, int offset, float mFrameWidth, float mWindowWidth, float mOffset) {
        Matrix matrix = new Matrix();
        //这里滑动的是根据屏幕的宽度。需要进行缩放
        if (mFrameWidth != 0)
            offset = (int) ((mFrameWidth / mWindowWidth) * offset);
        matrix.postTranslate(mOffset + mFrameWidth / NAVS_LENGTH * position
                + offset / NAVS_LENGTH, 0);
        mCursor.setImageMatrix(matrix);
    }

    protected void updateCurrentTab(Activity ac, int index, boolean isSetmatrix, float mOffset, float mFrameWidth) {
        for (int i = 0; i < mNavs.length; i++) {
            if (i == index) {
                mNavs[i].setTextColor(ac.getResources().getColor(R.color.nf_toolbar_selected));
                mNavs[i].invalidate();
            } else {
                mNavs[i].setTextColor(ac.getResources().getColor(R.color.nf_toolbar_unselected));
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

    public List<Fragment> getFrags() {
        return mFrags;
    }
}
