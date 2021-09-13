package com.grove.diet.repository;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ApiClient
{
    private static Retrofit retrofit = null;
    private static ApiInterface REST_CLIENT;

    static {
        setupRestClient();
    }

    public static ApiInterface get() {
        return REST_CLIENT;
    }

    private static void setupRestClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(UrlConstants.URL_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        REST_CLIENT=retrofit.create(ApiInterface.class);
    }

}
