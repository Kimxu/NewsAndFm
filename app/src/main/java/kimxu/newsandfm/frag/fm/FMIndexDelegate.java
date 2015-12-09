package kimxu.newsandfm.frag.fm;

import android.widget.BaseAdapter;
import android.widget.ListView;

import kimxu.mvp.view.AppDelegate;
import kimxu.newsandfm.R;

/**
 * Created by xuzhiguo on 15/12/8.
 */
public class FMIndexDelegate extends AppDelegate{
    private ListView listView;
    @Override
    public int getRootLayoutId() {
        return R.layout.fragment_fmindex;
    }

    @Override
    public void initWidget() {
        listView=get(R.id.list_FMIndex);
    }
    public void setAdapter(BaseAdapter adapter){
        listView.setAdapter(adapter);
    }
}
