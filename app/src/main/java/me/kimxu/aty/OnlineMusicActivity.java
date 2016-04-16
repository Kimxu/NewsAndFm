package me.kimxu.aty;

import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import kimxu.adapter.AssemblyAdapter;
import kimxu.core.bean.JOnlineMusic;
import kimxu.core.bean.JOnlineMusicList;
import kimxu.core.bean.SongListInfo;
import kimxu.core.net.ApiService;
import kimxu.utils.Constants;
import kimxu.utils.Ts;
import me.kimxu.KBaseActivity;
import me.kimxu.R;
import me.kimxu.adapter.factory.OnMoreClickListener;
import me.kimxu.adapter.factory.OnlineMusicFactory;
import me.kimxu.delegate.OnlineMusicDelegate;
import me.kimxu.enums.LoadStateEnum;
import me.kimxu.executor.DownloadOnlineMusic;
import me.kimxu.executor.PlayOnlineMusic;
import me.kimxu.executor.ShareOnlineMusic;
import me.kimxu.model.Music;
import me.kimxu.service.PlayService;
import me.kimxu.utils.Extras;
import me.kimxu.utils.FileUtils;
import me.kimxu.utils.ViewUtils;
import me.kimxu.widget.OnLoadListener;
import okhttp3.Call;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class OnlineMusicActivity extends KBaseActivity<OnlineMusicDelegate> implements OnLoadListener{

    private ArrayList<JOnlineMusic> mMusicList;
    private ProgressDialog mProgressDialog;
    private SongListInfo mListInfo;
    private PlayService mPlayService;
    private AssemblyAdapter mAdapter;
    private JOnlineMusicList mJOnlineMusicList;
    private int mOffset = 0;
    @Override
    protected void bindEvenListener() {
        mMusicList = new ArrayList<>();
        mListInfo = (SongListInfo) getIntent().getSerializableExtra(Extras.MUSIC_LIST_TYPE);
        setTitle(mListInfo.getTitle());

        mAdapter = new AssemblyAdapter(mMusicList);
        mAdapter.addItemFactory(new OnlineMusicFactory(position -> {
            JOnlineMusic jOnlineMusic = mMusicList.get(position);
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setTitle(mMusicList.get(position).getTitle());
            String path = FileUtils.getMusicDir() + FileUtils.getMp3FileName(jOnlineMusic.getArtist_name(), jOnlineMusic.getTitle());
            File file = new File(path);
            int itemsId = file.exists() ? R.array.online_music_dialog_no_download : R.array.online_music_dialog;
            dialog.setItems(itemsId, (dialog1, which) -> {
                switch (which) {
                    case 0:// 分享
                        share(jOnlineMusic);
                        break;
                    case 1:// 查看歌手信息
                        artistInfo(jOnlineMusic);
                        break;
                    case 2:// 下载
                        download(jOnlineMusic);
                        break;
                }
            });
            dialog.show();
        }));
        viewDelegate.lvOnlineMusic.setAdapter(mAdapter);
        viewDelegate.lvOnlineMusic.setOnLoadListener(this);
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage(getString(R.string.loading));
        ViewUtils.changeViewState(viewDelegate.lvOnlineMusic, viewDelegate.llLoading, viewDelegate.llLoadFail, LoadStateEnum.LOADING);
        bindService();
        setListener();
    }
    protected void setListener() {
        viewDelegate.lvOnlineMusic.setOnItemClickListener((parent, view, position, id) -> {
            play(mMusicList.get(position - 1));
        });
    }


    private void getMusic(final int offset) {
        Map<String,String> map =new HashMap<>();
        map.put("type",mListInfo.getType());
        map.put("size",String.valueOf(Constants.MUSIC_LIST_SIZE));
        map.put("offset",String.valueOf(offset));
        ApiService.getInstance()
                .apiManager
                .getSongList(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                    viewDelegate.lvOnlineMusic.onLoadComplete();
                    mJOnlineMusicList = response;
                    if (offset == 0) {
                        initHeader();
                        ViewUtils.changeViewState(viewDelegate.lvOnlineMusic, viewDelegate.llLoading, viewDelegate.llLoadFail, LoadStateEnum.LOAD_SUCCESS);
                    }
                    if (response.getSong_list() == null || response.getSong_list().length == 0) {
                        viewDelegate.lvOnlineMusic.setEnable(false);
                        return;
                    }
                    mOffset += Constants.MUSIC_LIST_SIZE;
                    Collections.addAll(mMusicList, response.getSong_list());
                    mAdapter.notifyDataSetChanged();

                },error->{
                    viewDelegate.lvOnlineMusic.onLoadComplete();
                    if (error.getCause() instanceof RuntimeException) {
                        // 歌曲全部加载完成
                        viewDelegate.lvOnlineMusic.setEnable(false);
                        return;
                    }
                    if (offset == 0) {
                        ViewUtils.changeViewState(viewDelegate.lvOnlineMusic, viewDelegate.llLoading, viewDelegate.llLoadFail, LoadStateEnum.LOAD_FAIL);
                    } else {
                        Ts.showToast(R.string.load_fail);
                    }
                });
    }

    private void initHeader() {
        final ImageView ivHeaderBg = (ImageView) viewDelegate.vHeader.findViewById(R.id.iv_header_bg);
        final ImageView ivCover = (ImageView) viewDelegate.vHeader.findViewById(R.id.iv_cover);
        TextView tvTitle = (TextView) viewDelegate.vHeader.findViewById(R.id.tv_title);
        TextView tvUpdateDate = (TextView) viewDelegate.vHeader.findViewById(R.id.tv_update_date);
        TextView tvComment = (TextView) viewDelegate.vHeader.findViewById(R.id.tv_comment);
        tvTitle.setText(mJOnlineMusicList.getBillboard().getName());
        tvUpdateDate.setText(getString(R.string.recent_update, mJOnlineMusicList.getBillboard().getUpdate_date()));
        tvComment.setText(mJOnlineMusicList.getBillboard().getComment());
        Picasso.with(this).load(mJOnlineMusicList.getBillboard().getPic_s640()).into(ivCover);
        Picasso.with(this).load(mJOnlineMusicList.getBillboard().getPic_s640()).into(ivHeaderBg);
    }

    private void play(JOnlineMusic jOnlineMusic) {
        new PlayOnlineMusic(this, jOnlineMusic) {

            @Override
            public void onPrepare() {
                mProgressDialog.show();
            }

            @Override
            public void onSuccess(Music music) {
                mProgressDialog.cancel();
                mPlayService.play(music);
                Ts.showToast(getString(R.string.now_play, music.getTitle()));
            }

            @Override
            public void onFail(String error) {
                mProgressDialog.cancel();
                Ts.showToast(R.string.unable_to_play);
            }
        }.execute();
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
            onLoad();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
        }
    };

    protected Class<OnlineMusicDelegate> getDelegateClass() {
        return OnlineMusicDelegate.class;
    }


    @Override
    public void onLoad() {
        getMusic(mOffset);
    }

    private void download(final JOnlineMusic jOnlineMusic) {
        new DownloadOnlineMusic(this, jOnlineMusic) {
            @Override
            public void onPrepare() {
                mProgressDialog.show();
            }

            @Override
            public void onSuccess() {
                mProgressDialog.cancel();
                Ts.showToast(getString(R.string.now_download, jOnlineMusic.getTitle()));
            }

            @Override
            public void onFail(String error) {
                mProgressDialog.cancel();
                Ts.showToast(getString(R.string.unable_to_download,error));
            }
        }.execute();
    }


    private void artistInfo(JOnlineMusic jOnlineMusic) {
       // ArtistInfoActivity.start(this, jOnlineMusic.getTing_uid());
    }
    private void share(final JOnlineMusic jOnlineMusic) {
        new ShareOnlineMusic(this, jOnlineMusic.getTitle(), jOnlineMusic.getSong_id()) {
            @Override
            public void onPrepare() {
                mProgressDialog.show();
            }

            @Override
            public void onSuccess() {
                mProgressDialog.cancel();
            }

            @Override
            public void onFail(Call call, Exception e) {
                mProgressDialog.cancel();
            }
        }.execute();
    }

}
