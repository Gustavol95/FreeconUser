package com.iesoluciones.freeconuser.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by iedeveloper on 10/08/17.
 */


public class LoginFbResponse {

    @SerializedName("user")
    User usuario;
    String token;

    public User getUsuario() {
        return usuario;
    }

    public void setUsuario(User usuario) {
        this.usuario = usuario;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "LoginFbResponse{" +
                "usuario=" + usuario +
                ", token='" + token + '\'' +
                '}';
    }
}
