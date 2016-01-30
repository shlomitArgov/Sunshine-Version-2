package com.example.android.sunshine.app;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v(this.getClass().getSimpleName(), "[" + this.getClass().getSimpleName() + "] Entered onCreate");
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            //When the activity is created for the first time, attach a forecast fragment.
            //When the activity is being restored, the attached fragment(s) are automatically reattached
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.main_container, new ForecastFragment())
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.v(this.getClass().getSimpleName(), "[" + this.getClass().getSimpleName() + "] Entered onCreateOptionsMenu");
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        // The activity is about to become visible.
        Log.v(this.getClass().getSimpleName(), "[" + this.getClass().getSimpleName() + "] Entered onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        // The activity is about to become visible.
        Log.v(this.getClass().getSimpleName(), "[" + this.getClass().getSimpleName() + "] Entered onResume");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.v(this.getClass().getSimpleName(), "[" + this.getClass().getSimpleName() + "] Entered onDestroy");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.v(this.getClass().getSimpleName(), "[" + this.getClass().getSimpleName() + "] Entered onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.v(this.getClass().getSimpleName(), "[" + this.getClass().getSimpleName() + "] Entered onStop");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        Log.v(this.getClass().getSimpleName(), "[" + this.getClass().getSimpleName() + "] Entered onOptionsItemSelected");
        int id = item.getItemId();

        switch (id) {
            case R.id.detail_settings_menu_item:
            case R.id.main_settings_menu_item: {
                Intent settingsIntent = new Intent(this, SettingsActivity.class);
                startActivity(settingsIntent);
                return true;
            }
            case R.id.preferred_location_on_map_action: {
                //Check that the app has permissions to implicitly call an app in order to view location on a map exists
                openPreferredLocationInMap();
                return true;
            }
            default: return super.onOptionsItemSelected(item);
        }
    }

    private void openPreferredLocationInMap() {
        Intent displayLocationOnMapIntent = new Intent(Intent.ACTION_VIEW);
        if (displayLocationOnMapIntent.resolveActivity(this.getPackageManager()) != null) {
            //Get user's preferred location
                String location = Utility.getPreferredLocation(this);
                Uri geolocationUri = Uri.parse("geo:0,0").buildUpon().appendQueryParameter("q", location).appendQueryParameter("z","11").build();
                displayLocationOnMapIntent.setData(geolocationUri);
                startActivity(displayLocationOnMapIntent);
        } else {
            Log.d(this.getClass().getSimpleName(), "Failed to get intent for viewing preferred location on map");
        }
    }
}


