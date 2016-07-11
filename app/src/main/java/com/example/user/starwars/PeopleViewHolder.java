package com.example.user.starwars;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import butterknife.OnClick;

import static android.support.v4.app.ActivityCompat.startActivity;

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
                Context context = view.getContext();
                Intent details = new Intent(context, DetailsActivity.class);
                details.putExtra("Person", person);
                context.startActivity(details);
            }
        });
    }

    public void setPerson(Person person) {
        this.person = person;
        nameView.setText(person.name);
    }
}
