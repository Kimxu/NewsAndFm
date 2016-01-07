package kimxu.newsandfm.frag.play;

import kimxu.mvp.view.AppFragDelegate;
import kimxu.newsandfm.R;
import kimxu.newsandfm.widget.LrcView;

/**
 *
 * Created by kimxu on 16/1/7.
 */

public class PlayLrcDelegate extends AppFragDelegate {
    private LrcView lrcView;
    @Override
    public int getRootLayoutId() {
        return R.layout.fragment_play_lrc;
    }

    @Override
    public void initWidget() {
        lrcView=get(R.id.lrc_fragPlayLrc);
    }
    public void setLrc(String path){
        lrcView.setLrcPath(path);
    }

    public void changeCurrent(int progress) {
        lrcView.changeCurrent(progress);
    }
}
