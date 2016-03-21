package kimxu.newsandfm.aty;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import kimxu.mvp.view.AppAtyDelegate;
import kimxu.newsandfm.R;

/**
 * 视图View
 * Created by xuzhiguo on 15/11/23.
 */
public class RegisterDelegate extends AppAtyDelegate {

    EditText tvUser;
    EditText tvPwd;
    EditText tvRePwd;
    Button btnLogin;

    @Override
    public void create(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.create(inflater, container, savedInstanceState);
    }

    @Override
    public int getRootLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    public void initWidget() {
        getToolbar().setTitle("注册");
        tvUser = get(R.id.tv_atyRegister_user);
        tvPwd = get(R.id.tv_atyRegister_pwd);
        tvRePwd = get(R.id.tv_atyRegister_rePwd);
        btnLogin = get(R.id.btn_atyRegister);
    }

    @Override
    public Toolbar getToolbar() {
        return get(R.id.tBar_atyRegister);
    }

}
