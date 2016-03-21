package kimxu.newsandfm.aty;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import kimxu.mvp.view.AppAtyDelegate;
import kimxu.newsandfm.R;
import kimxu.newsandfm.utils.GlobalUtils;
import kimxu.utils.Ts;

/**
 * 视图View
 * Created by xuzhiguo on 15/11/23.
 */
public class LoginDelegate extends AppAtyDelegate {

    EditText tvUser;
    EditText tvPwd;
    Button btnLogin;
    TextView tvRegister;
    @Override
    public void create(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.create(inflater, container, savedInstanceState);
    }

    @Override
    public int getRootLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initWidget() {
        getToolbar().setTitle("登录");
        tvUser=get(R.id.tv_atyLogin_user);
        tvPwd=get(R.id.tv_atyLogin_pwd);
        tvRegister=get(R.id.tv_atyLogin_register);
        btnLogin=get(R.id.btn_atyLogin);
    }

    @Override
    public Toolbar getToolbar() {
        return get(R.id.tBar_atyLogin);
    }

}
