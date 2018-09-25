package com.baraa.software.eventhorizon.viewmodel_1.root;

import com.baraa.software.eventhorizon.viewmodel_1.details.DetailsFragment;
import com.baraa.software.eventhorizon.viewmodel_1.movie.MovieFragment;
import com.baraa.software.eventhorizon.viewmodel_1.networking.MovieApiModule;
import com.baraa.software.eventhorizon.viewmodel_1.root.viewmodule.ViewModelModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ViewModelModule.class, MovieApiModule.class})
public interface ApplicationComponent {
    void inject(MovieFragment movieFragment);
    void inject(DetailsFragment detailsFragment);
}
