package com.baraa.software.eventhorizon.viewmodel_1.movie;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.baraa.software.eventhorizon.viewmodel_1.model.MovieItem;
import com.baraa.software.eventhorizon.viewmodel_1.model.ResponseNowPlaying;
import com.baraa.software.eventhorizon.viewmodel_1.networking.IMovieApiService;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieViewModel extends ViewModel {
    private static final String TAG = "MovieViewModel";
    // to show a progressbar while loading a set of recent movies
    MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    // display error if loading failed
    MutableLiveData<Boolean> error = new MutableLiveData<>();
    // List of recent movies fetched online
    MutableLiveData<List<MovieItem>> moviesList = new MutableLiveData<>();
    // use retrofit for making GET request to rest api
    private IMovieApiService apiService;

    private Call<ResponseNowPlaying> apiCall;

    @Inject
    public MovieViewModel(IMovieApiService movieApiService) {
        this.apiService = movieApiService;
        loadData(1);
    }

    public void loadData(int numPage){
        isLoading.setValue(true);
        error.setValue(false);
        apiCall = apiService.getNowPlaying(numPage);

        // Making GET request to rest api service using retrofit
        apiCall.enqueue(new Callback<ResponseNowPlaying>() {
            @Override
            public void onResponse(Call<ResponseNowPlaying> call, Response<ResponseNowPlaying> response) {
                // if there is no data set error and end
                if(response.body() == null){
                    error.setValue(true);
                    isLoading.setValue(false);
                    return;
                }
                // store MovieList data to a list
                List<MovieItem> movieItemList = response.body().getResults();
                isLoading.setValue(false);
                moviesList.setValue(movieItemList);
            }

            @Override
            public void onFailure(Call<ResponseNowPlaying> call, Throwable t) {
                Log.e(TAG, "onFailure: ",t);
                isLoading.setValue(false);
                error.setValue(true);
            }
        });
    }

    public LiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    public LiveData<Boolean> getError() {
        return error;
    }

    public LiveData<List<MovieItem>> getMoviesList() {
        return moviesList;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (apiCall != null){
            apiCall.cancel();
            apiCall = null;
        }
    }
}
