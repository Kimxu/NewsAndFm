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
import kimxu.bdyy.hotplaylist.ListHostPlay;
import kimxu.newsandfm.R;

/**
 * Created by kimxu on 16/1/13.
 */

public class HotPlaylistFactory extends AssemblyItemFactory<HotPlaylistFactory.HotPlaylistItem> {
    private Activity mActivity;
    private TextView tvTitle;
    private TextView tvSum;
    private ImageView ivAblum;
    public HotPlaylistFactory(Activity mActivity) {
        this.mActivity = mActivity;
    }

    @Override
    public Class<ListHostPlay> getBeanClass() {
        return ListHostPlay.class;
    }

    @Override
    public HotPlaylistItem createAssemblyItem(ViewGroup parent) {
        return new HotPlaylistItem(parent,this);
    }

    public class HotPlaylistItem extends AssemblyItem<ListHostPlay, HotPlaylistFactory> {

        protected HotPlaylistItem(ViewGroup parent, HotPlaylistFactory itemFactory) {
            super(LayoutInflater.from(parent.getContext()).inflate(R.layout.glist_item_hot_playlist,parent,false), itemFactory);
        }

        @Override
        protected void onFindViews(View convertView) {
            tvTitle= (TextView) convertView.findViewById(R.id.tv_itemHotPlaylist_title);
            tvSum= (TextView) convertView.findViewById(R.id.tv_itemHotPlaylist_sum);
            ivAblum= (ImageView) convertView.findViewById(R.id.iv_itemHotPlaylist);
        }

        @Override
        protected void onConfigViews(Context context) {

        }

        @Override
        protected void onSetData(int position, ListHostPlay hotPlaylist) {
            tvTitle.setText(hotPlaylist.getTitle());
            tvSum.setText(hotPlaylist.getListenum());
            Picasso.with(mActivity).load(hotPlaylist.getPic()).into(ivAblum);

        }
    }
}
