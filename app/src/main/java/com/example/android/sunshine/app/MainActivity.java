package com.example.android.sunshine.app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.v(this.getClass().getSimpleName(), "Entered onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new ForcastFragment())
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.v(this.getClass().getSimpleName(), "Entered onCreateOptionsMenu");
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        // The activity is about to become visible.
        Log.v(this.getClass().getSimpleName(), "Entered onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        // The activity is about to become visible.
        Log.v(this.getClass().getSimpleName(), "Entered onResume");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.v(this.getClass().getSimpleName(), "Entered onDestroy");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.v(this.getClass().getSimpleName(), "Entered onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.v(this.getClass().getSimpleName(), "Entered onStop");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        Log.v(this.getClass().getSimpleName(), "Entered onOptionsItemSelected");
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id) {
            case R.id.settings_menu_item: {
                Intent settingsIntent = new Intent(this, SettingsActivity.class);
                startActivity(settingsIntent);
                return true;
            }
            case R.id.preffered_location_on_map_action: {
                //Check that the app has permissions to implicitly call an app in order to view location on a map exists
                openPrefferedLocationInMap();
                return true;
            }
            default: return super.onOptionsItemSelected(item);
        }
    }

    private void openPrefferedLocationInMap() {
        Intent displayLocationOnMapIntent = new Intent(Intent.ACTION_VIEW);
        if (displayLocationOnMapIntent.resolveActivity(this.getPackageManager()) != null) {
            //Get user's preffered location
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
            String preferredLocation = sharedPreferences.getString(getString(R.string.pref_location_key), getString(R.string.pref_location_default));
            //Set user's location in the intent
            //Geocoder geocoder = new Geocoder(this, Locale.getDefault());
            //try {
              //  List<Address> addresses = geocoder.getFromLocationName(preferredLocation, 1);
                //Address address = addresses.get(0);
                Uri geolocationUri = Uri.parse("geo:0,0").buildUpon().appendQueryParameter("q", preferredLocation).appendQueryParameter("z","11").build();
                displayLocationOnMapIntent.setData(geolocationUri);
                startActivity(displayLocationOnMapIntent);
            //} catch (IOException e) {
            //Log.e(this.getClass().getSimpleName(), e.getLocalizedMessage());
            //}
        } else {
            Log.d(this.getClass().getSimpleName(), "Failed to get intent for viewing preferred location on map");
        }
    }
}


