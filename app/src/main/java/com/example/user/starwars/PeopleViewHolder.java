package com.example.user.starwars;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import butterknife.OnClick;

/**
 * Created by user on 08.07.2016.
 */
public class PeopleViewHolder extends RecyclerView.ViewHolder{

    private Person person;
    private TextView nameView;

    public PeopleViewHolder(View itemView) {
        super(itemView);
        nameView = (TextView) itemView.findViewById(R.id.text1);
        nameView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("asd",(person.name));
            }
        });
    }

    public void setPerson(Person person) {
        this.person = person;
        nameView.setText(person.name);
    }
}
