package me.kimxu.frag;

import android.os.Bundle;

import me.kimxu.KBaseFragment;

/**
 * Fm电台
 */
public class FMFragment extends KBaseFragment<FMDelegate> {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;


    public static FMFragment newInstance(String param1, String param2) {
        FMFragment fragment = new FMFragment();
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
    protected Class<FMDelegate> getDelegateClass() {
        return FMDelegate.class;
    }

}
