package com.grove.diet.repository;


import com.grove.diet.models.PojoDiet;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET(UrlConstants.GET_DIET)
    Call<PojoDiet> getDiet();
}
