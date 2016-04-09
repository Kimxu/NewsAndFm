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
import kimxu.bdyy.search.merge.SongList;
import kimxu.newsandfm.R;

/**
 *
 * Created by kimxu on 16/1/8.
 */

public class SearchSongFactory extends AssemblyItemFactory<SearchSongFactory.SearchItem> {
    private Activity mActivity;

    public SearchSongFactory(Activity mActivity) {
        this.mActivity = mActivity;
    }

    @Override
    public Class<SongList> getBeanClass() {
        return SongList.class;
    }

    @Override
    public SearchItem createAssemblyItem(ViewGroup parent) {
        return new SearchItem(parent, this);
    }

    public class SearchItem extends AssemblyItem<SongList, SearchSongFactory> {
        private TextView tvTitle;
        private TextView tvAlbum;

        protected SearchItem(ViewGroup parent, SearchSongFactory itemFactory) {
            super(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_search_song, parent, false), itemFactory);
        }

        @Override
        protected void onFindViews(View convertView) {
            tvTitle = (TextView) convertView.findViewById(R.id.tv_itemSearchSong_name);
            tvAlbum = (TextView) convertView.findViewById(R.id.tv_itemSearchSong_album);
        }

        @Override
        protected void onConfigViews(Context context) {

        }

        @Override
        protected void onSetData(int position, SongList result) {
            tvTitle.setText(result.getTitle());
            tvAlbum.setText(result.getAuthor()+"-"+result.getAlbumTitle());

        }
    }
}
