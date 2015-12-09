package kimxu.newsandfm.adapter.factory;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import kimxu.adapter.AssemblyItem;
import kimxu.adapter.AssemblyItemFactory;
import kimxu.newsandfm.R;
import kimxu.newsandfm.model.News;

/**
 *
 * Created by xuzhiguo on 15/11/25.
 */
public class NewsListItemFactory extends AssemblyItemFactory<NewsListItemFactory.NewsListItem> {

    private EventListener eventListener;


    public NewsListItemFactory(Context context ,EventListener listener) {
        this.eventListener =listener;
    }


    public interface EventListener{
        void onClick(int position,  News.ResultEntity news);
    }


    @Override
    public Class<?> getBeanClass() {
        return News.ResultEntity.class;
    }

    @Override
    public NewsListItem createAssemblyItem(ViewGroup parent) {

        return new NewsListItem(parent,this);
    }

    public static class NewsListItem extends AssemblyItem<News.ResultEntity,NewsListItemFactory>{
        private Context context;
        private ImageView iconImageView;
        private TextView nameTextView;
        private TextView likeTextView;
        private View convertView;
        protected NewsListItem(ViewGroup parent, NewsListItemFactory factory) {
            super(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_news, parent, false), factory);
            context =parent.getContext();
        }

        @Override
        protected void onFindViews(View convertView) {
            this.convertView=convertView;
            iconImageView = (ImageView) convertView.findViewById(R.id.image_gameListItem_icon);
            nameTextView = (TextView) convertView.findViewById(R.id.text_gameListItem_name);
            likeTextView = (TextView) convertView.findViewById(R.id.text_gameListItem_like);
        }

        @Override
        protected void onConfigViews(Context context) {
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getItemFactory().eventListener.onClick(getPosition(), getData());
                }
            });
        }

        @Override
        protected void onSetData(int position, News.ResultEntity news) {
            if (TextUtils.isEmpty(news.getTitle()))
                return;
            if (!TextUtils.isEmpty(news.getImage())){
            if (news.getImage().startsWith("ht")) {
                Picasso.with(context).load(news.getImage()).into(iconImageView);
            }else{
                String url ="http://i3.go2yd.com/image.php?type=webp_180x120&url="+news.getImage()+"&net=wifi";
                Picasso.with(context).load(url).into(iconImageView);

            }
            }
            nameTextView.setText(news.getTitle());
            likeTextView.setText(news.getDate());
        }
    }
}
