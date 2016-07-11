package com.example.user.starwars;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by user on 11.07.2016.
 */
public interface StarWarsService {

    @GET("people/")
    Call<ResultSet> listPeople();

}
