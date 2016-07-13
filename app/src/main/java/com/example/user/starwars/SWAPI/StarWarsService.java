package com.example.user.starwars.SWAPI;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by user on 11.07.2016.
 */
public interface StarWarsService {

    @GET("people/")
    Call<ResultSet> listPeople();

    @GET("films/")
    Call<ResultSet> listFilms();
}
