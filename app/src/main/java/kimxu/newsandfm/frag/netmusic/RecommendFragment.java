package kimxu.newsandfm.frag.netmusic;
import android.os.Bundle;

import kimxu.mvp.databind.DataBinder;
import kimxu.newsandfm.KBaseFragment;

/**
 *网络音乐推荐
 */
public class RecommendFragment extends KBaseFragment<NewMusicDelegate>{
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    public static RecommendFragment newInstance(String param1, String param2) {
        RecommendFragment fragment = new RecommendFragment();
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
    }

    @Override
    protected Class<NewMusicDelegate> getDelegateClass() {
        return NewMusicDelegate.class;
    }

    @Override
    public DataBinder getDataBinder() {
        return new RecommendDataBinder(mActivity);
    }

}
