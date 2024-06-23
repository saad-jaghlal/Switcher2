package com.example.switcher2;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

//   Exercice 2
//    Q1
public interface ApiService {
    @GET("posts")
    Call<List<Carburant>> getAllCarburant();
}
