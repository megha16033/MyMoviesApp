package com.example.megha.moviesdata2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MovieDatabase extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "movieManager";
    private static final int DATABASE_VERSION = 1;
    private static final String KEY_CATEGORY = "category";
    private static final String KEY_NAME = "name";
    private static final String KEY_STATUS = "status";
    private static final String TABLE_MOVIES = "movies";


    public MovieDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //Calling for creating database for all the movies..
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE movies(name TEXT PRIMARY KEY,status TEXT,category TEXT )");
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS movies");
        onCreate(db);
    }

    //Calling for deleting Movies table
    public void onReset(SQLiteDatabase db) {
        db.execSQL("DELETE FROM movies");
    }

   //Calling for adding movies under watched and tobewatched status
    public int addMovie(Movie movie) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        int result = 0;
        Cursor cursor = db.rawQuery("SELECT status FROM movies WHERE name = \"" + movie.getMovieName() + "\"", null);
        if (!cursor.moveToFirst()) {
            System.out.println("Inside else if");
            values.put(KEY_NAME, movie.getMovieName());
            values.put(KEY_STATUS, movie.getMovieStatus());
            values.put(KEY_CATEGORY, movie.getMovieCategory());
            result = 2;
            db.insert(TABLE_MOVIES, null, values);
        } else if (cursor.getString(0).equals("ToBeWatched")) {
            System.out.println("Inside if");
            String strSQL = "UPDATE  movies SET status = \"Watched\" WHERE name =  \"" + movie.getMovieName() + "\"";
            result = DATABASE_VERSION;
            db.execSQL(strSQL);
        }
        db.close();
        return result;
    }

    //Calling for retreiving movienames from table Movies...
    public String getAllMovies(String status, String category) {
        Cursor cursor = getReadableDatabase().rawQuery("SELECT  * FROM movies WHERE status = \"" + status + "\" and " + KEY_CATEGORY + " =\"" + category + "\"", null);
        String movieList = BuildConfig.FLAVOR;
        if (cursor.moveToFirst()) {
            do {
                movieList = movieList + cursor.getString(0) + "\n";
            } while (cursor.moveToNext());
        }
        return movieList;
    }

    //Calling for retreiving all data from table Movies...
    public String getMoviesDetails() {
        Cursor cursor = getReadableDatabase().rawQuery("SELECT  * FROM movies WHERE status = \"Watched\" ORDER BY category", null);
        String movieList = BuildConfig.FLAVOR;
        if (cursor.moveToFirst()) {
            do {
                movieList = movieList + cursor.getString(0) + "----" + cursor.getString(2) + "\n";
            } while (cursor.moveToNext());
        }
        return movieList;
    }
}
