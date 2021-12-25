package com.example.android3_1.data.remote;

import com.example.android3_1.data.models.Film;

public interface OnFilmDetailCallback {

    void success(Film film);
    void serverError();
    void failure(String msg);
}
