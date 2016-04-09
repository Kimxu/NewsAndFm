package kimxu.newsandfm.adapter.factory;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import kimxu.adapter.AssemblyItem;
import kimxu.adapter.AssemblyItemFactory;
import kimxu.bdyy.Song;
import kimxu.newsandfm.R;

/**
 * 搜索结果
 * Created by kimxu on 16/1/8.
 */

public class SearchFactory extends AssemblyItemFactory<SearchFactory.SearchItem> {
    private Activity mActivity;

    public SearchFactory(Activity mActivity) {
        this.mActivity = mActivity;
    }

    @Override
    public Class<Song> getBeanClass() {
        return Song.class;
    }

    @Override
    public SearchItem createAssemblyItem(ViewGroup parent) {
        return new SearchItem(parent, this);
    }

    public class SearchItem extends AssemblyItem<Song, SearchFactory> {
        private TextView tvTitle;
        private TextView tvAlbum;

        protected SearchItem(ViewGroup parent, SearchFactory itemFactory) {
            super(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_search, parent, false), itemFactory);
        }

        @Override
        protected void onFindViews(View convertView) {
            tvTitle = (TextView) convertView.findViewById(R.id.tv_itemSearch_name);
            tvAlbum = (TextView) convertView.findViewById(R.id.tv_itemSearch_album);
        }

        @Override
        protected void onConfigViews(Context context) {

        }

        @Override
        protected void onSetData(int position, Song result) {
            tvTitle.setText(result.getSongname());
            tvAlbum.setText(result.getArtistname());

        }
    }
}
