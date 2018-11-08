package com.prismaplus.entities;


import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

    public class CompanyLoginInfo implements Parcelable
    {

        @SerializedName("TotalTiquetes")
        @Expose
        private Integer totalTiquetes;
        @SerializedName("TipoFacturacion")
        @Expose
        private Integer tipoFacturacion;
        @SerializedName("Referencia1")
        @Expose
        private Object referencia1;
        @SerializedName("Referencia2")
        @Expose
        private Object referencia2;
        @SerializedName("IdEmpresa")
        @Expose
        private Integer idEmpresa;
        @SerializedName("Nombre")
        @Expose
        private String nombre;
        @SerializedName("TipoCedula")
        @Expose
        private Object tipoCedula;
        @SerializedName("Identificacion")
        @Expose
        private Object identificacion;
        @SerializedName("NombreComercial")
        @Expose
        private Object nombreComercial;
        @SerializedName("Provincia")
        @Expose
        private Object provincia;
        @SerializedName("Canton")
        @Expose
        private Object canton;
        @SerializedName("Distrito")
        @Expose
        private Object distrito;
        @SerializedName("Barrio")
        @Expose
        private Object barrio;
        @SerializedName("OtrasSenas")
        @Expose
        private Object otrasSenas;
        @SerializedName("CodigoPais")
        @Expose
        private Object codigoPais;
        @SerializedName("Telefono")
        @Expose
        private Object telefono;
        @SerializedName("Fax")
        @Expose
        private Object fax;
        @SerializedName("CorreoElectronico")
        @Expose
        private Object correoElectronico;
        @SerializedName("Certificado")
        @Expose
        private Object certificado;
        @SerializedName("FolderSalida")
        @Expose
        private Object folderSalida;
        @SerializedName("EmisorNumero")
        @Expose
        private Object emisorNumero;
        @SerializedName("EmisorTipo")
        @Expose
        private Object emisorTipo;
        @SerializedName("UsuarioHacienda")
        @Expose
        private Object usuarioHacienda;
        @SerializedName("ClaveHacienda")
        @Expose
        private Object claveHacienda;
        @SerializedName("LogoEmpresa")
        @Expose
        private Object logoEmpresa;
        @SerializedName("ConsecutivoFacturas")
        @Expose
        private Object consecutivoFacturas;
        @SerializedName("CodigoImpuesto")
        @Expose
        private Object codigoImpuesto;
        @SerializedName("PorcentajeImpuesto")
        @Expose
        private Integer porcentajeImpuesto;
        @SerializedName("TipoCodigoArticulo")
        @Expose
        private Object tipoCodigoArticulo;
        @SerializedName("Estado")
        @Expose
        private Integer estado;
        @SerializedName("UsaImpuesto")
        @Expose
        private Integer usaImpuesto;
        @SerializedName("FechaUltAct")
        @Expose
        private String fechaUltAct;
        @SerializedName("FechaPago")
        @Expose
        private String fechaPago;
        @SerializedName("TotalFacturas")
        @Expose
        private Integer totalFacturas;
        @SerializedName("TotalNDB")
        @Expose
        private Integer totalNDB;
        @SerializedName("TotalNCR")
        @Expose
        private Integer totalNCR;
        @SerializedName("TotalCotizaciones")
        @Expose
        private Integer totalCotizaciones;
        @SerializedName("Descripcion")
        @Expose
        private Object descripcion;
        @SerializedName("Codigo")
        @Expose
        private Object codigo;
        @SerializedName("EsCajero")
        @Expose
        private Integer esCajero;
        @SerializedName("AlDia")
        @Expose
        private Integer alDia;
        @SerializedName("Decimales")
        @Expose
        private Integer decimales;
        @SerializedName("II")
        @Expose
        private Integer iI;
        public final static Parcelable.Creator<CompanyLoginInfo> CREATOR = new Creator<CompanyLoginInfo>() {


            @SuppressWarnings({
                    "unchecked"
            })
            public CompanyLoginInfo createFromParcel(Parcel in) {
                return new CompanyLoginInfo(in);
            }

            public CompanyLoginInfo[] newArray(int size) {
                return (new CompanyLoginInfo[size]);
            }

        }
                ;

        protected CompanyLoginInfo(Parcel in) {
            this.totalTiquetes = ((Integer) in.readValue((Integer.class.getClassLoader())));
            this.tipoFacturacion = ((Integer) in.readValue((Integer.class.getClassLoader())));
            this.referencia1 = ((Object) in.readValue((Object.class.getClassLoader())));
            this.referencia2 = ((Object) in.readValue((Object.class.getClassLoader())));
            this.idEmpresa = ((Integer) in.readValue((Integer.class.getClassLoader())));
            this.nombre = ((String) in.readValue((String.class.getClassLoader())));
            this.tipoCedula = ((Object) in.readValue((Object.class.getClassLoader())));
            this.identificacion = ((Object) in.readValue((Object.class.getClassLoader())));
            this.nombreComercial = ((Object) in.readValue((Object.class.getClassLoader())));
            this.provincia = ((Object) in.readValue((Object.class.getClassLoader())));
            this.canton = ((Object) in.readValue((Object.class.getClassLoader())));
            this.distrito = ((Object) in.readValue((Object.class.getClassLoader())));
            this.barrio = ((Object) in.readValue((Object.class.getClassLoader())));
            this.otrasSenas = ((Object) in.readValue((Object.class.getClassLoader())));
            this.codigoPais = ((Object) in.readValue((Object.class.getClassLoader())));
            this.telefono = ((Object) in.readValue((Object.class.getClassLoader())));
            this.fax = ((Object) in.readValue((Object.class.getClassLoader())));
            this.correoElectronico = ((Object) in.readValue((Object.class.getClassLoader())));
            this.certificado = ((Object) in.readValue((Object.class.getClassLoader())));
            this.folderSalida = ((Object) in.readValue((Object.class.getClassLoader())));
            this.emisorNumero = ((Object) in.readValue((Object.class.getClassLoader())));
            this.emisorTipo = ((Object) in.readValue((Object.class.getClassLoader())));
            this.usuarioHacienda = ((Object) in.readValue((Object.class.getClassLoader())));
            this.claveHacienda = ((Object) in.readValue((Object.class.getClassLoader())));
            this.logoEmpresa = ((Object) in.readValue((Object.class.getClassLoader())));
            this.consecutivoFacturas = ((Object) in.readValue((Object.class.getClassLoader())));
            this.codigoImpuesto = ((Object) in.readValue((Object.class.getClassLoader())));
            this.porcentajeImpuesto = ((Integer) in.readValue((Integer.class.getClassLoader())));
            this.tipoCodigoArticulo = ((Object) in.readValue((Object.class.getClassLoader())));
            this.estado = ((Integer) in.readValue((Integer.class.getClassLoader())));
            this.usaImpuesto = ((Integer) in.readValue((Integer.class.getClassLoader())));
            this.fechaUltAct = ((String) in.readValue((String.class.getClassLoader())));
            this.fechaPago = ((String) in.readValue((String.class.getClassLoader())));
            this.totalFacturas = ((Integer) in.readValue((Integer.class.getClassLoader())));
            this.totalNDB = ((Integer) in.readValue((Integer.class.getClassLoader())));
            this.totalNCR = ((Integer) in.readValue((Integer.class.getClassLoader())));
            this.totalCotizaciones = ((Integer) in.readValue((Integer.class.getClassLoader())));
            this.descripcion = ((Object) in.readValue((Object.class.getClassLoader())));
            this.codigo = ((Object) in.readValue((Object.class.getClassLoader())));
            this.esCajero = ((Integer) in.readValue((Integer.class.getClassLoader())));
            this.alDia = ((Integer) in.readValue((Integer.class.getClassLoader())));
            this.decimales = ((Integer) in.readValue((Integer.class.getClassLoader())));
            this.iI = ((Integer) in.readValue((Integer.class.getClassLoader())));
        }

        public CompanyLoginInfo() {
        }

        public Integer getTotalTiquetes() {
            return totalTiquetes;
        }

        public void setTotalTiquetes(Integer totalTiquetes) {
            this.totalTiquetes = totalTiquetes;
        }

        public Integer getTipoFacturacion() {
            return tipoFacturacion;
        }

        public void setTipoFacturacion(Integer tipoFacturacion) {
            this.tipoFacturacion = tipoFacturacion;
        }

        public Object getReferencia1() {
            return referencia1;
        }

        public void setReferencia1(Object referencia1) {
            this.referencia1 = referencia1;
        }

        public Object getReferencia2() {
            return referencia2;
        }

        public void setReferencia2(Object referencia2) {
            this.referencia2 = referencia2;
        }

        public Integer getIdEmpresa() {
            return idEmpresa;
        }

        public void setIdEmpresa(Integer idEmpresa) {
            this.idEmpresa = idEmpresa;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public Object getTipoCedula() {
            return tipoCedula;
        }

        public void setTipoCedula(Object tipoCedula) {
            this.tipoCedula = tipoCedula;
        }

        public Object getIdentificacion() {
            return identificacion;
        }

        public void setIdentificacion(Object identificacion) {
            this.identificacion = identificacion;
        }

        public Object getNombreComercial() {
            return nombreComercial;
        }

        public void setNombreComercial(Object nombreComercial) {
            this.nombreComercial = nombreComercial;
        }

        public Object getProvincia() {
            return provincia;
        }

        public void setProvincia(Object provincia) {
            this.provincia = provincia;
        }

        public Object getCanton() {
            return canton;
        }

        public void setCanton(Object canton) {
            this.canton = canton;
        }

        public Object getDistrito() {
            return distrito;
        }

        public void setDistrito(Object distrito) {
            this.distrito = distrito;
        }

        public Object getBarrio() {
            return barrio;
        }

        public void setBarrio(Object barrio) {
            this.barrio = barrio;
        }

        public Object getOtrasSenas() {
            return otrasSenas;
        }

        public void setOtrasSenas(Object otrasSenas) {
            this.otrasSenas = otrasSenas;
        }

        public Object getCodigoPais() {
            return codigoPais;
        }

        public void setCodigoPais(Object codigoPais) {
            this.codigoPais = codigoPais;
        }

        public Object getTelefono() {
            return telefono;
        }

        public void setTelefono(Object telefono) {
            this.telefono = telefono;
        }

        public Object getFax() {
            return fax;
        }

        public void setFax(Object fax) {
            this.fax = fax;
        }

        public Object getCorreoElectronico() {
            return correoElectronico;
        }

        public void setCorreoElectronico(Object correoElectronico) {
            this.correoElectronico = correoElectronico;
        }

        public Object getCertificado() {
            return certificado;
        }

        public void setCertificado(Object certificado) {
            this.certificado = certificado;
        }

        public Object getFolderSalida() {
            return folderSalida;
        }

        public void setFolderSalida(Object folderSalida) {
            this.folderSalida = folderSalida;
        }

        public Object getEmisorNumero() {
            return emisorNumero;
        }

        public void setEmisorNumero(Object emisorNumero) {
            this.emisorNumero = emisorNumero;
        }

        public Object getEmisorTipo() {
            return emisorTipo;
        }

        public void setEmisorTipo(Object emisorTipo) {
            this.emisorTipo = emisorTipo;
        }

        public Object getUsuarioHacienda() {
            return usuarioHacienda;
        }

        public void setUsuarioHacienda(Object usuarioHacienda) {
            this.usuarioHacienda = usuarioHacienda;
        }

        public Object getClaveHacienda() {
            return claveHacienda;
        }

        public void setClaveHacienda(Object claveHacienda) {
            this.claveHacienda = claveHacienda;
        }

        public Object getLogoEmpresa() {
            return logoEmpresa;
        }

        public void setLogoEmpresa(Object logoEmpresa) {
            this.logoEmpresa = logoEmpresa;
        }

        public Object getConsecutivoFacturas() {
            return consecutivoFacturas;
        }

        public void setConsecutivoFacturas(Object consecutivoFacturas) {
            this.consecutivoFacturas = consecutivoFacturas;
        }

        public Object getCodigoImpuesto() {
            return codigoImpuesto;
        }

        public void setCodigoImpuesto(Object codigoImpuesto) {
            this.codigoImpuesto = codigoImpuesto;
        }

        public Integer getPorcentajeImpuesto() {
            return porcentajeImpuesto;
        }

        public void setPorcentajeImpuesto(Integer porcentajeImpuesto) {
            this.porcentajeImpuesto = porcentajeImpuesto;
        }

        public Object getTipoCodigoArticulo() {
            return tipoCodigoArticulo;
        }

        public void setTipoCodigoArticulo(Object tipoCodigoArticulo) {
            this.tipoCodigoArticulo = tipoCodigoArticulo;
        }

        public Integer getEstado() {
            return estado;
        }

        public void setEstado(Integer estado) {
            this.estado = estado;
        }

        public Integer getUsaImpuesto() {
            return usaImpuesto;
        }

        public void setUsaImpuesto(Integer usaImpuesto) {
            this.usaImpuesto = usaImpuesto;
        }

        public String getFechaUltAct() {
            return fechaUltAct;
        }

        public void setFechaUltAct(String fechaUltAct) {
            this.fechaUltAct = fechaUltAct;
        }

        public String getFechaPago() {
            return fechaPago;
        }

        public void setFechaPago(String fechaPago) {
            this.fechaPago = fechaPago;
        }

        public Integer getTotalFacturas() {
            return totalFacturas;
        }

        public void setTotalFacturas(Integer totalFacturas) {
            this.totalFacturas = totalFacturas;
        }

        public Integer getTotalNDB() {
            return totalNDB;
        }

        public void setTotalNDB(Integer totalNDB) {
            this.totalNDB = totalNDB;
        }

        public Integer getTotalNCR() {
            return totalNCR;
        }

        public void setTotalNCR(Integer totalNCR) {
            this.totalNCR = totalNCR;
        }

        public Integer getTotalCotizaciones() {
            return totalCotizaciones;
        }

        public void setTotalCotizaciones(Integer totalCotizaciones) {
            this.totalCotizaciones = totalCotizaciones;
        }

        public Object getDescripcion() {
            return descripcion;
        }

        public void setDescripcion(Object descripcion) {
            this.descripcion = descripcion;
        }

        public Object getCodigo() {
            return codigo;
        }

        public void setCodigo(Object codigo) {
            this.codigo = codigo;
        }

        public Integer getEsCajero() {
            return esCajero;
        }

        public void setEsCajero(Integer esCajero) {
            this.esCajero = esCajero;
        }

        public Integer getAlDia() {
            return alDia;
        }

        public void setAlDia(Integer alDia) {
            this.alDia = alDia;
        }

        public Integer getDecimales() {
            return decimales;
        }

        public void setDecimales(Integer decimales) {
            this.decimales = decimales;
        }

        public Integer getII() {
            return iI;
        }

        public void setII(Integer iI) {
            this.iI = iI;
        }

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeValue(totalTiquetes);
            dest.writeValue(tipoFacturacion);
            dest.writeValue(referencia1);
            dest.writeValue(referencia2);
            dest.writeValue(idEmpresa);
            dest.writeValue(nombre);
            dest.writeValue(tipoCedula);
            dest.writeValue(identificacion);
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
            dest.writeValue(certificado);
            dest.writeValue(folderSalida);
            dest.writeValue(emisorNumero);
            dest.writeValue(emisorTipo);
            dest.writeValue(usuarioHacienda);
            dest.writeValue(claveHacienda);
            dest.writeValue(logoEmpresa);
            dest.writeValue(consecutivoFacturas);
            dest.writeValue(codigoImpuesto);
            dest.writeValue(porcentajeImpuesto);
            dest.writeValue(tipoCodigoArticulo);
            dest.writeValue(estado);
            dest.writeValue(usaImpuesto);
            dest.writeValue(fechaUltAct);
            dest.writeValue(fechaPago);
            dest.writeValue(totalFacturas);
            dest.writeValue(totalNDB);
            dest.writeValue(totalNCR);
            dest.writeValue(totalCotizaciones);
            dest.writeValue(descripcion);
            dest.writeValue(codigo);
            dest.writeValue(esCajero);
            dest.writeValue(alDia);
            dest.writeValue(decimales);
            dest.writeValue(iI);
        }

        public int describeContents() {
            return 0;
        }

    }
