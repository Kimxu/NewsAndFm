package me.kimxu.executor;

import android.app.Dialog;
import android.app.DownloadManager;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.TypedValue;
import android.webkit.MimeTypeMap;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import kimxu.core.bean.JOnlineMusic;
import kimxu.core.net.ApiService;
import kimxu.utils.L;
import kimxu.utils.SPUtils;
import me.kimxu.KBaseApplication;
import me.kimxu.R;
import me.kimxu.enums.MusicTypeEnum;
import me.kimxu.model.Music;
import me.kimxu.utils.FileUtils;
import me.kimxu.utils.NetworkUtils;
import okhttp3.Call;
import okhttp3.ResponseBody;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 播放在线音乐
 * Created by wcy on 2016/1/3.
 */
public abstract class PlayOnlineMusic {
    private Context mContext;
    private JOnlineMusic mJOnlineMusic;
    private int mCounter = 0;

    public PlayOnlineMusic(Context context, JOnlineMusic jOnlineMusic) {
        mContext = context;
        mJOnlineMusic = jOnlineMusic;
    }

    public void execute() {
        checkNetwork();
    }

    private void checkNetwork() {
        boolean mobileNetworkPlay = SPUtils.enableMobileNetworkPlay(false);
        if (NetworkUtils.isActiveNetworkMobile(mContext) && !mobileNetworkPlay) {
            AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
            builder.setTitle(R.string.tips);
            builder.setMessage(R.string.play_tips);
            builder.setPositiveButton(R.string.play_tips_sure, (dialog, which) -> {
                SPUtils.saveMobileNetworkPlay(true);
                getPlayInfo();
            });
            builder.setNegativeButton(R.string.cancel, null);
            Dialog dialog = builder.create();
            dialog.setCanceledOnTouchOutside(false);
            dialog.show();
        } else {
            getPlayInfo();
        }
    }

    private void getPlayInfo() {
        onPrepare();
        String lrcFileName = FileUtils.getLrcFileName(mJOnlineMusic.getArtist_name(), mJOnlineMusic.getTitle());
        File lrcFile = new File(FileUtils.getLrcDir() + lrcFileName);
        if (TextUtils.isEmpty(mJOnlineMusic.getLrclink()) || lrcFile.exists()) {
            mCounter++;
        }
        String picUrl = TextUtils.isEmpty(mJOnlineMusic.getPic_big()) ? TextUtils.isEmpty(mJOnlineMusic.getPic_small())
                ? null : mJOnlineMusic.getPic_small() : mJOnlineMusic.getPic_big();
        if (TextUtils.isEmpty(picUrl)) {
            mCounter++;
        }
        final Music music = new Music();
        music.setType(MusicTypeEnum.ONLINE);
        music.setTitle(mJOnlineMusic.getTitle());
        music.setArtist(mJOnlineMusic.getArtist_name());
        music.setAlbum(mJOnlineMusic.getAlbum_title());
        // 获取歌曲播放链接
        ApiService.getInstance()
                .apiManager
                .getSongDownloadInfo(mJOnlineMusic.getSong_id())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                    music.setUri(response.getBitrate().getFile_link());
                    music.setDuration(response.getBitrate().getFile_duration() * 1000);
                    mCounter++;
                    if (mCounter == 3) {
                        onSuccess(music);
                    }

                });
        // 下载歌词
        if (!TextUtils.isEmpty(mJOnlineMusic.getLrclink()) && !lrcFile.exists()) {
            ApiService.getInstance()
                    .apiManager
                    .getSongLrc(mJOnlineMusic.getLrclink())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(response -> {
                        if (response.isSuccessful()) {
                            L.d("server contacted and has file");
                            new Thread(() -> {
                                boolean writtenToDisk = writeResponseBodyToDisk(response.body(), lrcFileName);
                                L.d("file download was a success? " + writtenToDisk);
                            }).start();

                        } else {
                            L.d("server contact failed");
                        }
                    }, error -> {
                        onFail(error.getLocalizedMessage());
                    });
        }
        // 下载歌曲封面
        if (!TextUtils.isEmpty(picUrl)) {

            Picasso.with(mContext).load(picUrl).into(new Target() {
                @Override
                public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                    music.setCover(bitmap);
                    mCounter++;
                    if (mCounter == 3) {
                        onSuccess(music);
                    }
                }

                @Override
                public void onBitmapFailed(Drawable errorDrawable) {

                }

                @Override
                public void onPrepareLoad(Drawable placeHolderDrawable) {

                }
            });
        }
    }

    private boolean writeResponseBodyToDisk(ResponseBody body, String lrcFileName) {
        try {
            // todo change the file location/name according to your needs
            File futureStudioIconFile = new File(FileUtils.getLrcDir(), lrcFileName);

            InputStream inputStream = null;
            OutputStream outputStream = null;
            try {
                byte[] fileReader = new byte[4096];

                long fileSize = body.contentLength();
                long fileSizeDownloaded = 0;

                inputStream = body.byteStream();
                outputStream = new FileOutputStream(futureStudioIconFile);

                while (true) {
                    int read = inputStream.read(fileReader);

                    if (read == -1) {
                        break;
                    }

                    outputStream.write(fileReader, 0, read);

                    fileSizeDownloaded += read;

                    L.d("file download: " + fileSizeDownloaded + " of " + fileSize);
                }

                outputStream.flush();

                return true;
            } catch (IOException e) {
                return false;
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }

                if (outputStream != null) {
                    outputStream.close();
                }
            }
        } catch (IOException e) {
            return false;
        }
    }

    public abstract void onPrepare();

    public abstract void onSuccess(Music music);

    public abstract void onFail(String error);
}
