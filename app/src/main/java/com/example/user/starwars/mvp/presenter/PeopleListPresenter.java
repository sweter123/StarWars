package com.example.user.starwars.mvp.presenter;

import com.example.user.starwars.R;
import com.example.user.starwars.SWAPI.ResultSet;
import com.example.user.starwars.SWAPI.StarWarsService;
import com.example.user.starwars.SWAPI.people.Person;
import com.example.user.starwars.database.people.PeopleRepository;
import com.example.user.starwars.database.people.specification.PeopleSpecification;
import com.example.user.starwars.mvp.contract.PeopleListContract;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

/**
 * Created by user on 13.07.2016.
 */
public class PeopleListPresenter implements PeopleListContract.Presenter {

    PeopleListContract.View view;
    StarWarsService service;
    PeopleRepository db;

    @Inject
    public PeopleListPresenter(PeopleListContract.View view, StarWarsService service, PeopleRepository db) {
        this.view = view;
        this.service = service;
        this.db = db;
    }

    @Override
    public void getData() {
        Timber.i("pobieram");
        service.listPeople().enqueue(new Callback<ResultSet<Person>>() {
            @Override
            public void onResponse(Call<ResultSet<Person>> call, Response<ResultSet<Person>> response) {
                if(response.isSuccessful()){
                    Timber.i(response.body().getCount());
                    List<Person> people = new ArrayList<>(response.body().getResults());
                    Timber.i(people.size()+"");
                    db.add(people);
                    view.onDataLoaded(people);
                }
            }

            @Override
            public void onFailure(Call<ResultSet<Person>> call, Throwable t) {
                Timber.i("Bład komunikacji pobieram dane z bazy");
                if (t instanceof IOException) {
                    List<Person> people = db.query(new PeopleSpecification());
                    if(people.isEmpty()){
                        view.onErrorOccured(R.string.error_list_empty);
                    }
                    view.onDataLoaded(people);
                }
            }
        });

    }
}
