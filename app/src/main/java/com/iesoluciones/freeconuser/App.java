package com.iesoluciones.freeconuser;

import android.app.Application;

import com.iesoluciones.freeconuser.network.interceptors.LogInterceptor;

import org.greenrobot.greendao.database.Database;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;

/**
 * Created by iedeveloper on 11/08/17.
 */

public class App extends Application {

    static final String[] URLS={
            "http://10.112.32.244/freecon/v1/",
            "http://10.112.32.135/freecon/backend/public/"
    };
    public static final String BASE_URL =URLS[0];
    private static App shareInstance;
   // private DaoSession daoSession;
    ApiRoutes apiRoutes;
    String token;

    public static synchronized App getInstance(){
        return shareInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        shareInstance = this;
       // DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this,"dbFreecon");
       // Database db = helper.getWritableDb();
       // daoSession = new DaoMaster(db).newSession();
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new LogInterceptor())
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build();

        apiRoutes = retrofit.create(ApiRoutes.class);
    }
    public interface ApiRoutes{


    }

}
