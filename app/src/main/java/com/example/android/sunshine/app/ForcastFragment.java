package com.example.android.sunshine.app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class ForcastFragment extends Fragment {
    private ArrayAdapter<String> forecastAdapter;
    private static final String EXTRA_FORECAST = "com.example.android.sunshine.app.FORECAST_DETAILS";

    public ForcastFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Support menu events in this fragment
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.forcastfragment, menu);
    }

    @Override
    public void onStart() {
        super.onStart();
        updateWeather();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (id) {
            case R.id.action_refresh: {
                updateWeather();
                return true;
            }

            default: {
                return super.onOptionsItemSelected(item);
            }
        }
    }

    private void updateWeather() {
        FetchWeatherTask fetchWeatherTask = new FetchWeatherTask(getActivity(),forecastAdapter);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String zipCode = sharedPreferences.getString(getString(R.string.pref_location_key), getString(R.string.pref_location_default));
        fetchWeatherTask.execute(zipCode);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        ListView forcastListView = (ListView)rootView.findViewById(R.id.listview_forcast);

        forecastAdapter = new ArrayAdapter<String>(
                getActivity(),  //Activity is a subclass of Context
                R.layout.list_item_forcast, //layout of single item in the list
                R.id.list_item_forcast_textview,    //TextView ID within the layout
                new ArrayList<String>());   //List of Data items

        //Bind the adapter to the ListView
        forcastListView.setAdapter(forecastAdapter);
        forcastListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

//                Toast toast = Toast.makeText(getActivity(), forecastAdapter.getItem(i),Toast.LENGTH_SHORT);
//                toast.show();
                Intent detailIntent = new Intent(getActivity(), DetailActivity.class);
                detailIntent.putExtra(EXTRA_FORECAST, forecastAdapter.getItem(i));
                startActivity(detailIntent);
            }
        });

        return rootView;
    }
}
