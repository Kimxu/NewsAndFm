package kimxu.newsandfm.aty;

import android.content.Intent;
import android.graphics.BitmapFactory;

import kimxu.mvp.databind.DataBinder;
import kimxu.newsandfm.KBaseActivity;
import kimxu.newsandfm.NfContant;
import kimxu.newsandfm.R;
import kimxu.utils.SPUtils;
import kimxu.utils.ScreenUtils;

public class MainActivity extends KBaseActivity<MainDelegate> {
    private int startPage = 0;
    private float mFrameWidth;
    private float mWindowWidth;
    private int mCursorWidth;

    @Override
    protected void bindEvenListener() {
        handleIntent();
        mCursorWidth = BitmapFactory.decodeResource(mActivity.getResources(),
                R.drawable.nf_cursor).getWidth();
        mWindowWidth = ScreenUtils.getScreenWidth(mActivity);
        viewDelegate.iVcenter.setOnClickListener(v->goToUserManager());
    }

    private void goToUserManager() {
       String userInfo = (String) SPUtils.get(mActivity, NfContant.NF_USERINFO, "null");
        if (userInfo.equals("null")){
            LoginActivity.launch(mActivity);
        }else {
            UserManagerActivity.launch(mActivity);
        }
    }

    @Override
    protected Class<MainDelegate> getDelegateClass() {
        return MainDelegate.class;
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus && mFrameWidth == 0) {
            mFrameWidth = viewDelegate.mFrameLayout.getWidth();
            NetMusicData data =new NetMusicData();
            data.mCursorWidth=mCursorWidth;
            data.startPage=startPage;
            data.mOffset= (mFrameWidth / MainDelegate.NAVS_LENGTH - mCursorWidth) / 2;
            data.mWindowWidth=mWindowWidth;
            data.mFrameWidth=mFrameWidth;
            notifyModelChanged(data);
        }
    }

    private void handleIntent() {
        Intent intent = getIntent();
        if (intent != null) {
            startPage = intent.getIntExtra("startPage", 0);
        }
    }

    @Override
    public DataBinder getDataBinder() {
        return new MainDataBinder(mActivity);
    }
}
