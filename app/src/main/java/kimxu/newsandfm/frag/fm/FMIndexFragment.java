package kimxu.newsandfm.frag.fm;


import android.os.Bundle;
import android.os.Message;

import kimxu.mvp.databind.DataBinder;
import kimxu.newsandfm.KBaseFragment;

/**
 *电台主页
 */
public class FMIndexFragment extends KBaseFragment<FMIndexDelegate> {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;


    public FMIndexFragment() {}
    public static FMIndexFragment newInstance(String param1, String param2) {
        FMIndexFragment fragment = new FMIndexFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected void handleErrorMessage(Message msg) {

    }

    @Override
    protected void handleSuccessMessage(Message msg) {

    }
    @Override
    protected void bindEvenListener() {
        if (getArguments() != null) {

//
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//
//            HashMap<String,String> map =new HashMap<>();
//            map.put("channel","and-f6");
//            mApiService.getDiscoverRecommend(map)
//                    .observeOn(Schedulers.computation())
//                    .subscribeOn(AndroidSchedulers.mainThread())
//                    .subscribe(fmDiscovery -> L.e(fmDiscovery.getMsg()+" okoko"),throwable -> L.e("error"));
        }
    }
    @Override
    protected Class<FMIndexDelegate> getDelegateClass() {
        return FMIndexDelegate.class;
    }

    @Override
    public DataBinder getDataBinder() {
        return null;
    }
}
