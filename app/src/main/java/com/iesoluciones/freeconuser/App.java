package com.iesoluciones.freeconuser;

import android.app.Application;

import com.iesoluciones.freeconuser.models.DaoMaster;
import com.iesoluciones.freeconuser.models.DaoSession;
import com.iesoluciones.freeconuser.models.LoginFbResponse;
import com.iesoluciones.freeconuser.models.RegistroBody;
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
    public static final String BASE_URL =URLS[1];
    private static App shareInstance;
    private DaoSession daoSession;
    ApiRoutes apiRoutes;
    String token;

    public static synchronized App getInstance(){
        return shareInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        shareInstance = this;
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this,"dbFreecon");
        Database db = helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();
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

    public ApiRoutes getApiRoutes() {
        return apiRoutes;
    }

    public void setApiRoutes(ApiRoutes apiRoutes) {
        this.apiRoutes = apiRoutes;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }

    public interface ApiRoutes{

        @FormUrlEncoded
        @POST("clientes/login")
        Observable<ResponseBody> login(@Field("email") String email, @Field("password") String password);


        @POST("clientes/register")
        Observable<LoginFbResponse> registrar(@Body RegistroBody servicio);


        @FormUrlEncoded
        @POST("clientes/confirmaccount")
        Observable<LoginFbResponse> confirmarRegistro(@Field("email") String email, @Field("codigo") String codigo);

        @FormUrlEncoded
        @POST("clientes/login-fb")
        Observable<LoginFbResponse> loginFb(@Field("token") String token);

    }

}
