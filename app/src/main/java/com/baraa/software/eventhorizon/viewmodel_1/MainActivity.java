package com.baraa.software.eventhorizon.viewmodel_1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import com.baraa.software.eventhorizon.viewmodel_1.movie.MovieFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.frameLayout)FrameLayout frameLayout;

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
