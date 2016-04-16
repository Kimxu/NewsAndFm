package me.kimxu.adapter.factory;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import kimxu.adapter.AssemblyItem;
import kimxu.adapter.AssemblyItemFactory;
import me.kimxu.R;

/**
 * 本地音乐
 * Created by kimxu on 15/12/30.
 */

public class SongListProfileFactory extends AssemblyItemFactory<SongListProfileFactory.LocalMusicItem> {

    public SongListProfileFactory() {
    }


    @Override
    public Class<String> getBeanClass() {
        return String.class;
    }

    @Override
    public LocalMusicItem createAssemblyItem(ViewGroup parent) {
        return new LocalMusicItem(parent, this);
    }

    public class LocalMusicItem extends AssemblyItem<String, SongListProfileFactory> {
        TextView tvProfile;
        protected LocalMusicItem(ViewGroup parent, SongListProfileFactory itemFactory) {
            super(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_song_list_profile, parent, false), itemFactory);
        }

        @Override
        protected void onFindViews(View convertView) {
            tvProfile = (TextView) convertView.findViewById(R.id.tv_profile);
        }

        @Override
        protected void onConfigViews(Context context) {

        }


        @Override
        protected void onSetData(int position, String title) {
            tvProfile.setText(title);
        }


    }
}
