package kimxu.newsandfm.aty;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import com.readystatesoftware.systembartint.SystemBarTintManager;

import kimxu.core.net.HttpConfig;
import kimxu.core.net.HttpHandler;
import kimxu.core.net.HttpService;
import kimxu.core.net.IHandleMessage;
import kimxu.newsandfm.R;
import kimxu.utils.T;

public abstract class KBaseActivity extends AppCompatActivity implements IHandleMessage {
    protected KBaseActivity mActivity;
    protected HttpService mHttpService;
    protected HttpHandler mHttpHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = this;
        mHttpService = HttpService.getInstance(this);
        mHttpHandler = new HttpHandler(this);
        setStatusBar();
    }

    private void setStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
        }
        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setStatusBarTintResource(R.color.colorPrimaryDark);
    }
    @TargetApi(19)
    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }



    @Override
    public void handleMessage(Message msg) {
        switch (msg.what) {
            case HttpConfig.MSGCODE_HTTP_ERROR:
                handleErrorMessage(msg);
                break;
            case HttpConfig.MSGCODE_HTTP_RESPONSE:
                handleSuccessMessage(msg);
                break;
            default:
                T.showToast(getApplicationContext(), "网络错误");
                break;
        }
    }


    protected abstract void handleErrorMessage(Message msg);
    protected abstract void handleSuccessMessage(Message msg);
}