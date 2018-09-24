package com.baraa.software.eventhorizon.viewmodel_1.root;

import android.app.Application;

public class App extends Application {
    ApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerApplicationComponent.create();
    }

    public ApplicationComponent getApplicationComponent() {
        return component;
    }
}
