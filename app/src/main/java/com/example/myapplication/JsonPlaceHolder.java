package com.example.myapplication;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceHolder {
    @GET("database.json")
   public Call<List<Ksiazki>> getKsiazki();
}
