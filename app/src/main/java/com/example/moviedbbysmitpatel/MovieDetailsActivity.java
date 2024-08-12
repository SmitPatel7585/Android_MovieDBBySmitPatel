package com.example.moviedbbysmitpatel;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import androidx.appcompat.app.AppCompatActivity;

public class MovieDetailsActivity extends AppCompatActivity {
    private TextView titleTextView, yearTextView, imdbRatingTextView,
            releasedTextView, genreTextView, directorTextView, writerTextView, actorsTextView,
            plotTextView, languageTextView, countryTextView, awardsTextView, imdbVotesTextView,
            totalSeasonsTextView;
    private ImageView posterImageView, favoriteButton;

    private boolean isFavorite = false;
    private FavoritesDbHelper favoritesDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);


        titleTextView = findViewById(R.id.titleTextView);
        yearTextView = findViewById(R.id.yearTextView);
        imdbRatingTextView = findViewById(R.id.imdbRatingTextView);
        releasedTextView = findViewById(R.id.releasedTextView);
        genreTextView = findViewById(R.id.genreTextView);
        directorTextView = findViewById(R.id.directorTextView);
        writerTextView = findViewById(R.id.writerTextView);
        actorsTextView = findViewById(R.id.actorsTextView);
        plotTextView = findViewById(R.id.plotTextView);
        languageTextView = findViewById(R.id.languageTextView);
        countryTextView = findViewById(R.id.countryTextView);
        awardsTextView = findViewById(R.id.awardsTextView);
        imdbVotesTextView = findViewById(R.id.imdbVotesTextView);
        totalSeasonsTextView = findViewById(R.id.totalSeasonsTextView);
        posterImageView = findViewById(R.id.posterImageView);
        favoriteButton = findViewById(R.id.favoriteButton);


        favoritesDbHelper = new FavoritesDbHelper(this);

        // Retrieve movie data from Intent
        Movie movie = (Movie) getIntent().getSerializableExtra("movie");
        if (movie != null) {
            // Set data to TextViews
            titleTextView.setText(movie.getTitle() != null ? movie.getTitle() : "N/A");
            yearTextView.setText(movie.getYear() != null ? "Year: " + movie.getYear() : "Year: N/A");
            imdbRatingTextView.setText(movie.getImdbRating() != null ? "IMDB Rating: " + movie.getImdbRating() : "IMDB Rating: N/A");
            releasedTextView.setText(movie.getReleased() != null ? "Released: " + movie.getReleased() : "Released: N/A");
            genreTextView.setText(movie.getGenre() != null ? "Genre: " + movie.getGenre() : "Genre: N/A");
            directorTextView.setText(movie.getDirector() != null ? "Director: " + movie.getDirector() : "Director: N/A");
            writerTextView.setText(movie.getWriter() != null ? "Writer: " + movie.getWriter() : "Writer: N/A");
            actorsTextView.setText(movie.getActors() != null ? "Actors: " + movie.getActors() : "Actors: N/A");
            plotTextView.setText(movie.getPlot() != null ? "Plot: " + movie.getPlot() : "Plot: N/A");
            languageTextView.setText(movie.getLanguage() != null ? "Language: " + movie.getLanguage() : "Language: N/A");
            countryTextView.setText(movie.getCountry() != null ? "Country: " + movie.getCountry() : "Country: N/A");
            awardsTextView.setText(movie.getAwards() != null ? "Awards: " + movie.getAwards() : "Awards: N/A");
            imdbVotesTextView.setText(movie.getImdbVotes() != null ? "IMDB Votes: " + movie.getImdbVotes() : "IMDB Votes: N/A");
            totalSeasonsTextView.setText(movie.getTotalSeasons() != null ? "Total Seasons: " + movie.getTotalSeasons() : "Total Seasons: N/A");

            // Load poster image
            if (movie.getPoster() != null && !movie.getPoster().isEmpty()) {
                Picasso.get().load(movie.getPoster()).into(posterImageView);
            }

            // Check if the movie is already in favorites
            isFavorite = favoritesDbHelper.isFavorite(movie.getImdbID());
            updateFavoriteButtonState(isFavorite);

            // Set click listener for the favorite button
            favoriteButton.setOnClickListener(v -> {
                if (isFavorite) {

                    favoritesDbHelper.removeFavorite(movie.getImdbID());
                    Toast.makeText(MovieDetailsActivity.this, "Removed from favorites", Toast.LENGTH_SHORT).show();
                } else {
                    long result = favoritesDbHelper.addFavorite(
                            movie.getImdbID(),
                            movie.getTitle(),
                            movie.getPoster()
                    );

                    if (result != -1) {
                        Toast.makeText(MovieDetailsActivity.this, "Added to favorites", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MovieDetailsActivity.this, "Failed to add to favorites", Toast.LENGTH_SHORT).show();
                    }
                }

                isFavorite = !isFavorite;
                updateFavoriteButtonState(isFavorite);
            });
        }
    }

    private void updateFavoriteButtonState(boolean isFavorite) {
        if (isFavorite) {
            favoriteButton.setImageResource(R.drawable.favorite_fill);
        } else {
            favoriteButton.setImageResource(R.drawable.favorite_outline);
        }
    }
}
