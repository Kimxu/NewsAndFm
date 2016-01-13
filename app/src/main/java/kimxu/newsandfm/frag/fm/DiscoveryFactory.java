package kimxu.newsandfm.frag.fm;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import kimxu.adapter.AssemblyItem;
import kimxu.adapter.AssemblyItemFactory;
import kimxu.newsandfm.R;
import kimxu.newsandfm.utils.GlobalUtils;
import kimxu.xmly.discoverRecommend.List_;

/**
 *
 * Created by xuzhiguo on 15/12/8.
 */
public class DiscoveryFactory extends AssemblyItemFactory<DiscoveryFactory.DiscoveryItem>{

    public DiscoveryFactory(FragmentActivity mActivity) {


    }

    @Override
    public Class<List_> getBeanClass() {
        return List_.class;
    }

    @Override
    public DiscoveryItem createAssemblyItem(ViewGroup parent) {
        return new DiscoveryItem(parent,this);
    }

    public class DiscoveryItem extends AssemblyItem<List_,DiscoveryFactory>{
        private final int aSize=4;
        private TextView txtTitle;
        private TextView txtMore;
        private ImageView[] discoveryCovers;//精品听单四个图标
        private TextView[] discoveryNames;//四个title
        private TextView[] getDiscoverySubNames;//四个subTitle
        public DiscoveryItem(ViewGroup parent, DiscoveryFactory discoveryFactory) {
            super(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_discovery,parent,false), discoveryFactory);
        }

        @Override
        protected void onFindViews(View convertView) {
            txtTitle = (TextView) convertView.findViewById(R.id.recommend_discovery_title);
            txtMore = (TextView) convertView.findViewById(R.id.recommend_discovery_more);
            discoveryCovers = new ImageView[aSize];
            discoveryNames = new TextView[aSize];
            getDiscoverySubNames = new TextView[aSize];
            for (int i = 0; i < aSize; i++) {
                discoveryCovers[i] = (ImageView) GlobalUtils.findView(convertView, "recommend_discovery_cover_" + i);
                discoveryNames[i] = (TextView) GlobalUtils.findView(convertView, "recommend_discovery_name_" + i);
                getDiscoverySubNames[i] = (TextView) GlobalUtils.findView(convertView, "recommend_discovery_subname_" + i);
            }
        }

        @Override
        protected void onConfigViews(Context context) {

        }

        @Override
        protected void onSetData(int position, List_ discovery) {
            String title = discovery.getTitle();
            txtTitle.setText(title);

        }
    }
}
