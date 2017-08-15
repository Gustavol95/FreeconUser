package com.iesoluciones.freeconuser.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by iedeveloper on 09/08/17.
 */

public class RegistroBody {

    @SerializedName("first_name")
    String nombre;
    @SerializedName("last_name")
    String apellido;
    @SerializedName("password")
    String contrasena;
    @SerializedName("email")
    String email;
    @SerializedName("profesion")
    String profesion;
    @SerializedName("descripcion")
    String descripcion;
    @SerializedName("celular")
    String celular;


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

    public String getProfesion() {
        return profesion;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    @Override
    public String toString() {
        return "RegistroBody{" +
                "nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", contrasena='" + contrasena + '\'' +
                ", email='" + email + '\'' +
                ", profesion='" + profesion + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", celular='" + celular + '\'' +
                '}';
    }
}
