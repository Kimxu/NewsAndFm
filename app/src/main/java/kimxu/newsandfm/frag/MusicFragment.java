package kimxu.newsandfm.frag;

import android.os.Bundle;
import android.view.View;

import kimxu.newsandfm.KBaseFragment;
import kimxu.newsandfm.R;
import kimxu.newsandfm.aty.LocalMusicActivity;

/**
 * 新闻
 */
public class MusicFragment extends KBaseFragment<MusicDelegate> implements View.OnClickListener{

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    public static MusicFragment newInstance(String param1, String param2) {
        MusicFragment fragment = new MusicFragment();
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
        viewDelegate.setOnClickListener(this,R.id.netMusic_fragMusic,R.id.localMusic_fragMusic,R.id.load_fragMusic);
    }
    @Override
    protected Class<MusicDelegate> getDelegateClass() {
        return MusicDelegate.class;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.netMusic_fragMusic:
                break;
            case R.id.localMusic_fragMusic:
                LocalMusicActivity.launch(mActivity);
                break;
            case R.id.load_fragMusic:
                break;
        }
    }
}

