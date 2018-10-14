package com.prismaplus.entities;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ClientInfo implements Parcelable
{

    @SerializedName("IdEmpresa")
    @Expose
    private String idEmpresa;
    @SerializedName("IdCliente")
    @Expose
    private String idCliente;
    @SerializedName("Nombre")
    @Expose
    private String nombre;
    @SerializedName("TipoCedula")
    @Expose
    private String tipoCedula;
    @SerializedName("Identificacion")
    @Expose
    private String identificacion;
    @SerializedName("IdentificacionExtranjero")
    @Expose
    private String identificacionExtranjero;
    @SerializedName("NombreComercial")
    @Expose
    private String nombreComercial;
    @SerializedName("Provincia")
    @Expose
    private String provincia;
    @SerializedName("Canton")
    @Expose
    private String canton;
    @SerializedName("Distrito")
    @Expose
    private String distrito;
    @SerializedName("Barrio")
    @Expose
    private String barrio;
    @SerializedName("OtrasSenas")
    @Expose
    private String otrasSenas;
    @SerializedName("CodigoPais")
    @Expose
    private String codigoPais;
    @SerializedName("Telefono")
    @Expose
    private String telefono;
    @SerializedName("Fax")
    @Expose
    private String fax;
    @SerializedName("CorreoElectronico")
    @Expose
    private String correoElectronico;
    @SerializedName("Estado")
    @Expose
    private Integer estado;
    @SerializedName("DiasCredito")
    @Expose
    private Integer diasCredito;

    public final static Parcelable.Creator<ClientInfo> CREATOR = new Creator<ClientInfo>() {


        @SuppressWarnings({
                "unchecked"
        })
        public ClientInfo createFromParcel(Parcel in) {
            return new ClientInfo(in);
        }

        public ClientInfo[] newArray(int size) {
            return (new ClientInfo[size]);
        }

    }
            ;

    protected ClientInfo(Parcel in) {
        this.idEmpresa = ((String) in.readValue((String.class.getClassLoader())));
        this.idCliente = ((String) in.readValue((String.class.getClassLoader())));
        this.nombre = ((String) in.readValue((String.class.getClassLoader())));
        this.tipoCedula = ((String) in.readValue((String.class.getClassLoader())));
        this.identificacion = ((String) in.readValue((String.class.getClassLoader())));
        this.identificacionExtranjero = ((String) in.readValue((String.class.getClassLoader())));
        this.nombreComercial = ((String) in.readValue((String.class.getClassLoader())));
        this.provincia = ((String) in.readValue((String.class.getClassLoader())));
        this.canton = ((String) in.readValue((String.class.getClassLoader())));
        this.distrito = ((String) in.readValue((String.class.getClassLoader())));
        this.barrio = ((String) in.readValue((String.class.getClassLoader())));
        this.otrasSenas = ((String) in.readValue((String.class.getClassLoader())));
        this.codigoPais = ((String) in.readValue((String.class.getClassLoader())));
        this.telefono = ((String) in.readValue((String.class.getClassLoader())));
        this.fax = ((String) in.readValue((String.class.getClassLoader())));
        this.correoElectronico = ((String) in.readValue((String.class.getClassLoader())));
        this.estado = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.diasCredito = ((Integer) in.readValue((Integer.class.getClassLoader())));
    }

    public ClientInfo() {
    }

    public String getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(String idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipoCedula() {
        return tipoCedula;
    }

    public void setTipoCedula(String tipoCedula) {
        this.tipoCedula = tipoCedula;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getIdentificacionExtranjero() {
        return identificacionExtranjero;
    }

    public void setIdentificacionExtranjero(String identificacionExtranjero) {
        this.identificacionExtranjero = identificacionExtranjero;
    }

    public String getNombreComercial() {
        return nombreComercial;
    }

    public void setNombreComercial(String nombreComercial) {
        this.nombreComercial = nombreComercial;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getCanton() {
        return canton;
    }

    public void setCanton(String canton) {
        this.canton = canton;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    public String getBarrio() {
        return barrio;
    }

    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }

    public String getOtrasSenas() {
        return otrasSenas;
    }

    public void setOtrasSenas(String otrasSenas) {
        this.otrasSenas = otrasSenas;
    }

    public String getCodigoPais() {
        return codigoPais;
    }

    public void setCodigoPais(String codigoPais) {
        this.codigoPais = codigoPais;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return correoElectronico;
    }

    public void setEmail(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public Integer getDiasCredito() {
        return diasCredito;
    }

    public void setDiasCredito(Integer diasCredito) {
        this.diasCredito = diasCredito;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(idEmpresa);
        dest.writeValue(idCliente);
        dest.writeValue(nombre);
        dest.writeValue(tipoCedula);
        dest.writeValue(identificacion);
        dest.writeValue(identificacionExtranjero);
        dest.writeValue(nombreComercial);
        dest.writeValue(provincia);
        dest.writeValue(canton);
        dest.writeValue(distrito);
        dest.writeValue(barrio);
        dest.writeValue(otrasSenas);
        dest.writeValue(codigoPais);
        dest.writeValue(telefono);
        dest.writeValue(fax);
        dest.writeValue(correoElectronico);
        dest.writeValue(estado);
        dest.writeValue(diasCredito);
    }

    public int describeContents() {
        return 0;
    }

}