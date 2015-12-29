package kimxu.newsandfm.frag.music;

import android.support.v7.widget.Toolbar;

import kimxu.mvp.view.AppAtyDelegate;
import kimxu.newsandfm.R;
/**
 *
 * Created by kimxu on 15/12/29.
 */
public class LocalMusicFDelegate extends AppAtyDelegate {
    @Override
    public int getRootLayoutId() {
        return R.layout.activity_local_music;
    }
    @Override
    public Toolbar getToolbar() {
        return get(R.id.tBar_atyMain);
    }
    @Override
    public void initWidget() {

    }
}

