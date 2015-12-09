package kimxu.newsandfm.frag.fm;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import kimxu.adapter.AssemblyItem;
import kimxu.adapter.AssemblyItemFactory;
import kimxu.newsandfm.R;

/**
 *
 * Created by xuzhiguo on 15/12/8.
 */
public class DiscoveryFactory extends AssemblyItemFactory<DiscoveryFactory.DiscoveryItem>{

    @Override
    public Class<FMDiscovery.DiscoveryColumnsEntity> getBeanClass() {
        return FMDiscovery.DiscoveryColumnsEntity.class;
    }

    @Override
    public DiscoveryItem createAssemblyItem(ViewGroup parent) {
        return new DiscoveryItem(parent,this);
    }

    public class DiscoveryItem extends AssemblyItem<FMDiscovery.DiscoveryColumnsEntity,DiscoveryFactory>{

        public DiscoveryItem(ViewGroup parent, DiscoveryFactory discoveryFactory) {
            super(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_discovery,parent,false), discoveryFactory);
        }

        @Override
        protected void onFindViews(View convertView) {

        }

        @Override
        protected void onConfigViews(Context context) {

        }

        @Override
        protected void onSetData(int position, FMDiscovery.DiscoveryColumnsEntity discoveryView) {

        }
    }
}