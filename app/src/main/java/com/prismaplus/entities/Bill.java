package com.prismaplus.entities;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Bill {

    @SerializedName("Idempresa")
    @Expose
    private String idempresa;
    @SerializedName("IdCliente")
    @Expose
    private String idCliente;
    @SerializedName("CondicionVenta")
    @Expose
    private String condicionVenta;
    @SerializedName("Situacion")
    @Expose
    private String situacion;
    @SerializedName("CodigoMoneda")
    @Expose
    private String moneda;
    @SerializedName("TipoCambio")
    @Expose
    private String tipoCambio;
    @SerializedName("FormaPago")
    @Expose
    private String formaPago;
    @SerializedName("Observaciones")
    @Expose
    private String observaciones;
    @SerializedName("Detalle_Factura")
    @Expose
    private List<Detail> detail = null;
    @SerializedName("TipoAccion")
    @Expose
    private String tipoAccion;
    @SerializedName("IdFactura")
    @Expose
    private String idFactura;
    @SerializedName("Usuario")
    @Expose
    private String usuario;

    public String getIdempresa() {
        return idempresa;
    }

    public void setIdempresa(String idempresa) {
        this.idempresa = idempresa;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
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

    public String getTipoCambio() {
        return tipoCambio;
    }

    public void setTipoCambio(String tipoCambio) {
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

    public String getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(String idFactura) {
        this.idFactura = idFactura;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

}

