package me.kimxu.delegate;

import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import kimxu.mvp.view.AppFragDelegate;
import me.kimxu.R;
import me.kimxu.adapter.PlayPagerAdapter;
import me.kimxu.widget.AlbumCoverView;
import me.kimxu.widget.IndicatorLayout;
import me.kimxu.widget.LrcView;

/**
 *
 * Created by kimxu on 15/11/24.
 */
public class PlayDelegate extends AppFragDelegate {
    public LinearLayout llContent;
    public ImageView ivPlayingBg;
    public ImageView ivBack;
    public TextView tvTitle;
    public TextView tvArtist;
    public ViewPager vpPlay;
    public IndicatorLayout ilIndicator;
    public SeekBar sbProgress;
    public TextView tvCurrentTime;
    public TextView tvTotalTime;
    public ImageView ivMode;
    public ImageView ivPlay;
    public ImageView ivNext;
    public ImageView ivPrev;
    public AlbumCoverView mAlbumCoverView;
    public LrcView mLrcViewSingle;
    public LrcView mLrcViewFull;
    public SeekBar sbVolume;

    private List<View> mViewPagerContent;
    public int mLastProgress;

    private Context mContext;
    @Override
    public int getRootLayoutId() {
        return R.layout.fragment_play;
    }

    @Override
    public void create(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.create(inflater, container, savedInstanceState);
        mContext=container.getContext();
    }

    @Override
    public void setContext(Context context) {
        super.setContext(context);
        mContext=context;
    }

    @Override
    public void initWidget() {
        llContent =get(R.id.ll_content);
        ivPlayingBg =get(R.id.iv_play_page_bg);
        ivBack =get(R.id.iv_back);
        tvTitle =get(R.id.tv_title);
        tvArtist =get(R.id.tv_artist);
        vpPlay =get(R.id.vp_play_page);
        ilIndicator =get(R.id.il_indicator);
        sbProgress =get(R.id.sb_progress);
        tvCurrentTime =get(R.id.tv_current_time);
        tvTotalTime =get(R.id.tv_total_time);
        ivMode =get(R.id.iv_mode);
        ivPlay =get(R.id.iv_play);
        ivNext =get(R.id.iv_next);
        ivPrev =get(R.id.iv_prev);

        View coverView = LayoutInflater.from(mContext).inflate(R.layout.fragment_play_page_cover, null);
        View lrcView = LayoutInflater.from(mContext).inflate(R.layout.fragment_play_page_lrc, null);
        mAlbumCoverView = (AlbumCoverView) coverView.findViewById(R.id.album_cover_view);
        mLrcViewSingle = (LrcView) coverView.findViewById(R.id.lrc_view_single);
        mLrcViewFull = (LrcView) lrcView.findViewById(R.id.lrc_view_full);
        sbVolume = (SeekBar) lrcView.findViewById(R.id.sb_volume);
        mViewPagerContent = new ArrayList<>(2);
        mViewPagerContent.add(coverView);
        mViewPagerContent.add(lrcView);
        ilIndicator.create(mViewPagerContent.size());
        vpPlay.setAdapter(new PlayPagerAdapter(mViewPagerContent));

    }
}
