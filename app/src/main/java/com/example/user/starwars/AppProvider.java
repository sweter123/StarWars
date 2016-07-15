package com.example.user.starwars;

import com.example.user.starwars.SWAPI.StarWarsService;
import com.example.user.starwars.database.StarWarsSQLiteOpenhelper;
import com.example.user.starwars.database.films.FilmsRepository;
import com.example.user.starwars.database.people.PeopleRepository;
import com.example.user.starwars.database.planets.PlanetsRepository;
import com.example.user.starwars.database.starships.StarshipsRepository;
import com.google.gson.Gson;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * Created by user on 15.07.2016.
 */
public interface AppProvider {

    OkHttpClient getOkHttpClient();

    Gson getGson();

    Retrofit getRetrofit();

    StarWarsService getStarWarsService();

    StarWarsSQLiteOpenhelper getStarWarsSQLiteOpenHelper();

    StarshipsRepository getStarshipRepository();

    FilmsRepository getFilmsRepository();

    PeopleRepository getPeopleRepository();

    PlanetsRepository getPlanetsRepository();
}
