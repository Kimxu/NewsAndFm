package kimxu.core.net;

import kimxu.core.bean.JSplash;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;
import retrofit2.RxJavaCallAdapterFactory;
import retrofit2.http.GET;
import rx.Observable;

public class ApiService {
    public ApiManagerService apiManager;//百度云音乐

    private static ApiService apiService;

    private ApiService() {
        String ENDPOINT = "http://tingapi.ting.baidu.com/v1/restserver/";
        Retrofit bdyyRetrofit = new Retrofit.Builder()
                .baseUrl(ENDPOINT)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiManager = bdyyRetrofit.create(ApiManagerService.class);
    }

    public static ApiService getInstance() {
        if (apiService == null) {
            synchronized (ApiService.class) {
                apiService = new ApiService();
            }
        }
        return apiService;
    }

    public interface ApiManagerService {
        /**
         * 获取首屏广告信息
         * @return
         */
        @GET("http://news-at.zhihu.com/api/4/start-image/720*1184")
        Observable<JSplash> updateSplashAd();
    }


}
