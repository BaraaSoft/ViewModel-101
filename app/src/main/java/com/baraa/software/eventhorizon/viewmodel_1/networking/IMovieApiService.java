package com.baraa.software.eventhorizon.viewmodel_1.networking;

import com.baraa.software.eventhorizon.viewmodel_1.model.ResponseNowPlaying;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IMovieApiService {
    @GET("movie/now_playing")
    Call<ResponseNowPlaying> getNowPlaying(@Query("page") int page);
}
