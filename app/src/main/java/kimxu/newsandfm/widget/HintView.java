package kimxu.newsandfm.widget;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import kimxu.newsandfm.R;


/**
 * 页面空视图
 * Created by xuzhiguo on 15/11/20.
 */
public class HintView extends FrameLayout {

    // 网络错误相关
    private ViewStub netErrorViewStub;
    private ViewGroup netErrorRootView;
    private Button netErrorButton;
    private TextView netErrorTextView;

    // 页面空相关
    private ViewStub emptyViewStub;
    private ViewGroup emptyRootView;
    private TextView emptyTextView;

    // 加载中相关
    private ViewStub loadingViewStub;
    private ViewGroup loadingRootView;
    private TextView loadingTextView;


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
        emptyViewStub = new ViewStub(getContext(), R.layout.widget_hintview_empty);
        addView(emptyViewStub);
        netErrorViewStub = new ViewStub(getContext(), R.layout.widget_hintview_net_error);
        addView(netErrorViewStub);
        loadingViewStub = new ViewStub(getContext(), R.layout.widget_hintview_loading);
        addView(loadingViewStub);
        setVisibility(GONE);
    }

    /**
     * 显示加载中页面，默认提示信息为“加载中…”
     */
    public LoadingBuilder loading() {
        return new LoadingBuilder(this);
    }

    /**
     * 显示加载失败页面
     *
     * @param retryButtonClickListener 重试按钮点击监听器
     */
    public NetErrorBuilder netError(View.OnClickListener retryButtonClickListener) {
        return new NetErrorBuilder(this, retryButtonClickListener);
    }

    /**
     * 显示页面空页面
     *
     * @param message
     * @return
     */
    public EmptyBuilder empty(String message) {
        return new EmptyBuilder(this, message);
    }

    public static class LoadingBuilder {
        private HintView hintView;
        private String message;

        private LoadingBuilder(HintView hintView) {
            this.hintView = hintView;
            this.message = "加载中…";
        }

        /**
         * 设置提示消息
         */
        public LoadingBuilder message(String message) {
            this.message = message;
            return this;
        }

        /**
         * 显示
         */
        public void show() {
            //hintView.tryCancelDelayedHidden();
            // 如果是第一次执行此方法就始化加载中部分的View
            if (hintView.loadingViewStub != null) {
                View rootView = hintView.loadingViewStub.inflate();
                hintView.loadingViewStub = null;
                hintView.loadingRootView = (ViewGroup) rootView;
                hintView.loadingRootView.setClickable(true);
                hintView.loadingTextView = (TextView) rootView.findViewById(R.id.hintView_loading_text);
            }
            hintView.loadingTextView.setText(message);
            // 隐藏掉其它的
            if (hintView.netErrorRootView != null) {
                hintView.netErrorRootView.setVisibility(View.GONE);
            }
            if (hintView.emptyRootView != null) {
                hintView.emptyRootView.setVisibility(View.GONE);
            }
            // 显示出来
            hintView.loadingRootView.setVisibility(View.VISIBLE);
            hintView.setVisibility(VISIBLE);

            //hintView.loadingTime = System.currentTimeMillis();
        }
    }

    public static class NetErrorBuilder {
        private HintView hintView;
        private String message;
        private OnClickListener retryButtonClickListener;

        private NetErrorBuilder(final HintView hintView, OnClickListener retryButtonClickListener) {
            this.hintView = hintView;
            this.retryButtonClickListener = retryButtonClickListener;
        }


        /**
         * 设置消息
         */
        public NetErrorBuilder message(String message) {
            this.message = message;
            return this;
        }

        /**
         * 设置重试按钮点击监听器
         */
        public NetErrorBuilder retryButtonClick(OnClickListener retryButtonClickListener) {
            this.retryButtonClickListener = retryButtonClickListener;
            return this;
        }

        /**
         * 显示
         */
        public void show() {
            //hintView.tryCancelDelayedHidden();
            // 如果是第一次执行此方法就始化网络错误部分的View
            if (hintView.netErrorViewStub != null) {
                View rootView = hintView.netErrorViewStub.inflate();
                hintView.netErrorViewStub = null;

                hintView.netErrorRootView = (ViewGroup) rootView;
                hintView.netErrorRootView.setClickable(true);
                hintView.netErrorTextView = (TextView) rootView.findViewById(R.id.hintView_net_error_text);
                hintView.netErrorButton = (Button) rootView.findViewById(R.id.hintView_net_error_btn);
            }

            // 设置提示消息
            if (!TextUtils.isEmpty(message))
            hintView.netErrorTextView.setText(message);
            hintView.netErrorButton.setOnClickListener(retryButtonClickListener);
            if (retryButtonClickListener != null) {
                if (hintView.netErrorButton.getVisibility() != View.VISIBLE) {
                    hintView.netErrorButton.setVisibility(View.VISIBLE);
                }
            } else {
                if (hintView.netErrorButton.getVisibility() != View.GONE) {
                    hintView.netErrorButton.setVisibility(View.GONE);
                }
            }
            // 隐藏掉其它的
            if (hintView.loadingRootView != null) {
                hintView.loadingRootView.setVisibility(View.GONE);
            }
            if (hintView.emptyRootView != null) {
                hintView.emptyRootView.setVisibility(View.GONE);
            }

            // 显示出来
            hintView.netErrorRootView.setVisibility(View.VISIBLE);
            hintView.setVisibility(VISIBLE);
        }
    }


    public static class EmptyBuilder {
        private HintView hintView;
        private String message;

        private EmptyBuilder(HintView hintView, String message) {
            this.hintView = hintView;
            this.message = message;
        }

        /**
         * 设置提示消息
         */
        public EmptyBuilder message(String message) {
            this.message = message;
            return this;
        }

        /**
         * 显示
         */
        public void show() {
            //hintView.tryCancelDelayedHidden();
            // 如果是第一次执行此方法就始化网络错误部分的View
            if (hintView.emptyViewStub != null) {
                View rootView = hintView.emptyViewStub.inflate();
                hintView.emptyViewStub = null;
                hintView.emptyRootView = (ViewGroup) rootView;
                hintView.emptyRootView.setClickable(true);
                hintView.emptyTextView = (TextView) rootView.findViewById(R.id.hintView_empty_text);
            }
            // 设置提示文本以及按钮点击事件
            hintView.emptyTextView.setText(message);
            // 隐藏掉其它的
            if (hintView.loadingRootView != null) {
                hintView.loadingRootView.setVisibility(View.GONE);
            }
            if (hintView.netErrorRootView != null) {
                hintView.netErrorRootView.setVisibility(View.GONE);
            }
            // 显示出来
            hintView.emptyRootView.setVisibility(View.VISIBLE);
            hintView.setVisibility(VISIBLE);
        }
    }

    /**
     * 快速隐藏
     */
    public void hidden(){
        if(netErrorRootView != null && netErrorRootView.getVisibility() != View.GONE){
            netErrorRootView.setVisibility(View.GONE);
        }
        if(emptyRootView != null && emptyRootView.getVisibility() != View.GONE){
            emptyRootView.setVisibility(View.GONE);
        }
        if(loadingRootView != null && loadingRootView.getVisibility() != View.GONE){
            loadingRootView.setVisibility(View.GONE);
        }
        if(getVisibility() != View.GONE){
            setVisibility(View.GONE);
        }
    }
}
