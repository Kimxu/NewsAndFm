package kimxu.newsandfm.frag.news;


import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.Fragment;

import com.google.gson.Gson;

import kimxu.adapter.AssemblyAdapter;
import kimxu.core.net.model.CQueuedRequest;
import kimxu.mvp.databind.DataBinder;
import kimxu.newsandfm.KBaseFragment;
import kimxu.newsandfm.adapter.factory.NewsListItemFactory;
import kimxu.newsandfm.aty.WebActivity;
import kimxu.newsandfm.model.News;
import kimxu.utils.L;
import kimxu.utils.Ts;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsChildFragment extends KBaseFragment<NewsChildDelegate> implements  NewsListItemFactory.EventListener{

    public static final int PAGER_KJ =0;
    public static final int PAGER_SH =1;
    public static final int PAGER_DZ =3;
    public static final int RESONPOSE_SUCCESS =1;
    private static String FROM_PAGER="FromPager";
    private  int mPager;

    AssemblyAdapter mAdapter;

    public static NewsChildFragment newInstance(int fromPager) {
        NewsChildFragment fragment = new NewsChildFragment();
        Bundle args = new Bundle();
        args.putInt(FROM_PAGER, fromPager);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public DataBinder getDataBinder() {
        return null;
    }

    @Override
    protected void bindEvenListener() {
        if (getArguments() != null) {
            mPager = getArguments().getInt(FROM_PAGER);
        }

        switch (mPager){
            case PAGER_KJ:
                mHttpService.sendYiDianKeji(mHttpHandler,RESONPOSE_SUCCESS);
                break;
            case PAGER_SH:
                mHttpService.sendYiDianSheHui(mHttpHandler,RESONPOSE_SUCCESS);
                break;
            case PAGER_DZ:
                mHttpService.sendYiDianDuanZi(mHttpHandler,RESONPOSE_SUCCESS);
                break;

        }
    }

    @Override
    protected Class<NewsChildDelegate> getDelegateClass() {
        return NewsChildDelegate.class;
    }

    @Override
    protected void handleErrorMessage(Message msg) {
        Ts.showToast(mActivity, "接收失败");
    }

    @Override
    protected void handleSuccessMessage(Message msg) {
        Ts.showToast(mActivity, "接收成功");
        CQueuedRequest qr = new CQueuedRequest(msg.obj);
        switch (qr.requestId) {
            case RESONPOSE_SUCCESS:
                L.i((String) qr.result);
                Gson gson =new Gson();
                News news = gson.fromJson((String) qr.result, News.class);
                mAdapter =new AssemblyAdapter(news.getResult());
                mAdapter.addItemFactory(new NewsListItemFactory(mActivity,this));
                viewDelegate.setAdapter(mAdapter);
                break;
        }
    }
    @Override
    public void onClick(int position, News.ResultEntity news) {
        WebActivity.launch(mActivity,news.getUrl());
    }
}
