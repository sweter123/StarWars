package com.example.user.starwars.mvp.presenter;

import android.content.Intent;

import com.example.user.starwars.Person;
import com.example.user.starwars.mvp.contract.DetailsContract;

import timber.log.Timber;

/**
 * Created by user on 13.07.2016.
 */
public class DetailsPresenter implements DetailsContract.Presenter {

    Intent intent;
    DetailsContract.View view;

    public DetailsPresenter(Intent intent, DetailsContract.View view) {
        this.intent = intent;
        this.view = view;
    }

    @Override
    public void getData() {
        Person person = (Person) intent.getExtras().getSerializable("Person");
        view.onDataLoaded(person);
    }
}
