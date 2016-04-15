package me.kimxu.aty;

import kimxu.mvp.databind.DataBinder;
import me.kimxu.KBaseActivity;
import me.kimxu.databinder.MainDataBinder;
import me.kimxu.delegate.MainDelegate;

public class MainActivity extends KBaseActivity<MainDelegate> {


    @Override
    protected void bindEvenListener() {

    }

    protected Class<MainDelegate> getDelegateClass() {
        return MainDelegate.class;
    }



    @Override
    public DataBinder getDataBinder() {
        return new MainDataBinder((MainActivity) mActivity);
    }
}
