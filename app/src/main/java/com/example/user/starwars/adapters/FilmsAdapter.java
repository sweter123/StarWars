package com.example.user.starwars.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.user.starwars.R;
import com.example.user.starwars.SWAPI.films.Film;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by user on 14.07.2016.
 */
public class FilmsAdapter extends RecyclerView.Adapter<FilmsAdapter.FilmsViewHolder>{

    private List<Film> items;
    LayoutInflater layoutInflater;
    PeopleClickListener callback;

    public interface PeopleClickListener {
        void onPersonClick(Film film);
    }


    public FilmsAdapter(List<Film> items) {
        this.items = items;
    }


    @Override
    public FilmsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.people_list_row, parent, false);
        return new FilmsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FilmsViewHolder holder, int position) {
        Film film = items.get(position);
        holder.nameView.setText(film.getTitle());
    }


    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setItems(List<Film> list){
        items = list;
        notifyDataSetChanged();
    }

    public void setOnClickListener(PeopleClickListener callback) {
        this.callback = callback;
    }

    class FilmsViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.text1)
        TextView nameView;

        public FilmsViewHolder(View itemView) {
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
