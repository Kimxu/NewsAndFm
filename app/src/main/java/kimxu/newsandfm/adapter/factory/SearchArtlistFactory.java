package kimxu.newsandfm.adapter.factory;

import android.app.Activity;
import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import kimxu.adapter.AssemblyItem;
import kimxu.adapter.AssemblyItemFactory;
import kimxu.bdyy.Song;
import kimxu.bdyy.search.merge.ArtistList;
import kimxu.newsandfm.R;

/**
 *
 * Created by kimxu on 16/1/8.
 */

public class SearchArtlistFactory extends AssemblyItemFactory<SearchArtlistFactory.SearchItem> {
    private Activity mActivity;

    public SearchArtlistFactory(Activity mActivity) {
        this.mActivity = mActivity;
    }

    @Override
    public Class<ArtistList> getBeanClass() {
        return ArtistList.class;
    }

    @Override
    public SearchItem createAssemblyItem(ViewGroup parent) {
        return new SearchItem(parent, this);
    }

    public class SearchItem extends AssemblyItem<ArtistList, SearchArtlistFactory> {
        private TextView tvTitle;
        private TextView tvNum;
        private ImageView ivAlbum;

        protected SearchItem(ViewGroup parent, SearchArtlistFactory itemFactory) {
            super(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_search_artist, parent, false), itemFactory);
        }

        @Override
        protected void onFindViews(View convertView) {
            tvTitle = (TextView) convertView.findViewById(R.id.tv_itemSearchArtist_name);
            tvNum = (TextView) convertView.findViewById(R.id.tv_itemSearchArtist_num);
            ivAlbum = (ImageView) convertView.findViewById(R.id.iv_itemSearchArtist_pic);
        }

        @Override
        protected void onConfigViews(Context context) {

        }

        @Override
        protected void onSetData(int position, ArtistList result) {
            tvTitle.setText(Html.fromHtml(result.getAuthor()));
            tvNum.setText(result.getSongNum()+"首");
            Picasso.with(mActivity).load(result.getAvatarMiddle()).into(ivAlbum);
        }
    }
}
