package com.example.user.starwars;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by user on 08.07.2016.
 */
public class PeopleAdapter extends RecyclerView.Adapter<PeopleViewHolder>{

    private List<Person> people;

    public PeopleAdapter(List<Person> people) {
        this.people = people;
    }

    @Override
    public PeopleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(PeopleViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return people.size();
    }
}
