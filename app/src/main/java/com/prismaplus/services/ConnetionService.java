package com.prismaplus.services;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ConnetionService {
    private static ConnectionInterface singleton;

    public static ConnectionInterface obtenerServicio(){

        if(singleton == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://www.prismasolucionescr.com/plus_app/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            singleton = retrofit.create(ConnectionInterface.class);
        }

        return singleton;
    }
}
