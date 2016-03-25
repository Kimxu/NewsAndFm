package kimxu.newsandfm.aty;

import android.support.v4.app.FragmentActivity;

import kimxu.bdyy.search.searchresult.SearchResult;
import kimxu.mvp.databind.DataBinder;

/**
 * 数据绑定
 * Created by kimxu on 16/1/8.
 */

public class SearchDataBinder implements DataBinder<SearchDelegate, SearchResult> {
    private FragmentActivity mActivity;
    private SearchResult mData;

    public SearchDataBinder(FragmentActivity mActivity) {
        this.mActivity = mActivity;
    }

    @Override
    public void viewBindModel(SearchDelegate viewDelegate, SearchResult data) {
        mData = data;
    }
}

