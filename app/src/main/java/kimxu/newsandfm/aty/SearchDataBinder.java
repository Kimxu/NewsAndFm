package kimxu.newsandfm.aty;

import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.AdapterView;

import kimxu.adapter.AssemblyAdapter;
import kimxu.bdyy.search.catalogsug.CatalogSug;
import kimxu.mvp.databind.DataBinder;

/**
 * 数据绑定
 * Created by kimxu on 16/1/8.
 */

public class SearchDataBinder implements DataBinder<SearchDelegate, CatalogSug> {
    private FragmentActivity mActivity;

    public SearchDataBinder(FragmentActivity mActivity) {
        this.mActivity = mActivity;
    }

    @Override
    public void viewBindModel(SearchDelegate viewDelegate, CatalogSug data) {
        if (data.getSong().size() != 0){
            viewDelegate.mTGRecommend.setVisibility(View.GONE);
        }else{
            viewDelegate.mTGRecommend.setVisibility(View.VISIBLE);
        }
        AssemblyAdapter mAdapter =new AssemblyAdapter(data.getSong());
        mAdapter.addItemFactory(new SearchFactory(mActivity));
        viewDelegate.mListView.setAdapter(mAdapter);
        viewDelegate.mListView.setOnItemClickListener((parent, view, position, id) -> {
            String query =data.getSong().get(position).getSongname();
        });
    }


}

