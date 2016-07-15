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

import com.example.user.starwars.SWAPI.starships.Starship;
import com.example.user.starwars.adapters.StarshipsAdapter;
import com.example.user.starwars.mvp.contract.PeopleListContract;
import com.example.user.starwars.mvp.presenter.StarshipsListPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class StarshipsFragment extends Fragment implements PeopleListContract.View<Starship>, StarshipsAdapter.PeopleClickListener {


    @BindView(R.id.starshipsRecyclerView)
    RecyclerView starshipsRecyclerView;

    private PeopleListContract.Presenter presenter;
    private StarshipsAdapter adapter;

    public StarshipsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_starships, container, false);
        ButterKnife.bind(this, view);
        presenter = new StarshipsListPresenter(this, getContext());
        starshipsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

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
        touchHelper.attachToRecyclerView(starshipsRecyclerView);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        presenter.getData();
    }


    @Override
    public void onDataLoaded(List<Starship> items) {
        ensureAdapter(items);
    }


    @Override
    public void onErrorOccured(@StringRes int errorMessage) {
        Toast.makeText(getContext(), errorMessage, Toast.LENGTH_LONG).show();
    }

    private void ensureAdapter(List<Starship> items) {
        if (adapter == null) {
            adapter = new StarshipsAdapter(items);
            adapter.setOnClickListener(this);
            starshipsRecyclerView.setAdapter(adapter);
        } else {
            adapter.setItems(items);
        }
    }

    @Override
    public void onPersonClick(Starship starship) {
        Intent details = new Intent(getContext(), StarshipsDetailsActivity.class);
        details.putExtra("Person", starship);
        startActivity(details);
    }
}
