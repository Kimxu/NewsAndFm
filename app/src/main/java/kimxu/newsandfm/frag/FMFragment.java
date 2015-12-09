package kimxu.newsandfm.frag;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Message;

import java.io.File;
import java.util.LinkedHashMap;

import kimxu.mvp.databind.DataBinder;
import kimxu.newsandfm.KBaseFragment;
import kimxu.utils.L;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Fm电台
 */
public class FMFragment extends KBaseFragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    @Override
    protected void handleErrorMessage(Message msg) {

    }

    @Override
    protected void handleSuccessMessage(Message msg) {

    }

    public static FMFragment newInstance(String param1, String param2) {
        FMFragment fragment = new FMFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public FMFragment() {
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
    protected void bindEvenListener() {
//        HashMap<String,String> map =new HashMap<>();
//        map.put("albumId","321705");
//        map.put("position","3");
//        mApiService.getAlbum("1",map)
//                .observeOn(Schedulers.computation())
//                .subscribeOn(AndroidSchedulers.mainThread())
//                .subscribe(album -> {
//                    if (album.getRet()==0){
//                        for (int i = 0; i < album.getTracks().getList().size(); i++) {
//                            L.d(album.getTracks().getList().get(i).getAlbumTitle());
//                        }
//                    }else{
//                        Ts.showToast(mActivity,"加载数据失败");
//                    }
//            },throwable -> {
//                    Ts.showToast(mActivity,"加载数据失败");
//                });

        LinkedHashMap<String,String> map =new LinkedHashMap<>();
        //map.put("channel","and-f6");
        mApiService.getDiscoverRecommend(map)
                .observeOn(Schedulers.computation())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(fmDiscovery -> L.e(fmDiscovery.getRet()+" okoko"), throwable -> L.e("error"+throwable.getMessage()));


    }

    private void rxAction() {
        Action1<String> action1 =new Action1<String>() {
            @Override
            public void call(String o) {

            }
        };
        Action1 action2 =new Action1() {
            @Override
            public void call(Object o) {

            }
        };
        Action1 action3 =new Action1() {
            @Override
            public void call(Object o) {

            }
        };
        Action0 action0 =new Action0() {
            @Override
            public void call() {

            }
        };
        Observable<String> observable=Observable.just("");
        observable.subscribe(action1);

    }

    private void rxBase() {
        //观察者
        Observer<String> observer = new Observer<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String o) {

            }
        };
        //等同于上面
        Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String o) {

            }

            @Override
            public void onStart() {

                super.onStart();
            }
        };

        if (!subscriber.isUnsubscribed()) {
            subscriber.unsubscribe();
        }

        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("Hello");
                subscriber.onNext("Hi");
                subscriber.onNext("Aloha");
                subscriber.onCompleted();
            }
        });
        observable.subscribe(observer);

        Observable oJust = Observable.just("s", "s", "s");
        String[] words = {"Hello", "Hi", "Aloha"};
        Observable oFrom = Observable.from(words);

}

    private void rxAndroid() {
        File[] folder=new File[2] ;
        Observable.from(folder).flatMap(new Func1<File, Observable<File>>() {
            @Override
            public Observable<File> call(File file) {
                return null;
            }
        }).filter(new Func1<File, Boolean>() {
            @Override
            public Boolean call(File file) {
                return null;
            }
        }).map(new Func1<File, Bitmap>() {

            @Override
            public Bitmap call(File file) {
                return null;
            }
        })
          .subscribeOn(Schedulers.io())
          .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Action1<Bitmap>() {
            @Override
            public void call(Bitmap bitmap) {

            }
        });
    }

    @Override
    protected Class getDelegateClass() {
        return FMFDelegate.class;
    }

    @Override
    public DataBinder getDataBinder() {
        return null;
    }
}
