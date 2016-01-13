package kimxu.newsandfm.frag.netmusic;

import android.widget.LinearLayout;

import kimxu.mvp.view.AppFragDelegate;
import kimxu.newsandfm.R;
import kimxu.newsandfm.widget.HintView;

/**
 *
 * Created by kimxu on 16/1/8.
 */

public class RecommendDelegate extends AppFragDelegate {
    public LinearLayout mLLayout;
    public HintView mHintView;
    @Override
    public int getRootLayoutId() {
        return R.layout.fragment_recommend;
    }

    @Override
    public void initWidget() {
        mLLayout=get(R.id.ll_fragRecommend);
        mHintView=get(R.id.hintView_empty);
        mHintView.loading().show();
    }
}
