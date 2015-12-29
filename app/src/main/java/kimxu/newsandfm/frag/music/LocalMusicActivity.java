package kimxu.newsandfm.frag.music;


import android.app.Activity;
import android.content.Intent;

import kimxu.mvp.databind.DataBinder;
import kimxu.newsandfm.KBaseSwipeBackActivity;

/**
 * 本地音乐
 * Created by kimxu on 15/12/29.
 */
public class LocalMusicActivity extends KBaseSwipeBackActivity<LocalMusicFDelegate> {

    @Override
    protected void bindEvenListener() {

    }
    @Override
    protected Class<LocalMusicFDelegate> getDelegateClass() {
        return LocalMusicFDelegate.class;
    }

    @Override
    public DataBinder getDataBinder() {
        return null;
    }

    public static void launch(Activity activity){
        activity.startActivity(new Intent(activity,LocalMusicActivity.class));
    }
}
