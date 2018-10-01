package com.prismaplus.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Detail {

    @SerializedName("NumeroLinea")
    @Expose
    private String numeroLinea;
//    @SerializedName("EsGravado")
//    @Expose
//    private String esGravado;
    @SerializedName("CodigoArticulo")
    @Expose
    private String codigoArticulo;
    @SerializedName("Cantidad")
    @Expose
    private String cantidad;
    @SerializedName("PrecioUnitario")
    @Expose
    private String precioUnitario;
    @SerializedName("Subtotal")
    @Expose
    private String subtotal;
    @SerializedName("TotalLinea")
    @Expose
    private String totalLinea;
    @SerializedName("NaturalezaDescuento")
    @Expose
    private String naturalezaDescuento;
    @SerializedName("Descripcion")
    @Expose
    private String descripcion;
    @SerializedName("PorcentajeDescuento")
    @Expose
    private String porcentajeDescuento;
    @SerializedName("MontoDescuento")
    @Expose
    private String montoDescuento;
    @SerializedName("MontoImpuesto")
    @Expose
    private String montoImpuesto;
    @SerializedName("MontoTotal")
    @Expose
    private String montoTotal;

    public String getNumeroLinea() {
        return numeroLinea;
    }

    public void setNumeroLinea(String numeroLinea) {
        this.numeroLinea = numeroLinea;
    }

//    public String getEsGravado() {
//        return esGravado;
//    }
//
//    public void setEsGravado(String esGravado) {
//        this.esGravado = esGravado;
//    }

    public String getCodigoArticulo() {
        return codigoArticulo;
    }

    public void setCodigoArticulo(String codigoArticulo) {
        this.codigoArticulo = codigoArticulo;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(String precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public String getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(String subtotal) {
        this.subtotal = subtotal;
    }

    public String getTotalLinea() {
        return totalLinea;
    }

    public void setTotalLinea(String totalLinea) {
        this.totalLinea = totalLinea;
    }

    public String getNaturalezaDescuento() {
        return naturalezaDescuento;
    }

    public void setNaturalezaDescuento(String naturalezaDescuento) {
        this.naturalezaDescuento = naturalezaDescuento;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPorcentajeDescuento() {
        return porcentajeDescuento;
    }

    public void setPorcentajeDescuento(String porcentajeDescuento) {
        this.porcentajeDescuento = porcentajeDescuento;
    }

    public String getMontoDescuento() {
        return montoDescuento;
    }

    public void setMontoDescuento(String montoDescuento) {
        this.montoDescuento = montoDescuento;
    }

    public String getMontoImpuesto() {
        return montoImpuesto;
    }

    public void setMontoImpuesto(String montoImpuesto) {
        this.montoImpuesto = montoImpuesto;
    }

    public String getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(String montoTotal) {
        this.montoTotal = montoTotal;
    }

}
