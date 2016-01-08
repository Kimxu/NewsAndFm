package kimxu.newsandfm.frag.netmusic;
import android.os.Bundle;

import kimxu.mvp.databind.DataBinder;
import kimxu.newsandfm.KBaseFragment;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 *网络音乐歌单
 */
public class PlayListFragment extends KBaseFragment<PlaylistDelegate>{
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    public static PlayListFragment newInstance(String param1, String param2) {
        PlayListFragment fragment = new PlayListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void bindEvenListener() {
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        mApiService
                .apiBdyyManager
                .getPlaylist()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::notifyModelChanged);
    }

    @Override
    protected Class<PlaylistDelegate> getDelegateClass() {
        return PlaylistDelegate.class;
    }

    @Override
    public DataBinder getDataBinder() {
        return new PlayListDataBinder(mActivity);
    }

}
