package com.baraa.software.eventhorizon.viewmodel_1.root;

import android.app.Application;
import android.content.Context;

public class App extends Application {
    ApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerApplicationComponent.create();
    }

    public static ApplicationComponent getApplicationComponent(Context context) {

        return ((App) context.getApplicationContext()).component;
    }
}
