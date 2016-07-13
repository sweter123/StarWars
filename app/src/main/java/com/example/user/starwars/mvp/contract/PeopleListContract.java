package com.example.user.starwars.mvp.contract;

import android.support.annotation.StringRes;

import com.example.user.starwars.Person;

import java.util.List;

/**
 * Created by user on 13.07.2016.
 */
public interface PeopleListContract {

    interface Presenter {

        void getData();
    }

    interface View {

        void onDataLoaded(List<Person> items);

        void onErrorOccured(@StringRes int errorMessage);

    }

}
