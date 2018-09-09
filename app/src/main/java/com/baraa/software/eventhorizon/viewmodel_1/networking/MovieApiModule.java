package com.baraa.software.eventhorizon.viewmodel_1.networking;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class MovieApiModule {
    public static final String BASE_URL = "https://api.themoviedb.org/3/";
    public static final String API_KEY = "Please_Paste_Your_Own_API_Key_Here";


    public OkHttpClient providesHttpClient(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        return new OkHttpClient.Builder().addInterceptor(interceptor).addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                HttpUrl url = request.url().newBuilder().addQueryParameter("api_key",API_KEY)
                        .addQueryParameter("region","US")
                        .build();
                request = request.newBuilder().url(url).build();
                return chain.proceed(request);
            }
        }).build();
    }


    public Retrofit providesRetrofit(String baseUrl, OkHttpClient client){
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public IMovieApiService providesMovieApiSevice(){
        return providesRetrofit(BASE_URL,providesHttpClient()).create(IMovieApiService.class);
    }

}
