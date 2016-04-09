package kimxu.newsandfm.adapter.factory;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.Field;

import kimxu.adapter.AssemblyItem;
import kimxu.adapter.AssemblyItemFactory;
import kimxu.xmly.discoverRecommend.List_______;
import kimxu.newsandfm.R;

/**
 *
 * Created by xuzhiguo on 15/12/8.
 */
public class SpecialFactory extends AssemblyItemFactory<SpecialFactory.SpecialItem>{

    public SpecialFactory(FragmentActivity mActivity) {


    }

    @Override
    public Class<List_______> getBeanClass() {
        return List_______.class;
    }

    @Override
    public SpecialItem createAssemblyItem(ViewGroup parent) {
        return new SpecialItem(parent,this);
    }

    public class SpecialItem extends AssemblyItem<List_______,SpecialFactory>{
        private final int alSize=2;
        private TextView txtTitle;
        private TextView txtMore;
        private ImageView[] specialCovers;//两张图片
        private TextView[] specialNames;//两个精品听单title
        private TextView[] specialSubNames;//两个精品听单的subTitle
        private TextView[] specialFootnote;//专辑数
        protected SpecialItem(ViewGroup parent, SpecialFactory itemFactory) {
            super(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_special,parent,false), itemFactory);
        }

        @Override
        protected void onFindViews(View convertView) {
            txtTitle = (TextView) convertView.findViewById(R.id.recommend_special_title);
            txtMore = (TextView) convertView.findViewById(R.id.recommend_special_more);
            specialCovers = new ImageView[alSize];
            specialNames = new TextView[alSize];
            specialSubNames = new TextView[alSize];
            specialFootnote = new TextView[alSize];
            for (int i = 0; i < 2; i++) {
                specialCovers[i] = (ImageView) findView(convertView, "recommend_special_cover_" + i);
                specialNames[i] = (TextView) findView(convertView, "recommend_special_name_" + i);
                specialSubNames[i] = (TextView) findView(convertView, "recommend_special_subname_" + i);
                specialFootnote[i] = (TextView) findView(convertView, "recommend_special_footnote_" + i);
            }
        }

        @Override
        protected void onConfigViews(Context context) {

        }

        @Override
        protected void onSetData(int position, List_______ special) {
            String title = special.getTitle();
            txtTitle.setText(title);
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
