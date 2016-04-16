package me.kimxu.frag;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.media.AudioManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.SeekBar;


import com.squareup.picasso.Picasso;

import java.util.prefs.Preferences;

import kimxu.utils.Constants;
import kimxu.utils.SPUtils;
import kimxu.utils.Ts;
import me.kimxu.KBaseFragment;
import me.kimxu.R;
import me.kimxu.delegate.PlayDelegate;
import me.kimxu.enums.MusicTypeEnum;
import me.kimxu.enums.PlayModeEnum;
import me.kimxu.model.Music;
import me.kimxu.utils.Actions;
import me.kimxu.utils.CoverLoader;
import me.kimxu.utils.FileUtils;
import me.kimxu.utils.ImageUtils;
import me.kimxu.utils.ScreenUtils;
import me.kimxu.utils.SystemUtils;

/**
 * Fm电台
 */
public class PlayFragment extends KBaseFragment<PlayDelegate> implements View.OnClickListener, ViewPager.OnPageChangeListener, SeekBar.OnSeekBarChangeListener {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    private AudioManager mAudioManager;

    public static PlayFragment newInstance(String param1, String param2) {
        PlayFragment fragment = new PlayFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void bindEvenListener() {
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        initSystemBar();
        viewDelegate.mAlbumCoverView.initNeedle(getPlayService().isPlaying());
        initVolume();
        initPlayMode();
        onChange(getPlayService().getPlayingMusic());
        setListener();
    }

    @Override
    public void onResume() {
        super.onResume();
        IntentFilter filter = new IntentFilter(Actions.VOLUME_CHANGED_ACTION);
        getActivity().registerReceiver(mVolumeReceiver, filter);
    }

    protected void setListener() {
        viewDelegate.ivBack.setOnClickListener(this);
        viewDelegate.ivMode.setOnClickListener(this);
        viewDelegate.ivPlay.setOnClickListener(this);
        viewDelegate.ivPrev.setOnClickListener(this);
        viewDelegate.ivNext.setOnClickListener(this);
        viewDelegate.sbProgress.setOnSeekBarChangeListener(this);
        viewDelegate.sbVolume.setOnSeekBarChangeListener(this);
        viewDelegate.vpPlay.setOnPageChangeListener(this);
    }

    private void initVolume() {
        mAudioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);
        viewDelegate.sbVolume.setMax(mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC));
        viewDelegate.sbVolume.setProgress(mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC));
    }

    private void initPlayMode() {
        int mode = SPUtils.getPlayMode(0);
        viewDelegate.ivMode.setImageLevel(mode);
    }

    /**
     * 沉浸式状态栏
     */
    private void initSystemBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            int top = ScreenUtils.getSystemBarHeight(getActivity());
            viewDelegate.llContent.setPadding(0, top, 0, 0);
        }
    }

    public void onChange(Music music) {
        onPlay(music);
    }

    private void onPlay(Music music) {
        if (music == null) {
            return;
        }
        viewDelegate.tvTitle.setText(music.getTitle());
        viewDelegate.tvArtist.setText(music.getArtist());
        viewDelegate.sbProgress.setMax((int) music.getDuration());
        viewDelegate.sbProgress.setProgress(0);
        viewDelegate.mLastProgress = 0;
        viewDelegate.tvCurrentTime.setText(R.string.play_time_start);
        viewDelegate.tvTotalTime.setText(formatTime(music.getDuration()));
        setCoverAndBg(music);
        setLrc(music);
        if (getPlayService().isPlaying()) {
            viewDelegate.ivPlay.setSelected(true);
            viewDelegate.mAlbumCoverView.start();
        } else {
            viewDelegate.ivPlay.setSelected(false);
            viewDelegate.mAlbumCoverView.pause();
        }
    }

    private String formatTime(long time) {
        return SystemUtils.formatTime("mm:ss", time);
    }

    private void setCoverAndBg(Music music) {
        if (music.getType() == MusicTypeEnum.LOCAL) {
            viewDelegate.mAlbumCoverView.setCoverBitmap(CoverLoader.getInstance().loadRound(music.getCoverUri()));
            viewDelegate.ivPlayingBg.setImageBitmap(CoverLoader.getInstance().loadBlur(music.getCoverUri()));
        } else {
            if (music.getCover() == null) {
                viewDelegate.mAlbumCoverView.setCoverBitmap(CoverLoader.getInstance().loadRound(null));
                viewDelegate.ivPlayingBg.setImageResource(R.drawable.play_page_default_bg);
            } else {
                Bitmap cover = ImageUtils.resizeImage(music.getCover(), ScreenUtils.getScreenWidth() / 2, ScreenUtils.getScreenWidth() / 2);
                cover = ImageUtils.createCircleImage(cover);
                viewDelegate.mAlbumCoverView.setCoverBitmap(cover);
                //Bitmap bg = ImageUtils.blur(music.getCover(), ImageUtils.BLUR_RADIUS,true);
                viewDelegate.ivPlayingBg.setImageBitmap(music.getCover());}
        }
    }

    /**
     * 更新播放进度
     */
    public void onPublish(int progress) {
        viewDelegate.sbProgress.setProgress(progress);
        if (viewDelegate.mLrcViewSingle.hasLrc()) {
            viewDelegate.mLrcViewSingle.updateTime(progress);
            viewDelegate.mLrcViewFull.updateTime(progress);
        }
        //更新当前播放时间
        if (progress - viewDelegate.mLastProgress >= 1000) {
            viewDelegate.tvCurrentTime.setText(formatTime(progress));
            viewDelegate.mLastProgress = progress;
        }
    }

    public void onPlayerPause() {
        viewDelegate.ivPlay.setSelected(false);
        viewDelegate.mAlbumCoverView.pause();
    }

    public void onPlayerResume() {
        viewDelegate.ivPlay.setSelected(true);
        viewDelegate.mAlbumCoverView.start();
    }


    private void play() {
        getPlayService().playPause();
    }

    private void next() {
        getPlayService().next();
    }

    private void prev() {
        getPlayService().prev();
    }


    private void switchPlayMode() {
        PlayModeEnum mode = PlayModeEnum.valueOf(SPUtils.getPlayMode(0));
        switch (mode) {
            case LOOP:
                mode = PlayModeEnum.SHUFFLE;
                Ts.showToast(R.string.mode_shuffle);
                break;
            case SHUFFLE:
                mode = PlayModeEnum.ONE;
                Ts.showToast(R.string.mode_one);
                break;
            case ONE:
                mode = PlayModeEnum.LOOP;
                Ts.showToast(R.string.mode_loop);
                break;
        }
        SPUtils.savePlayMode(mode.value());
        initPlayMode();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                onBackPressed();
                break;
            case R.id.iv_mode:
                switchPlayMode();
                break;
            case R.id.iv_play:
                play();
                break;
            case R.id.iv_next:
                next();
                break;
            case R.id.iv_prev:
                prev();
                break;
        }
    }

    private void onBackPressed() {
        getActivity().onBackPressed();
        viewDelegate.ivBack.setEnabled(false);
        mHandler.postDelayed(() -> viewDelegate.ivBack.setEnabled(true), 300);
    }

    private void setLrc(Music music) {
        String lrcPath;
        if (music.getType() == MusicTypeEnum.LOCAL) {
            lrcPath = FileUtils.getLrcDir() + music.getFileName().replace(Constants.FILENAME_MP3, Constants.FILENAME_LRC);
        } else {
            lrcPath = FileUtils.getLrcDir() + FileUtils.getLrcFileName(music.getArtist(), music.getTitle());
        }
        viewDelegate.mLrcViewSingle.loadLrc(lrcPath);
        viewDelegate.mLrcViewFull.loadLrc(lrcPath);
    }

    @Override
    protected Class<PlayDelegate> getDelegateClass() {
        return PlayDelegate.class;
    }

    private BroadcastReceiver mVolumeReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (!Actions.VOLUME_CHANGED_ACTION.equals(intent.getAction())) {
                return;
            }
            viewDelegate.sbVolume.setProgress(mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC));
        }
    };

    @Override
    public void onDestroy() {
        getActivity().unregisterReceiver(mVolumeReceiver);
        super.onDestroy();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        viewDelegate.ilIndicator.setCurrent(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        if (seekBar == viewDelegate.sbProgress) {
            if (getPlayService().isPlaying() || getPlayService().isPause()) {
                int progress = seekBar.getProgress();
                getPlayService().seekTo(progress);
                viewDelegate.mLrcViewSingle.onDrag(progress);
                viewDelegate.mLrcViewFull.onDrag(progress);
                viewDelegate.tvCurrentTime.setText(formatTime(progress));
                viewDelegate.mLastProgress = progress;
            } else {
                seekBar.setProgress(0);
            }
        } else if (seekBar == viewDelegate.sbVolume) {
            mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, seekBar.getProgress(),
                    AudioManager.FLAG_REMOVE_SOUND_AND_VIBRATE);
        }
    }
}
