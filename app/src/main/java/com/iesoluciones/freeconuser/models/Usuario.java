package com.iesoluciones.freeconuser.models;

import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Created by iedeveloper on 10/08/17.
 */

@Entity
public class Usuario {
    @Id
    long id;
    @SerializedName("first_name")
    String nombre;
    @SerializedName("last_name")
    String apellido;
    @SerializedName("password")
    String contrasena;
    @SerializedName("email")
    String email;
    @SerializedName("avatar")
    String avatar;
    @SerializedName("celular")
    String celular;
    @SerializedName("activado")
    boolean activado;


    @Generated(hash = 633122678)
    public Usuario(long id, String nombre, String apellido, String contrasena,
            String email, String avatar, String celular, boolean activado) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.contrasena = contrasena;
        this.email = email;
        this.avatar = avatar;
        this.celular = celular;
        this.activado = activado;
    }

    @Generated(hash = 562950751)
    public Usuario() {
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public boolean isActivado() {
        return activado;
    }

    public void setActivado(boolean activado) {
        this.activado = activado;
    }

    public boolean getActivado() {
        return this.activado;
    }
}
