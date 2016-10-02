package com.example.megha.moviesdata2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class CategoryActivity extends Activity {

    /**
     *Calling to display genre on top  of the screen
     * @param savedInstanceState
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_category);
        Bundle basket = getIntent().getExtras();
        String category = BuildConfig.FLAVOR;
        if (basket != null) {
            category = basket.getString("category");
        }
        ((TextView) findViewById(R.id.todo)).setText(category);
    }



    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**Performing action on watched movies like
     *
     * adding new movie or displaying already watched  movies.
     *
     */
    public void onWatched(View view) {
        Bundle basket = getIntent().getExtras();
        String Category = BuildConfig.FLAVOR;
        if (basket != null) {
            Category = basket.getString("category");
        }
        Intent watchedIntent = new Intent(this, MoviesStatus.class);
        watchedIntent.putExtra("todo", "Watched");
        watchedIntent.putExtra("category", Category);
        startActivity(watchedIntent);
    }

    /**Performing action on to be watched movies like
     *
     * adding new movie or displaying already watched  movies.
     *
     */
    public void onToWatch(View view) {
        Bundle basket = getIntent().getExtras();
        String Category = BuildConfig.FLAVOR;
        if (basket != null) {
            Category = basket.getString("category");
        }
        Intent watchedIntent = new Intent(this, MoviesStatus.class);
        watchedIntent.putExtra("todo", "ToBeWatched");
        watchedIntent.putExtra("category", Category);
        startActivity(watchedIntent);
    }

    /**
     *   Calling to send conrol back to
     MainActivity
     */
    public void onBackMovie(View view) {
        startActivity(new Intent(this, MainActivity.class));
    }
}
