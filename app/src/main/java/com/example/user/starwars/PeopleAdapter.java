package com.example.user.starwars;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by user on 08.07.2016.
 */
public class PeopleAdapter extends RecyclerView.Adapter<PeopleAdapter.PeopleViewHolder>{

    private List<Person> items;
    LayoutInflater layoutInflater;
    PeopleClickListener callback;

    interface PeopleClickListener {
        void onPersonClick(Person person);
    }


    public PeopleAdapter(List<Person> items) {
        this.items = items;
    }

    @Override
    public PeopleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.people_list_row, parent, false);
        return new PeopleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PeopleViewHolder holder, int position) {
        final Person person = items.get(position);
        holder.nameView.setText(person.getName());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setItems(List<Person> list){
        items = list;
        notifyDataSetChanged();
    }

    public void setOnClickListener(PeopleClickListener callback) {
        this.callback = callback;
    }

    class PeopleViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.text1)
         TextView nameView;

        public PeopleViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.text1)
        public void onItemClick() {
            if(callback != null) {
                callback.onPersonClick(items.get(getAdapterPosition()));
            }
        }
    }

}
