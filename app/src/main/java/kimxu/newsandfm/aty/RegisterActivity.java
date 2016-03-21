package kimxu.newsandfm.aty;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;

import com.google.gson.Gson;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import cn.pedant.SweetAlert.SweetAlertDialog;
import kimxu.mvp.databind.DataBinder;
import kimxu.newsandfm.KBaseActivity;
import kimxu.newsandfm.NfContant;
import kimxu.newsandfm.utils.GlobalUtils;
import kimxu.utils.L;
import kimxu.utils.SPUtils;
import kimxu.utils.Ts;

public class RegisterActivity extends KBaseActivity<RegisterDelegate> {

    @Override
    protected void bindEvenListener() {
        viewDelegate.btnLogin.setOnClickListener(v->{
            validation();
        });
    }

    /**
     * 验证登录信息
     */
    private void validation() {
       String user = viewDelegate.tvRePwd.getText().toString();
       String pwd = viewDelegate.tvRePwd.getText().toString();
       String rePwd = viewDelegate.tvRePwd.getText().toString();
        if (TextUtils.isEmpty(user)
                ||TextUtils.isEmpty(pwd)
                ||TextUtils.isEmpty(rePwd)
                ||!pwd.equals(rePwd)){
            Ts.showToast(mActivity,"账号信息不能为空");
            return;
        }

        //这里使用Bmob
        SweetAlertDialog dialog= GlobalUtils.showProgressDialog(mActivity);
        BmobUser userInfo = new BmobUser();
        userInfo.setUsername(user);
        userInfo.setPassword(pwd);
        userInfo.signUp(this, new SaveListener() {
            @Override
            public void onSuccess() {
                Ts.showToast(mActivity, "注册成功:");
                String userStr = new Gson().toJson(userInfo, BmobUser.class);
                SPUtils.put(mActivity, NfContant.NF_USERINFO, userStr);
                dialog.dismissWithAnimation();
                setResult(1);
                finish();
                 }

            @Override
            public void onFailure(int code, String msg) {
                dialog.dismissWithAnimation();
                Ts.showToast(mActivity, "注册失败:" + msg);
            }
        });
    }
    @Override
    protected Class<RegisterDelegate> getDelegateClass() {
        return RegisterDelegate.class;
    }


    @Override
    public DataBinder getDataBinder() {
        return null;
    }

    public static void launch(Activity activity){
        activity.startActivity(new Intent(activity,RegisterActivity.class));
    }
    public static void launchForResult(Activity activity){
        activity.startActivityForResult(new Intent(activity, RegisterActivity.class),0);
    }
}
