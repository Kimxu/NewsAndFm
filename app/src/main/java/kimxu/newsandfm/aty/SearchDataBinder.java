package kimxu.newsandfm.aty;

import android.support.v4.app.FragmentActivity;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import kimxu.adapter.AssemblyAdapter;
import kimxu.bdyy.search.catalogsug.CatalogSug;
import kimxu.bdyy.search.merge.SearchMerge;
import kimxu.bdyy.search.merge.SongList;
import kimxu.mvp.databind.DataBinder;
import kimxu.mvp.model.IModel;
import kimxu.newsandfm.adapter.factory.SearchArtlistFactory;
import kimxu.newsandfm.adapter.factory.SearchFactory;
import kimxu.newsandfm.adapter.factory.SearchSongFactory;

/**
 * 数据绑定
 * Created by kimxu on 16/1/8.
 */

public class SearchDataBinder implements DataBinder<SearchDelegate, IModel> {
    private SearchActivity mActivity;

    public SearchDataBinder(SearchActivity mActivity) {
        this.mActivity = mActivity;
    }

    @Override
    public void viewBindModel(SearchDelegate viewDelegate, IModel data) {
        if (data instanceof CatalogSug) {
            setSearchList(viewDelegate, (CatalogSug) data);
        } else if (data instanceof SearchMerge) {
            setSearchResult(viewDelegate, (SearchMerge) data);
        }
    }

    /**
     * 最后搜索的结果显示
     *
     * @param data
     */
    private void setSearchResult(SearchDelegate viewDelegate, SearchMerge data) {
        viewDelegate.mTGRecommend.setVisibility(View.GONE);
        List datas = new ArrayList<>();
        datas.addAll(data.getResult().getSongInfo().getSongList());
        AssemblyAdapter mAdapter = new AssemblyAdapter(datas);
        if (data.getResult().getArtistInfo() != null) {
            datas.addAll(data.getResult().getArtistInfo().getArtistList());
            mAdapter.addItemFactory(new SearchArtlistFactory(mActivity));
        }
        mAdapter.addItemFactory(new SearchSongFactory(mActivity));
        viewDelegate.mListView.setAdapter(mAdapter);
        viewDelegate.mListView.setOnItemClickListener((parent, view, position, id) -> {
            if (datas.get(position) instanceof SongList) {
                //点击的是歌曲,直接播放歌曲
                SongList songList = (SongList) datas.get(position);
                mActivity.getMusicInfo(songList.getSongId());
            } else {
                //点击的是专辑,跳转
            }

        });
    }

    /**
     * 搜索的联动显示
     *
     * @param viewDelegate
     * @param data
     */
    private void setSearchList(SearchDelegate viewDelegate, CatalogSug data) {
        if (data.getSong().size() != 0) {
            viewDelegate.mTGRecommend.setVisibility(View.GONE);
        } else {
            viewDelegate.mTGRecommend.setVisibility(View.VISIBLE);
        }
        AssemblyAdapter mAdapter = new AssemblyAdapter(data.getSong());
        mAdapter.addItemFactory(new SearchFactory(mActivity));
        viewDelegate.mListView.setAdapter(mAdapter);
        viewDelegate.mListView.setOnItemClickListener((parent, view, position, id) -> {
            String query = data.getSong().get(position).getSongname();
            mActivity.getResultList(query);
        });
    }


}

