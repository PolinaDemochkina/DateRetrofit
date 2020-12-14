package ru.mustakimov.retrofitsample.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import ru.mustakimov.retrofitsample.InfoModel;

public interface DateApi {
    @GET("/")
    Call<InfoModel> getInfo(@Query("service") String type);
}
