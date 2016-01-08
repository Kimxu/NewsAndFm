package kimxu.newsandfm.frag.netmusic;

import android.support.v4.app.FragmentActivity;

import kimxu.bdyy.ranking.Ranking;
import kimxu.mvp.databind.DataBinder;

/**
 * Created by kimxu on 16/1/8.
 */

public class RecommendDataBinder implements DataBinder<NewMusicDelegate,Ranking> {
    private FragmentActivity mActivity;

    public RecommendDataBinder(FragmentActivity mActivity) {
        this.mActivity = mActivity;
    }

    @Override
    public void viewBindModel(NewMusicDelegate viewDelegate, Ranking data){

    }
}
