package com.iesoluciones.freeconuser;

import android.app.Application;

import com.iesoluciones.freeconuser.models.CategoriaResponse;
import com.iesoluciones.freeconuser.models.DaoMaster;
import com.iesoluciones.freeconuser.models.DaoSession;
import com.iesoluciones.freeconuser.models.LoginFbResponse;
import com.iesoluciones.freeconuser.models.Prestador;
import com.iesoluciones.freeconuser.models.RegistroBody;
import com.iesoluciones.freeconuser.models.ServicioResponse;
import com.iesoluciones.freeconuser.network.interceptors.LogInterceptor;

import org.greenrobot.greendao.database.Database;

import java.util.List;

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
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

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
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this,"dbFreeconUser");
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
        Observable<ResponseBody> login(@Field("email") String email, @Field("password") String password, @Field("token") String tokenFirebase);


        @POST("clientes/register")
        Observable<LoginFbResponse> registrar(@Body RegistroBody servicio);


        @FormUrlEncoded
        @POST("clientes/confirmaccount")
        Observable<LoginFbResponse> confirmarRegistro(@Field("email") String email, @Field("codigo") String codigo);

        @FormUrlEncoded
        @POST("clientes/login-fb")
        Observable<LoginFbResponse> loginFb(@Field("token") String tokenFacebook, @Field("token_firebase") String tokenFirebase);

        @GET("clientes/servicios")
        Observable<ServicioResponse> getServicios();

        @GET("clientes/categorias")
        Observable<CategoriaResponse> getCategorias();


        @GET("clientes/proveedores/servcate")
        Observable<List<Prestador>> getPrestadoresPorServicio(@Query("servicio") String servicio);

        @FormUrlEncoded
        @POST("clientes/solicitudes")
        Observable<ResponseBody> solicitar(@Header("Authorization") String token,@Field("categoria_id") String idCategoria, @Field("servicio_id") String idServicio, @Field("user_proveedor_id") String idPrestador, @Field("descripcion") String descripcion);

        @FormUrlEncoded
        @POST("clientes/logoutfirebase")
        Observable<ResponseBody> logout(@Header("Authorization") String jwtToken, @Field("token") String firebaseToken);

        @PUT("clientes/finalizarregistro-fb")
        Observable<LoginFbResponse> finalizarRegistroFb(@Body RegistroBody servicio);

    }

}
