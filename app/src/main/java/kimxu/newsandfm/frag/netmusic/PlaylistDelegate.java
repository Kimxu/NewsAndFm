package kimxu.newsandfm.frag.netmusic;

import android.widget.GridView;

import kimxu.mvp.view.AppFragDelegate;
import kimxu.newsandfm.R;

/**
 * Created by kimxu on 16/1/8.
 */

public class PlaylistDelegate extends AppFragDelegate {
    public GridView mGridView;
    @Override
    public int getRootLayoutId() {
        return R.layout.fragment_playlist;
    }

    @Override
    public void initWidget() {
        mGridView=get(R.id.glist_fragPlaylist);
    }
}
