package com.baraa.software.eventhorizon.viewmodel_1.root;

import com.baraa.software.eventhorizon.viewmodel_1.details.DetailsFragment;
import com.baraa.software.eventhorizon.viewmodel_1.movie.MovieFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {
    void inject(MovieFragment fragment);
    void inject(DetailsFragment fragment);
}
