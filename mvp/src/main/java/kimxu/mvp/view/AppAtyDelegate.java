package kimxu.mvp.view;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 * 视图层代理的基类
 * 默认Toolbar带有返回键，重写setSupportActionBar就可以取消
 * Created by xuzhiguo on 15/11/23.
 */
public abstract class AppAtyDelegate implements IDelegate{
    //存视图的数组
    protected final SparseArray<View> mViews = new SparseArray<View>();
    /** 根视图*/
    protected View rootView;
    /** 获取根视图资源Id*/
    public abstract int getRootLayoutId();
    @Override
    public void create(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        int rootLayoutId = getRootLayoutId();
        rootView = inflater.inflate(rootLayoutId, container, false);

    }

    @Override
    public void setSupportActionBar(ActionBar actionBar) {
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public int getOptionsMenuId() {
        return 0;
    }

    @Override
    public abstract Toolbar getToolbar();

    @Override
    public View getRootView() {
        return rootView;
    }

    @Override
    public void initWidget() {

    }

    /** 绑定View上的view Id*/
    public <T extends View> T bindView(int id) {
        T view = (T) mViews.get(id);
        if (view == null) {
            view = (T) rootView.findViewById(id);
            mViews.put(id, view);
        }
        return view;
    }
    /** 根据Id获得View实体 Id*/
    public <T extends View> T get(int id) {
        return (T) bindView(id);
    }

    public void setOnClickListener(View.OnClickListener listener, int... ids) {
        if (ids == null) {
            return;
        }
        for (int id : ids) {
            get(id).setOnClickListener(listener);
        }
    }

    public void toast(CharSequence msg) {
        Toast.makeText(rootView.getContext(), msg, Toast.LENGTH_SHORT).show();
    }
}
