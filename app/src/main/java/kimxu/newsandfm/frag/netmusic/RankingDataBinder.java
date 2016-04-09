package kimxu.newsandfm.frag.netmusic;

import android.support.v4.app.FragmentActivity;

import kimxu.adapter.AssemblyAdapter;
import kimxu.bdyy.ranking.Ranking;
import kimxu.mvp.databind.DataBinder;
import kimxu.newsandfm.adapter.factory.RankingFactory;

/**
 * Created by kimxu on 16/1/8.
 */

public class RankingDataBinder implements DataBinder<NewMusicDelegate, Ranking> {
    private FragmentActivity mActivity;

    public RankingDataBinder(FragmentActivity mActivity) {
        this.mActivity = mActivity;
    }

    @Override
    public void viewBindModel(NewMusicDelegate viewDelegate, Ranking data) {
        AssemblyAdapter adapter = new AssemblyAdapter(data.getContent());
        adapter.addItemFactory(new RankingFactory(mActivity));
        viewDelegate.mListView.setAdapter(adapter);
    }
}