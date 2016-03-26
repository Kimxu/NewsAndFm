package kimxu.newsandfm.aty;

import android.app.Activity;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;

import kimxu.core.net.ApiService;
import kimxu.newsandfm.KBaseActivity;
import rx.android.schedulers.AndroidSchedulers;
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

    private void getSearchResult(String query) {
        ApiService.getInstance()
                .apiBdyyManager
                .getCatalogSug(query)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::notifyModelChanged);

    }


    @Override
    protected Class<SearchDelegate> getDelegateClass() {
        return SearchDelegate.class;
    }


    @Override
    public SearchDataBinder getDataBinder() {
        return new SearchDataBinder(mActivity);
    }

    public static void launch(Activity activity) {
        activity.startActivity(new Intent(activity, SearchActivity.class));
    }
}
