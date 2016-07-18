package com.example.user.starwars.mvp.presenter;

import android.content.Context;

import com.example.user.starwars.AppProvider;
import com.example.user.starwars.R;
import com.example.user.starwars.SWAPI.ResultSet;
import com.example.user.starwars.SWAPI.StarWarsService;
import com.example.user.starwars.SWAPI.films.Film;
import com.example.user.starwars.database.StarWarsSQLiteOpenhelper;
import com.example.user.starwars.database.films.FilmsRepository;
import com.example.user.starwars.database.films.specification.FilmsSpecification;
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
public class FilmsListPresenter implements PeopleListContract.Presenter {


    private final PeopleListContract.View view;
    private final StarWarsService service;
    private final FilmsRepository db;

    @Inject
    public FilmsListPresenter(PeopleListContract.View view, StarWarsService service, FilmsRepository db) {
        this.view = view;
        this.service = service;
        this.db = db;


    }


    @Override
    public void getData() {
        Timber.i("pobieram");
        service.listFilms().enqueue(new Callback<ResultSet<Film>>() {
            @Override
            public void onResponse(Call<ResultSet<Film>> call, Response<ResultSet<Film>> response) {
                if (response.isSuccessful()) {
                    Timber.i(response.body().getCount());
                    List<Film> films = new ArrayList<>(response.body().getResults());
                    Timber.i(films.size() + "");
                    db.add(films);
                    view.onDataLoaded(films);
                }
            }

            @Override
            public void onFailure(Call<ResultSet<Film>> call, Throwable t) {
                Timber.i("Bład komunikacji pobieram dane z bazy");
                if (t instanceof IOException) {
                    List<Film> films = db.query(new FilmsSpecification());
                    if (films.isEmpty()) {
                        view.onErrorOccured(R.string.error_list_empty);
                    }
                    view.onDataLoaded(films);
                }
            }
        });

    }
}
