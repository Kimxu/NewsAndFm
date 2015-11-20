package kimxu.newsandfm.frag;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import kimxu.adapter.AbstractLoadMoreListItemFactory;
import kimxu.adapter.AssemblyAdapter;
import kimxu.newsandfm.R;
import kimxu.newsandfm.adapter.factory.GameListItemFactory;
import kimxu.newsandfm.adapter.factory.LoadMoreListItemFactory;
import kimxu.newsandfm.adapter.factory.UserListItemFactory;
import kimxu.newsandfm.model.Game;
import kimxu.newsandfm.model.User;

/**
 * 新闻
 */
public class NewsFragment extends KBaseFragment implements AbstractLoadMoreListItemFactory.EventListener {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    private ListView mListView;
    private AssemblyAdapter mAdapter;
    private int size = 20;
    private int nextStart;

    @Override
    protected void handleErrorMessage(Message msg) {

    }

    @Override
    protected void handleSuccessMessage(Message msg) {

    }
    public static NewsFragment newInstance(String param1, String param2) {
        NewsFragment fragment = new NewsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public NewsFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_news, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mListView = (ListView) view.findViewById(R.id.nf_frag_listview);
        loadData();

    }

    private void loadData(){
        new AsyncTask<String, String, List<Object>>(){

            @Override
            protected List<Object> doInBackground(String... params) {
                int index = 0;
                List<Object> dataList = new ArrayList<Object>(size);
                boolean userStatus = true;
                boolean gameStatus = true;
                while (index < size){
                    if(index % 2 == 0){
                        User user = new User();
                        user.headResId = R.mipmap.ic_launcher;
                        user.name = "王大卫"+(index+nextStart+1);
                        user.sex = userStatus?"男":"女";
                        user.age = ""+(index+nextStart+1);
                        user.job = "实施工程师";
                        user.monthly = ""+9000+index+nextStart+1;
                        dataList.add(user);
                        userStatus = !userStatus;
                    }else{
                        Game game = new Game();
                        game.iconResId = R.mipmap.ic_launcher;
                        game.name = "英雄联盟"+(index+nextStart+1);
                        game.like = gameStatus?"不喜欢":"喜欢";
                        dataList.add(game);
                        gameStatus = !gameStatus;
                    }
                    index++;
                }
                if(nextStart != 0){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                return dataList;
            }

            @Override
            protected void onPostExecute(List<Object> objects) {
                if(getActivity() == null){
                    return;
                }

                nextStart += size;
                if(mAdapter == null){
                    mAdapter = new AssemblyAdapter(objects);
                    mAdapter.addItemFactory(new UserListItemFactory(getActivity().getBaseContext()));
                    mAdapter.addItemFactory(new GameListItemFactory(getActivity().getBaseContext()));
                    if(nextStart < 100){
                        mAdapter.enableLoadMore(new LoadMoreListItemFactory(NewsFragment.this));
                    }
                    mListView.setAdapter(mAdapter);
                }else{
                    mAdapter.loadMoreFinished();
                    if(nextStart == 100){
                        mAdapter.disableLoadMore();
                    }
                    mAdapter.append(objects);
                }
            }
        }.execute("");
    }

    @Override
    public void onLoadMore(AbstractLoadMoreListItemFactory.AdapterCallback adapterCallback) {
        loadData();
    }
}

