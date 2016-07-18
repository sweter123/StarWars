package com.example.user.starwars.mvp.presenter;

import android.content.Context;

import com.example.user.starwars.App;
import com.example.user.starwars.AppProvider;
import com.example.user.starwars.R;
import com.example.user.starwars.SWAPI.ResultSet;
import com.example.user.starwars.SWAPI.StarWarsService;
import com.example.user.starwars.SWAPI.planets.Planet;
import com.example.user.starwars.database.StarWarsSQLiteOpenhelper;
import com.example.user.starwars.database.people.PeopleRepository;
import com.example.user.starwars.database.people.specification.PeopleSpecification;
import com.example.user.starwars.database.planets.PlanetsRepository;
import com.example.user.starwars.database.planets.specification.PlanetsSpecification;
import com.example.user.starwars.mvp.contract.PeopleListContract;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

/**
 * Created by user on 14.07.2016.
 */
public class PlanetsListPresenter implements PeopleListContract.Presenter {

    private final PeopleListContract.View view;
    private final StarWarsService service;
    private final PlanetsRepository db;

    @Inject
    public PlanetsListPresenter(PeopleListContract.View view, StarWarsService service, PlanetsRepository db) {
        this.view = view;
        this.service = service;
        this.db = db;
    }

    @Override
    public void getData() {
        Timber.i("pobieram");
        service.listPlanet().enqueue(new Callback<ResultSet<Planet>>() {
            @Override
            public void onResponse(Call<ResultSet<Planet>> call, Response<ResultSet<Planet>> response) {
                if(response.isSuccessful()){
                    Timber.i(response.body().getCount());
                    List<Planet> planets = new ArrayList<>(response.body().getResults());
                    Timber.i(planets.size()+"");
                    db.add(planets);
                    view.onDataLoaded(planets);
                }
            }

            @Override
            public void onFailure(Call<ResultSet<Planet>> call, Throwable t) {
                Timber.i("Bład komunikacji pobieram dane z bazy");
                if (t instanceof IOException) {
                    List<Planet> planets = db.query(new PlanetsSpecification());
                    if(planets.isEmpty()){
                        view.onErrorOccured(R.string.error_list_empty);
                    }
                    view.onDataLoaded(planets);
                }
            }
        });

    }
}
