package kimxu.newsandfm.aty;

import android.support.v7.widget.Toolbar;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import kimxu.mvp.view.AppAtyDelegate;
import kimxu.newsandfm.R;

/**
 *
 * Created by xuzhiguo on 15/11/26.
 */
public class WebDelegate extends AppAtyDelegate {
    private Toolbar toolbar;
    private WebView webView;
    @Override
    public int getRootLayoutId() {
        return R.layout.activity_web;
    }

    @Override
    public void initWidget() {
        super.initWidget();
        toolbar=get(R.id.tBar_atyWeb);
        webView=get(R.id.wView_atyWeb);
    }

    public void loadURL(String url){
        //打开网页时不调用系统浏览器， 而是在本WebView中显示

        webView.loadUrl(url);
    }

    private void setWebViewAllConfig(){
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        //设置 缓存模式
        webView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
        // 开启 DOM storage API 功能
        webView.getSettings().setDomStorageEnabled(true);


    }
    public boolean canGoBack(){
        return webView.canGoBack();
    }

    public void goBack(){
        webView.goBack();
    }

    @Override
    public Toolbar getToolbar() {
        return toolbar;
    }
}
