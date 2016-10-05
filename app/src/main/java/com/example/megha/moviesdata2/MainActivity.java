package com.example.megha.moviesdata2;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import com.example.megha.moviesdata2.R;

public class MainActivity extends Activity {

    SharedPreferences sharedpreferences;

    /**
     * Calling to display number of watched and tobewatched movies.
     * Made use of sharedPreferences..
     * @param savedInstanceState
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_main);
        sharedpreferences = getSharedPreferences("MyMovies", 0);
        int numofWatched = sharedpreferences.getInt("watched", 0);
        int numoftoWatch = sharedpreferences.getInt("toWatch", 0);
        ((TextView) findViewById(R.id.watched)).setText("Watched:" + numofWatched);
        ((TextView) findViewById(R.id.towatch)).setText("ToBeWatched:" + numoftoWatch);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //Calling function for performing work related to Adventure genre movies
    public void onAdventure(View view) {
        Intent adventureIntent = new Intent(this, CategoryActivity.class);
        adventureIntent.putExtra("category", "Adventure");
        startActivity(adventureIntent);
    }

    //Calling function for performing work related to Animation genre movies
    public void onAnimation(View view) {
        Intent animationIntent = new Intent(this, CategoryActivity.class);
        animationIntent.putExtra("category", "Animation");
        startActivity(animationIntent);
    }

    //Calling function for performing work related to Comedy genre movies
    public void onComedy(View view) {
        Intent comedyIntent = new Intent(this, CategoryActivity.class);
        comedyIntent.putExtra("category", "Comedy");
        startActivity(comedyIntent);
    }

    //Calling function for performing work related to Horror genre movies
    public void onHorror(View view) {
        Intent horrorIntent = new Intent(this, CategoryActivity.class);
        horrorIntent.putExtra("category", "Horror");
        startActivity(horrorIntent);
    }

    //Calling function for performing work related to Fantasy genre movies
    public void onFantasy(View view) {
        Intent fantasyIntent = new Intent(this, CategoryActivity.class);
        fantasyIntent.putExtra("category", "Fantasy");
        startActivity(fantasyIntent);
    }

    /**
     * Calling function to save files on internal storage and external storage
     * @param view
     */
    public void onFile(View view) {
        String movieDetails = new MovieDatabase(this).getMoviesDetails();
        try {
            FileOutputStream outputStream = new FileOutputStream(new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "MyWatchedMoviesExternal.txt"));
            outputStream.write(movieDetails.getBytes());
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            FileOutputStream outputStream = openFileOutput("MyWatchedMoviesInternal.txt", 0);
            outputStream.write(movieDetails.getBytes());
            outputStream.close();
            Toast.makeText(this, "File has been saved", Toast.LENGTH_SHORT).show();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

   //Calling function for deleting all tables and sharedpreferences also
    public void onReset(View view) {
//
        Intent resetIntent = new Intent(this, DeleteActivity.class);
        startActivity(resetIntent);
    }

   //Directing to Instructions on click on Instructions button
    public  void onInstructions(View view)
    {
        Intent instructionsIntent = new Intent(this, Instructions.class);
        startActivity(instructionsIntent);

    }
}

