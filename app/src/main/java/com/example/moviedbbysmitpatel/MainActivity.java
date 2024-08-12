package com.example.moviedbbysmitpatel;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private EditText searchField;
    private Button searchButton;
    private RecyclerView recyclerView;
    private MovieAdapter adapter;
    private List<Movie> movieList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchField = findViewById(R.id.searchField);
        searchButton = findViewById(R.id.searchButton);
        recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MovieAdapter(movieList, movie -> {
            Intent intent = new Intent(MainActivity.this, MovieDetailsActivity.class);
            intent.putExtra("movie", movie);
            startActivity(intent);
        });
        recyclerView.setAdapter(adapter);

        searchButton.setOnClickListener(v -> searchMovies());
    }

    private void searchMovies() {
        String searchTerm = searchField.getText().toString();
        OMDBApiService apiService = RetrofitClient.getRetrofitInstance().create(OMDBApiService.class);
        Call<MovieResponse> call = apiService.searchMovies(searchTerm, "527b1648");

        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    MovieResponse movieResponse = response.body();

                    if ("True".equals(movieResponse.getResponse())) {
                        movieList.clear();
                        List<Movie> movies = movieResponse.getMovies();

                        if (movies != null && !movies.isEmpty()) {
                            movieList.addAll(movies);
                            adapter.notifyDataSetChanged();


                            for (Movie movie : movies) {
                                fetchMovieDetails(movie);
                            }
                        } else {
                            Toast.makeText(MainActivity.this, "No results found.", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(MainActivity.this, "Error: " + movieResponse.getError(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Failed to retrieve data.", Toast.LENGTH_SHORT).show();
                    Log.e("API_CALL", "onResponse: " + response.message());
                }
            }


            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {

                Toast.makeText(MainActivity.this, "Failed to retrieve data. Please check your connection.", Toast.LENGTH_SHORT).show();
                Log.e("API_CALL", "onFailure: " + t.getMessage());
            }
        });
    }

    private void fetchMovieDetails(Movie movie) {
        OMDBApiService apiService = RetrofitClient.getRetrofitInstance().create(OMDBApiService.class);
        Call<Movie> call = apiService.getMovieDetails(movie.getImdbID(), "527b1648");

        call.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Movie detailedMovie = response.body();

                    movie.setRottenTomatoesRating(detailedMovie.getRottenTomatoesRating());
                    movie.setImdbRating(detailedMovie.getImdbRating());
                    movie.setReleased(detailedMovie.getReleased());
                    movie.setGenre(detailedMovie.getGenre());
                    movie.setDirector(detailedMovie.getDirector());
                    movie.setWriter(detailedMovie.getWriter());
                    movie.setActors(detailedMovie.getActors());
                    movie.setPlot(detailedMovie.getPlot());
                    movie.setLanguage(detailedMovie.getLanguage());
                    movie.setCountry(detailedMovie.getCountry());
                    movie.setAwards(detailedMovie.getAwards());
                    movie.setImdbVotes(detailedMovie.getImdbVotes());
                    movie.setTotalSeasons(detailedMovie.getTotalSeasons());
                    adapter.notifyDataSetChanged();
                } else {
                    Log.e("API_CALL", "onResponse: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                Log.e("API_CALL", "onFailure: " + t.getMessage());
            }
        });
    }
}
