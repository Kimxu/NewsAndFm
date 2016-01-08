package kimxu.newsandfm.frag.netmusic;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import kimxu.adapter.AssemblyItem;
import kimxu.adapter.AssemblyItemFactory;
import kimxu.bdyy.ranking.RankingContent;
import kimxu.newsandfm.R;

/**
 * 歌单
 * Created by kimxu on 16/1/8.
 */

public class RankingFactory extends AssemblyItemFactory<RankingFactory.RankingItem> {
    private Activity mActivity;

    public RankingFactory(Activity mActivity) {
        this.mActivity = mActivity;
    }

    @Override
    public Class<RankingContent> getBeanClass() {
        return RankingContent.class;
    }

    @Override
    public RankingItem createAssemblyItem(ViewGroup parent) {
        return new RankingItem(parent, this);
    }

    public class RankingItem extends AssemblyItem<RankingContent, RankingFactory> {
        private ImageView ivPic;
        private TextView tvTitle;
        private TextView tvSong1;
        private TextView tvSong2;
        private TextView tvSong3;

        protected RankingItem(ViewGroup parent, RankingFactory itemFactory) {
            super(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_ranking, parent, false), itemFactory);
        }

        @Override
        protected void onFindViews(View convertView) {
            ivPic= (ImageView) convertView.findViewById(R.id.iv_itemRanking);
            tvTitle= (TextView) convertView.findViewById(R.id.tv_itemRanking_title);
            tvSong1= (TextView) convertView.findViewById(R.id.tv_itemRanking_song_1);
            tvSong2= (TextView) convertView.findViewById(R.id.tv_itemRanking_song_2);
            tvSong3= (TextView) convertView.findViewById(R.id.tv_itemRanking_song_3);
        }

        @Override
        protected void onConfigViews(Context context) {

        }

        @Override
        protected void onSetData(int position, RankingContent rank) {
            Picasso.with(mActivity).load(rank.getPicS192()).into(ivPic);
            tvTitle.setText(rank.getName());
            tvSong1.setText(rank.getContent().get(0).getTitle());
            tvSong2.setText(rank.getContent().get(1).getTitle());
            tvSong3.setText(rank.getContent().get(2).getTitle());
        }
    }
}
