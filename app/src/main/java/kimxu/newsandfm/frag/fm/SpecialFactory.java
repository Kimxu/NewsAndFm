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
public class SpecialFactory extends AssemblyItemFactory<SpecialFactory.SpecialItem>{

    @Override
    public Class<FMDiscovery.SpecialColumnEntity> getBeanClass() {
        return FMDiscovery.SpecialColumnEntity.class;
    }

    @Override
    public SpecialItem createAssemblyItem(ViewGroup parent) {
        return new SpecialItem(parent,this);
    }

    public class SpecialItem extends AssemblyItem<FMDiscovery.SpecialColumnEntity,SpecialFactory>{

        protected SpecialItem(ViewGroup parent, SpecialFactory itemFactory) {
            super(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_special,parent,false), itemFactory);
        }

        @Override
        protected void onFindViews(View convertView) {

        }

        @Override
        protected void onConfigViews(Context context) {

        }

        @Override
        protected void onSetData(int position, FMDiscovery.SpecialColumnEntity specialView) {

        }
    }
}
