package me.kimxu.delegate;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import kimxu.mvp.view.AppAtyDelegate;
import me.kimxu.R;

/**
 * 视图View
 * Created by xuzhiguo on 15/11/23.
 */
public class SplashDelegate extends AppAtyDelegate {
    public ImageView ivSplash;
    @Override
    public void create(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.create(inflater, container, savedInstanceState);
    }

    @Override
    public int getRootLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    public void initWidget() {
        ivSplash=get(R.id.iv_activitySplash);
    }



    @Override
    public void setSupportActionBar(ActionBar actionBar) {
        //TODO 这里是去除toolbar返回键
    }

    @Override
    public Toolbar getToolbar() {
        return null;
    }

}
