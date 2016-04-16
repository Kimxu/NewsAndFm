package me.kimxu.delegate;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.LinearLayout;

import java.util.ArrayList;

import kimxu.mvp.view.AppAtyDelegate;
import me.kimxu.R;
import me.kimxu.enums.LoadStateEnum;
import me.kimxu.utils.ScreenUtils;
import me.kimxu.utils.ViewUtils;
import me.kimxu.widget.AutoLoadListView;

/**
 * 视图View
 * Created by xuzhiguo on 15/11/23.
 */
public class OnlineMusicDelegate extends AppAtyDelegate {
    public AutoLoadListView lvOnlineMusic;
    public LinearLayout llLoading;
    public LinearLayout llLoadFail;
    public View vHeader;
    private Context mContext;
    @Override
    public int getRootLayoutId() {
        return R.layout.activity_online_music;
    }

    @Override
    public void setContext(Context context) {
        this.mContext=context;
    }

    @Override
    public void initWidget() {
        lvOnlineMusic=get(R.id.lv_online_music_list);
        llLoading=get(R.id.ll_loading);
        llLoadFail=get(R.id.ll_load_fail);
        vHeader = LayoutInflater.from(mContext).inflate(R.layout.activity_online_music_list_header, null);
        AbsListView.LayoutParams params = new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ScreenUtils.dp2px(150));
        vHeader.setLayoutParams(params);
        lvOnlineMusic.addHeaderView(vHeader, null, false);



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
