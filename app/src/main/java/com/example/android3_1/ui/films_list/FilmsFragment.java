package com.example.android3_1.ui.films_list;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android3_1.R;
import com.example.android3_1.data.models.Film;
import com.example.android3_1.data.remote.OnFilmReadyCallback;
import com.example.android3_1.databinding.FragmentFilmsBinding;
import com.example.android3_1.ui.app.App;
import com.example.android3_1.ui.film_detail.OnItemClick;

import java.util.List;


public class FilmsFragment extends Fragment {

    private FragmentFilmsBinding binding;
    private FilmAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFilmsBinding.inflate(inflater, container, false);
        adapter = new FilmAdapter();
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.recyclerFilms.setAdapter(adapter);
        getInformation();
        sendInformation();

        App.apiService.getFilms(new OnFilmReadyCallback() {
            @Override
            public void success(List<Film> films) {
                Log.e("tag", "tag");
                adapter.setFilms(films);
            }

            @Override
            public void onServerError() {
                Log.e("TAG", "onServerError");
            }

            @Override
            public void failure(String msg) {
                Log.e("TAG", "failure: " + msg);

            }
        });
    }

    private void getInformation() {
        App.apiService.getFilms(new OnFilmReadyCallback() {
            @Override
            public void success(List<Film> list) {
                adapter.setFilms(list);
            }

            @Override
            public void onServerError() {

            }

            @Override
            public void failure(String msg) {

            }
        });
    }

    private void sendInformation() {
        adapter.setOnItemClick(new OnItemClick() {
            @Override
            public void itemClick(String position) {
                Bundle bundle= new Bundle();
                Log.e("anime", position);
                bundle.putString("id", position);
                Navigation.findNavController(requireView()).navigate(R.id.filmDetailFragment, bundle);
            }
        });
    }
}