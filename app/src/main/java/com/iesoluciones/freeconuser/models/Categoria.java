package com.iesoluciones.freeconuser.models;

import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Created by iedeveloper on 09/08/17.
 */
@Entity
public class Categoria{

    @Id
    long id;
    @SerializedName("nombre")
    String nombre;



    @Generated(hash = 1341272489)
    public Categoria(long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    @Generated(hash = 577285458)
    public Categoria() {
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

    @Override
    public String toString() {
        return "Categoria{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}