package com.example.user.starwars.mvp;


import com.example.user.starwars.mvp.contract.PeopleListContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Aditya on 11-May-16.
 */
@Module
public class PeopleScreenModule {
    private final PeopleListContract.View mView;


    public PeopleScreenModule(PeopleListContract.View mView) {
        this.mView = mView;
    }

    @Provides
    @CustomScope
    PeopleListContract.View providesMainScreenContractView() {
        return mView;
    }
}
