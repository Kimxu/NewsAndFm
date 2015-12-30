package kimxu.newsandfm.aty;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import kimxu.mvp.databind.DataBinder;
import kimxu.newsandfm.KBaseSwipeBackActivity;
import kimxu.newsandfm.R;
import kimxu.newsandfm.model.Audio;
import kimxu.newsandfm.service.PlayMusicService;
import kimxu.utils.Ts;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 音乐播放器主界面
 * <p>
 * Created by kimxu on 2015/12/30
 */
public class MusicPlayerActivity extends KBaseSwipeBackActivity<MusicPlayerDelegate> implements View.OnClickListener {
    private static final String CURRENT_AUDIO = "Audios";
    private static final String CURRENT_AUDIO_POSITION = "Audio_position";
    private ArrayList<Audio> mAudios;
    private int mPosition;//记录传进来的歌曲标识
    private PlayMusicService.State mState;
    private int mCurrentPosition;//当前播放的表识

    @Override
    public DataBinder getDataBinder() {
        return null;
    }

    @Override
    protected void bindEvenListener() {
        mAudios = (ArrayList<Audio>) getIntent().getSerializableExtra(CURRENT_AUDIO);
        mPosition = getIntent().getIntExtra(CURRENT_AUDIO_POSITION, 0);
        viewDelegate.setOnClickListener(this, R.id.ib_musicPlayer_playStart, R.id.ib_musicPlayer_playPre, R.id.ib_musicPlayer_playNext);
        //歌曲状态改变
        mApplication.mPlayMusicService.setOnPlaybackListener((source, state) -> mState = state);
        //播放完毕，播放下一首
        mApplication.mPlayMusicService.setmCompletionListener(mp -> playNext());

        String title = mAudios.get(mPosition).getTitle();


        mApiService.apiBdyyManager.getSongId(title).map(searchId -> {
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
                        mApiService.apiBdyyManager.getAlbumPic(songid).map(albumPic -> {
                            return albumPic.getSonginfo().getPicHuge();
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
                                    public void onNext(String s) {
                                        Picasso.with(mActivity).load(s).into(viewDelegate.ivPhotoAlbum);
                                    }
                                });
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
            case R.id.ib_musicPlayer_playPre:
                playPre();
                break;
            case R.id.ib_musicPlayer_playStart:
                playStart();
                break;
            case R.id.ib_musicPlayer_playNext:
                playNext();
                break;
        }
    }

    private void playNext() {
        if (mAudios != null) {
            mCurrentPosition++;
            if (mCurrentPosition <= mAudios.size()) {
                mApplication.mPlayMusicService.start(mAudios.get(++mCurrentPosition));
            } else {
                Ts.showToast(mActivity, "是最后一首啦~");
            }
        }
    }

    private void playPre() {
        if (mAudios != null) {
            mCurrentPosition--;
            if (mCurrentPosition >= 0) {
                mApplication.mPlayMusicService.start(mAudios.get(--mCurrentPosition));
            } else {
                Ts.showToast(mActivity, "是第一首啦~");
            }
        }
    }

    private void playStart() {
        if (mState == PlayMusicService.State.STARTED) {
            mApplication.mPlayMusicService.paused();
        } else if (mState == PlayMusicService.State.PAUSED) {
            mApplication.mPlayMusicService.reStart();
        } else {
            if (mAudios != null) {
                mCurrentPosition = mPosition;
                mApplication.mPlayMusicService.start(mAudios.get(mCurrentPosition));

            }
        }
    }

    public static void launch(Activity activity, ArrayList<Audio> audios, int position) {
        activity.startActivity(new Intent(activity, MusicPlayerActivity.class)
                .putExtra(CURRENT_AUDIO, audios)
                .putExtra(CURRENT_AUDIO_POSITION, position));
    }

}
