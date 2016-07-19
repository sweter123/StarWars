package com.example.user.starwars.SWAPI;

import android.support.v7.util.SortedList;

import com.example.user.starwars.SWAPI.films.Film;
import com.example.user.starwars.SWAPI.people.Person;
import com.example.user.starwars.SWAPI.planets.Planet;
import com.example.user.starwars.SWAPI.starships.Starship;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by user on 11.07.2016.
 */
public interface StarWarsService {

    @GET("people/")
    Call<ResultSet<Person>> listPeople();

    @GET("films/")
    Call<ResultSet<Film>> listFilms();

    @GET("planets/")
    Observable<ResultSet<Planet>> listPlanet();

    @GET("starships/")
    Call<ResultSet<Starship>> listStarships();
}
