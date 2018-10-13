package com.prismaplus.entities;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UnidadInfo implements Parcelable
{

    @SerializedName("CodigoUnidad")
    @Expose
    private String codigoUnidad;
    @SerializedName("Descripcion")
    @Expose
    private String descripcion;
    public final static Parcelable.Creator<UnidadInfo> CREATOR = new Creator<UnidadInfo>() {


        @SuppressWarnings({
                "unchecked"
        })
        public UnidadInfo createFromParcel(Parcel in) {
            return new UnidadInfo(in);
        }

        public UnidadInfo[] newArray(int size) {
            return (new UnidadInfo[size]);
        }

    }
            ;

    protected UnidadInfo(Parcel in) {
        this.codigoUnidad = ((String) in.readValue((String.class.getClassLoader())));
        this.descripcion = ((String) in.readValue((String.class.getClassLoader())));
    }

    public UnidadInfo() {
    }

    public String getCodigoUnidad() {
        return codigoUnidad;
    }

    public void setCodigoUnidad(String codigoUnidad) {
        this.codigoUnidad = codigoUnidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(codigoUnidad);
        dest.writeValue(descripcion);
    }

    public int describeContents() {
        return 0;
    }

}