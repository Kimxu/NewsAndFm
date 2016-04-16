package me.kimxu.adapter.factory;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import kimxu.adapter.AssemblyItem;
import kimxu.adapter.AssemblyItemFactory;
import me.kimxu.R;
import me.kimxu.aty.MusicActivity;
import me.kimxu.enums.MusicTypeEnum;
import me.kimxu.model.Music;
import me.kimxu.service.PlayService;
import me.kimxu.utils.CoverLoader;
import me.kimxu.utils.FileUtils;

/**
 * 本地音乐
 * Created by kimxu on 15/12/30.
 */

public class LocalMusicFactory extends AssemblyItemFactory<LocalMusicFactory.LocalMusicItem> {
    private OnMoreClickListener mListener;
    private int mPlayingPosition;

    public LocalMusicFactory(OnMoreClickListener onClickListener) {
        this.mListener = onClickListener;
    }

    @Override
    public Class<Music> getBeanClass() {
        return Music.class;
    }

    @Override
    public LocalMusicItem createAssemblyItem(ViewGroup parent) {
        return new LocalMusicItem(parent, this);
    }

    public class LocalMusicItem extends AssemblyItem<Music, LocalMusicFactory> {
        ImageView ivPlaying;
        ImageView ivCover;
        TextView tvTitle;
        TextView tvArtist;
        ImageView ivMore;


        protected LocalMusicItem(ViewGroup parent, LocalMusicFactory itemFactory) {
            super(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_local_music, parent, false), itemFactory);
        }

        @Override
        protected void onFindViews(View convertView) {
            ivPlaying = (ImageView) convertView.findViewById(R.id.iv_playing);
            ivCover = (ImageView) convertView.findViewById(R.id.iv_cover);
            tvTitle = (TextView) convertView.findViewById(R.id.tv_title);
            tvArtist = (TextView) convertView.findViewById(R.id.tv_artist);
            ivMore = (ImageView) convertView.findViewById(R.id.iv_more);
        }

        @Override
        protected void onConfigViews(Context context) {
            PlayService playService = ((MusicActivity) context).getPlayService();
            if (playService.getPlayingMusic() != null && playService.getPlayingMusic().getType() == MusicTypeEnum.LOCAL) {
                mPlayingPosition = playService.getPlayingPosition();
            } else {
                mPlayingPosition = -1;
            }
        }

        @Override
        protected void onSetData(int position, Music music) {
            Bitmap cover = CoverLoader.getInstance().loadThumbnail(music.getCoverUri());
            ivCover.setImageBitmap(cover);
            tvTitle.setText(music.getTitle());
            String artist = FileUtils.getArtistAndAlbum(music.getArtist(), music.getAlbum());
            tvArtist.setText(artist);
            ivMore.setOnClickListener(v -> {
                if (mListener != null) {
                    mListener.onMoreClick(position);
                }
            });
            if (position == mPlayingPosition) {
                ivPlaying.setVisibility(View.VISIBLE);
            } else {
                ivPlaying.setVisibility(View.INVISIBLE);
            }
        }


    }
}
