package kimxu.newsandfm.aty;

import android.app.Activity;
import android.content.Intent;

import kimxu.mvp.databind.DataBinder;
import kimxu.newsandfm.KBaseActivity;
import kimxu.newsandfm.NfContant;
import kimxu.utils.SPUtils;
import kimxu.utils.Ts;

public class UserManagerActivity extends KBaseActivity<UserManagerDelegate> {

    @Override
    protected void bindEvenListener() {
        viewDelegate.btnLogout.setOnClickListener(v -> logout());
    }

    private void logout() {
        Ts.showToast(mActivity, "退出成功");
        SPUtils.remove(mActivity, NfContant.NF_USERINFO);
        finish();
    }

    @Override
    protected Class<UserManagerDelegate> getDelegateClass() {
        return UserManagerDelegate.class;
    }


    @Override
    public DataBinder getDataBinder() {
        return null;
    }

    public static void launch(Activity activity){
        activity.startActivity(new Intent(activity,UserManagerActivity.class));
    }
}
