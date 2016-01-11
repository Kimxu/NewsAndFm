package kimxu.newsandfm.frag.netmusic;

import android.support.v4.app.FragmentActivity;

import kimxu.bdyy.banner.Banner;
import kimxu.bdyy.hotplaylist.HotPlaylist;
import kimxu.bdyy.kingranking.KingRanking;
import kimxu.bdyy.radio.Radio;
import kimxu.bdyy.recommend.Recommend;
import kimxu.mvp.databind.DataBinder;
import kimxu.mvp.model.IModel;

/**
 * 这个数据是一点一点添加的，所以采用分条的形式添加。
 * Created by kimxu on 16/1/8.
 */

public class RecommendDataBinder implements DataBinder<NewMusicDelegate,IModel> {
    private FragmentActivity mActivity;

    public RecommendDataBinder(FragmentActivity mActivity) {
        this.mActivity = mActivity;
    }

    @Override
    public void viewBindModel(NewMusicDelegate viewDelegate, IModel data){

        if (data instanceof Banner){

        }else if (data instanceof HotPlaylist){

        }else if (data instanceof Recommend){

        }else if (data instanceof KingRanking){

        }else if (data instanceof Radio){

        }
        //viewDelegate.mListView.addView();
    }
}
