package kimxu.newsandfm.aty;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.widget.ImageButton;
import android.widget.SeekBar;

import kimxu.mvp.view.AppAtyDelegate;
import kimxu.newsandfm.R;
import kimxu.newsandfm.service.PlayMusicService;

/**
 *
 * Created by kimxu on 15/12/30.
 */

public class MusicPlayerDelegate extends AppAtyDelegate{
    //public ImageView ivPhotoAlbum;
    public ImageButton ibStart;
    public SeekBar skProgress;
    public ViewPager viewPager;
    @Override
    public int getRootLayoutId() {
        return R.layout.activity_music_player;
    }

    @Override
    public void initWidget() {
        //ivPhotoAlbum=get(R.id.iv_atyMusicPlayer_photoAlbum);
        ibStart=get(R.id.iB_atyMusicPlayer_playStart);
        skProgress=get(R.id.sb_atyMusicPlayer_playProgress);
        viewPager=get(R.id.vPager_atyMusicPlayer);
    }



    public void setProgressListener(SeekBar.OnSeekBarChangeListener onSeekBarChangeListener){
        skProgress.setOnSeekBarChangeListener(onSeekBarChangeListener);
    }

    public void setPlayStartStatus(Context context, PlayMusicService.State state){
        if (state== PlayMusicService.State.STARTED){
            ibStart.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.nf_player_btn_play_normal));
        }else{
            ibStart.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.nf_player_btn_pause_normal));
        }
    }


    @Override
    public Toolbar getToolbar() {
        return get(R.id.tBar_atyMusicPlayer);
    }

    public void setToolbarTitle(String title){
        getToolbar().setTitle(title);
    }
}
