package kimxu.newsandfm.frag.netmusic;

import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bigkoo.convenientbanner.ConvenientBanner;

import kimxu.bdyy.banner.Banner;
import kimxu.bdyy.banner.Pic;
import kimxu.bdyy.hotplaylist.HotPlaylist;
import kimxu.bdyy.kingranking.KingRanking;
import kimxu.bdyy.radio.Radio;
import kimxu.bdyy.recommend.Recommend;
import kimxu.mvp.databind.DataBinder;
import kimxu.mvp.model.IModel;
import kimxu.newsandfm.R;
import kimxu.utils.L;

/**
 * 这个数据是一点一点添加的，所以采用分条的形式添加。
 * Created by kimxu on 16/1/8.
 */

public class RecommendDataBinder implements DataBinder<NewMusicDelegate, IModel> {
    private FragmentActivity mActivity;

    public RecommendDataBinder(FragmentActivity mActivity) {
        this.mActivity = mActivity;
    }

    @Override
    public void viewBindModel(NewMusicDelegate viewDelegate, IModel data) {

        if (data instanceof Banner) {
            Banner banner = (Banner) data;
            View view = LayoutInflater.from(mActivity).inflate(R.layout.list_item_banner, (ViewGroup) mActivity.findViewById(android.R.id.content), false);
            ConvenientBanner<Pic> convenientBanner = (ConvenientBanner) view.findViewById(R.id.cb_recommendFrag_banner);
            convenientBanner.setPages(LocalImageHolderView::new, banner.getPic())
                    .setPageIndicator(new int[]{R.drawable.nf_page_indicator, R.drawable.nf_page_indicator_focused})
                    .setOnItemClickListener(position-> L.d("o"+position));
            ((ViewGroup) mActivity.findViewById(android.R.id.content)).addView(view);
            //viewDelegate. mListView.setAdapter(new ArrayAdapter<Integer>(mActivity,0));
            //viewDelegate.mListView.addHeaderView(view);

        } else if (data instanceof HotPlaylist) {
            HotPlaylist hotPlaylist = (HotPlaylist) data;
            View view = LayoutInflater.from(mActivity).inflate(R.layout.list_item_banner, (ViewGroup) viewDelegate.mListView.getParent(), false);

        } else if (data instanceof Recommend) {
            Recommend recommend = (Recommend) data;
            View view = LayoutInflater.from(mActivity).inflate(R.layout.list_item_banner, (ViewGroup) viewDelegate.mListView.getParent(), false);

        } else if (data instanceof KingRanking) {
            KingRanking kingRanking = (KingRanking) data;
            View view = LayoutInflater.from(mActivity).inflate(R.layout.list_item_banner, (ViewGroup) viewDelegate.mListView.getParent(), false);

        } else if (data instanceof Radio) {
            Radio radio = (Radio) data;
            View view = LayoutInflater.from(mActivity).inflate(R.layout.list_item_banner, (ViewGroup) viewDelegate.mListView.getParent(), false);

        }
    }
}
