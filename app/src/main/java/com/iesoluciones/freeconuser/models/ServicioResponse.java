package com.iesoluciones.freeconuser.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by iedeveloper on 08/08/17.
 */

public class ServicioResponse {

    @SerializedName("data")
    List<Servicio> data;

    public List<Servicio> getData() {
        return data;
    }

    public void setData(List<Servicio> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ServicioResponse{" +
                "data=" + data +
                '}';
    }
}
