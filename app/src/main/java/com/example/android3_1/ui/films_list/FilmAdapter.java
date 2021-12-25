package com.example.android3_1.ui.films_list;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android3_1.data.models.Film;
import com.example.android3_1.databinding.ItemFilmBinding;
import com.example.android3_1.ui.film_detail.OnItemClick;

import java.util.ArrayList;
import java.util.List;

public class FilmAdapter extends RecyclerView.Adapter<FilmAdapter.FilmsViewHolder> {

    private List<Film> films = new ArrayList<>();
    public static OnItemClick onItemClick;

    public void setOnItemClick(OnItemClick click) {
        onItemClick = click;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setFilms(List<Film> films) {
        this.films = films;
        notifyDataSetChanged();

    }

    @NonNull
    @Override
    public FilmsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemFilmBinding binding = ItemFilmBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false
        );
        return new FilmsViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull FilmsViewHolder holder, int position) {
        holder.onBind(films.get(position));
    }

    @Override
    public int getItemCount() {
        return films.size();
    }

    public static class FilmsViewHolder extends RecyclerView.ViewHolder {
        private final ItemFilmBinding binding;

        public FilmsViewHolder(@NonNull ItemFilmBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void onBind(Film film) {
            binding.tvTitle.setText(film.getTitle());
            binding.tvDescription.setText(film.getDescription());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClick.itemClick(film.getId());
                }
            });
        }
    }
}
