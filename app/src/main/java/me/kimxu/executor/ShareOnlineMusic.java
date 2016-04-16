package me.kimxu.executor;

import android.content.Context;
import android.content.Intent;


import com.squareup.picasso.Picasso;

import kimxu.core.net.ApiService;
import kimxu.utils.Constants;
import kimxu.utils.SPUtils;
import me.kimxu.R;
import okhttp3.Call;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 分享在线歌曲
 * Created by hzwangchenyan on 2016/1/13.
 */
public abstract class ShareOnlineMusic {
    private Context mContext;
    private String mTitle;
    private String mSongId;

    public ShareOnlineMusic(Context context, String title, String songId) {
        mContext = context;
        mTitle = title;
        mSongId = songId;
    }

    public void execute() {
        share();
    }

    private void share() {
        onPrepare();
        // 获取歌曲播放链接
        ApiService.getInstance()
                .apiManager
                .getSongDownloadInfo(mSongId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(data -> {
                    onSuccess();
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setType("text/plain");
                    intent.putExtra(Intent.EXTRA_TEXT, mContext.getString(R.string.share_music, mContext.getString(R.string.app_name),
                            mTitle, data.getBitrate().getFile_link()));
                    mContext.startActivity(Intent.createChooser(intent, mContext.getString(R.string.share)));
                });
    }

    public abstract void onPrepare();

    public abstract void onSuccess();

    public abstract void onFail(Call call, Exception e);
}
