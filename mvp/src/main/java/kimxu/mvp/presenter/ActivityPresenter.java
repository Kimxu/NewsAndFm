package kimxu.mvp.presenter;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;

import kimxu.mvp.view.IDelegate;

/**
 * Presenter层的实现基类(Activity)
 *
 * Created by xuzhiguo on 15/11/23.
 */
public abstract class ActivityPresenter<T extends IDelegate> extends AppCompatActivity {
    /** 视图代理*/
    protected T viewDelegate;

    public ActivityPresenter() {
        try {
            viewDelegate = getDelegateClass().newInstance();
        } catch (InstantiationException e) {
            throw new RuntimeException("create IDelegate error");
        } catch (IllegalAccessException e) {
            throw new RuntimeException("create IDelegate error");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewDelegate.create(getLayoutInflater(), null, savedInstanceState);
        viewDelegate.setContext(ActivityPresenter.this);
        setContentView(viewDelegate.getRootView());
        viewDelegate.initWidget();
        initToolbar();
        bindEvenListener();
    }

    protected void initToolbar() {
        Toolbar toolbar = viewDelegate.getToolbar();
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            viewDelegate.setSupportActionBar(getSupportActionBar());
            if (!TextUtils.isEmpty(viewDelegate.getToolbar().getTitle())) {
                viewDelegate.getToolbar().setTitleTextColor(Color.parseColor("#ffffff"));
            }
        }
    }


    protected abstract void bindEvenListener();


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (viewDelegate.getOptionsMenuId() != 0) {
            getMenuInflater().inflate(viewDelegate.getOptionsMenuId(), menu);
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        viewDelegate = null;
    }


    protected abstract Class<T> getDelegateClass();

    /**
     * 为fragment设置functions，具体实现子类来做
     * @param fragmentId
     * */
    public void setFunctionsForFragment(int fragmentId){

    }
}
