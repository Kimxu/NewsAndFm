package kimxu.newsandfm.aty;

import android.app.Activity;
import android.content.Intent;
import android.os.Message;
import android.view.KeyEvent;

import kimxu.mvp.databind.DataBinder;
import kimxu.newsandfm.KBaseSwipeBackActivity;

public class WebActivity extends KBaseSwipeBackActivity<WebDelegate> {
    public static final String REQUEST_URL="request_url";
    private String url;
    @Override
    protected void bindEvenListener() {
        url = getIntent().getStringExtra(REQUEST_URL);
        viewDelegate.loadURL(url);
    }

    @Override
    protected Class<WebDelegate> getDelegateClass() {
        return WebDelegate.class;
    }

    @Override
    public DataBinder getDataBinder() {
        return null;
    }

    @Override
    protected void handleErrorMessage(Message msg) {

    }

    @Override
    protected void handleSuccessMessage(Message msg) {

    }

    public static void launch(Activity activity,String url){
        activity.startActivity(new Intent(activity,WebActivity.class).putExtra(REQUEST_URL,url));
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) &&   viewDelegate.canGoBack()) {
            viewDelegate.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
