package com.kurio.corona.data.remote.service;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class CoronaServiceFactory {

    private String baseUrl;


    private CoronaServiceFactory() {
    }

    public CoronaServiceFactory(String baseUrl) {
        this.baseUrl = baseUrl;

    }

    public CoronaService doCoronaService(boolean isDebug) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(baseUrl)
                .client(makeOkHttpClient(makeLoggingInterceptor(isDebug)))
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build();
        return retrofit.create(CoronaService.class);
    }

    private OkHttpClient makeOkHttpClient(HttpLoggingInterceptor httpLoggingInterceptor) {
        return new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .connectTimeout(120, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
                .build();
    }

    private HttpLoggingInterceptor makeLoggingInterceptor(boolean isDebug) {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        if (isDebug) {
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        } else {
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
        }
        return httpLoggingInterceptor;
    }
}
