package kimxu.newsandfm.aty;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import kimxu.mvp.view.AppAtyDelegate;
import kimxu.newsandfm.R;
import kimxu.newsandfm.widget.SettingLayout;

/**
 * 视图View
 * Created by xuzhiguo on 15/11/23.
 */
public class UserManagerDelegate extends AppAtyDelegate {

    SettingLayout slUserInfo;
    Button btnLogout;
    @Override
    public void create(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.create(inflater, container, savedInstanceState);
    }

    @Override
    public int getRootLayoutId() {
        return R.layout.activity_user_manager;
    }

    @Override
    public void initWidget() {
        getToolbar().setTitle("个人中心");
        slUserInfo=get(R.id.sLayout_atyUserManager_userInfo);
        btnLogout=get(R.id.btn_atyUserManager_logout);
    }

    @Override
    public Toolbar getToolbar() {
        return get(R.id.tBar_atyUserManager);
    }

}
