package com.example.user.starwars.mvp.presenter;

import android.content.Intent;

import com.example.user.starwars.SWAPI.people.Person;
import com.example.user.starwars.mvp.contract.DetailsContract;

/**
 * Created by user on 13.07.2016.
 */
public class DetailsPresenter<T> implements DetailsContract.Presenter {

    Intent intent;
    DetailsContract.View view;

    public DetailsPresenter(Intent intent, DetailsContract.View view) {
        this.intent = intent;
        this.view = view;
    }

    @Override
    public void getData() {
        T person = (T) intent.getExtras().getParcelable("Person");
        view.onDataLoaded(person);
    }
}
