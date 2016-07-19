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
import android.widget.Toast;

import com.example.user.starwars.SWAPI.planets.Planet;
import com.example.user.starwars.adapters.PlanetsAdapter;
import com.example.user.starwars.component.DaggerFragmentComponent;
import com.example.user.starwars.mvp.ListScreenModule;
import com.example.user.starwars.mvp.contract.PeopleListContract;
import com.example.user.starwars.mvp.presenter.PlanetsListPresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class PlanetsFragment extends Fragment implements PeopleListContract.View<Planet>,PlanetsAdapter.PeopleClickListener {


    @BindView(R.id.planetsRecycleView)
    RecyclerView planetsRecycleView;

    @Inject
    PlanetsListPresenter presenter;
    private PlanetsAdapter adapter;

    public PlanetsFragment() {
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
        View view = inflater.inflate(R.layout.fragment_planets, container, false);
        ButterKnife.bind(this, view);
        planetsRecycleView.setLayoutManager(new LinearLayoutManager(getContext()));

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
        touchHelper.attachToRecyclerView(planetsRecycleView);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        presenter.getData();
    }


    @Override
    public void onDataLoaded(List<Planet> items) {
        ensureAdapter(items);
    }

    @Override
    public void onErrorOccured(@StringRes int errorMessage) {
        Toast.makeText(getContext(), errorMessage, Toast.LENGTH_LONG).show();
    }

    private void ensureAdapter(List<Planet> items) {
        if (adapter == null) {
            adapter = new PlanetsAdapter(items);
            adapter.setOnClickListener(this);
            planetsRecycleView.setAdapter(adapter);
        } else {
            adapter.setItems(items);
        }
    }


    @Override
    public void onPersonClick(Planet planet) {
        Intent details = new Intent(getContext(), PlanetsDetailsActivity.class);
        details.putExtra("Person", planet);
        startActivity(details);
    }
}
