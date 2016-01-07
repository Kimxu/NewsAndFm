package kimxu.newsandfm.frag;

import android.widget.ImageView;
import android.widget.TextView;

import kimxu.mvp.view.AppFragDelegate;
import kimxu.newsandfm.R;

/**
 * Fragment视图类
 * Created by kimxu on 15/11/24.
 */
public class MusicDelegate extends AppFragDelegate {
    private ImageView ivUserPhoto;
    private TextView tvUserName;

    @Override
    public int getRootLayoutId() {
        return R.layout.fragment_music;
    }

    @Override
    public void initWidget() {
        ivUserPhoto = get(R.id.userPhoto_fragMusic);
        tvUserName = get(R.id.userName_fragMusic);
    }
}
