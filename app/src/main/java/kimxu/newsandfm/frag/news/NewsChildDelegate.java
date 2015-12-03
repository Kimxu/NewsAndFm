package kimxu.newsandfm.frag.news;

import android.widget.BaseAdapter;
import android.widget.ListView;

import kimxu.mvp.view.AppDelegate;
import kimxu.newsandfm.R;

/**
 * Created by xuzhiguo on 15/11/25.
 */
public class NewsChildDelegate extends AppDelegate {
    private ListView mListView;
    @Override
    public int getRootLayoutId() {
        return R.layout.fragment_news_child;
    }

    @Override
    public void initWidget() {
        super.initWidget();
        mListView=get(R.id.lView_fragNewsChild_content);
    }
    public void setAdapter(BaseAdapter adapter){
        mListView.setAdapter(adapter);
    }
}
