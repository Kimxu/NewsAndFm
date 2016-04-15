package me.kimxu.aty;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.IBinder;
import android.text.TextUtils;

import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;

import java.io.File;

import kimxu.core.net.ApiService;
import kimxu.utils.Constants;
import kimxu.utils.SPUtils;
import me.kimxu.KBaseActivity;
import me.kimxu.delegate.SplashDelegate;
import me.kimxu.service.PlayService;
import me.kimxu.utils.SystemUtils;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class SplashActivity extends KBaseActivity<SplashDelegate> {
    private ServiceConnection mPlayServiceConnection;
    private Handler mHandler;

    @Override
    protected void bindEvenListener() {
        mHandler = new Handler();
        initSplash();
        parseIntent();
    }

    protected Class<SplashDelegate> getDelegateClass() {
        return SplashDelegate.class;
    }


    @Override
    protected void onDestroy() {
        if (mPlayServiceConnection != null) {
            unbindService(mPlayServiceConnection);
        }
        super.onDestroy();
    }

    private void parseIntent() {
        if (SystemUtils.isStackResumed(this)) {
            //startMusicActivity();
            finish();
            return;
        }

        startService();
        initSplash();
        updateSplash();

        mHandler.postDelayed(this::bindService, 1000);
    }

    /**
     * 获得广告图片
     */
    private void updateSplash() {
        ApiService.getInstance()
                .apiManager
                .updateSplashAd()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(data -> {
                    String lastImgUrl = (String) SPUtils.get(Constants.SP_SPLASH_AD, "");
                    if (lastImgUrl.equals(data.getImg())) {
                        return;
                    }
                    //获取图片存储到本地
                    SPUtils.put(Constants.SP_SPLASH_AD, data.getImg());
                    Picasso.with(mActivity).load(data.getImg()).fetch();
                });
    }

    private void initSplash() {
        String imgUrl = (String) SPUtils.get(Constants.SP_SPLASH_AD, "");
        if (!TextUtils.isEmpty(imgUrl)) {
            Picasso.with(mActivity).load(imgUrl).into(viewDelegate.ivSplash);
        }
    }

    private void startService() {
        Intent intent = new Intent();
        intent.setClass(this, PlayService.class);
        startService(intent);
    }

    private void bindService() {
        mPlayServiceConnection = new PlayServiceConnection();
        Intent intent = new Intent();
        intent.setClass(this, PlayService.class);
        bindService(intent, mPlayServiceConnection, Context.BIND_AUTO_CREATE);
    }

    class PlayServiceConnection implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            PlayService playService = ((PlayService.PlayBinder) service).getService();
            playService.updateMusicList();
            //startMusicActivity();
            finish();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
        }
    }

}
