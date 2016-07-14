package com.example.user.starwars;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.user.starwars.SWAPI.planets.Planet;
import com.example.user.starwars.mvp.contract.DetailsContract;
import com.example.user.starwars.mvp.presenter.DetailsPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PlanetsDetailsActivity extends AppCompatActivity implements DetailsContract.View<Planet> {
    DetailsContract.Presenter presenter;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.population)
    TextView population;
    @BindView(R.id.climate)
    TextView climate;
    @BindView(R.id.terrain)
    TextView terrain;
    @BindView(R.id.gravity)
    TextView gravity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planets_details);
        ButterKnife.bind(this);
        presenter = new DetailsPresenter(getIntent(), this);
        presenter.getData();
    }

    @Override
    public void onDataLoaded(Planet item) {
        name.setText(item.getName());
        population.setText(item.getPopulation());
        climate.setText(item.getClimate());
        terrain.setText(item.getTerrain());
        gravity.setText(item.getGravity());
    }
}
