package com.example.user.starwars.mvp.contract;

import android.support.annotation.StringRes;

import java.util.List;

/**
 * Created by user on 13.07.2016.
 */
public interface PeopleListContract {

    interface Presenter {

        void getData();
    }

    interface View<T> {

        void onDataLoaded(List<T> items);

        void onErrorOccured(@StringRes int errorMessage);

    }

}
