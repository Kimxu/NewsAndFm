package kimxu.newsandfm.aty;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;

import com.squareup.picasso.Picasso;

import kimxu.mvp.databind.DataBinder;
import kimxu.newsandfm.KBaseSwipeBackActivity;
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
    //private static final String CURRENT_AUDIO = "Audios";
    //private static final String CURRENT_AUDIO_POSITION = "Audio_position";
    //private ArrayList<Audio> mAudios;
    //private int mPosition;//记录传进来的歌曲标识
    private PlayMusicService.State mState;
    //private int mCurrentPosition;//当前播放的表识

    @Override
    public DataBinder getDataBinder() {
        return null;
    }

    @Override
    protected void bindEvenListener() {
        //mAudios = mApplication.audios;
        //playIsCurrent
        //mPosition = mApplication.mPosition;
        viewDelegate.setOnClickListener(this, R.id.ib_atyMusicPlayer_playNext, R.id.ib_atyMusicPlayer_playPre, R.id.ib_atyMusicPlayer_playStart);
        //歌曲状态改变
        mApplication.mPlayMusicService.setOnPlaybackListener(new PlayMusicService.OnPlaybackListener() {
            @Override
            public void onStateChanged(Audio source, PlayMusicService.State state) {
                mState = state;
                //控制通知栏的播放状态
                mApplication.setNotificationStatus(mState);
                viewDelegate.setPlayStartStatus(mActivity, mState);
                if (source != null)
                    viewDelegate.setToolbarTitle(source.getTitle());
            }

            @Override
            public void onStartProgressChanged(int progress) {
                viewDelegate.skProgress.setProgress(progress);
            }

            @Override
            public void onStartProgressDuration(int duration) {
                viewDelegate.skProgress.setMax(duration);
            }
        });
        //播放完毕，播放下一首
        mApplication.mPlayMusicService.setmCompletionListener(mp -> playNext());
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
        if (mApplication.playNext() != null)
            funStart(mApplication.playNext());
    }

    private void playPre() {
        if (mApplication.playPre() != null)
            funStart(mApplication.playPre());
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
}
