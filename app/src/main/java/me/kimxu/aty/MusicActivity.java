package me.kimxu.aty;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.media.AudioManager;
import android.os.IBinder;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;
import android.view.View;

import com.squareup.picasso.Picasso;

import me.kimxu.KBaseActivity;
import me.kimxu.R;
import me.kimxu.delegate.MusicDelegate;
import me.kimxu.executor.NaviMenuExecutor;
import me.kimxu.frag.PlayFragment;
import me.kimxu.model.Music;
import me.kimxu.receiver.RemoteControlReceiver;
import me.kimxu.service.OnPlayerEventListener;
import me.kimxu.service.PlayService;
import me.kimxu.utils.CoverLoader;
import me.kimxu.utils.Extras;
import me.kimxu.utils.SystemUtils;

public class MusicActivity extends KBaseActivity<MusicDelegate> implements View.OnClickListener, OnPlayerEventListener,
        NavigationView.OnNavigationItemSelectedListener, ViewPager.OnPageChangeListener {
    private PlayService mPlayService;
    private AudioManager mAudioManager;
    private ComponentName mRemoteReceiver;
    private boolean isPlayFragmentShow = false;
    @Override
    protected void onDestroy() {
        unbindService(mPlayServiceConnection);
        mAudioManager.unregisterMediaButtonEventReceiver(mRemoteReceiver);
        super.onDestroy();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        parseIntent(intent);
    }

    private void bindService() {
        Intent intent = new Intent();
        intent.setClass(this, PlayService.class);
        bindService(intent, mPlayServiceConnection, Context.BIND_AUTO_CREATE);
    }
    private ServiceConnection mPlayServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mPlayService = ((PlayService.PlayBinder) service).getService();
            mPlayService.setOnPlayEventListener(MusicActivity.this);
            init();
            parseIntent(getIntent());
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
        }
    };

    private void init() {
        registerReceiver();
        onChange(mPlayService.getPlayingMusic());
        //UpdateUtils.checkUpdate(this);
        setListener();
    }

    private void parseIntent(Intent intent) {
        if (intent.hasExtra(Extras.FROM_NOTIFICATION)) {
            showPlayingFragment();
        }
    }


    @Override
    protected void bindEvenListener() {
        bindService();
    }


    protected Class<MusicDelegate> getDelegateClass() {
        return MusicDelegate.class;
    }


    public PlayService getPlayService() {
        return mPlayService;
    }

    private void registerReceiver() {
        mAudioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        mRemoteReceiver = new ComponentName(getPackageName(), RemoteControlReceiver.class.getName());
        mAudioManager.registerMediaButtonEventReceiver(mRemoteReceiver);
    }

    private void showPlayingFragment() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.anim.fragment_slide_up, 0);
        if (viewDelegate.mPlayFragment == null) {
            viewDelegate.mPlayFragment = new PlayFragment();
            ft.replace(android.R.id.content, viewDelegate.mPlayFragment);
        } else {
            ft.show(viewDelegate.mPlayFragment);
        }
        ft.commit();
        isPlayFragmentShow = true;
    }

    private void hidePlayingFragment() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.setCustomAnimations(0, R.anim.fragment_slide_down);
        ft.hide(viewDelegate.mPlayFragment);
        ft.commit();
        isPlayFragmentShow = false;
    }

    private void play() {
        getPlayService().playPause();
    }

    private void next() {
        getPlayService().next();
    }

    public void onPlay(Music music) {
        if (music == null) {
            return;
        }
        Bitmap cover;
        if (music.getCover() == null) {
            cover = CoverLoader.getInstance().loadThumbnail(music.getCoverUri());
        } else {
            cover = music.getCover();
        }
        viewDelegate.ivPlayBarCover.setImageBitmap(cover);
        viewDelegate.tvPlayBarTitle.setText(music.getTitle());
        viewDelegate.tvPlayBarArtist.setText(music.getArtist());
        if (getPlayService().isPlaying()) {
            viewDelegate.ivPlayBarPlay.setSelected(true);
        } else {
            viewDelegate.ivPlayBarPlay.setSelected(false);
        }
        viewDelegate.mProgressBar.setMax((int) music.getDuration());
        viewDelegate.mProgressBar.setProgress(0);

        if (viewDelegate.mLocalMusicFragment != null && viewDelegate.mLocalMusicFragment.isResume()) {
            viewDelegate.mLocalMusicFragment.onItemPlay();
        }
    }

    @Override
    public void onBackPressed() {
        if (viewDelegate.mPlayFragment != null && isPlayFragmentShow) {
            hidePlayingFragment();
            return;
        }
        if (viewDelegate.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            viewDelegate.drawerLayout.closeDrawers();
            return;
        }
        moveTaskToBack(false);
    }

    protected void setListener() {
        viewDelegate.ivMenu.setOnClickListener(this);
        viewDelegate.tvLocalMusic.setOnClickListener(this);
        viewDelegate.tvOnlineMusic.setOnClickListener(this);
        viewDelegate.mViewPager.setOnPageChangeListener(this);
        viewDelegate.flPlayBar.setOnClickListener(this);
        viewDelegate.ivPlayBarPlay.setOnClickListener(this);
        viewDelegate.ivPlayBarNext.setOnClickListener(this);
        viewDelegate.navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onPublish(int progress) {
        viewDelegate.mProgressBar.setProgress(progress);
        if (viewDelegate.mPlayFragment != null && viewDelegate.mPlayFragment.isResume()) {
            viewDelegate.mPlayFragment.onPublish(progress);
        }
    }

    @Override
    public void onChange(Music music) {
        onPlay(music);
        if (viewDelegate.mPlayFragment != null && viewDelegate.mPlayFragment.isResume()) {
            viewDelegate.mPlayFragment.onChange(music);
        }
    }

    @Override
    public void onPlayerPause() {
        viewDelegate.ivPlayBarPlay.setSelected(false);
        if (viewDelegate.mPlayFragment != null && viewDelegate.mPlayFragment.isResume()) {
            viewDelegate.mPlayFragment.onPlayerPause();
        }
    }

    @Override
    public void onPlayerResume() {
        viewDelegate.ivPlayBarPlay.setSelected(true);
        if (viewDelegate.mPlayFragment != null && viewDelegate.mPlayFragment.isResume()) {
            viewDelegate.mPlayFragment.onPlayerResume();
        }
    }

    @Override
    public void onTimer(long remain) {
        if (viewDelegate.timerItem == null) {
            viewDelegate.timerItem = viewDelegate.navigationView.getMenu().findItem(R.id.action_timer);
        }
        String title = getString(R.string.menu_timer);
        viewDelegate.timerItem.setTitle(remain == 0 ? title : SystemUtils.formatTime(title + "(mm:ss)", remain));

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_menu:
                viewDelegate.drawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.tv_local_music:
                viewDelegate.mViewPager.setCurrentItem(0);
                break;
            case R.id.tv_online_music:
                viewDelegate.mViewPager.setCurrentItem(1);
                break;
            case R.id.fl_play_bar:
                showPlayingFragment();
                break;
            case R.id.iv_play_bar_play:
                play();
                break;
            case R.id.iv_play_bar_next:
                next();
                break;
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        viewDelegate.drawerLayout.closeDrawers();
        mHandler.postDelayed(() -> item.setChecked(false), 500);
        return NaviMenuExecutor.onNavigationItemSelected(item, this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if (position == 0) {
            viewDelegate.tvLocalMusic.setSelected(true);
            viewDelegate.tvOnlineMusic.setSelected(false);
        } else {
            viewDelegate.tvLocalMusic.setSelected(false);
            viewDelegate.tvOnlineMusic.setSelected(true);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
