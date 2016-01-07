package kimxu.newsandfm.frag.play;

import android.os.Bundle;

import kimxu.newsandfm.KBaseFragment;

/**
 * 封面
 * Created by kimxu on 16/1/7.
 */
public class PlayCoverFragment extends KBaseFragment<PlayCoverDelegate> {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public static PlayCoverFragment newInstance(String param1, String param2) {
        PlayCoverFragment fragment = new PlayCoverFragment();
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
    protected Class<PlayCoverDelegate> getDelegateClass() {
        return PlayCoverDelegate.class;
    }

    public void setCover(String picHuge) {
        viewDelegate.setCoverPath(picHuge);
    }
}
