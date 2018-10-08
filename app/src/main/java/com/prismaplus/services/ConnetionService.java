package com.prismaplus.services;

import android.util.Log;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ConnetionService {
    private static ConnectionInterface singleton;
    public static String way;

    public static String URL_PROD = "https://www.prismasolucionescr.com/plus/api/";
    public static String URL_PRUEBAS = "https://www.prismasolucionescr.com/plus_test/api/";

    public static void val(String a){
        way = a.contains("pruebas") ?  URL_PROD : URL_PRUEBAS;
    }

    public static ConnectionInterface obtenerServicio( String url ){
        Log.d("MYURL", url);
        if(singleton == null) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

            httpClient.addInterceptor(logging);
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build())
                    .build();

            singleton = retrofit.create(ConnectionInterface.class);
        }

        return singleton;
    }



}
