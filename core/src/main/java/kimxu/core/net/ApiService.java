package kimxu.core.net;

import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;
import retrofit2.RxJavaCallAdapterFactory;

public class ApiService {
    public ApiBdyyManagerService apiBdyyManager;//百度云音乐
    private static ApiService apiService;
    private ApiService (){
        String ENDPOINT = "http://tingapi.ting.baidu.com/v1/restserver/";
        Retrofit bdyyRetrofit = new Retrofit.Builder()
                .baseUrl(ENDPOINT)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiBdyyManager =bdyyRetrofit.create(ApiBdyyManagerService.class);

    }
    public static ApiService getInstance(){
        if (apiService==null){
            synchronized (ApiService.class) {
                apiService = new ApiService();
            }
        }
        return apiService;
    }


    //百度音乐
    public interface ApiBdyyManagerService{

    }
}
