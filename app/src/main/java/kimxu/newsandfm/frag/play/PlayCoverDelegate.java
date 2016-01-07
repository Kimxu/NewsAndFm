package kimxu.newsandfm.frag.play;

import kimxu.mvp.view.AppFragDelegate;
import kimxu.newsandfm.R;
import kimxu.newsandfm.widget.MusicPlayerView;

/**
 *
 * Created by kimxu on 16/1/7.
 */

public class PlayCoverDelegate extends AppFragDelegate {
    MusicPlayerView musicPlayerView;
    @Override
    public int getRootLayoutId() {
        return R.layout.fragment_play_cover;
    }

    @Override
    public void initWidget() {
        musicPlayerView=get(R.id.mpv_fragPlayCover);
        musicPlayerView.setProgressVisibility(false);
    }

    public void setCoverPath(String path){
        musicPlayerView.setCoverURL(path);


    }
}
