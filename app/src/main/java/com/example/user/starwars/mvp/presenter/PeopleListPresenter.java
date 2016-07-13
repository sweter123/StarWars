package com.example.user.starwars.mvp.presenter;

import android.content.Context;
import android.widget.Toast;

import com.example.user.starwars.Person;
import com.example.user.starwars.R;
import com.example.user.starwars.ResultSet;
import com.example.user.starwars.StarWarsService;
import com.example.user.starwars.database.StarWarsSQLiteOpenhelper;
import com.example.user.starwars.database.people.PeopleRepository;
import com.example.user.starwars.database.people.specification.PeopleSpecification;
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
 * Created by user on 13.07.2016.
 */
public class PeopleListPresenter implements PeopleListContract.Presenter {

    public static final String HTTP_SWAPI_CO_API = "http://swapi.co/api/";

    private final PeopleListContract.View view;
    private final StarWarsService service;
    private final PeopleRepository database;


    public PeopleListPresenter(PeopleListContract.View view, Context context) {
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
        database =  new PeopleRepository(new StarWarsSQLiteOpenhelper(context));


    }

    @Override
    public void getData() {
        service.listPeople().enqueue(new Callback<ResultSet>() {
            @Override
            public void onResponse(Call<ResultSet> call, Response<ResultSet> response) {
                if(response.isSuccessful()){
                    Timber.i(response.body().getCount());
                    List<Person> people = new ArrayList<>(response.body().getResults());
                    Timber.i(people.size()+"");
                    database.add(people);
                    view.onDataLoaded(people);
                }
            }

            @Override
            public void onFailure(Call<ResultSet> call, Throwable t) {
                Timber.i("Bład komunikacji pobieram dane z bazy");
                if (t instanceof IOException) {
                    List<Person> people = database.query(new PeopleSpecification());
                    if(people.isEmpty()){
                        view.onErrorOccured(R.string.error_list_empty);
                    }
                    view.onDataLoaded(people);
                }
            }
        });

    }
}
