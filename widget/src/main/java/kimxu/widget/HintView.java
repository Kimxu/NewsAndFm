package kimxu.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewStub;
import android.widget.FrameLayout;

/**
 * 页面空视图
 * Created by xuzhiguo on 15/11/20.
 */
public class HintView extends FrameLayout {

    // 网络错误相关
    private ViewStub failureViewStub;

    // 页面空相关
    private ViewStub emptyViewStub;

    // 加载中相关
    private ViewStub loadingViewStub;


    public HintView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public HintView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        //进行初始化

    }


}
