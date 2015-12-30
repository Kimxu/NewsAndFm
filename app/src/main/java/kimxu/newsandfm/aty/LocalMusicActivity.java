package kimxu.newsandfm.aty;


import android.app.Activity;
import android.content.Intent;

import java.util.ArrayList;

import kimxu.adapter.AssemblyAdapter;
import kimxu.mvp.databind.DataBinder;
import kimxu.newsandfm.KBaseSwipeBackActivity;
import kimxu.newsandfm.model.Audio;
import kimxu.newsandfm.utils.MediaUtils;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 本地音乐
 * Created by kimxu on 15/12/29.
 */
public class LocalMusicActivity extends KBaseSwipeBackActivity<LocalMusicFDelegate> {

    @Override
    protected void bindEvenListener() {
        Observable.create((Observable.OnSubscribe<ArrayList<Audio>>) subscriber -> {

           subscriber.onNext(MediaUtils.getAudioList(mActivity));
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((ArrayList<Audio> audios) -> {
                    AssemblyAdapter adapter = new AssemblyAdapter(audios);
                    adapter.addItemFactory(new LocalMusicFactory(position -> {
                        MusicPlayerActivity.launch(mActivity,audios,position);
                    }));
                    viewDelegate.setAdapter(adapter);
                    viewDelegate.hiddenHintView();
                });
    }

    @Override
    protected Class<LocalMusicFDelegate> getDelegateClass() {
        return LocalMusicFDelegate.class;
    }

    @Override
    public DataBinder getDataBinder() {
        return null;
    }

    public static void launch(Activity activity) {
        activity.startActivity(new Intent(activity, LocalMusicActivity.class));
    }
}
