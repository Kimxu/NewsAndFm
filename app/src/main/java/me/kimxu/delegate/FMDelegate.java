package me.kimxu.delegate;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import kimxu.mvp.view.AppFragDelegate;
import me.kimxu.R;

/**
 *
 * Created by kimxu on 15/11/24.
 */
public class FMDelegate extends AppFragDelegate {

    @Override
    public int getRootLayoutId() {
        return R.layout.fragment_fm;
    }

    @Override
    public void initWidget() {

        initFrag();
    }
    private void initFrag() {
    }
}
