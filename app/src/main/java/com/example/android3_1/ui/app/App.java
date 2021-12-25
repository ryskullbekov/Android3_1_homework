package com.example.android3_1.ui.app;

import android.app.Application;

import com.example.android3_1.data.remote.FilmsApi;
import com.example.android3_1.data.remote.FilmsApiService;
import com.example.android3_1.data.remote.RetrofitClient;

public class App extends Application {

    private RetrofitClient retrofitClient;
    public static FilmsApi api;
    public static FilmsApiService apiService;

    @Override
    public void onCreate() {
        super.onCreate();
        retrofitClient = new RetrofitClient();
        api = retrofitClient.filmsApiClient();
        apiService = new FilmsApiService();
    }
}
