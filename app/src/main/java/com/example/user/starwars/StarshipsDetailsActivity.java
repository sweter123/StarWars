package com.example.user.starwars;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.user.starwars.SWAPI.starships.Starship;
import com.example.user.starwars.mvp.contract.DetailsContract;
import com.example.user.starwars.mvp.presenter.DetailsPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StarshipsDetailsActivity extends AppCompatActivity implements DetailsContract.View<Starship> {

    DetailsContract.Presenter presenter;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.model)
    TextView model;
    @BindView(R.id.manufacturer)
    TextView manufacturer;
    @BindView(R.id.starshipClass)
    TextView starshipClass;
    @BindView(R.id.crew)
    TextView crew;
    @BindView(R.id.length)
    TextView length;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starships_details);
        ButterKnife.bind(this);
        presenter = new DetailsPresenter(getIntent(), this);
        presenter.getData();
    }

    @Override
    public void onDataLoaded(Starship item) {
        setTitle(item.getName());
        name.setText(item.getName());
        model.setText(item.getModel());
        manufacturer.setText(item.getManufacturer());
        starshipClass.setText(item.getStarshipClass());
        crew.setText(item.getCrew());
        length.setText(item.getLength());
    }
}
