package com.iesoluciones.freeconuser;


import android.util.Log;

import com.iesoluciones.freeconuser.models.LoginFbResponse;
import com.iesoluciones.freeconuser.models.RegistroBody;
import com.iesoluciones.freeconuser.models.Usuario;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by iedeveloper on 08/08/17.
 */

public class ObservableHelper {

    static final String TAG = ObservableHelper.class.getSimpleName();


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

    public static Observable<LoginFbResponse> loginFb(String token) {
        return App.getInstance().getApiRoutes().loginFb(token)
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
}



