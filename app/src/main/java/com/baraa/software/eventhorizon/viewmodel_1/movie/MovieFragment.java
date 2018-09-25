package com.baraa.software.eventhorizon.viewmodel_1.movie;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.baraa.software.eventhorizon.viewmodel_1.R;
import com.baraa.software.eventhorizon.viewmodel_1.details.DetailViewModel;
import com.baraa.software.eventhorizon.viewmodel_1.details.DetailsFragment;
import com.baraa.software.eventhorizon.viewmodel_1.model.MovieItem;
import com.baraa.software.eventhorizon.viewmodel_1.root.App;
import com.baraa.software.eventhorizon.viewmodel_1.root.viewmodule.ViewModelFactory;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class MovieFragment extends Fragment implements MovieRecyclerViewAdapter.MovieListListener {
    private static final String TAG = "MovieFragment";
    @BindView(R.id.recyclerView) RecyclerView recyclerView;
    @BindView(R.id.progressbar) ProgressBar progressBar;

    @Inject
    ViewModelFactory viewModelFactory;

    Unbinder unbinder;
    MovieViewModel viewModel;
    MovieRecyclerViewAdapter adapter;
    List<MovieItem> movies = new ArrayList<>();


    public MovieFragment() {
        // Required empty public constructor
    }


    public static MovieFragment newInstance() {
        MovieFragment fragment = new MovieFragment();
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        App.getApplicationComponent(context).inject(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie, container, false);
        unbinder = ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = ViewModelProviders.of(this,viewModelFactory).get(MovieViewModel.class);
        adapter = new MovieRecyclerViewAdapter(movies);
        adapter.setMovieListListener(this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
        obseverViewModel();
    }

    @Override
    public void onMovieSelected(MovieItem movieItem) {
        DetailViewModel detailViewModel =ViewModelProviders.of(getActivity(),viewModelFactory).get(DetailViewModel.class);
        detailViewModel.setMovieItem(movieItem);
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.frameLayout, DetailsFragment.newInstance(null,null))
                .commit();
    }

    public void obseverViewModel(){
        viewModel.getError().observe(this,error->{
            if (error)
                Toast.makeText(getContext(),"Error! Loading",Toast.LENGTH_LONG).show();
        });

        viewModel.getIsLoading().observe(this,(isLoading)->{
            if (isLoading)
                progressBar.setVisibility(View.VISIBLE);
            else
                progressBar.setVisibility(View.GONE);
        });

        viewModel.getMoviesList().observe(this,movieItems -> {
            Log.e(TAG, ">> obseverViewModel: Is Observing .... List Size: "+movieItems.size());
            movies.addAll(movieItems);
            adapter.notifyDataSetChanged();
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(unbinder != null){
            unbinder.unbind();
            unbinder = null;
        }
    }

}
