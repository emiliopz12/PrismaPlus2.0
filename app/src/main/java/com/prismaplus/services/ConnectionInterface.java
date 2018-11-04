package com.prismaplus.services;

import com.prismaplus.entities.Bill;
import com.prismaplus.entities.BillInfo;
import com.prismaplus.entities.ClientInfo;
import com.prismaplus.entities.CompanyLoginInfo;
import com.prismaplus.entities.ListDocsInfo;
import com.prismaplus.entities.LoginInfo;
import com.prismaplus.entities.ProductCalc;
import com.prismaplus.entities.ProductInfo;
import com.prismaplus.entities.UnidadInfo;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface ConnectionInterface {

    @GET("Loginapi")
    Call<List<LoginInfo>> doLogin(@Query("usuario") String user, @Query("clave") String pass);

    @GET("Loginapi")
    Call<List<LoginInfo>> forgotPass(@Query("usuario") String user);

    @GET("customersapi")
    Call<List<ClientInfo>> getClients(@Query("idempresa") String company, @Query("idcliente") int pass);

    @GET("productsapi")
    Call<List<ProductInfo>> getProduct(@Query("idempresa") String company, @Query("CodigoArticulo") String articulo);

    @GET("productsapi")
    Call<List<ProductCalc>> getProductCalc(@Query("idempresa") String company, @Query("CodigoArticulo") String articulo
            , @Query("Cantidad") String Cantidad, @Query("precio") String precio, @Query("descuento") String descuento);

    @GET("facturasapi")
    Call<List<ListDocsInfo>> getFacturasListado(@Query("idempresa") String company, @Query("inicio") String inicio, @Query("fin")
            String fin, @Query("TipoDocumento") String tipoDocumento , @Query("PuntodeVenta") int puntodeVenta);

    @POST("FacturaApi")
    Call<BillInfo> doBill(@Body Bill bill);

    @POST("customersapi")
    Call<ClientInfo> saveClient(@Body ClientInfo client);

    @POST("productsapi")
    Call<ProductInfo> saveProduct(@Body ProductInfo client);

    @GET("Unidadesapi")
    Call<List<UnidadInfo>> getUnidad();

    @POST("ReenviarApi")
    Call<Object> reenviarApi(@Query("IdEmpresa") String IdEmpresa, @Query("IdFactura") String IdFactura, @Query("IdCliente") String IdCliente);


    @GET("Ciasapi")
    Call<List<CompanyLoginInfo>> getCompaniesLogin(@Query("usuario") String user);

    @GET
    Call<Object> printFact(@Url String url, @Query("IdFactura") String IdFactura, @Query("Informe") String empresa, @Query("reimpresion") String reimpresion);

}
