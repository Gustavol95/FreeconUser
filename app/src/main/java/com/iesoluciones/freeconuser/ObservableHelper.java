package com.iesoluciones.freeconuser;


import android.util.Log;

import com.iesoluciones.freeconuser.models.Categoria;
import com.iesoluciones.freeconuser.models.CategoriaResponse;
import com.iesoluciones.freeconuser.models.LoginFbResponse;
import com.iesoluciones.freeconuser.models.Prestador;
import com.iesoluciones.freeconuser.models.RegistroBody;
import com.iesoluciones.freeconuser.models.Servicio;
import com.iesoluciones.freeconuser.models.ServicioResponse;
import com.iesoluciones.freeconuser.models.Usuario;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * Created by iedeveloper on 08/08/17.
 */

public class ObservableHelper {

    static final String TAG = ObservableHelper.class.getSimpleName();


    public static Observable<LoginFbResponse> login(String username, String password, String firebasetoken){
        return App.getInstance().getApiRoutes().login(username,password,firebasetoken)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .map((LoginFbResponse data) -> {

                    Usuario user = new Usuario();
                    user.setId(data.getUsuario().getId());
                    user.setNombre(data.getUsuario().getFirst_name());
                    user.setApellido(data.getUsuario().getLast_name());
                    user.setEmail(data.getUsuario().getEmail());
                    user.setAvatar(data.getUsuario().getAvatar());
                    Log.i(TAG, data.toString());
                    App.getInstance().getDaoSession().getUsuarioDao().insertOrReplaceInTx(user);
                    App.getInstance().setToken(data.getToken());
                    return data;
                });
    }


    public static Observable<LoginFbResponse> registrar(RegistroBody registroBody) {
        return App.getInstance().getApiRoutes().registrar(registroBody)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .map((LoginFbResponse data)->{
                    Usuario user = new Usuario();
                    user.setId(data.getUsuario().getId());
                    user.setNombre(data.getUsuario().getFirst_name());
                    user.setApellido(data.getUsuario().getLast_name());
                    user.setEmail(data.getUsuario().getEmail());
                    user.setAvatar(data.getUsuario().getAvatar());
                    user.setCelular(data.getUsuario().getCelular());
                    if (data.getUsuario().getActivado() == 0)
                        user.setActivado(false);
                    else
                        user.setActivado(true);
                     App.getInstance().getDaoSession().getUsuarioDao().insertOrReplaceInTx(user);
                    App.getInstance().setToken(data.getToken());
                    return data;
                });
    }

    public static Observable<LoginFbResponse> activarCuenta(String codigo){
        return App.getInstance().getApiRoutes().
                confirmarRegistro(App.getInstance().getDaoSession().getUsuarioDao().loadAll().get(0).getEmail(),codigo)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .map((LoginFbResponse data)->{
                    Usuario user = new Usuario();
                    user.setId(data.getUsuario().getId());
                    user.setNombre(data.getUsuario().getFirst_name());
                    user.setApellido(data.getUsuario().getLast_name());
                    user.setEmail(data.getUsuario().getEmail());
                    user.setAvatar(data.getUsuario().getAvatar());
                    user.setCelular(data.getUsuario().getCelular());
                    if (data.getUsuario().getActivado() == 0)
                        user.setActivado(false);
                    else
                        user.setActivado(true);
                   App.getInstance().getDaoSession().getUsuarioDao().insertOrReplaceInTx(user);
                    App.getInstance().setToken(data.getToken());
                    return data;
                });

}

    public static Observable<LoginFbResponse> loginFb(String tokenFacebook, String tokenFirebase) {
        return App.getInstance().getApiRoutes().loginFb(tokenFacebook,tokenFirebase)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .map((LoginFbResponse data) -> {

                    Usuario user = new Usuario();
                    user.setId(data.getUsuario().getId());
                    user.setNombre(data.getUsuario().getFirst_name());
                    user.setApellido(data.getUsuario().getLast_name());
                    user.setEmail(data.getUsuario().getEmail());
                    user.setAvatar(data.getUsuario().getAvatar());
                    Log.i(TAG, data.toString());
                    App.getInstance().getDaoSession().getUsuarioDao().insertOrReplaceInTx(user);
                    App.getInstance().setToken(data.getToken());
                    return data;
                });

    }

    public static Observable<List<Servicio>> getServicios() {
        return App.getInstance()
                .getApiRoutes()
                .getServicios()
                .subscribeOn(Schedulers.newThread())
                .map((ServicioResponse info) -> {
                    Log.i(TAG, "AHI ESTA "+info.toString());
                    App.getInstance().getDaoSession().getServicioDao().insertOrReplaceInTx(info.getData());
                    return info.getData();
                })
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<List<Categoria>> getCatgorias() {
        return App.getInstance().getApiRoutes().getCategorias()
                .subscribeOn(Schedulers.newThread())
                .map((CategoriaResponse info) -> {
                    Log.i(TAG, "AHI ESTA "+info.toString());
                    App.getInstance().getDaoSession().getCategoriaDao().insertOrReplaceInTx(info.getData());
                    return info.getData();
                })
                .observeOn(AndroidSchedulers.mainThread());
    }


    public static Observable<List<Prestador>> getPrestadoresPorServicio(String idServicio){
        return App.getInstance().getApiRoutes().getPrestadoresPorServicio(idServicio)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<ResponseBody> solicitar(String idCategoria, String idServicio, String idPrestador, String descripcion){
        return App.getInstance().getApiRoutes().solicitar("Bearer "+App.getInstance().getToken(),idCategoria,idServicio,idPrestador,descripcion)
                .subscribeOn(Schedulers.newThread())
                .map((ResponseBody info) -> {
                   //º Log.i(TAG, "AHI ESTA "+info.source().readUtf8().toString());
                    return info;
                })
                .observeOn(AndroidSchedulers.mainThread());
    }
    public static Observable<ResponseBody> logout(String firebaseToken){
        return App.getInstance().getApiRoutes().logout("Bearer "+App.getInstance().getToken(),firebaseToken)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<LoginFbResponse> finalizarRegistroFb(RegistroBody registroBody) {
        return App.getInstance().getApiRoutes().finalizarRegistroFb(registroBody)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .map((LoginFbResponse data)->{
                    Usuario user = new Usuario();
                    user.setId(data.getUsuario().getId());
                    user.setNombre(data.getUsuario().getFirst_name());
                    user.setApellido(data.getUsuario().getLast_name());
                    user.setEmail(data.getUsuario().getEmail());
                    user.setAvatar(data.getUsuario().getAvatar());
                    user.setCelular(data.getUsuario().getCelular());
                    if (data.getUsuario().getActivado() == 0)
                        user.setActivado(false);
                    else
                        user.setActivado(true);
                    App.getInstance().getDaoSession().getUsuarioDao().insertOrReplaceInTx(user);
                    App.getInstance().setToken(data.getToken());
                    return data;
                });
    }
}



