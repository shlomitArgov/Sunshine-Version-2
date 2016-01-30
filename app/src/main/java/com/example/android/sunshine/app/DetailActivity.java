package com.example.android.sunshine.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.ShareActionProvider;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class DetailActivity extends ActionBarActivity {
    private static final String EXTRA_FORECAST = "com.example.android.sunshine.app.FORECAST_DETAILS";
    private static final String SUNSHINE_APP = " #SunshineApp";
    private static final String LOG_TAG = DetailActivity.class.getSimpleName();
    private static String mForecastText;
    private ShareActionProvider mShareActionProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.detail_container, new PlaceholderFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.detail, menu);
        MenuItem shareItem = (MenuItem) menu.findItem(R.id.share_forecast_action);
        mShareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(shareItem);
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        String stringExtra = mForecastText + SUNSHINE_APP;
        //Prevent remaining on the app used for sharing when the task is cancelled
        shareIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
        shareIntent.putExtra(Intent.EXTRA_TEXT, stringExtra);
        shareIntent.setType("text/plain");
        if (mShareActionProvider != null) {
            mShareActionProvider.setShareIntent(shareIntent);
        } else {
            Log.d(LOG_TAG, "ShareActionProvider is null");
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.detail_settings_menu_item) {

            Intent settingsIntent = new Intent(this, SettingsActivity.class);
            startActivity(settingsIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            View rootView = inflater.inflate(R.layout.fragment_detail, container, false);
            TextView itemTextView = (TextView)rootView.findViewById(R.id.detail_text_view);
            mForecastText = getActivity().getIntent().getStringExtra(EXTRA_FORECAST);
            itemTextView.setText(mForecastText);
            return rootView;
        }
    }
}