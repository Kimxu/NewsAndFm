package kimxu.newsandfm.aty;

import android.support.v7.widget.Toolbar;
import android.widget.ListView;

import kimxu.adapter.AssemblyAdapter;
import kimxu.mvp.view.AppAtyDelegate;
import kimxu.newsandfm.R;
import kimxu.newsandfm.widget.HintView;

/**
 *
 * Created by kimxu on 15/12/29.
 */
public class LocalMusicFDelegate extends AppAtyDelegate {
    private HintView mHintView;
    private ListView mListView;
    @Override
    public int getRootLayoutId() {
        return R.layout.activity_local_music;
    }
    @Override
    public Toolbar getToolbar() {
        return get(R.id.tBar_atyXXX);
    }
    @Override
    public void initWidget() {
        mHintView=get(R.id.hintView_empty);
        mHintView.loading().show();
        mListView=get(R.id.lv_atyLocalMusic);

    }

    public void setAdapter(AssemblyAdapter adapter){
        mListView.setAdapter(adapter);
    }
    public void hiddenHintView(){
        mHintView.hidden();
    }
}

