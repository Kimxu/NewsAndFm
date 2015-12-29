package kimxu.newsandfm;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Window;

import kimxu.mvp.view.IDelegate;
import kimxu.newsandfm.widget.SwipeBackLayout;

/**
 * 带右滑销毁的activity
 * @param <T>
 */
public abstract class KBaseSwipeBackActivity<T extends IDelegate> extends KBaseActivity<T>{
    protected SwipeBackLayout layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        layout = (SwipeBackLayout) LayoutInflater.from(this).inflate(
                R.layout.widget_swipe_back, null);
        layout.attachToActivity(this);
        super.onCreate(savedInstanceState);
    }


    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        overridePendingTransition(R.anim.base_slide_right_in, R.anim.base_slide_remain);
    }


    // Press the back button in mobile phone
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(0, R.anim.base_slide_right_out);
    }

}
