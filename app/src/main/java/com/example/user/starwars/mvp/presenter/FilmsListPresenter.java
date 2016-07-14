package com.example.user.starwars.mvp.presenter;

import android.content.Context;

import com.example.user.starwars.R;
import com.example.user.starwars.SWAPI.ResultSet;
import com.example.user.starwars.SWAPI.StarWarsService;
import com.example.user.starwars.SWAPI.films.Film;
import com.example.user.starwars.database.StarWarsSQLiteOpenhelper;
import com.example.user.starwars.database.films.FilmsRepository;
import com.example.user.starwars.database.films.specification.FilmsSpecification;
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

    public static final String HTTP_SWAPI_CO_API = "http://swapi.co/api/";

    private final PeopleListContract.View view;
    private final StarWarsService service;
    private final FilmsRepository database;


    public FilmsListPresenter(PeopleListContract.View view, Context context) {
        this.view = view;
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(HTTP_SWAPI_CO_API)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        service = retrofit.create(StarWarsService.class);
        database =  new FilmsRepository(new StarWarsSQLiteOpenhelper(context));


    }


    @Override
    public void getData() {
        Timber.i("pobieram");
        service.listFilms().enqueue(new Callback<ResultSet<Film>>() {
            @Override
            public void onResponse(Call<ResultSet<Film>> call, Response<ResultSet<Film>> response) {
                if(response.isSuccessful()){
                    Timber.i(response.body().getCount());
                    List<Film> films = new ArrayList<>(response.body().getResults());
                    Timber.i(films.size()+"");
                    database.add(films);
                    view.onDataLoaded(films);
                }
            }

            @Override
            public void onFailure(Call<ResultSet<Film>> call, Throwable t) {
                Timber.i("Bład komunikacji pobieram dane z bazy");
                if (t instanceof IOException) {
                    List<Film> films = database.query(new FilmsSpecification());
                    if(films.isEmpty()){
                        view.onErrorOccured(R.string.error_list_empty);
                    }
                    view.onDataLoaded(films);
                }
            }
        });

    }
}
