package com.baraa.software.eventhorizon.viewmodel_1.details;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.baraa.software.eventhorizon.viewmodel_1.model.MovieItem;

import javax.inject.Inject;

public class DetailViewModel extends ViewModel {
    MutableLiveData<MovieItem> movieItem = new MutableLiveData<>();

    @Inject
    public DetailViewModel() {

    }

    public void setMovieItem(MovieItem movieItem) {
        this.movieItem.setValue(movieItem);
    }

    public LiveData<MovieItem> getMovieItem() {
        return movieItem;
    }
}
