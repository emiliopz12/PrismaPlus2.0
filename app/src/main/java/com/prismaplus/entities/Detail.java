package com.prismaplus.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Detail {

    @SerializedName("NumeroLinea")
    @Expose
    private Integer numeroLinea;
    @SerializedName("EsGravado")
    @Expose
    private Integer esGravado;
    @SerializedName("CodigoArticulo")
    @Expose
    private String codigoArticulo;
    @SerializedName("Cantidad")
    @Expose
    private Integer cantidad;
    @SerializedName("PrecioUnitario")
    @Expose
    private Double precioUnitario;
    @SerializedName("Subtotal")
    @Expose
    private Double subtotal;
    @SerializedName("TotalLinea")
    @Expose
    private Double totalLinea;
    @SerializedName("NaturalezaDescuento")
    @Expose
    private String naturalezaDescuento;
    @SerializedName("PorcentajeDescuento")
    @Expose
    private Double porcentajeDescuento;
    @SerializedName("MontoDescuento")
    @Expose
    private Double montoDescuento;
    @SerializedName("MontoImpuesto")
    @Expose
    private Double montoImpuesto;
    @SerializedName("MontoTotal")
    @Expose
    private Double montoTotal;

    public Integer getNumeroLinea() {
        return numeroLinea;
    }

    public void setNumeroLinea(Integer numeroLinea) {
        this.numeroLinea = numeroLinea;
    }

    public Integer getEsGravado() {
        return esGravado;
    }

    public void setEsGravado(Integer esGravado) {
        this.esGravado = esGravado;
    }

    public String getCodigoArticulo() {
        return codigoArticulo;
    }

    public void setCodigoArticulo(String codigoArticulo) {
        this.codigoArticulo = codigoArticulo;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(Double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    public Double getTotalLinea() {
        return totalLinea;
    }

    public void setTotalLinea(Double totalLinea) {
        this.totalLinea = totalLinea;
    }

    public String getNaturalezaDescuento() {
        return naturalezaDescuento;
    }

    public void setNaturalezaDescuento(String naturalezaDescuento) {
        this.naturalezaDescuento = naturalezaDescuento;
    }

    public Double getPorcentajeDescuento() {
        return porcentajeDescuento;
    }

    public void setPorcentajeDescuento(Double porcentajeDescuento) {
        this.porcentajeDescuento = porcentajeDescuento;
    }

    public Double getMontoDescuento() {
        return montoDescuento;
    }

    public void setMontoDescuento(Double montoDescuento) {
        this.montoDescuento = montoDescuento;
    }

    public Double getMontoImpuesto() {
        return montoImpuesto;
    }

    public void setMontoImpuesto(Double montoImpuesto) {
        this.montoImpuesto = montoImpuesto;
    }

    public Double getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(Double montoTotal) {
        this.montoTotal = montoTotal;
    }

}
