package com.example.user.starwars.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.user.starwars.R;
import com.example.user.starwars.SWAPI.starships.Starship;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by user on 14.07.2016.
 */
public class StarshipsAdapter extends RecyclerView.Adapter<StarshipsAdapter.StarshipsViewHolder>{

    private List<Starship> items;
    LayoutInflater layoutInflater;
    PeopleClickListener callback;

    public interface PeopleClickListener {
        void onPersonClick(Starship person);
    }


    public StarshipsAdapter(List<Starship> items) {
        this.items = items;
    }

    @Override
    public StarshipsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.people_list_row, parent, false);
        return new StarshipsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StarshipsViewHolder holder, int position) {
        final Starship person = items.get(position);
        holder.nameView.setText(person.getName());
    }


    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setItems(List<Starship> list){
        items = list;
        notifyDataSetChanged();
    }

    public void setOnClickListener(PeopleClickListener callback) {
        this.callback = callback;
    }

    class StarshipsViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.text1)
        TextView nameView;

        public StarshipsViewHolder(View itemView) {
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
