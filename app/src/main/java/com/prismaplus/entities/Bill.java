package com.prismaplus.entities;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Bill {

    @SerializedName("Idempresa")
    @Expose
    private Integer idempresa;
    @SerializedName("IdCliente")
    @Expose
    private Integer idCliente;
    @SerializedName("CondicionVenta")
    @Expose
    private String condicionVenta;
    @SerializedName("Situacion")
    @Expose
    private String situacion;
    @SerializedName("Moneda")
    @Expose
    private String moneda;
    @SerializedName("TipoCambio")
    @Expose
    private Double tipoCambio;
    @SerializedName("FormaPago")
    @Expose
    private String formaPago;
    @SerializedName("Observaciones")
    @Expose
    private String observaciones;
    @SerializedName("Detail")
    @Expose
    private List<Detail> detail = null;
    @SerializedName("TipoAccion")
    @Expose
    private String tipoAccion;
    @SerializedName("IdFactura")
    @Expose
    private Integer idFactura;
    @SerializedName("Usuario")
    @Expose
    private String usuario;

    public Integer getIdempresa() {
        return idempresa;
    }

    public void setIdempresa(Integer idempresa) {
        this.idempresa = idempresa;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public String getCondicionVenta() {
        return condicionVenta;
    }

    public void setCondicionVenta(String condicionVenta) {
        this.condicionVenta = condicionVenta;
    }

    public String getSituacion() {
        return situacion;
    }

    public void setSituacion(String situacion) {
        this.situacion = situacion;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public Double getTipoCambio() {
        return tipoCambio;
    }

    public void setTipoCambio(Double tipoCambio) {
        this.tipoCambio = tipoCambio;
    }

    public String getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(String formaPago) {
        this.formaPago = formaPago;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public List<Detail> getDetail() {
        return detail;
    }

    public void setDetail(List<Detail> detail) {
        this.detail = detail;
    }

    public String getTipoAccion() {
        return tipoAccion;
    }

    public void setTipoAccion(String tipoAccion) {
        this.tipoAccion = tipoAccion;
    }

    public Integer getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(Integer idFactura) {
        this.idFactura = idFactura;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

}

