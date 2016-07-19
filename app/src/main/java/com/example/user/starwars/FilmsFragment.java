package com.example.user.starwars;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.starwars.SWAPI.films.Film;
import com.example.user.starwars.adapters.FilmsAdapter;
import com.example.user.starwars.component.DaggerFragmentComponent;
import com.example.user.starwars.mvp.ListScreenModule;
import com.example.user.starwars.mvp.contract.PeopleListContract;
import com.example.user.starwars.mvp.presenter.FilmsListPresenter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class FilmsFragment extends Fragment implements PeopleListContract.View, FilmsAdapter.PeopleClickListener {


    @BindView(R.id.filmsRecyclerView)
    RecyclerView filmsRecyclerView;

    @Inject
    FilmsListPresenter presenter;
    FilmsAdapter adapter;

    public FilmsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerFragmentComponent.builder()
                .listScreenModule(new ListScreenModule(this))
                .netComponent(((App) getContext().getApplicationContext()).getNetComponent())
                .build().inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_films, container, false);
        ButterKnife.bind(this, view);
        filmsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));



        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return true;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                adapter.notifyDataSetChanged();
            }
        };

        ItemTouchHelper touchHelper = new ItemTouchHelper(simpleCallback);
        touchHelper.attachToRecyclerView(filmsRecyclerView);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        presenter.getData();
    }

    @Override
    public void onDataLoaded(List items) {
        ensureAdapter(items);
    }

    @Override
    public void onErrorOccured(@StringRes int errorMessage) {

    }

    private void ensureAdapter(List<Film> items) {
        if (adapter == null) {
            adapter = new FilmsAdapter(items);
            adapter.setOnClickListener(this);
            filmsRecyclerView.setAdapter(adapter);
        } else {
            adapter.setItems(items);
        }
    }

    @Override
    public void onPersonClick(Film film) {
        Intent details = new Intent(getContext(), FilmsDetailsActivity.class);
        details.putExtra("Person", film);
        startActivity(details);
    }
}
