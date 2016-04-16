package me.kimxu.delegate;

import android.content.Context;
import android.widget.ListView;
import android.widget.TextView;

import kimxu.mvp.view.AppFragDelegate;
import me.kimxu.R;

/**
 *
 * Created by kimxu on 15/11/24.
 */
public class LocalMusicDelegate extends AppFragDelegate {

    public ListView lvLocalMusic;
    public TextView tvEmpty;

    @Override
    public int getRootLayoutId() {
        return R.layout.fragment_local_music;
    }

    @Override
    public void initWidget() {
        lvLocalMusic=get(R.id.lv_local_music);
        tvEmpty=get(R.id.tv_empty);
    }


}
