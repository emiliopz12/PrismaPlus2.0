package com.prismaplus.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ClientInfo {

    @SerializedName("IdCliente")
    @Expose
    private Integer idCliente;
    @SerializedName("Nombre")
    @Expose
    private String nombre;

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
