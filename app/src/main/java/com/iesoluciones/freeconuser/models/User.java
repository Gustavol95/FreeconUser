package com.iesoluciones.freeconuser.models;

/**
 * Created by iedeveloper on 10/08/17.
 */

public class User {

    long id;
    String first_name;
    String last_name;
    String email;
    String avatar;
    String profesion;
    String descripcion;
    String celular;
    String calle;
    String num_int;
    String num_ext;
    String colonia;
    String cp;
    String ciudad;
    String estado;
    String tipo_usuario;
    Integer solicitar;
    String codigo_activacion;
    Integer activado;
    LastLogin last_Login;
    String created_at;
    String updated_at;
    String deleted_at;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", email='" + email + '\'' +
                ", avatar='" + avatar + '\'' +
                ", profesion='" + profesion + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", celular='" + celular + '\'' +
                ", calle='" + calle + '\'' +
                ", num_int='" + num_int + '\'' +
                ", num_ext='" + num_ext + '\'' +
                ", colonia='" + colonia + '\'' +
                ", cp='" + cp + '\'' +
                ", ciudad='" + ciudad + '\'' +
                ", estado='" + estado + '\'' +
                ", tipo_usuario='" + tipo_usuario + '\'' +
                ", solicitar=" + solicitar +
                ", codigo_activacion='" + codigo_activacion + '\'' +
                ", activado=" + activado +
                ", last_Login=" + last_Login +
                ", created_at='" + created_at + '\'' +
                ", updated_at='" + updated_at + '\'' +
                ", deleted_at='" + deleted_at + '\'' +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
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

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getNum_int() {
        return num_int;
    }

    public void setNum_int(String num_int) {
        this.num_int = num_int;
    }

    public String getNum_ext() {
        return num_ext;
    }

    public void setNum_ext(String num_ext) {
        this.num_ext = num_ext;
    }

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTipo_usuario() {
        return tipo_usuario;
    }

    public void setTipo_usuario(String tipo_usuario) {
        this.tipo_usuario = tipo_usuario;
    }

    public Integer getSolicitar() {
        return solicitar;
    }

    public void setSolicitar(Integer solicitar) {
        this.solicitar = solicitar;
    }

    public Integer getActivado() {
        return activado;
    }

    public void setActivado(Integer activado) {
        this.activado = activado;
    }

    public String getCodigo_activacion() {
        return codigo_activacion;
    }

    public void setCodigo_activacion(String codigo_activacion) {
        this.codigo_activacion = codigo_activacion;
    }



    public LastLogin getLast_Login() {
        return last_Login;
    }

    public void setLast_Login(LastLogin last_Login) {
        this.last_Login = last_Login;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getDeleted_at() {
        return deleted_at;
    }

    public void setDeleted_at(String deleted_at) {
        this.deleted_at = deleted_at;
    }
}
