package kimxu.newsandfm.frag.netmusic;

import android.widget.ListView;

import kimxu.mvp.view.AppFragDelegate;
import kimxu.newsandfm.R;

/**
 * Created by kimxu on 16/1/8.
 */

public class NewMusicDelegate extends AppFragDelegate {
    public ListView mListView;
    @Override
    public int getRootLayoutId() {
        return R.layout.fragment_fmindex;
    }

    @Override
    public void initWidget() {
        mListView=get(R.id.list_FMIndex);
    }
}
