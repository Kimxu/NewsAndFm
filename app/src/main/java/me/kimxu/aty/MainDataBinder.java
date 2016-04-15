package me.kimxu.aty;

import kimxu.mvp.databind.DataBinder;

/**
 *数据绑定
 * Created by kimxu on 16/1/8.
 */

public class MainDataBinder implements DataBinder<MainDelegate, MainData> {

    MainActivity mActivity;
    public MainDataBinder(MainActivity mActivity) {
        this.mActivity = mActivity;
    }

    @Override
    public void viewBindModel(MainDelegate viewDelegate, MainData data) {

    }
}
