package kimxu.core.net;

import java.util.Map;

import kimxu.core.bean.JDownloadInfo;
import kimxu.core.bean.JOnlineMusicList;
import kimxu.core.bean.JSplash;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;
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

        @GET("ting?method=baidu.ting.billboard.billList")
        Observable<JOnlineMusicList> getSongList(@QueryMap Map<String, String> map);

        /**
         * 获取歌曲播放链接
         * @param songid 歌曲id
         * @return
         */
        @GET("ting?method=baidu.ting.song.play")
        Observable<JDownloadInfo> getSongDownloadInfo(@Query("songid")String songid);

        /**
         * 获取歌曲的lrc
         * @param url 连接地址
         * @return
         */
        @Streaming
        @GET
        Observable<Response<ResponseBody>> getSongLrc(@Url String url);
    }


}
