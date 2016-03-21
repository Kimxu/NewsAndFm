package kimxu.newsandfm.aty;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;

import com.google.gson.Gson;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.SaveListener;
import cn.pedant.SweetAlert.SweetAlertDialog;
import kimxu.mvp.databind.DataBinder;
import kimxu.newsandfm.KBaseActivity;
import kimxu.newsandfm.NfContant;
import kimxu.newsandfm.utils.GlobalUtils;
import kimxu.utils.L;
import kimxu.utils.SPUtils;
import kimxu.utils.Ts;

public class LoginActivity extends KBaseActivity<LoginDelegate> {

    @Override
    protected void bindEvenListener() {
        viewDelegate.btnLogin.setOnClickListener(v -> validation());
        viewDelegate.tvRegister.setOnClickListener(v->RegisterActivity.launchForResult(mActivity));
    }

    /**
     * 验证登录信息
     */
    private void validation() {
        if (TextUtils.isEmpty(viewDelegate.tvUser.getText())||TextUtils.isEmpty(viewDelegate.tvPwd.getText())){
            Ts.showToast(mActivity,"账号信息不能为空");
            return;
        }
        //这里使用Bmob
        SweetAlertDialog dialog= GlobalUtils.showProgressDialog(mActivity);
        BmobUser user = new BmobUser();
        user.setUsername(viewDelegate.tvUser.getText().toString());
        user.setPassword(viewDelegate.tvPwd.getText().toString());
        user.login(mActivity, new SaveListener() {
            @Override
            public void onSuccess() {
                //存储登录信息
                String userInfo = new Gson().toJson(user, BmobUser.class);
                L.e(userInfo);
                SPUtils.put(mActivity, NfContant.NF_USERINFO, userInfo);
                Ts.showToast(mActivity, "登录成功");
                dialog.dismissWithAnimation();
                finish();
            }

            @Override
            public void onFailure(int i, String s) {
                dialog.dismissWithAnimation();
                Ts.showToast(mActivity, s);
            }
        });
    }
    @Override
    protected Class<LoginDelegate> getDelegateClass() {
        return LoginDelegate.class;
    }


    @Override
    public DataBinder getDataBinder() {
        return null;
    }

    public static void launch(Activity activity){
        activity.startActivity(new Intent(activity, LoginActivity.class));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==0){
            finish();
        }
    }
}
