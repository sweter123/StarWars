package com.example.user.starwars;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.user.starwars.SWAPI.people.Person;
import com.example.user.starwars.adapters.PeopleAdapter;
import com.example.user.starwars.mvp.contract.PeopleListContract;
import com.example.user.starwars.mvp.presenter.PeopleListPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class PeopleFragment extends Fragment implements PeopleAdapter.PeopleClickListener, PeopleListContract.View{


    @BindView(R.id.peopleRecycleView)
    RecyclerView peopleRecycleView;

    private PeopleListContract.Presenter presenter;
    private PeopleAdapter adapter;

    public PeopleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_people, container, false);
        ButterKnife.bind(this, view);
        presenter = new PeopleListPresenter(this, getContext());
        peopleRecycleView.setLayoutManager(new LinearLayoutManager(getContext()));

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
        touchHelper.attachToRecyclerView(peopleRecycleView);

        presenter.getData();
        return view;
    }

    @Override
    public void onPersonClick(Person person) {
        Intent details = new Intent(getContext(), DetailsActivity.class);
        details.putExtra("Person", person);
        startActivity(details);
    }

    @Override
    public void onDataLoaded(List<Person> items) {
        ensureAdapter(items);
    }

    @Override
    public void onErrorOccured(@StringRes int errorMessage) {
        Toast.makeText(getContext(), errorMessage, Toast.LENGTH_LONG).show();
    }

    private void ensureAdapter(List<Person> items) {
        if (adapter == null) {
            adapter = new PeopleAdapter(items);
            adapter.setOnClickListener(this);
            peopleRecycleView.setAdapter(adapter);
        } else {
            adapter.setItems(items);
        }
    }

}


