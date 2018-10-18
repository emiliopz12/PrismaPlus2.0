package com.prismaplus.entities;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductCalc implements Parcelable
{

    @SerializedName("PrecioSI")
    @Expose
    private Double precioSI;
    @SerializedName("PorcentajeDescuento")
    @Expose
    private Double porcentajeDescuento;
    @SerializedName("MontoDescuento")
    @Expose
    private Double montoDescuento;
    @SerializedName("MontoImpuesto")
    @Expose
    private Double montoImpuesto;
    @SerializedName("TotalLinea")
    @Expose
    private Double totalLinea;
    @SerializedName("Cantidad")
    @Expose
    private Double cantidad;
    @SerializedName("Descuento")
    @Expose
    private Double descuento;
    @SerializedName("Impuesto")
    @Expose
    private Double impuesto;
    @SerializedName("Total")
    @Expose
    private Double total;
    @SerializedName("Fin")
    @Expose
    private Object fin;
    @SerializedName("Inicio")
    @Expose
    private Object inicio;
    @SerializedName("Nombre")
    @Expose
    private Object nombre;
    @SerializedName("Identificacion")
    @Expose
    private Object identificacion;
    @SerializedName("Consecutivo")
    @Expose
    private Object consecutivo;
    @SerializedName("Fecha")
    @Expose
    private Object fecha;
    @SerializedName("EsServicio")
    @Expose
    private Double esServicio;
    @SerializedName("CodigoArticulo")
    @Expose
    private Object codigoArticulo;
    @SerializedName("Descripcion")
    @Expose
    private Object descripcion;
    @SerializedName("UnidadDeMedida")
    @Expose
    private Object unidadDeMedida;
    @SerializedName("TipoAccion")
    @Expose
    private Object tipoAccion;
    @SerializedName("IdEmpresa")
    @Expose
    private Double idEmpresa;
    @SerializedName("UnidadMedidaDsc")
    @Expose
    private Object unidadMedidaDsc;
    @SerializedName("Estado")
    @Expose
    private Double estado;
    @SerializedName("Precio")
    @Expose
    private Double precio;
    @SerializedName("TipoCodigo")
    @Expose
    private Object tipoCodigo;
    @SerializedName("CodigoImpuesto")
    @Expose
    private Object codigoImpuesto;
    @SerializedName("PorcentajeImpuesto")
    @Expose
    private Double porcentajeImpuesto;
    public final static Parcelable.Creator<ProductCalc> CREATOR = new Creator<ProductCalc>() {


        @SuppressWarnings({
                "unchecked"
        })
        public ProductCalc createFromParcel(Parcel in) {
            return new ProductCalc(in);
        }

        public ProductCalc[] newArray(int size) {
            return (new ProductCalc[size]);
        }

    }
            ;

    protected ProductCalc(Parcel in) {
        this.precioSI = ((Double) in.readValue((Double.class.getClassLoader())));
        this.porcentajeDescuento = ((Double) in.readValue((Double.class.getClassLoader())));
        this.montoDescuento = ((Double) in.readValue((Double.class.getClassLoader())));
        this.montoImpuesto = ((Double) in.readValue((Double.class.getClassLoader())));
        this.totalLinea = ((Double) in.readValue((Double.class.getClassLoader())));
        this.cantidad = ((Double) in.readValue((Double.class.getClassLoader())));
        this.descuento = ((Double) in.readValue((Double.class.getClassLoader())));
        this.impuesto = ((Double) in.readValue((Double.class.getClassLoader())));
        this.total = ((Double) in.readValue((Double.class.getClassLoader())));
        this.fin = ((Object) in.readValue((Object.class.getClassLoader())));
        this.inicio = ((Object) in.readValue((Object.class.getClassLoader())));
        this.nombre = ((Object) in.readValue((Object.class.getClassLoader())));
        this.identificacion = ((Object) in.readValue((Object.class.getClassLoader())));
        this.consecutivo = ((Object) in.readValue((Object.class.getClassLoader())));
        this.fecha = ((Object) in.readValue((Object.class.getClassLoader())));
        this.esServicio = ((Double) in.readValue((Double.class.getClassLoader())));
        this.codigoArticulo = ((Object) in.readValue((Object.class.getClassLoader())));
        this.descripcion = ((Object) in.readValue((Object.class.getClassLoader())));
        this.unidadDeMedida = ((Object) in.readValue((Object.class.getClassLoader())));
        this.tipoAccion = ((Object) in.readValue((Object.class.getClassLoader())));
        this.idEmpresa = ((Double) in.readValue((Double.class.getClassLoader())));
        this.unidadMedidaDsc = ((Object) in.readValue((Object.class.getClassLoader())));
        this.estado = ((Double) in.readValue((Double.class.getClassLoader())));
        this.precio = ((Double) in.readValue((Double.class.getClassLoader())));
        this.tipoCodigo = ((Object) in.readValue((Object.class.getClassLoader())));
        this.codigoImpuesto = ((Object) in.readValue((Object.class.getClassLoader())));
        this.porcentajeImpuesto = ((Double) in.readValue((Double.class.getClassLoader())));
    }

    public ProductCalc() {
    }

    public Double getPrecioSI() {
        return precioSI;
    }

    public void setPrecioSI(Double precioSI) {
        this.precioSI = precioSI;
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

    public Double getTotalLinea() {
        return totalLinea;
    }

    public void setTotalLinea(Double totalLinea) {
        this.totalLinea = totalLinea;
    }

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    public Double getDescuento() {
        return descuento;
    }

    public void setDescuento(Double descuento) {
        this.descuento = descuento;
    }

    public Double getImpuesto() {
        return impuesto;
    }

    public void setImpuesto(Double impuesto) {
        this.impuesto = impuesto;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Object getFin() {
        return fin;
    }

    public void setFin(Object fin) {
        this.fin = fin;
    }

    public Object getInicio() {
        return inicio;
    }

    public void setInicio(Object inicio) {
        this.inicio = inicio;
    }

    public Object getNombre() {
        return nombre;
    }

    public void setNombre(Object nombre) {
        this.nombre = nombre;
    }

    public Object getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(Object identificacion) {
        this.identificacion = identificacion;
    }

    public Object getConsecutivo() {
        return consecutivo;
    }

    public void setConsecutivo(Object consecutivo) {
        this.consecutivo = consecutivo;
    }

    public Object getFecha() {
        return fecha;
    }

    public void setFecha(Object fecha) {
        this.fecha = fecha;
    }

    public Double getEsServicio() {
        return esServicio;
    }

    public void setEsServicio(Double esServicio) {
        this.esServicio = esServicio;
    }

    public Object getCodigoArticulo() {
        return codigoArticulo;
    }

    public void setCodigoArticulo(Object codigoArticulo) {
        this.codigoArticulo = codigoArticulo;
    }

    public Object getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(Object descripcion) {
        this.descripcion = descripcion;
    }

    public Object getUnidadDeMedida() {
        return unidadDeMedida;
    }

    public void setUnidadDeMedida(Object unidadDeMedida) {
        this.unidadDeMedida = unidadDeMedida;
    }

    public Object getTipoAccion() {
        return tipoAccion;
    }

    public void setTipoAccion(Object tipoAccion) {
        this.tipoAccion = tipoAccion;
    }

    public Double getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Double idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public Object getUnidadMedidaDsc() {
        return unidadMedidaDsc;
    }

    public void setUnidadMedidaDsc(Object unidadMedidaDsc) {
        this.unidadMedidaDsc = unidadMedidaDsc;
    }

    public Double getEstado() {
        return estado;
    }

    public void setEstado(Double estado) {
        this.estado = estado;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Object getTipoCodigo() {
        return tipoCodigo;
    }

    public void setTipoCodigo(Object tipoCodigo) {
        this.tipoCodigo = tipoCodigo;
    }

    public Object getCodigoImpuesto() {
        return codigoImpuesto;
    }

    public void setCodigoImpuesto(Object codigoImpuesto) {
        this.codigoImpuesto = codigoImpuesto;
    }

    public Double getPorcentajeImpuesto() {
        return porcentajeImpuesto;
    }

    public void setPorcentajeImpuesto(Double porcentajeImpuesto) {
        this.porcentajeImpuesto = porcentajeImpuesto;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(precioSI);
        dest.writeValue(porcentajeDescuento);
        dest.writeValue(montoDescuento);
        dest.writeValue(montoImpuesto);
        dest.writeValue(totalLinea);
        dest.writeValue(cantidad);
        dest.writeValue(descuento);
        dest.writeValue(impuesto);
        dest.writeValue(total);
        dest.writeValue(fin);
        dest.writeValue(inicio);
        dest.writeValue(nombre);
        dest.writeValue(identificacion);
        dest.writeValue(consecutivo);
        dest.writeValue(fecha);
        dest.writeValue(esServicio);
        dest.writeValue(codigoArticulo);
        dest.writeValue(descripcion);
        dest.writeValue(unidadDeMedida);
        dest.writeValue(tipoAccion);
        dest.writeValue(idEmpresa);
        dest.writeValue(unidadMedidaDsc);
        dest.writeValue(estado);
        dest.writeValue(precio);
        dest.writeValue(tipoCodigo);
        dest.writeValue(codigoImpuesto);
        dest.writeValue(porcentajeImpuesto);
    }

    public int describeContents() {
        return 0;
    }

}