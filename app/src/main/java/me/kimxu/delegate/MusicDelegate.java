package me.kimxu.delegate;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import kimxu.mvp.view.AppAtyDelegate;
import me.kimxu.KBaseActivity;
import me.kimxu.R;
import me.kimxu.adapter.FragmentAdapter;
import me.kimxu.executor.WeatherExecutor;
import me.kimxu.frag.LocalMusicFragment;
import me.kimxu.frag.PlayFragment;
import me.kimxu.frag.SongListFragment;

/**
 * 视图View
 * Created by xuzhiguo on 15/11/23.
 */
public class MusicDelegate extends AppAtyDelegate {
    public DrawerLayout drawerLayout;
    public NavigationView navigationView;
    public ImageView ivMenu;
    public TextView tvLocalMusic;
    public TextView tvOnlineMusic;
    public ViewPager mViewPager;
    public FrameLayout flPlayBar;
    public ImageView ivPlayBarCover;
    public TextView tvPlayBarTitle;
    public TextView tvPlayBarArtist;
    public ImageView ivPlayBarPlay;
    public ImageView ivPlayBarNext;
    public ProgressBar mProgressBar;
    public View vNavigationHeader;

    public LocalMusicFragment mLocalMusicFragment;
    public SongListFragment mSongListFragment;
    public PlayFragment mPlayFragment;
    public MenuItem timerItem;

    private Context mContext;
    @Override
    public void create(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.create(inflater, container, savedInstanceState);
    }

    @Override
    public void setContext(Context context) {
        super.setContext(context);
        mContext=context;
    }

    @Override
    public int getRootLayoutId() {
        return R.layout.activity_music;
    }

    @Override
    public void initWidget() {
        drawerLayout = get(R.id.drawer_layout);
        navigationView = get(R.id.navigation_view);
        ivMenu = get(R.id.iv_menu);
        tvLocalMusic = get(R.id.tv_local_music);
        tvOnlineMusic = get(R.id.tv_online_music);
        mViewPager = get(R.id.viewpager);
        flPlayBar = get(R.id.fl_play_bar);
        ivPlayBarCover = get(R.id.iv_play_bar_cover);
        tvPlayBarTitle = get(R.id.tv_play_bar_title);
        tvPlayBarArtist = get(R.id.tv_play_bar_artist);
        ivPlayBarPlay = get(R.id.iv_play_bar_play);
        ivPlayBarNext = get(R.id.iv_play_bar_next);
        mProgressBar = get(R.id.pb_play_bar);
        vNavigationHeader = LayoutInflater.from(mContext).inflate(R.layout.navigation_header, navigationView, false);
        navigationView.addHeaderView(vNavigationHeader);

        mLocalMusicFragment = LocalMusicFragment.newInstance("","");
        mSongListFragment = new SongListFragment();
        FragmentAdapter adapter = new FragmentAdapter(((KBaseActivity)mContext).getSupportFragmentManager());
        adapter.addFragment(mLocalMusicFragment);
        adapter.addFragment(mSongListFragment);
        mViewPager.setAdapter(adapter);
        tvLocalMusic.setSelected(true);

        updateWeather();
    }

    private void updateWeather() {
        new WeatherExecutor(vNavigationHeader).execute();
    }

    @Override
    public void setSupportActionBar(ActionBar actionBar) {
        //TODO 这里是去除toolbar返回键
    }

    @Override
    public Toolbar getToolbar() {
        return get(R.id.tBar_atyMain);
    }

}
