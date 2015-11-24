package kimxu.newsandfm.aty;

import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import kimxu.mvp.view.AppDelegate;
import kimxu.newsandfm.R;

/**
 * 视图View
 * Created by xuzhiguo on 15/11/23.
 */
public class MainDelegate extends AppDelegate {
    private ViewPager mPager;
    private FrameLayout mFrameLayout;
    private ImageView mCursor;
    private TextView mToolFm;
    private TextView mToolNews;


    @Override
    public int getRootLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initWidget() {
        super.initWidget();
        mPager = get(R.id.nf_main_pager);
        mCursor = get(R.id.nf_toolbar_cursor);
        mFrameLayout = get(R.id.nf_toolbar_fl);
        mToolFm= get(R.id.nf_toolbar_fm);
        mToolNews =get(R.id.nf_toolbar_news);
    }


    public TextView getmToolFm() {
        return mToolFm;
    }



    public TextView getmToolNews() {
        return mToolNews;
    }

    public ViewPager getmPager() {
        return mPager;
    }


    public FrameLayout getmFrameLayout() {
        return mFrameLayout;
    }


    public ImageView getmCursor() {
        return mCursor;
    }




    @Override
    public Toolbar getToolbar() {
        return get(R.id.nf_toolbar);
    }
}
