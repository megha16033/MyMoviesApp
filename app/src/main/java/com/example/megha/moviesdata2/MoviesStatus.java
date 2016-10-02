package com.example.megha.moviesdata2;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MoviesStatus extends Activity {
    SharedPreferences sharedpreferences;

    //Caling for displaying watched or tobewatched movies whenever click all those buttons.
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies_status);
        this.sharedpreferences = getSharedPreferences("MyMovies", 0);
        Bundle basket = getIntent().getExtras();
        String category = BuildConfig.FLAVOR;
        String todo = BuildConfig.FLAVOR;
        if (basket != null) {
            category = basket.getString("category");
            todo = basket.getString("todo");
        }
        ((TextView) findViewById(R.id.label)).setText(todo);
        String movies = new MovieDatabase(this).getAllMovies(todo, category);
        ((TextView) findViewById(R.id.movielist)).setText(movies);
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == com.example.megha.moviesdata2.R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //Calling while adding new movie under watched or tobewatched status
    public void onAddMovie(View view) {
        Bundle basket = getIntent().getExtras();
        String category = BuildConfig.FLAVOR;
        String todo = BuildConfig.FLAVOR;
        if (basket != null) {
            category = basket.getString("category");
            todo = basket.getString("todo");
        }
        String movieName = ((EditText) findViewById(R.id.moviename)).getText().toString();
        if(movieName.equals(""))
        {
            Toast.makeText(this, " Please enter a movie name" , Toast.LENGTH_SHORT).show();
        }
        else
        {
            int res = new MovieDatabase(this).addMovie(new Movie(((EditText) findViewById(R.id.moviename)).getText().toString(), todo, category));
            Editor editor = getSharedPreferences("MyMovies", 0).edit();
            if (res == 1)
            {
                editor.putInt("watched", this.sharedpreferences.getInt("watched", 0) + 1);
                editor.putInt("toWatch", this.sharedpreferences.getInt("toWatch", 0) - 1);
            }
            else if (res == 2)
            {
                if (todo.equals("Watched")) {
                    editor.putInt("watched", this.sharedpreferences.getInt("watched", 0) + 1);
                } else if (todo.equals("ToBeWatched")) {
                    int numoftoWatch = this.sharedpreferences.getInt("toWatch", 0);
                    editor.putInt("toWatch", numoftoWatch + 1);
                }
            }
            editor.commit();
            ((TextView) findViewById(R.id.label)).setText(todo);
            String movies = new MovieDatabase(this).getAllMovies(todo, category);
            ((TextView) findViewById(R.id.movielist)).setText(movies);
            Toast.makeText(this, "Movie has been added",
                    Toast.LENGTH_SHORT).show();
        }
    }

    //Redirecting control back to CategoryActivity
    public void onBackMovie(View view) {
        Intent movieStatusIntent = new Intent(this, CategoryActivity.class);
        movieStatusIntent.putExtra("category", getIntent().getExtras().getString("category"));
        startActivity(movieStatusIntent);
    }

    //Redirecting control back to MainActivity
    public void onHomeMovie(View view) {
        startActivity(new Intent(this, MainActivity.class));
    }
}
