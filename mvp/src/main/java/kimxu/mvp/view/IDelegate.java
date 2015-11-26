package kimxu.mvp.view;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 视图层代理接口协议
 * Created by xuzhiguo on 15/11/23.
 */
public interface IDelegate {
    /** 创建视图*/
    void create(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);
    /** 获得菜单资源Id*/
    int getOptionsMenuId();
    /** 获得ToolBar*/
    Toolbar getToolbar();
    /** 获得根目录视图*/
    View getRootView();
    /** 初始化视图*/
    void initWidget();
    /** 设置toolbar*/
    void setSupportActionBar(ActionBar actionBar);
}
