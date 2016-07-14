package com.example.user.starwars;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.user.starwars.SWAPI.films.Film;
import com.example.user.starwars.mvp.contract.DetailsContract;
import com.example.user.starwars.mvp.presenter.DetailsPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

public class FilmsDetailsActivity extends AppCompatActivity implements DetailsContract.View<Film> {

    DetailsContract.Presenter presenter;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.director)
    TextView director;
    @BindView(R.id.producer)
    TextView producer;
    @BindView(R.id.release_date)
    TextView releaseDate;
    @BindView(R.id.crawl)
    TextView crawl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_films_details);
        ButterKnife.bind(this);
        Timber.i("Wystartowalem");
        presenter = new DetailsPresenter(getIntent(), this);
        presenter.getData();
    }

    @Override
    public void onDataLoaded(Film item) {
        name.setText(item.getTitle());
        director.setText(item.getDirector());
        producer.setText(item.getProducer());
        releaseDate.setText(item.getReleaseDate());
        crawl.setText(item.getOpeningCrawl());
    }
}
