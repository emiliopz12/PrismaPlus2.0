package com.prismaplus.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductInfo {

    @SerializedName("EsServicio")
    @Expose
    private Integer esServicio;
    @SerializedName("CodigoArticulo")
    @Expose
    private String codigoArticulo;
    @SerializedName("Descripcion")
    @Expose
    private String descripcion;
    @SerializedName("UnidadDeMedida")
    @Expose
    private String unidadDeMedida;
    @SerializedName("IdEmpresa")
    @Expose
    private Integer idEmpresa;
    @SerializedName("UnidadMedidaDsc")
    @Expose
    private String unidadMedidaDsc;
    @SerializedName("Estado")
    @Expose
    private Integer estado;
    @SerializedName("Precio")
    @Expose
    private float precio;
    @SerializedName("TipoCodigo")
    @Expose
    private String tipoCodigo;
    @SerializedName("CodigoImpuesto")
    @Expose
    private String codigoImpuesto;
    @SerializedName("PorcentajeImpuesto")
    @Expose
    private Integer porcentajeImpuesto;

    public Integer getEsServicio() {
        return esServicio;
    }

    public void setEsServicio(Integer esServicio) {
        this.esServicio = esServicio;
    }

    public String getCodigoArticulo() {
        return codigoArticulo;
    }

    public void setCodigoArticulo(String codigoArticulo) {
        this.codigoArticulo = codigoArticulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUnidadDeMedida() {
        return unidadDeMedida;
    }

    public void setUnidadDeMedida(String unidadDeMedida) {
        this.unidadDeMedida = unidadDeMedida;
    }

    public Integer getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Integer idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public String getUnidadMedidaDsc() {
        return unidadMedidaDsc;
    }

    public void setUnidadMedidaDsc(String unidadMedidaDsc) {
        this.unidadMedidaDsc = unidadMedidaDsc;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public String getTipoCodigo() {
        return tipoCodigo;
    }

    public void setTipoCodigo(String tipoCodigo) {
        this.tipoCodigo = tipoCodigo;
    }

    public String getCodigoImpuesto() {
        return codigoImpuesto;
    }

    public void setCodigoImpuesto(String codigoImpuesto) {
        this.codigoImpuesto = codigoImpuesto;
    }

    public Integer getPorcentajeImpuesto() {
        return porcentajeImpuesto;
    }

    public void setPorcentajeImpuesto(Integer porcentajeImpuesto) {
        this.porcentajeImpuesto = porcentajeImpuesto;
    }

}
