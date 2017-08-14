package com.iesoluciones.freeconuser.models;

import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Created by iedeveloper on 08/08/17.
 */

@Entity
public class Servicio {

    @SerializedName("id")
    @Id
    long id;
    @SerializedName("categoria_id")
    long categoriaId;
    @SerializedName("nombre")
    String nombre;



    @Generated(hash = 168516395)
    public Servicio(long id, long categoriaId, String nombre) {
        this.id = id;
        this.categoriaId = categoriaId;
        this.nombre = nombre;
    }

    @Generated(hash = 780542706)
    public Servicio() {
    }



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(long categoriaId) {
        this.categoriaId = categoriaId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Servicio{" +
                "id=" + id +
                ", categoriaId=" + categoriaId +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
