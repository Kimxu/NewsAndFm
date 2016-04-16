package me.kimxu.adapter.factory;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import kimxu.adapter.AssemblyItem;
import kimxu.adapter.AssemblyItemFactory;
import kimxu.core.bean.JOnlineMusic;
import me.kimxu.R;
import me.kimxu.utils.FileUtils;

/**
 * 本地音乐
 * Created by kimxu on 15/12/30.
 */

public class OnlineMusicFactory extends AssemblyItemFactory<OnlineMusicFactory.LocalMusicItem> {
    private OnMoreClickListener mListener;
    public OnlineMusicFactory(OnMoreClickListener onClickListener) {
        this.mListener = onClickListener;
    }

    @Override
    public Class<JOnlineMusic> getBeanClass() {
        return JOnlineMusic.class;
    }

    @Override
    public LocalMusicItem createAssemblyItem(ViewGroup parent) {
        return new LocalMusicItem(parent, this);
    }

    public class LocalMusicItem extends AssemblyItem<JOnlineMusic, OnlineMusicFactory> {
        ImageView ivCover;
        TextView tvTitle;
        TextView tvArtist;
        ImageView ivMore;
        private Context mContext;

        protected LocalMusicItem(ViewGroup parent, OnlineMusicFactory itemFactory) {
            super(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_local_music, parent, false), itemFactory);
            mContext=parent.getContext();
        }

        @Override
        protected void onFindViews(View convertView) {
            ivCover = (ImageView) convertView.findViewById(R.id.iv_cover);
            tvTitle = (TextView) convertView.findViewById(R.id.tv_title);
            tvArtist = (TextView) convertView.findViewById(R.id.tv_artist);
            ivMore = (ImageView) convertView.findViewById(R.id.iv_more);
        }

        @Override
        protected void onConfigViews(Context context) {

        }

        @Override
        protected void onSetData(int position, JOnlineMusic music) {
            Picasso.with(mContext).load(music.getPic_small()).into(ivCover);

            tvTitle.setText(music.getTitle());
            String artist = FileUtils.getArtistAndAlbum(music.getArtist_name(), music.getAlbum_title());
            tvArtist.setText(artist);
            ivMore.setOnClickListener(v -> mListener.onMoreClick(position));
        }
    }
    public void setOnMoreClickListener(OnMoreClickListener listener) {
        mListener = listener;
    }
}
