package me.kimxu.delegate;

import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.List;

import kimxu.core.bean.SongListInfo;
import kimxu.mvp.view.AppFragDelegate;
import me.kimxu.R;

/**
 *
 * Created by kimxu on 15/11/24.
 */
public class SongListDelegate extends AppFragDelegate {
    public ListView lvSongList;
    public LinearLayout llLoading;
    public LinearLayout llLoadFail;

    @Override
    public int getRootLayoutId() {
        return R.layout.fragment_song_list;
    }

    @Override
    public void initWidget() {
        lvSongList=get(R.id.lv_song_list);
        llLoading=get(R.id.ll_loading);
        llLoadFail=get(R.id.ll_load_fail);
    }
}
