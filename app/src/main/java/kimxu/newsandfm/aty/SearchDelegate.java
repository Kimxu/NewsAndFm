package kimxu.newsandfm.aty;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import kimxu.mvp.view.AppAtyDelegate;
import kimxu.newsandfm.R;
import kimxu.newsandfm.widget.MultipleTextViewGroup;

/**
 * 视图View
 * Created by xuzhiguo on 15/11/23.
 */
public class SearchDelegate extends AppAtyDelegate {

    EditText mEtSearch;
    ImageView mIvSearch;
    ListView mListView;
    MultipleTextViewGroup mTGRecommend;
    @Override
    public void create(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.create(inflater, container, savedInstanceState);
    }

    @Override
    public int getRootLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    public void initWidget() {
        mEtSearch = get(R.id.eT_appSearchToolBar);
        mIvSearch = get(R.id.iV_appSearchToolBar);
        mListView=get(R.id.lV_atySearch);
        mTGRecommend=get(R.id.mTG_AtyMain);
    }

    @Override
    public Toolbar getToolbar() {
        return get(R.id.tBar_atySearch);
    }

}
