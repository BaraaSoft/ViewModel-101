package com.baraa.software.eventhorizon.viewmodel_1.root.viewmodule;

import android.arch.lifecycle.ViewModel;

import com.baraa.software.eventhorizon.viewmodel_1.details.DetailViewModel;
import com.baraa.software.eventhorizon.viewmodel_1.movie.MovieViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(DetailViewModel.class)
    abstract ViewModel bindDetailViewModel(DetailViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(MovieViewModel.class)
    abstract ViewModel bindMovieViewModel(MovieViewModel viewModel);
}
