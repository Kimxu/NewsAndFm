package kimxu.newsandfm.aty;

import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import kimxu.mvp.view.AppAtyDelegate;
import kimxu.newsandfm.R;

/**
 *
 * Created by kimxu on 15/12/30.
 */

public class MusicPlayerDelegate extends AppAtyDelegate{
    public ImageView ivPhotoAlbum;

    @Override
    public int getRootLayoutId() {
        return R.layout.activity_music_player;
    }

    @Override
    public void initWidget() {
        ivPhotoAlbum=get(R.id.iv_atyMusicPlayer_photoAlbum);
    }

    @Override
    public Toolbar getToolbar() {
        return get(R.id.tBar_atyXXX);
    }
}
