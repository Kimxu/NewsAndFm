package kimxu.newsandfm.frag.fm;


import android.os.Bundle;
import android.os.Message;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import kimxu.adapter.AssemblyAdapter;
import kimxu.mvp.databind.DataBinder;
import kimxu.newsandfm.KBaseFragment;
import kimxu.utils.L;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 *电台主页
 */
public class FMIndexFragment extends KBaseFragment<FMIndexDelegate>{
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    private AssemblyAdapter mAdapter;


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
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        LinkedHashMap<String,String> map =new LinkedHashMap<>();
        mApiService.getDiscoverRecommend(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(discoverRecommend -> {
                    List<Object> list =new ArrayList<>();
                    list.addAll(discoverRecommend.getDiscoveryColumns().getList());
                    list.addAll(discoverRecommend.getEditorRecommendAlbums().getList());
                    list.addAll(discoverRecommend.getSpecialColumn().getList());
                    mAdapter =new AssemblyAdapter(list);
                    mAdapter.addItemFactory(new AlbumFactory(mActivity));
                    mAdapter.addItemFactory(new DiscoveryFactory(mActivity));
                    mAdapter.addItemFactory(new SpecialFactory(mActivity));
                    viewDelegate.setAdapter(mAdapter);
                }, throwable -> L.e("error"+throwable.getMessage()));



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
