package kimxu.core.net;

import java.util.Map;

import kimxu.bdyy.pic.AlbumPic;
import kimxu.bdyy.searchSongId.SearchId;
import kimxu.xmly.album.Album;
import kimxu.xmly.discoverRecommend.DiscoverRecommend;
import retrofit.RestAdapter;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;
import retrofit.http.QueryMap;
import rx.Observable;

public class ApiService {
    public ApiXmlyManagerService apiXmlyManager;//获得喜马拉雅
    public ApiBdyyManagerService apiBdyyManager;//百度云音乐
    private static ApiService apiService;
    private ApiService (){
        String ENDPOINT = "http://mobile.ximalaya.com";
        RestAdapter restXmlyAdapter= new RestAdapter.Builder().setEndpoint(ENDPOINT).setLogLevel(RestAdapter.LogLevel.FULL).build();
        apiXmlyManager = restXmlyAdapter.create(ApiXmlyManagerService.class);
        //-------------
        ENDPOINT = "http://tingapi.ting.baidu.com/v1/restserver";
        RestAdapter restBdyyAdapter =new RestAdapter.Builder().setEndpoint(ENDPOINT).setLogLevel(RestAdapter.LogLevel.FULL).build();
        apiBdyyManager =restBdyyAdapter.create(ApiBdyyManagerService.class);
    }
    public static ApiService getInstance(){
        if (apiService==null){
            synchronized (ApiService.class) {
                apiService = new ApiService();
            }
        }
        return apiService;
    }
    //喜马拉雅
    public interface ApiXmlyManagerService {
        /**
         * retrofit 支持 rxjava 整合
         * 这种方法适用于新接口
         */
        @GET("/mobile/others/ca/album/track/321705/true/{pager}/20")
        Observable<Album> getAlbum(@Path("pager")String pager, @QueryMap Map<String,String> map);
        /**
          获得推荐
         * @return
         */
       @GET("/mobile/discovery/v1/recommends")
       Observable<DiscoverRecommend> getDiscoverRecommend(@QueryMap Map<String,String> map);
    }

    //百度音乐
    public interface ApiBdyyManagerService{
        String bdyyUrl="?from=android&version=5.6.6.1&channel=360safe&operator=3&format=json&ts=1451464605101&e=bLkVYIBl6FyH5SR9BbVH5Iv7HxAMs0b4xqQNRrBDXPwPoEE6uYJc4pqcgxu%2Bsjft&nw=2&ucf=1&res=1&l2p=0&lpb=&usup=1&lebo=0&";
        @GET("/ting"+bdyyUrl+"method=baidu.ting.search.catalogSug&format=json")
        Observable<SearchId> getSongId(@Query("query") String query);

        @GET("/ting"+bdyyUrl+"method=baidu.ting.song.getInfos")
        Observable<AlbumPic> getAlbumPic(@Query("songid")String songid);
    }
    public Observable<Album> getAlbum(String pager, Map<String,String> map){
        //albumId=321705&pageSize=20&isAsc=true&position=3&device=android
        map.put("pageSize","20");
        map.put("isAsc","true");
        map.put("device","android");
        return apiXmlyManager.getAlbum(pager,map);
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
        return apiXmlyManager.getDiscoverRecommend(map);
    }
}
