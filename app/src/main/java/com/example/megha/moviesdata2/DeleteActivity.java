package com.example.megha.moviesdata2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class DeleteActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
        String cat[]={"Adventure","Animation","Comedy","Horror","Fantasy"};
        String movies="";
        for(int i=0;i<cat.length;i++)
        {
             movies = movies + new MovieDatabase(this).getAllMovies("ToBeWatched", cat[i]);

        }
        ((TextView) findViewById(R.id.movielist)).setText(movies);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_delete, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onDeleteMovie(View view)
    {
        MovieDatabase mb = new MovieDatabase(this);
        SharedPreferences sharedpreferences;
        sharedpreferences = getSharedPreferences("MyMovies", 0);
        String movieName = ((EditText) findViewById(R.id.moviename)).getText().toString();
        mb.movieDelete(movieName);
        SharedPreferences.Editor editor = getSharedPreferences("MyMovies", 0).edit();
        editor.putInt("toWatch", sharedpreferences.getInt("toWatch", 0) - 1);
        editor.commit();
        Toast.makeText(this, "Movie has been deleted",
                Toast.LENGTH_SHORT).show();
        Intent deleteStatusIntent = new Intent(this, DeleteActivity.class);
        startActivity(deleteStatusIntent);


    }

    public void onBackMovie(View view) {
        Intent movieStatusIntent = new Intent(this, MainActivity.class);
        startActivity(movieStatusIntent);
    }
}
