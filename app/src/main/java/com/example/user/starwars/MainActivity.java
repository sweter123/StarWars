package com.example.user.starwars;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.peopleRecycleView)
    RecyclerView peopleRecycleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        ArrayList<Person> people = new ArrayList<>();
        peopleRecycleView.setLayoutManager(new LinearLayoutManager(this));
        PeopleAdapter adapter = new PeopleAdapter(people, getLayoutInflater());
        peopleRecycleView.setAdapter(adapter);
        adapter.add("luke");
        adapter.add("vader");
        adapter.add("hans");
        adapter.add("andrzej");
    }
}
