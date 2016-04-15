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
public class MainDelegate extends AppAtyDelegate {
    public final static int NAVS_LENGTH = 2;
    public final static int TAB_ID_MUSIC = 0;
    public final static int TAB_ID_FM = 1;

    ImageView iVcenter;

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
        initBar();
    }

    private void initBar() {
    }


    @Override
    public void setSupportActionBar(ActionBar actionBar) {
        //TODO 这里是去除toolbar返回键
    }

    @Override
    public Toolbar getToolbar() {
        return get(R.id.tBar_atyMain);
    }

}
