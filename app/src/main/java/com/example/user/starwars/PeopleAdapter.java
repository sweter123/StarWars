package com.example.user.starwars;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by user on 08.07.2016.
 */
public class PeopleAdapter extends RecyclerView.Adapter<PeopleViewHolder>{

    private final ArrayList<Person> people;
    LayoutInflater layoutInflater;

    public PeopleAdapter(ArrayList<Person> people, LayoutInflater layoutInflater) {
        this.people = people;
        this.layoutInflater = layoutInflater;
    }

    @Override
    public PeopleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.people_list_row, parent, false);
        return new PeopleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PeopleViewHolder holder, int position) {
        holder.setPerson(people.get(position));
    }

    @Override
    public int getItemCount() {
        return people.size();
    }

    public void add(String name){
        Person person = new Person();
        person.name = name;
        people.add(person);
        notifyItemInserted(people.size());
    }

}
