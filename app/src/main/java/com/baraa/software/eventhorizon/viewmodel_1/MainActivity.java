package com.baraa.software.eventhorizon.viewmodel_1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        MovieFragment fragment = MovieFragment.newInstance();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frameLayout,fragment).commit();
    }
}
