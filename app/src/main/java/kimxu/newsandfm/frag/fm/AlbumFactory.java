package kimxu.newsandfm.frag.fm;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.Field;

import kimxu.adapter.AssemblyItem;
import kimxu.adapter.AssemblyItemFactory;
import kimxu.newsandfm.R;

/**
 *
 * Created by xuzhiguo on 15/12/8.
 */
public class AlbumFactory extends AssemblyItemFactory<AlbumFactory.FMListItem>{
    @Override
    public Class<?> getBeanClass() {
        return FMDiscovery.EditorRecommendAlbumsEntity.class;
    }

    @Override
    public FMListItem createAssemblyItem(ViewGroup parent) {
        return new FMListItem(parent,this);
    }

    public class FMListItem extends AssemblyItem<FMDiscovery.EditorRecommendAlbumsEntity,AlbumFactory> {
        private final int alblumSize =3;
        private TextView  txtTitle;
        private TextView  txtMore;
        private ImageView[] albumIcons;//三张图片，在不同的RelativeLayout里面
        private TextView [] albumNames;//三个专辑标题
        private TextView [] trackNames;//三个曲目名称
        public FMListItem(ViewGroup parent, AlbumFactory fmListFactory) {
            super(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_album, parent, false),fmListFactory);
        }
        @Override
        protected void onFindViews(View convertView) {
            txtTitle = (TextView) convertView.findViewById(R.id.recommend_album_title);
            txtMore = (TextView) convertView.findViewById(R.id.recommend_album_more);
            albumIcons = new ImageView[alblumSize];
            albumNames = new TextView[alblumSize];
            trackNames = new TextView[alblumSize];
            for (int i = 0; i < alblumSize; i++) {
                //给Holder设置数组的内容
                albumIcons[i] = (ImageView) findView(convertView, "recommend_album_icon_" + i);
                // TODO 点击专辑图片，进入专辑详情
                albumNames[i] = (TextView) findView(convertView,"recommend_album_name_" + i);
                trackNames[i] = (TextView) findView(convertView, "recommend_album_track_name_" + i);
            }
        }

        @Override
        protected void onConfigViews(Context context) {
            for (int i = 0; i < alblumSize; i++) {
                albumIcons[i].setOnClickListener(view->{

                });

            }
        }
        @Override
        protected void onSetData(int position, FMDiscovery.EditorRecommendAlbumsEntity album) {

            //txtTitle.setText(album.title);
        }
        /**
         * 根据名称获取ID(反射)
         * @param container
         * @param fieldName
         * @return
         */
        public View findView(View container, String fieldName){
            View  ret = null;
            if (container != null && fieldName != null) {
                Class<R.id> idClass = R.id.class;
                Field field = null;
                try {
                    field = idClass.getDeclaredField(fieldName);
                    int id = field.getInt(idClass);
                    //通过静态常量，获取int 值，
                    ret = container.findViewById(id);
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }

            return ret;
        }
    }
}
