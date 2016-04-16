package me.kimxu.adapter.factory;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

import kimxu.adapter.AssemblyItem;
import kimxu.adapter.AssemblyItemFactory;
import kimxu.core.bean.JOnlineMusic;
import kimxu.core.bean.SongListInfo;
import kimxu.core.net.ApiService;
import kimxu.utils.Constants;
import kimxu.utils.SPUtils;
import me.kimxu.R;
import me.kimxu.aty.OnlineMusicActivity;
import me.kimxu.utils.Extras;
import okhttp3.Call;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 本地音乐
 * Created by kimxu on 15/12/30.
 */

public class SongListFactory extends AssemblyItemFactory<SongListFactory.LocalMusicItem> {
    private Context mContext;

    public SongListFactory() {
    }

    @Override
    public Class<SongListInfo> getBeanClass() {
        return SongListInfo.class;
    }

    @Override
    public LocalMusicItem createAssemblyItem(ViewGroup parent) {
        return new LocalMusicItem(parent, this);
    }

    public class LocalMusicItem extends AssemblyItem<SongListInfo, SongListFactory> {
        ImageView ivCover;
        TextView tvMusic1;
        TextView tvMusic2;
        TextView tvMusic3;
        protected LocalMusicItem(ViewGroup parent, SongListFactory itemFactory) {
            super(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_song_list, parent, false), itemFactory);
            mContext = parent.getContext();
        }

        @Override
        protected void onFindViews(View convertView) {
            ivCover = (ImageView) convertView.findViewById(R.id.iv_cover);
            tvMusic1 = (TextView) convertView.findViewById(R.id.tv_music_1);
            tvMusic2 = (TextView) convertView.findViewById(R.id.tv_music_2);
            tvMusic3 = (TextView) convertView.findViewById(R.id.tv_music_3);
        }

        @Override
        protected void onConfigViews(Context context) {
            getConvertView().setOnClickListener(v -> {
                Intent intent = new Intent(mContext, OnlineMusicActivity.class);
                intent.putExtra(Extras.MUSIC_LIST_TYPE, getData());
                mContext.startActivity(intent);
            });
        }

        @Override
        protected void onSetData(int position, SongListInfo songListInfo) {
            getMusicListInfo(songListInfo);

        }

        private void getMusicListInfo(final SongListInfo songListInfo) {
            if (songListInfo.getCoverUrl() == null) {
                ivCover.setImageResource(R.drawable.default_cover);
                tvMusic1.setText("1.加载中…");
                tvMusic2.setText("2.加载中…");
                tvMusic3.setText("3.加载中…");
                ivCover.setTag(songListInfo);
                Map<String,String> map =new HashMap<>();
                map.put("type",songListInfo.getType());
                map.put("size","3");
                ApiService.getInstance()
                        .apiManager
                        .getSongList(map)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(data -> {
                            if (data == null || data.getSong_list() == null) {
                                return;
                            }
                            if (ivCover.getTag() != songListInfo) {
                                return;
                            }
                            JOnlineMusic[] jOnlineMusics = data.getSong_list();
                            songListInfo.setCoverUrl(data.getBillboard().getPic_s260());
                            if (jOnlineMusics.length >= 1) {
                                songListInfo.setMusic1("1." + jOnlineMusics[0].getTitle() + " - " + jOnlineMusics[0].getArtist_name());
                            } else {
                                songListInfo.setMusic1("");
                            }
                            if (jOnlineMusics.length >= 2) {
                                songListInfo.setMusic2("2." + jOnlineMusics[1].getTitle() + " - " + jOnlineMusics[1].getArtist_name());
                            } else {
                                songListInfo.setMusic2("");
                            }
                            if (jOnlineMusics.length >= 3) {
                                songListInfo.setMusic3("3." + jOnlineMusics[2].getTitle() + " - " + jOnlineMusics[2].getArtist_name());
                            } else {
                                songListInfo.setMusic3("");
                            }
                            Picasso.with(mContext).load(songListInfo.getCoverUrl()).into(ivCover);

                            tvMusic1.setText(songListInfo.getMusic1());
                            tvMusic2.setText(songListInfo.getMusic2());
                            tvMusic3.setText(songListInfo.getMusic3());
                        });

            } else {
                Picasso.with(mContext).load(songListInfo.getCoverUrl()).into(ivCover);
                tvMusic1.setText(songListInfo.getMusic1());
                tvMusic2.setText(songListInfo.getMusic2());
                tvMusic3.setText(songListInfo.getMusic3());
            }
        }

    }
}
