package kimxu.newsandfm.frag;

import android.os.Bundle;
import android.os.Message;

import kimxu.mvp.databind.DataBinder;
import kimxu.newsandfm.KBaseFragment;

/**
 * Fm电台
 */
public class FMFragment extends KBaseFragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    @Override
    protected void handleErrorMessage(Message msg) {

    }

    @Override
    protected void handleSuccessMessage(Message msg) {

    }

    public static FMFragment newInstance(String param1, String param2) {
        FMFragment fragment = new FMFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public FMFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }



    @Override
    protected void bindEvenListener() {

    }

    @Override
    protected Class getDelegateClass() {
        return FMFDelegate.class;
    }

    @Override
    public DataBinder getDataBinder() {
        return null;
    }
}
