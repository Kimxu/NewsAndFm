package kimxu.newsandfm.aty;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import kimxu.bdyy.pic.AlbumInfo;
import kimxu.bdyy.pic.SongInfo;
import kimxu.bdyy.searchSongId.Song;
import kimxu.mvp.databind.DataBinder;
import kimxu.newsandfm.KBaseSwipeBackActivity;
import kimxu.newsandfm.NfContant;
import kimxu.newsandfm.R;
import kimxu.newsandfm.frag.play.PlayCoverFragment;
import kimxu.newsandfm.frag.play.PlayLrcFragment;
import kimxu.newsandfm.model.Audio;
import kimxu.newsandfm.service.PlayMusicService;
import kimxu.newsandfm.utils.GlobalUtils;
import kimxu.utils.L;
import kimxu.utils.Ts;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 音乐播放器主界面
 * <p>
 * Created by kimxu on 2015/12/30
 */
public class MusicPlayerActivity extends KBaseSwipeBackActivity<MusicPlayerDelegate> implements View.OnClickListener {
    private IntentFilter mPlayMusicFilter;
    private MusicPlayReceiver mMusicPlayReceiver;
    private PlayMusicService.State mState;
    public static final String INTENT_NAME = "intent_name";
    public static final int INTENT_STATE_CHANGED = 0x001;
    public static final int INTENT_STATE_PROGRESS_CHANGED = 0x002;
    public static final int INTENT_STATE_PROGRESS_DURATION = 0x003;

    public static final String ARG_SOURCE = "source";
    public static final String ARG_STATE = "state";
    public static final String ARG_PROGRESS = "progress";
    public static final String ARG_DURATION = "duration";

    private List<Fragment> mFragments;
    private PlayCoverFragment mPlayCoverFrag;
    private PlayLrcFragment mPlayLrcFrag;

    @Override
    public DataBinder getDataBinder() {
        return null;
    }

    @Override
    protected void bindEvenListener() {
        initView();
        initPlay();
    }

    private void initView() {
        mFragments=new ArrayList<>();
        mPlayCoverFrag=PlayCoverFragment.newInstance("","");
        mPlayLrcFrag =PlayLrcFragment.newInstance("","");
        mFragments.add(mPlayCoverFrag);
        mFragments.add(mPlayLrcFrag);
        viewDelegate.viewPager.setAdapter(new VFragAdapter(getSupportFragmentManager()));
        viewDelegate.viewPager.setOnPageChangeListener(new VOnPageChangeListener());
    }

    private void initPlay() {
        viewDelegate.setOnClickListener(this, R.id.ib_atyMusicPlayer_playNext, R.id.ib_atyMusicPlayer_playPre, R.id.ib_atyMusicPlayer_playStart);
        //播放完毕，播放下一首
        mApplication.mPlayMusicService.setmCompletionListener(mp -> playNext());
        viewDelegate.setToolbarTitle(mApplication.getCurrentAudioTitle());
        mState = mApplication.mState;
        viewDelegate.setPlayStartStatus(mActivity, mState);
        viewDelegate.setProgressListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    mApplication.mPlayMusicService.seekTo(progress);
                    mPlayLrcFrag.changeCurrent(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        mMusicPlayReceiver = new MusicPlayReceiver();
        mPlayMusicFilter = new IntentFilter(NfContant.INTENT_PLAY_MUSIC);
    }

    /**
     * 设置歌曲相册
     *
     * @param title
     */
    private void setPhotoAlbum(String title) {
        boolean hasLrc = false;
        if (!TextUtils.isEmpty(GlobalUtils.getLrcPath(mActivity,title))){
            hasLrc=true;
            mPlayLrcFrag.setLrc(GlobalUtils.getLrcPath(mActivity,title));
        }
        final boolean finalHasLrc = hasLrc;
        mApiService.apiBdyyManager.getSongId(title).map(searchId -> {
            //如果获取不到，就返回null
            if (searchId.getSong().size() == 0) {
                return null;
            } else {
                return searchId.getSong().get(0);
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(song -> {
                    if (song != null) {
                        getCover(song, finalHasLrc);
                    }
                });
    }

    /**
     * 获得封面
     * @param hasLrc 是否需要加载歌词
     */
    private void getCover(Song song,boolean hasLrc) {
        mApiService.apiBdyyManager.getAlbumPic(song.getSongid()).map(AlbumInfo::getSonginfo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> {
                    parsingLrc(s,hasLrc);
                    mPlayCoverFrag.setCover(s.getPicHuge());
                });
    }

    /**
     * 获得歌词，保存到本地
     */
    private void parsingLrc(SongInfo s,boolean hasLrc) {
        if (!TextUtils.isEmpty(s.getLrclink())&&!hasLrc) {
            //0 歌曲编号 1 歌曲名字
            String[] params = s.getLrclink().substring(37).split("/");

            mApiService.apiLrcManager
                    .getLrc(params[0], params[1])
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(response -> {
                        try {
                            byte[] bytes = GlobalUtils.getBytesFromStream(response.getBody().in());
                            String name =URLDecoder.decode(params[1], "UTF-8");
                            GlobalUtils.saveBytes2File(bytes, GlobalUtils.getLrcPath(mActivity) + name);
                            mPlayLrcFrag.setLrc(GlobalUtils.getLrcPath(mActivity,name.replace(".lrc","")));
                        } catch (IOException e) {
                            L.e(e.getLocalizedMessage() + "歌词没加载进去");
                        }
                    });
        } else {
            Ts.showToast(mActivity, "本首歌没有歌词哦");
        }
    }

    @Override
    protected Class<MusicPlayerDelegate> getDelegateClass() {
        return MusicPlayerDelegate.class;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ib_atyMusicPlayer_playPre:
                playPre();
                break;
            case R.id.ib_atyMusicPlayer_playStart:
                playStart();
                break;
            case R.id.ib_atyMusicPlayer_playNext:
                playNext();
                break;
        }
    }

    private void playNext() {
        Audio audio;
        if ((audio = mApplication.playNext()) != null)
            funStart(audio);
    }

    private void playPre() {
        Audio audio;
        if ((audio = mApplication.playPre()) != null)
            funStart(audio);
    }

    private void playStart() {
        if (mState == PlayMusicService.State.STARTED) {
            mApplication.mPlayMusicService.paused();
        } else if (mState == PlayMusicService.State.PAUSED) {
            mApplication.mPlayMusicService.reStart();
        } else {
            if (mApplication.play() != null) {
                funStart(mApplication.play());
            }
        }

    }

    private void funStart(Audio audio) {
        mApplication.mPlayMusicService.start(audio);
        setPhotoAlbum(audio.getTitle());
    }

    public static void launch(Activity activity) {
        activity.startActivity(new Intent(activity, MusicPlayerActivity.class));
    }

    @Override
    protected void onNewIntent(Intent intent) {
        L.e("onNewIntent");
        super.onNewIntent(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(mMusicPlayReceiver, mPlayMusicFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mMusicPlayReceiver);
    }

    public class MusicPlayReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            int intentName = intent.getIntExtra(INTENT_NAME, -1);
            if (intentName == -1)
                return;
            switch (intentName) {
                case INTENT_STATE_CHANGED:
                    Audio source = (Audio) intent.getSerializableExtra(ARG_SOURCE);
                    mState = (PlayMusicService.State) intent.getSerializableExtra(ARG_STATE);
                    viewDelegate.setPlayStartStatus(mActivity, mState);
                    if (source != null)
                        viewDelegate.setToolbarTitle(source.getTitle());
                    break;
                case INTENT_STATE_PROGRESS_CHANGED:
                    int progress = intent.getIntExtra(ARG_PROGRESS, -1);
                    viewDelegate.skProgress.setProgress(progress);
                    break;
                case INTENT_STATE_PROGRESS_DURATION:
                    int duration = intent.getIntExtra(ARG_DURATION, -1);
                    viewDelegate.skProgress.setMax(duration);
                    break;
            }
        }
    }


    class VOnPageChangeListener implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageScrollStateChanged(int state) {
        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int offset) {

        }

        @Override
        public void onPageSelected(int currentTab) {

        }
    }


    class VFragAdapter extends FragmentStatePagerAdapter {

        public VFragAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {

        }
    }
}
