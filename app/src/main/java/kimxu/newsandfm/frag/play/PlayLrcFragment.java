package kimxu.newsandfm.frag.play;

import android.os.Bundle;

import kimxu.newsandfm.KBaseFragment;

/**
 * 歌词
 * Created by kimxu on 16/1/7.
 */
public class PlayLrcFragment extends KBaseFragment<PlayLrcDelegate> {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public static PlayLrcFragment newInstance(String param1, String param2) {
        PlayLrcFragment fragment = new PlayLrcFragment();
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

    public void setLrc(String path){
        viewDelegate.setLrc(path);
    }
    @Override
    protected Class<PlayLrcDelegate> getDelegateClass() {
        return PlayLrcDelegate.class;
    }

    public void changeCurrent(int progress) {
        viewDelegate.changeCurrent(progress);
    }
}
