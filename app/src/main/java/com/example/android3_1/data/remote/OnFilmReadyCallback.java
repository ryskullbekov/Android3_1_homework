package com.example.android3_1.data.remote;

import com.example.android3_1.data.models.Film;

import java.util.List;

public interface OnFilmReadyCallback {

    void success(List<Film> films);
    void onServerError();
    void failure(String msg);
}
