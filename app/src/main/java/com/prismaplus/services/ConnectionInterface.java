package com.prismaplus.services;

import com.prismaplus.entities.Bill;
import com.prismaplus.entities.BillInfo;
import com.prismaplus.entities.ClientInfo;
import com.prismaplus.entities.LoginInfo;
import com.prismaplus.entities.ProductInfo;

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

public interface ConnectionInterface {

    @GET("Loginapi")
    Call<List<LoginInfo>> doLogin(@Query("usuario") String user, @Query("clave") String pass);

    @GET("Loginapi")
    Call<List<LoginInfo>> forgotPass(@Query("usuario") String user);

    @GET("customersapi")
    Call<List<ClientInfo>> getClients(@Query("idempresa") String company, @Query("idcliente") int pass);

    @GET("productsapi")
    Call<List<ProductInfo>> getProduct(@Query("idempresa") String company, @Query("CodigoArticulo") String articulo);

    @POST("FacturaApi")
    Call<BillInfo> doBill(@Body Bill bill);

    @POST("customersapi")
    Call<ClientInfo> saveClient(@Body ClientInfo client);

    @POST("productsapi")
    Call<ProductInfo> saveProduct(@Body ProductInfo client);

}
