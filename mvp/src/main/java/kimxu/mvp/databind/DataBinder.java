package kimxu.mvp.databind;

import kimxu.mvp.model.IModel;
import kimxu.mvp.view.IDelegate;

/**
 * ViewModel实现
 *
 * Created by xuzhiguo on 15/11/23.
 */
public interface DataBinder <T extends IDelegate, D extends IModel>{
    /**
     * 将数据与View绑定，这样当数据改变的时候，
     * 框架就知道这个数据是和哪个View绑定在一起的，
     * 就可以自动改变ui当数据改变的时候，会回调本方法。
     *
     * @param viewDelegate 视图层代理
     * @param data         数据模型对象
     */
    void viewBindModel(T viewDelegate, D data);
}
