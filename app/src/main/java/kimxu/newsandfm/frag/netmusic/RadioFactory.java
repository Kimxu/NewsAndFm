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
import kimxu.bdyy.radio.ListRadio;
import kimxu.newsandfm.R;

/**
 * 电台
 * Created by kimxu on 16/1/13.
 */

public class RadioFactory extends AssemblyItemFactory<RadioFactory.HotPlaylistItem> {
    private Activity mActivity;
    private TextView tvTitle;
    private TextView tvSum;
    private ImageView ivAblum;
    public RadioFactory(Activity mActivity) {
        this.mActivity = mActivity;
    }

    @Override
    public Class<ListRadio> getBeanClass() {
        return ListRadio.class;
    }

    @Override
    public HotPlaylistItem createAssemblyItem(ViewGroup parent) {
        return new HotPlaylistItem(parent,this);
    }

    public class HotPlaylistItem extends AssemblyItem<ListRadio, RadioFactory> {

        protected HotPlaylistItem(ViewGroup parent, RadioFactory itemFactory) {
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
        protected void onSetData(int position, ListRadio radio) {
            tvTitle.setText(radio.getTitle());
            tvSum.setText(radio.getDesc());
            Picasso.with(mActivity).load(radio.getPic()).into(ivAblum);

        }
    }
}
