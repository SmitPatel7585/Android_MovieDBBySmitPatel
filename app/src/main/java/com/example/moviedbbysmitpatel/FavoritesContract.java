package com.example.moviedbbysmitpatel;

import android.provider.BaseColumns;

public final class FavoritesContract {

    private FavoritesContract() {}

    public static final class FavoriteEntry implements BaseColumns {
        public static final String TABLE_NAME = "favorites";
        public static final String COLUMN_MOVIE_ID = "movie_id";
        public static final String COLUMN_MOVIE_TITLE = "movie_title";
        public static final String COLUMN_MOVIE_POSTER_URL = "movie_poster_url";
    }
}
