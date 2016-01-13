package kimxu.newsandfm.frag.netmusic;

import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.squareup.picasso.Picasso;

import java.util.List;

import kimxu.adapter.AssemblyAdapter;
import kimxu.bdyy.banner.Banner;
import kimxu.bdyy.banner.Pic;
import kimxu.bdyy.hotplaylist.HotPlaylist;
import kimxu.bdyy.kingranking.KingRanking;
import kimxu.bdyy.radio.Radio;
import kimxu.bdyy.recommend.Recommend;
import kimxu.bdyy.recommend.SongList;
import kimxu.mvp.databind.DataBinder;
import kimxu.mvp.model.IModel;
import kimxu.newsandfm.R;
import kimxu.newsandfm.utils.GlobalUtils;
import kimxu.newsandfm.widget.ItemShow;
import kimxu.utils.L;

/**
 * 这个数据是一点一点添加的，所以采用分条的形式添加。
 * Created by kimxu on 16/1/8.
 */

public class RecommendDataBinder implements DataBinder<RecommendDelegate, IModel> {
    private FragmentActivity mActivity;

    public RecommendDataBinder(FragmentActivity mActivity) {
        this.mActivity = mActivity;
    }

    @Override
    public void viewBindModel(RecommendDelegate viewDelegate, IModel data) {
        if (data instanceof Banner) {
            Banner banner = (Banner) data;
            View view = LayoutInflater.from(mActivity).inflate(R.layout.list_item_banner, viewDelegate.mLLayout, false);
            ConvenientBanner<Pic> convenientBanner = (ConvenientBanner) view.findViewById(R.id.cb_recommendFrag_banner);
            convenientBanner.setPages(LocalImageHolderView::new, banner.getPic())
                    .setPageIndicator(new int[]{R.drawable.nf_page_indicator, R.drawable.nf_page_indicator_focused})
                    .setOnItemClickListener(position -> L.d("o" + position));
            viewDelegate.mLLayout.addView(convenientBanner, 0);
        } else if (data instanceof HotPlaylist) {
            HotPlaylist hotPlaylist = (HotPlaylist) data;
            View view = LayoutInflater.from(mActivity).inflate(R.layout.list_item_gridview, viewDelegate.mLLayout, false);
            ((TextView) view.findViewById(R.id.tv_itemGridView_title)).setText("热门歌单");
            GridView gridView = (GridView) view.findViewById(R.id.glist_itemGridView);
            AssemblyAdapter adapter = new AssemblyAdapter(hotPlaylist.getContent().getList());
            adapter.addItemFactory(new HotPlaylistFactory(mActivity));
            gridView.setAdapter(adapter);
            viewDelegate.mLLayout.addView(view);
        } else if (data instanceof Recommend) {
            Recommend recommend = (Recommend) data;
            View view = LayoutInflater.from(mActivity).inflate(R.layout.list_item_play_recommend, viewDelegate.mLLayout, false);
            List<SongList> songList = recommend.getContent().get(0).getSongList();
            for (int i = 0; i < songList.size(); i++) {
                ItemShow itemShow = (ItemShow) GlobalUtils.findView(view, "recommend_item_" + (i + 1));

                Picasso.with(mActivity).load(songList.get(i).getPicBig()).into(itemShow.ivAlbum);
                itemShow.tvTitle.setText(songList.get(i).getTitle());
                itemShow.tvDesc.setText(songList.get(i).getDesc());
                if (i == 5)
                    break;
            }
            viewDelegate.mLLayout.addView(view);
        } else if (data instanceof KingRanking) {
            KingRanking kingRanking = (KingRanking) data;
            View view = LayoutInflater.from(mActivity).inflate(R.layout.list_item_king, viewDelegate.mLLayout, false);
            for (int i = 0; i < kingRanking.getResult().size(); i++) {
                ImageView ivAlbum = (ImageView) GlobalUtils.findView(view, "iv_itemKing_album_" + (i + 1));
                Picasso.with(mActivity).load(kingRanking.getResult().get(i).getPicBig()).into(ivAlbum);
                TextView tvTitle = (TextView) GlobalUtils.findView(view, "tv_itemKing_title_" + (i + 1));
                tvTitle.setText(kingRanking.getResult().get(i).getTitle());
                if (i == 2)
                    break;
            }
            viewDelegate.mLLayout.addView(view);
        } else if (data instanceof Radio) {
            Radio radio = (Radio) data;
            View view = LayoutInflater.from(mActivity).inflate(R.layout.list_item_gridview, viewDelegate.mLLayout, false);
            ((TextView) view.findViewById(R.id.tv_itemGridView_title)).setText("电台节目");
            GridView gridView = (GridView) view.findViewById(R.id.glist_itemGridView);
            AssemblyAdapter adapter = new AssemblyAdapter(radio.getList());
            adapter.addItemFactory(new RadioFactory(mActivity));
            gridView.setAdapter(adapter);
            viewDelegate.mLLayout.addView(view);
        }
        if ( viewDelegate.mLLayout.getChildCount()==5)
            viewDelegate.mHintView.hidden();
    }
}
