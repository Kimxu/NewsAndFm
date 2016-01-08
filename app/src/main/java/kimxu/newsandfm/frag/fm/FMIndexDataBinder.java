package kimxu.newsandfm.frag.fm;

import android.support.v4.app.FragmentActivity;

import java.util.ArrayList;
import java.util.List;

import kimxu.adapter.AssemblyAdapter;
import kimxu.mvp.databind.DataBinder;
import kimxu.xmly.discoverRecommend.DiscoverRecommend;

/**
 * Created by kimxu on 16/1/8.
 */
public class FMIndexDataBinder implements DataBinder<FMIndexDelegate,DiscoverRecommend>{
    private FragmentActivity mActivity;
    public FMIndexDataBinder(FragmentActivity activity){
        mActivity=activity;
    }
    @Override
    public void viewBindModel(FMIndexDelegate viewDelegate, DiscoverRecommend discoverRecommend) {
        List<Object> list =new ArrayList<>();
        list.addAll(discoverRecommend.getDiscoveryColumns().getList());
        list.addAll(discoverRecommend.getEditorRecommendAlbums().getList());
        list.addAll(discoverRecommend.getSpecialColumn().getList());
        AssemblyAdapter mAdapter =new AssemblyAdapter(list);
        mAdapter.addItemFactory(new AlbumFactory(mActivity));
        mAdapter.addItemFactory(new DiscoveryFactory(mActivity));
        mAdapter.addItemFactory(new SpecialFactory(mActivity));
        viewDelegate.setAdapter(mAdapter);
    }
}
