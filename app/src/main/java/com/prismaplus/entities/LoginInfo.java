package com.prismaplus.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginInfo {

    @SerializedName("User")
    @Expose
    private Object user;
    @SerializedName("Pass")
    @Expose
    private Object pass;
    @SerializedName("MSJ")
    @Expose
    private String mSJ;
    @SerializedName("Nombre")
    @Expose
    private String nombre;
    @SerializedName("IdEmpresa")
    @Expose
    private Integer idEmpresa;
    @SerializedName("EsCajero")
    @Expose
    private Integer esCajero;
    @SerializedName("AlDia")
    @Expose
    private Integer alDia;
    @SerializedName("Usuario")
    @Expose
    private Object usuario;
    @SerializedName("Clave")
    @Expose
    private Object clave;

    public Object getUser() {
        return user;
    }

    public void setUser(Object user) {
        this.user = user;
    }

    public Object getPass() {
        return pass;
    }

    public void setPass(Object pass) {
        this.pass = pass;
    }

    public String getMSJ() {
        return mSJ;
    }

    public void setMSJ(String mSJ) {
        this.mSJ = mSJ;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Integer idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public Integer getEsCajero() {
        return esCajero;
    }

    public void setEsCajero(Integer esCajero) {
        this.esCajero = esCajero;
    }

    public Integer getAlDia() {
        return alDia;
    }

    public void setAlDia(Integer alDia) {
        this.alDia = alDia;
    }

    public Object getUsuario() {
        return usuario;
    }

    public void setUsuario(Object usuario) {
        this.usuario = usuario;
    }

    public Object getClave() {
        return clave;
    }

    public void setClave(Object clave) {
        this.clave = clave;
    }

}
