package kimxu.newsandfm.frag.fm;

import android.widget.BaseAdapter;
import android.widget.ListView;

import kimxu.mvp.view.AppFragDelegate;
import kimxu.newsandfm.R;

/**
 * 
 * Created by kimxu on 15/12/8.
 */
public class FMIndexDelegate extends AppFragDelegate {
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
