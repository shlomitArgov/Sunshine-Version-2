package com.example.android.sunshine.app;

import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            //Temporary use of dummy forecast data
            String[] weekForecastArray = new String[]{
                    "Today - Sunny - 33/24",
                    "Tomorrow - Mild - 27/20",
                    "Friday - Cloudy - 25/17",
                    "Saturday - Rainy - 24/18",
                    "Sunday - Foggy - 23/16",
                    "Monday - Sunny - 25/20",
                    "Tuesday - Clear - 28/21"};
            ArrayList<String> weekForecastList = new ArrayList<>(Arrays.asList(weekForecastArray));

            ArrayAdapter<String> forecastAdapter = new ArrayAdapter<String>(
                    getActivity(),  //Activity is a subclass of Context
                    R.layout.list_item_forcast, //layout of single item in the list
                    R.id.list_item_forcast_textview,    //TextView ID within the layout
                    weekForecastList);   //List of Data items

            //Bind the adapter to the ListView
            ((ListView) rootView.findViewById(R.id.listview_forcast)).setAdapter(forecastAdapter);
            return rootView;
        }
    }
}
