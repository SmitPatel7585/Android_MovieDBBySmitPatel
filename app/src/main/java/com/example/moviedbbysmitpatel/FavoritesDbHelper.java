package com.example.moviedbbysmitpatel;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class FavoritesDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "favorites.db";
    private static final int DATABASE_VERSION = 1;

    public FavoritesDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_FAVORITES_TABLE = "CREATE TABLE " + FavoritesContract.FavoriteEntry.TABLE_NAME + " ("
                + FavoritesContract.FavoriteEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + FavoritesContract.FavoriteEntry.COLUMN_MOVIE_ID + " TEXT NOT NULL, "
                + FavoritesContract.FavoriteEntry.COLUMN_MOVIE_TITLE + " TEXT NOT NULL, "
                + FavoritesContract.FavoriteEntry.COLUMN_MOVIE_POSTER_URL + " TEXT NOT NULL);";

        db.execSQL(SQL_CREATE_FAVORITES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + FavoritesContract.FavoriteEntry.TABLE_NAME);
        onCreate(db);
    }

    // Method to add a movie to favorites
    public long addFavorite(String movieId, String movieTitle, String moviePosterUrl) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(FavoritesContract.FavoriteEntry.COLUMN_MOVIE_ID, movieId);
        values.put(FavoritesContract.FavoriteEntry.COLUMN_MOVIE_TITLE, movieTitle);
        values.put(FavoritesContract.FavoriteEntry.COLUMN_MOVIE_POSTER_URL, moviePosterUrl);
        return db.insert(FavoritesContract.FavoriteEntry.TABLE_NAME, null, values);
    }

    // Method to check if a movie is already in favorites
    public boolean isFavorite(String movieId) {
        SQLiteDatabase db = getReadableDatabase();
        String[] projection = {
                FavoritesContract.FavoriteEntry._ID
        };
        String selection = FavoritesContract.FavoriteEntry.COLUMN_MOVIE_ID + " = ?";
        String[] selectionArgs = { movieId };
        Cursor cursor = db.query(
                FavoritesContract.FavoriteEntry.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }

    // Method to remove a movie from favorites
    public void removeFavorite(String movieId) {
        SQLiteDatabase db = getWritableDatabase();
        String selection = FavoritesContract.FavoriteEntry.COLUMN_MOVIE_ID + " = ?";
        String[] selectionArgs = { movieId };
        db.delete(FavoritesContract.FavoriteEntry.TABLE_NAME, selection, selectionArgs);
    }



}
