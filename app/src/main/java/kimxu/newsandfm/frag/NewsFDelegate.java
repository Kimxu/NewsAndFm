package kimxu.newsandfm.frag;

import android.widget.ListView;

import kimxu.mvp.view.AppDelegate;
import kimxu.newsandfm.R;
import kimxu.newsandfm.widget.HintView;

/**
 * Created by xuzhiguo on 15/11/24.
 */
public class NewsFDelegate extends AppDelegate {
    private ListView mListView;
    private HintView mHintView;

    @Override
    public int getRootLayoutId() {
        return R.layout.fragment_news;
    }

    @Override
    public void initWidget() {
        super.initWidget();
        mListView = get(R.id.nf_newsFrag_listView);
        mHintView= get(R.id.nf_newsFrag_hintView);
    }

    public ListView getmListView() {
        return mListView;
    }

    public void setmListView(ListView mListView) {
        this.mListView = mListView;
    }

    public HintView getmHintView() {
        return mHintView;
    }

    public void setmHintView(HintView mHintView) {
        this.mHintView = mHintView;
    }
}
