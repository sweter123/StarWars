package com.example.user.starwars;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.widget.Toast;

import com.example.user.starwars.mvp.contract.PeopleListContract;
import com.example.user.starwars.mvp.presenter.PeopleListPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements PeopleAdapter.PeopleClickListener, PeopleListContract.View {


    @BindView(R.id.peopleRecycleView)
    RecyclerView peopleRecycleView;

    private PeopleListContract.Presenter presenter;
    private PeopleAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new PeopleListPresenter(this, this);
        ButterKnife.bind(this);
        peopleRecycleView.setLayoutManager(new LinearLayoutManager(this));

        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return true;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                     adapter.notifyDataSetChanged();
            }
        };
        ItemTouchHelper touchHelper = new ItemTouchHelper(simpleCallback);
        touchHelper.attachToRecyclerView(peopleRecycleView);

        presenter.getData();
    }

    @Override
    public void onPersonClick(Person person) {
        Intent details = new Intent(this, DetailsActivity.class);
        details.putExtra("Person", person);
        startActivity(details);
    }

    @Override
    public void onDataLoaded(List<Person> items) {
        ensureAdapter(items);
    }

    @Override
    public void onErrorOccured(@StringRes int errorMessage) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
    }

    private void ensureAdapter(List<Person> items) {
        if(adapter == null) {
            adapter = new PeopleAdapter(items);
            adapter.setOnClickListener(this);
            peopleRecycleView.setAdapter(adapter);
        } else {
            adapter.setItems(items);
        }
    }

}
