package com.example.exit_exam;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("search.json")
    Call<SearchResponse> searchBooks(
            @Query("q") String query,
            @Query("fields") String fields
    );
}