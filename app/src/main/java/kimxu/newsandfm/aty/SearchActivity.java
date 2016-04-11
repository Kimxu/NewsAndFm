package kimxu.newsandfm.aty;

import android.app.Activity;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;

import kimxu.bdyy.search.merge.SearchMerge;
import kimxu.core.net.ApiService;
import kimxu.newsandfm.KBaseActivity;
import kimxu.utils.L;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class SearchActivity extends KBaseActivity<SearchDelegate> {

    @Override
    protected void bindEvenListener() {

        ApiService.getInstance()
                .apiBdyyManager
                .getSearchRecommend()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(datas -> {
                    viewDelegate.mTGRecommend.setTextViews(datas.getResult());
                });

        viewDelegate.mEtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                getSearchResult(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    /**
     * 获得搜索结果
     * @param query
     */
    private void getSearchResult(String query) {
        ApiService.getInstance()
                .apiBdyyManager
                .getCatalogSug(query)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::notifyModelChanged);

    }

    /**
     * 获得搜索完成的listview内容
     * @param query
     */
    public void getResultList(String query){
        ApiService.getInstance()
                .apiBdyyManager
                .getSearchMerge(query)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::notifyModelChanged);

    }

    /**
     *
     * @Execption 这里有个问题,就是不能根据歌曲id去获得到歌曲音频源
     * 所以这里固定写死了.做个演示用
     */
    public void getMusicInfo(String songId){
        ApiService.getInstance()
                .apiBdyyManager
                .getMp3Info()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s->{
                    L.e(s.getErrorCode().toString());

                },error->{
                    L.e(error.getMessage());
                });
    }

    @Override
    protected Class<SearchDelegate> getDelegateClass() {
        return SearchDelegate.class;
    }


    @Override
    public SearchDataBinder getDataBinder() {
        return new SearchDataBinder((SearchActivity) mActivity);
    }

    public static void launch(Activity activity) {
        activity.startActivity(new Intent(activity, SearchActivity.class));
    }
}
