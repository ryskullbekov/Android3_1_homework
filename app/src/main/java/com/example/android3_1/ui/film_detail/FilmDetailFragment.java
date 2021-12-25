package com.example.android3_1.ui.film_detail;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.android3_1.R;
import com.example.android3_1.data.models.Film;
import com.example.android3_1.data.remote.OnFilmDetailCallback;
import com.example.android3_1.databinding.FragmentFilmDetailBinding;
import com.example.android3_1.ui.app.App;


public class FilmDetailFragment extends Fragment {


    private FragmentFilmDetailBinding binding;
    private String id;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFilmDetailBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        id = getArguments().getString("id");
        getInformation();
    }

    private void getInformation() {
        Log.e("anime", id);
        App.apiService.getDetailFilms(id, new OnFilmDetailCallback() {
            @Override
            public void success(Film film) {
                Log.e("TAG", "getIn");
                binding.titleTv.setText(film.getTitle());
                binding.releaseDateTv.setText(Integer.toString(film.getReleaseDate()));
                binding.runningTimeTv.setText(Integer.toString(film.getRunningTime()));
                binding.producerTv.setText(film.getProducer());
                binding.descriptionTv.setText(film.getDescription());
                Glide.with(binding.getRoot())
                        .load(film.getImage())
                        .centerCrop()
                        .into(binding.picture);
            }

            @Override
            public void serverError() {
                Log.e("TAG", "error");
            }

            @Override
            public void failure(String msg) {
                Log.e("error", msg);
            }
        });
    }
}