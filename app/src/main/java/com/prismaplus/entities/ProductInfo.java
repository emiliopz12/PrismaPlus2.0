package com.prismaplus.entities;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductInfo implements Parcelable {

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
    private Double precio;
    @SerializedName("TipoCodigo")
    @Expose
    private String tipoCodigo;
    @SerializedName("CodigoImpuesto")
    @Expose
    private String codigoImpuesto;
    @SerializedName("PorcentajeImpuesto")
    @Expose
    private Double porcentajeImpuesto;

    public final static Parcelable.Creator<ProductInfo> CREATOR = new Creator<ProductInfo>() {


        @SuppressWarnings({
                "unchecked"
        })
        public ProductInfo createFromParcel(Parcel in) {
            return new ProductInfo(in);
        }

        public ProductInfo[] newArray(int size) {
            return (new ProductInfo[size]);
        }

    }
            ;

    protected ProductInfo(Parcel in) {
        this.idEmpresa = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.codigoArticulo = ((String) in.readValue((String.class.getClassLoader())));
        this.codigoImpuesto = ((String) in.readValue((String.class.getClassLoader())));
        this.descripcion = ((String) in.readValue((String.class.getClassLoader())));
        this.esServicio = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.porcentajeImpuesto = ((Double) in.readValue((Double.class.getClassLoader())));
        this.precio = ((Double) in.readValue((Double.class.getClassLoader())));
        this.tipoCodigo = ((String) in.readValue((String.class.getClassLoader())));
        this.unidadDeMedida = ((String) in.readValue((String.class.getClassLoader())));
        this.unidadMedidaDsc = ((String) in.readValue((String.class.getClassLoader())));
        this.estado = ((Integer) in.readValue((Integer.class.getClassLoader())));
    }

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

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
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

    public Double getPorcentajeImpuesto() {
        return porcentajeImpuesto;
    }

    public void setPorcentajeImpuesto(Double porcentajeImpuesto) {
        this.porcentajeImpuesto = porcentajeImpuesto;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(idEmpresa);
        dest.writeValue(codigoArticulo);
        dest.writeValue(descripcion);
        dest.writeValue(codigoArticulo);
        dest.writeValue(esServicio);
        dest.writeValue(porcentajeImpuesto);
        dest.writeValue(precio);
        dest.writeValue(estado);
        dest.writeValue(unidadDeMedida);
        dest.writeValue(unidadMedidaDsc);
        dest.writeValue(tipoCodigo);
    }

    public int describeContents() {
        return 0;
    }

}
