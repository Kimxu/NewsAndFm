package kimxu.newsandfm.frag.netmusic;
import android.os.Bundle;

import kimxu.mvp.databind.DataBinder;
import kimxu.newsandfm.KBaseFragment;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 *网络音乐排行
 */
public class RankingFragment extends KBaseFragment<NewMusicDelegate>{
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    public static RankingFragment newInstance(String param1, String param2) {
        RankingFragment fragment = new RankingFragment();
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
        mApiService.apiBdyyManager
                .getRanking()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::notifyModelChanged);
    }

    @Override
    protected Class<NewMusicDelegate> getDelegateClass() {
        return NewMusicDelegate.class;
    }

    @Override
    public DataBinder getDataBinder() {
        return new RankingDataBinder(mActivity);
    }

}
