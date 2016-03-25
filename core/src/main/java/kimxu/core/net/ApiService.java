package kimxu.core.net;

import android.view.SearchEvent;

import java.util.Map;

import kimxu.bdyy.banner.Banner;
import kimxu.bdyy.hotplaylist.HotPlaylist;
import kimxu.bdyy.kingranking.KingRanking;
import kimxu.bdyy.pic.AlbumInfo;
import kimxu.bdyy.playlist.Playlist;
import kimxu.bdyy.radio.Radio;
import kimxu.bdyy.ranking.Ranking;
import kimxu.bdyy.recommend.Recommend;
import kimxu.bdyy.search.SearchRecommended;
import kimxu.bdyy.search.searchresult.SearchResult;
import kimxu.bdyy.searchSongId.SearchId;
import kimxu.xmly.album.Album;
import kimxu.xmly.discoverRecommend.DiscoverRecommend;
import okhttp3.ResponseBody;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;
import retrofit2.RxJavaCallAdapterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import rx.Observable;

public class ApiService {
    public ApiXmlyManagerService apiXmlyManager;//获得喜马拉雅
    public ApiBdyyManagerService apiBdyyManager;//百度云音乐
    public ApiLrcManagerService apiLrcManager;//歌词搜索
    private static ApiService apiService;
    private ApiService (){
        String ENDPOINT = "http://mobile.ximalaya.com/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ENDPOINT)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiXmlyManager = retrofit.create(ApiXmlyManagerService.class);
        //-------------
        ENDPOINT = "http://tingapi.ting.baidu.com/v1/restserver/";
        Retrofit bdyyRetrofit = new Retrofit.Builder()
                .baseUrl(ENDPOINT)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiBdyyManager =bdyyRetrofit.create(ApiBdyyManagerService.class);
        ENDPOINT="http://musicdata.baidu.com/data2/lrc/";
        Retrofit lrcRetrofit = new Retrofit.Builder()
                .baseUrl(ENDPOINT)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiLrcManager=lrcRetrofit.create(ApiLrcManagerService.class);
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
        @GET("mobile/others/ca/album/track/321705/true/{pager}/20")
        Observable<Album> getAlbum(@Path("pager")String pager, @QueryMap Map<String,String> map);
        /**
          获得推荐
         * @return
         */
       @GET("mobile/discovery/v1/recommends")
       Observable<DiscoverRecommend> getDiscoverRecommend(@QueryMap Map<String,String> map);
    }

    //百度音乐
    public interface ApiBdyyManagerService{
        String bdyyUrl="?from=android&version=5.6.6.1&channel=360safe&operator=3&format=json&ts=1451464605101&e=bLkVYIBl6FyH5SR9BbVH5Iv7HxAMs0b4xqQNRrBDXPwPoEE6uYJc4pqcgxu%2Bsjft&nw=2&ucf=1&res=1&l2p=0&lpb=&usup=1&lebo=0&";
        /** 歌曲id */
        @GET("ting"+bdyyUrl+"method=baidu.ting.search.catalogSug")
        Observable<SearchId> getSongId(@Query("query") String query);
        /** 专辑封面 */
        @GET("ting"+bdyyUrl+"method=baidu.ting.song.getInfos")
        Observable<AlbumInfo> getAlbumPic(@Query("songid")String songid);
        /** 排行 */
        @GET("ting"+bdyyUrl+"method=baidu.ting.billboard.billCategory")
        Observable<Ranking> getRanking();
        /** 歌单 */
        @GET("ting"+bdyyUrl+"method=baidu.ting.diy.gedan&page_size=30&page_no=1&operator=3")
        Observable<Playlist> getPlaylist();

        /** 歌曲推荐 */
        @GET("ting"+bdyyUrl+"method=baidu.ting.song.getEditorRecommend&num=6")
        Observable<Recommend> getRecommend();
        /** 热门歌单*/
        @GET("ting"+bdyyUrl+"method=baidu.ting.diy.getHotGeDanAndOfficial&num=6")
        Observable<HotPlaylist> getHotPlaylist();
        /** 电台节目*/
        @GET("ting"+bdyyUrl+"method=baidu.ting.radio.getRecommendRadioList&num=6")
        Observable<Radio> getRadio();
        /** King榜单*/
        @GET("ting"+bdyyUrl+"method=baidu.ting.plaza.king")
        Observable<KingRanking> getKingList();
        /** Banner*/
        @GET("ting"+bdyyUrl+"method=baidu.ting.plaza.getFocusPic&num=6")
        Observable<Banner> getBanner();
        /** 歌曲搜索推荐*/
        @GET("ting"+bdyyUrl+"method=baidu.ting.search.hot")
        Observable<SearchRecommended> getSearchRecommend();
        /** 歌曲搜索*/
        @GET("ting"+bdyyUrl+" method=baidu.ting.search.catalogSug")
        Observable<SearchResult> getSearchResult(@Query("query")String query);


    }

    public interface ApiLrcManagerService{
        //搜索歌词
        @GET("{songId}/{songName}")
        Observable<ResponseBody> getLrc(@Path("songId")String songId, @Path("songName")String songName);

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
