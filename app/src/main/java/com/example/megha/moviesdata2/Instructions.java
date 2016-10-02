package com.example.megha.moviesdata2;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class Instructions extends ActionBarActivity {

    @Override
    /**
     * Calling to display instructions related to storage of files
     * and called when clicked Instructions button.
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructions2);

        String instructions = "1. A file created for internal storage named MyWatchedMoviesInternal.txt will contain names and genre" +
                "of watched movies and will be deleted once an app is deleted." + "\n" + "2. A file created for external storage named " +
                "MyWatchedMoviesExternal.txt will also contain names and genre of watched movies but will be accessible to an external user";
        TextView instructionList = (TextView) findViewById(R.id.instructionslist);
        instructionList.setText(instructions);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_instructions, menu);
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

    /**
     * Sending control back to MainActivity
     * @param view
     */
    public void onBack(View view) {
        Intent instructionsIntent = new Intent(this, MainActivity.class);
        startActivity(instructionsIntent);
    }
}
