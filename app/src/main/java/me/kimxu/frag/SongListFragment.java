package me.kimxu.frag;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import java.util.ArrayList;
import java.util.List;

import kimxu.adapter.AssemblyAdapter;
import kimxu.core.bean.SongListInfo;
import me.kimxu.KBaseFragment;
import me.kimxu.R;
import me.kimxu.adapter.factory.SongListFactory;
import me.kimxu.adapter.factory.SongListProfileFactory;
import me.kimxu.delegate.FMDelegate;
import me.kimxu.delegate.SongListDelegate;
import me.kimxu.enums.LoadStateEnum;
import me.kimxu.utils.Extras;
import me.kimxu.utils.NetworkUtils;
import me.kimxu.utils.ViewUtils;

/**
 * Fm电台
 */
public class SongListFragment extends KBaseFragment<SongListDelegate> {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    private List<Object> mData;

    public static SongListFragment newInstance(String param1, String param2) {
        SongListFragment fragment = new SongListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected void bindEvenListener() {
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        if (!NetworkUtils.isNetworkAvailable(getActivity())) {
            ViewUtils.changeViewState(viewDelegate.lvSongList, viewDelegate.llLoading, viewDelegate.llLoadFail, LoadStateEnum.LOAD_FAIL);
            return;
        }
        setDatas();
    }

    private void setDatas() {
        mData = new ArrayList<>();
        String[] titles = getResources().getStringArray(R.array.online_music_list_title);
        String[] types = getResources().getStringArray(R.array.online_music_list_type);
        for (int i = 0; i < titles.length; i++) {
            if (types[i].equals("#")){
                mData.add(titles[i]);
            }else {
                SongListInfo info = new SongListInfo();
                info.setTitle(titles[i]);
                info.setType(types[i]);
                mData.add(info);
            }
        }
        AssemblyAdapter mAdapter = new AssemblyAdapter(mData);
        mAdapter.addItemFactory(new SongListProfileFactory());
        mAdapter.addItemFactory(new SongListFactory());
        viewDelegate.lvSongList.setAdapter(mAdapter);

    }


    @Override
    protected Class<SongListDelegate> getDelegateClass() {
        return SongListDelegate.class;
    }

}
