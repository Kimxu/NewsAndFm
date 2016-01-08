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
import kimxu.bdyy.playlist.PlaylistContent;
import kimxu.newsandfm.R;

/**
 * 歌单
 * Created by kimxu on 16/1/8.
 */

public class PlayListFactory extends AssemblyItemFactory<PlayListFactory.PlaylistItem> {
    private Activity mActivity;

    public PlayListFactory(Activity mActivity) {
        this.mActivity = mActivity;
    }

    @Override
    public Class<PlaylistContent> getBeanClass() {
        return PlaylistContent.class;
    }

    @Override
    public PlaylistItem createAssemblyItem(ViewGroup parent) {
        return new PlaylistItem(parent, this);
    }

    public class PlaylistItem extends AssemblyItem<PlaylistContent, PlayListFactory> {
        private ImageView ivPic;
        private TextView tvTitle;
        private TextView tvTag;

        protected PlaylistItem(ViewGroup parent, PlayListFactory itemFactory) {
            super(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_playlist, parent, false), itemFactory);
        }

        @Override
        protected void onFindViews(View convertView) {
            ivPic = (ImageView) convertView.findViewById(R.id.iv_itemPlaylist);
            tvTitle = (TextView) convertView.findViewById(R.id.tv_itemPlaylist_title);
            tvTag = (TextView) convertView.findViewById(R.id.tv_itemPlaylist_tag);
        }

        @Override
        protected void onConfigViews(Context context) {

        }

        @Override
        protected void onSetData(int position, PlaylistContent play) {
            Picasso.with(mActivity).load(play.getPic300()).into(ivPic);
            tvTitle.setText(play.getTitle());
            tvTag.setText(play.getTag());

        }
    }
}
