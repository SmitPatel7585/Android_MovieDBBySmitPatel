package com.example.moviedbbysmitpatel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface OMDBApiService {
    @GET("/")
    Call<MovieResponse> searchMovies(@Query("s") String searchTerm, @Query("apikey") String apiKey);

    @GET("/")
    Call<Movie> getMovieDetails(@Query("i") String imdbID, @Query("apikey") String apiKey);
}
