package kimxu.core.net;

import java.util.Map;

import kimxu.core.net.model.album.Album;
import kimxu.core.net.model.discoverRecommend.DiscoverRecommend;
import retrofit.RestAdapter;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.QueryMap;
import rx.Observable;

public class ApiService {
    public ApiManagerService apiManager;
    private static ApiService apiService;
    private ApiService (){
        String ENDPOINT = "http://mobile.ximalaya.com";
        RestAdapter restAdapter= new RestAdapter.Builder().setEndpoint(ENDPOINT).setLogLevel(RestAdapter.LogLevel.FULL).build();
        apiManager = restAdapter.create(ApiManagerService.class);
    }
    public static ApiService getInstance(){
        if (apiService==null){
            synchronized (ApiService.class) {
                apiService = new ApiService();
            }
        }
        return apiService;
    }

    public interface ApiManagerService{
        /**
         * retrofit 支持 rxjava 整合
         * 这种方法适用于新接口
         */
        @GET("/mobile/others/ca/album/track/321705/true/{pager}/20")
        Observable<Album> getAlbum(@Path("pager")String pager, @QueryMap Map<String,String> map);
        /**
         * 获得推荐
         * @return
         */
       @GET("/mobile/discovery/v1/recommends")
       Observable<DiscoverRecommend> getDiscoverRecommend(@QueryMap Map<String,String> map);
    }


    public Observable<Album> getAlbum(String pager, Map<String,String> map){
        //albumId=321705&pageSize=20&isAsc=true&position=3&device=android
        map.put("pageSize","20");
        map.put("isAsc","true");
        map.put("device","android");
        return apiManager.getAlbum(pager,map);
    }
    //http://mobile.ximalaya.com/mobile/discovery/v1/recommends?channel=and-f6&device=android&includeActivity=true&includeSpecial=true&scale=2&version=4.1.7.1
    public Observable<DiscoverRecommend> getDiscoverRecommend(Map<String,String> map){
        //?channel=and-f6&device=android&includeActivity=true&includeSpecial=true&scale=2&version=4.1.7.1
        map.put("channel","and-f6");
        map.put("device","android");
        map.put("includeActivity","true");
        map.put("includeSpecial","true");
        map.put("scale","2");
        map.put("version","4.1.7.1");
        return apiManager.getDiscoverRecommend(map);
    }
}
