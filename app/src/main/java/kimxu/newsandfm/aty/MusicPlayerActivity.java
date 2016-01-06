package kimxu.newsandfm.aty;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;

import com.squareup.picasso.Picasso;

import kimxu.mvp.databind.DataBinder;
import kimxu.newsandfm.KBaseSwipeBackActivity;
import kimxu.newsandfm.NfContant;
import kimxu.newsandfm.R;
import kimxu.newsandfm.model.Audio;
import kimxu.newsandfm.service.PlayMusicService;
import kimxu.utils.L;
import rx.Subscriber;
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
    public static final String INTENT_NAME ="intent_name";
    public static final int INTENT_STATE_CHANGED =0x001;
    public static final int INTENT_STATE_PROGRESS_CHANGED =0x002;
    public static final int INTENT_STATE_PROGRESS_DURATION =0x003;

    public static final String ARG_SOURCE="source";
    public static final String ARG_STATE="state";
    public static final String ARG_PROGRESS="progress";
    public static final String ARG_DURATION="duration";


    @Override
    public DataBinder getDataBinder() {
        return null;
    }

    @Override
    protected void bindEvenListener() {
        viewDelegate.setOnClickListener(this, R.id.ib_atyMusicPlayer_playNext, R.id.ib_atyMusicPlayer_playPre, R.id.ib_atyMusicPlayer_playStart);
        //播放完毕，播放下一首
        mApplication.mPlayMusicService.setmCompletionListener(mp -> playNext());
        viewDelegate.setToolbarTitle(mApplication.getCurrentAudioTitle());
        mState=mApplication.mState;
        viewDelegate.setPlayStartStatus(mActivity, mState);
        viewDelegate.setProgressListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser)
                    mApplication.mPlayMusicService.seekTo(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        mMusicPlayReceiver=new MusicPlayReceiver();
        mPlayMusicFilter =new IntentFilter(NfContant.INTENT_PLAY_MUSIC);
    }

    /**
     * 设置歌曲相册
     *
     * @param title
     */
    private void setPhotoAlbum(String title, ImageView imageView) {
        mApiService.apiBdyyManager.getSongId(title).map(searchId -> {
            //如果获取不到，就返回-1
            if (searchId.getSong().size() == 0) {
                return "-1";
            } else {
                return searchId.getSong().get(0).getSongid();
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(String songid) {
                        if (!songid.equals("-1")) {
                            mApiService.apiBdyyManager.getAlbumPic(songid).map(albumPic -> albumPic.getSonginfo().getPicHuge())
                                    .subscribeOn(Schedulers.io())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe(new Subscriber<String>() {
                                        @Override
                                        public void onCompleted() {

                                        }

                                        @Override
                                        public void onError(Throwable e) {

                                        }

                                        @Override
                                        public void onNext(String s) {
                                            Picasso.with(mActivity).load(s).into(imageView);
                                        }
                                    });
                        }
                    }
                });
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
        if ((audio=mApplication.playNext()) != null)
            funStart(audio);
    }

    private void playPre() {
        Audio audio;
        if ((audio=mApplication.playPre()) != null)
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
        setPhotoAlbum(audio.getTitle(), viewDelegate.ivPhotoAlbum);
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
        registerReceiver(mMusicPlayReceiver,mPlayMusicFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mMusicPlayReceiver);
    }

    public class MusicPlayReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            int intentName = intent.getIntExtra(INTENT_NAME,-1);
            if (intentName==-1)
                return;
            switch (intentName){
                case INTENT_STATE_CHANGED:
                    Audio source = (Audio) intent.getSerializableExtra(ARG_SOURCE);
                    mState = (PlayMusicService.State) intent.getSerializableExtra(ARG_STATE);
                    viewDelegate.setPlayStartStatus(mActivity, mState);
                    if (source != null)
                        viewDelegate.setToolbarTitle(source.getTitle());
                    break;
                case INTENT_STATE_PROGRESS_CHANGED:
                    int progress= intent.getIntExtra(ARG_PROGRESS,-1);
                    viewDelegate.skProgress.setProgress(progress);
                    break;
                case INTENT_STATE_PROGRESS_DURATION:
                    int duration= intent.getIntExtra(ARG_DURATION,-1);
                    viewDelegate.skProgress.setMax(duration);
                    break;
            }
        }
    }
}
