package ru.mustakimov.retrofitsample;

import android.app.Application;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.mustakimov.retrofitsample.api.DateApi;

public class App extends Application {

    private static DateApi dateApi;
    private Retrofit retrofit;

    @Override
    public void onCreate() {
        super.onCreate();

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        retrofit = new Retrofit.Builder()
                .baseUrl("http://date.jsontest.com/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        dateApi = retrofit.create(DateApi.class);
    }

    public static DateApi getApi() {
        return dateApi;
    }
}
