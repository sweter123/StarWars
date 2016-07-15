package com.example.user.starwars.mvp.presenter;

import android.content.Context;

import com.example.user.starwars.AppProvider;
import com.example.user.starwars.R;
import com.example.user.starwars.SWAPI.ResultSet;
import com.example.user.starwars.SWAPI.StarWarsService;
import com.example.user.starwars.SWAPI.starships.Starship;
import com.example.user.starwars.database.starships.StarshipsRepository;
import com.example.user.starwars.database.starships.specification.StarshipsSpecification;
import com.example.user.starwars.mvp.contract.PeopleListContract;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

/**
 * Created by user on 14.07.2016.
 */
public class StarshipsListPresenter implements PeopleListContract.Presenter {

    private final PeopleListContract.View view;
    private final StarWarsService service;
    private final StarshipsRepository database;


    public StarshipsListPresenter(PeopleListContract.View view, Context context) {
        this.view = view;
        AppProvider app = (AppProvider)context.getApplicationContext();
        service = (app).getStarWarsService();
        database =  app.getStarshipRepository();

    }

    @Override
    public void getData() {
        Timber.i("pobieram");
        service.listStarships().enqueue(new Callback<ResultSet<Starship>>() {
            @Override
            public void onResponse(Call<ResultSet<Starship>> call, Response<ResultSet<Starship>> response) {
                if(response.isSuccessful()){
                    Timber.i(response.body().getCount());
                    List<Starship> starships = new ArrayList<>(response.body().getResults());
                    Timber.i(starships.size()+"");
                    database.add(starships);
                    view.onDataLoaded(starships);
                }
            }

            @Override
            public void onFailure(Call<ResultSet<Starship>> call, Throwable t) {
                Timber.i("Bład komunikacji pobieram dane z bazy");
                if (t instanceof IOException) {
                    List<Starship> starships = database.query(new StarshipsSpecification());
                    if(starships.isEmpty()){
                        view.onErrorOccured(R.string.error_list_empty);
                    }
                    view.onDataLoaded(starships);
                }
            }
        });

    }
}
