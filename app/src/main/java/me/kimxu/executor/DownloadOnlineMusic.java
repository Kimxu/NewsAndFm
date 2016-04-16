package me.kimxu.executor;

import android.app.Dialog;
import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.webkit.MimeTypeMap;


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
import me.kimxu.utils.FileUtils;
import me.kimxu.utils.NetworkUtils;
import okhttp3.Call;
import okhttp3.ResponseBody;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 下载音乐
 * Created by wcy on 2016/1/3.
 */
public abstract class DownloadOnlineMusic {
    private Context mContext;
    private JOnlineMusic mJOnlineMusic;

    public DownloadOnlineMusic(Context context, JOnlineMusic jOnlineMusic) {
        mContext = context;
        mJOnlineMusic = jOnlineMusic;
    }

    public void execute() {
        checkNetwork();
    }

    private void checkNetwork() {
        boolean mobileNetworkDownload = SPUtils.enableMobileNetworkDownload(false);
        if (NetworkUtils.isActiveNetworkMobile(mContext) && !mobileNetworkDownload) {
            AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
            builder.setTitle(R.string.tips);
            builder.setMessage(R.string.download_tips);
            builder.setPositiveButton(R.string.download_tips_sure, (dialog, which) -> {
                download();
            });
            builder.setNegativeButton(R.string.cancel, null);
            Dialog dialog = builder.create();
            dialog.setCanceledOnTouchOutside(false);
            dialog.show();
        } else {
            download();
        }
    }

    private void download() {
        onPrepare();
        final DownloadManager downloadManager = (DownloadManager) mContext.getSystemService(Context.DOWNLOAD_SERVICE);
        // 获取歌曲下载链接
        ApiService.getInstance()
                .apiManager
                .getSongDownloadInfo(mJOnlineMusic.getSong_id())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(data -> {
                    Uri uri = Uri.parse(data.getBitrate().getFile_link());
                    DownloadManager.Request request = new DownloadManager.Request(uri);
                    String mp3FileName = FileUtils.getMp3FileName(mJOnlineMusic.getArtist_name(), mJOnlineMusic.getTitle());
                    request.setDestinationInExternalPublicDir(FileUtils.getRelativeMusicDir(), mp3FileName);
                    request.setMimeType(MimeTypeMap.getFileExtensionFromUrl(data.getBitrate().getFile_link()));
                    request.allowScanningByMediaScanner();
                    request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE | DownloadManager.Request.NETWORK_WIFI);
                    request.setAllowedOverRoaming(false);// 不允许漫游
                    long id = downloadManager.enqueue(request);
                    KBaseApplication.getInstance().getDownloadList().put(id, mJOnlineMusic.getTitle());
                    onSuccess();
                });
        // 下载歌词
        String lrcFileName = FileUtils.getLrcFileName(mJOnlineMusic.getArtist_name(), mJOnlineMusic.getTitle());
        File lrcFile = new File(FileUtils.getLrcDir() + lrcFileName);
        if (!TextUtils.isEmpty(mJOnlineMusic.getLrclink()) && !lrcFile.exists()) {
            ApiService.getInstance()
                    .apiManager
                    .getSongLrc(mJOnlineMusic.getLrclink())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(response -> {
                        if (response.isSuccessful()) {
                            L.d("server contacted and has file");
                            boolean writtenToDisk = writeResponseBodyToDisk(response.body(),lrcFileName);
                            L.d("file download was a success? " + writtenToDisk);
                        } else {
                            L.d("server contact failed");
                        }
                    },error->{
                        onFail(error.getLocalizedMessage());
                    });
        }
    }
    private boolean writeResponseBodyToDisk(ResponseBody body,String lrcFileName) {
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

    public abstract void onSuccess();

    public abstract void onFail(String e);
}
