package kimxu.newsandfm.aty;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;

import kimxu.mvp.databind.DataBinder;
import kimxu.newsandfm.KBaseSwipeBackActivity;
import kimxu.newsandfm.R;
import kimxu.utils.ScreenUtils;

public class NetMusicActivity extends KBaseSwipeBackActivity<NetMusicDelegate> {
    private float mFrameWidth;
    private float mWindowWidth;
    private int mCursorWidth;
    @Override
    public DataBinder getDataBinder() {
        return new NetMusicDataBinder(mActivity);
    }

    @Override
    protected void bindEvenListener() {
        mCursorWidth = BitmapFactory.decodeResource(mActivity.getResources(),
                R.drawable.nf_cursor).getWidth();
        mWindowWidth = ScreenUtils.getScreenWidth(mActivity);
    }
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus && mFrameWidth == 0) {
            mFrameWidth = viewDelegate.mToolbarLayout.getWidth();
            NetMusicData data =new NetMusicData();
            data.mCursorWidth=mCursorWidth;
            data.mOffset= (mFrameWidth / viewDelegate.NAVS_LENGTH - mCursorWidth) / 2;
            data.mWindowWidth=mWindowWidth;
            data.mFrameWidth=mFrameWidth;
            notifyModelChanged(data);
        }
    }

    @Override
    protected Class<NetMusicDelegate> getDelegateClass() {
        return NetMusicDelegate.class;
    }
    public static void launch(Activity ac){
        ac.startActivity(new Intent(ac,NetMusicActivity.class));
    }
}
