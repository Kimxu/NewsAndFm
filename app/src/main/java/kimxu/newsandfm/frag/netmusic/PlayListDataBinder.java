package kimxu.newsandfm.frag.netmusic;

import android.support.v4.app.FragmentActivity;

import kimxu.adapter.AssemblyAdapter;
import kimxu.bdyy.playlist.Playlist;
import kimxu.mvp.databind.DataBinder;
import kimxu.newsandfm.adapter.factory.PlayListFactory;

/**
 *
 * Created by kimxu on 16/1/8.
 */

public class PlayListDataBinder implements DataBinder<PlaylistDelegate, Playlist> {
    private FragmentActivity mActivity;

    public PlayListDataBinder(FragmentActivity mActivity) {
        this.mActivity = mActivity;
    }

    @Override
    public void viewBindModel(PlaylistDelegate viewDelegate, Playlist data) {
        AssemblyAdapter adapter = new AssemblyAdapter(data.getContent());
        adapter.addItemFactory(new PlayListFactory(mActivity));
        viewDelegate.mGridView.setAdapter(adapter);
    }
}
