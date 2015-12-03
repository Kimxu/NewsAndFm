package kimxu.mvp.databind;

import android.os.Bundle;

import kimxu.mvp.model.IModel;
import kimxu.mvp.presenter.ActivityPresenter;
import kimxu.mvp.view.IDelegate;

/**
 * 集成数据-视图绑定的Activity基类(Presenter层)
 *
 * Created by xuzhiguo on 15/11/23.
 */
public abstract class DataBindActivity<T extends IDelegate> extends
        ActivityPresenter<T> {
    protected DataBinder binder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binder = getDataBinder();
    }

    public abstract DataBinder getDataBinder();

    public <D extends IModel> void notifyModelChanged(D data) {
        if (binder != null)
            binder.viewBindModel(viewDelegate, data);
    }

}
