package com.sinapsedigital.clima_sinapse;

import android.content.Intent;
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
public class ForecastFragment extends Fragment {
    private ArrayAdapter<String> mForecastAdptador;
    public ForecastFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Adicione esta linha para que este fragmento para lidar com eventos de menu
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.forecastfragment, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_refresh) {
            atualizarClima();
            return  true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        mForecastAdptador = new ArrayAdapter<String>(
                getActivity(),
                R.layout.list_item_forecast,
                R.id.list_item_forecast_textview,
                new ArrayList<String>());

        ListView listView = (ListView)rootView.findViewById(R.id.listview_forecast);

        listView.setAdapter(mForecastAdptador);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object item = parent.getItemAtPosition(position);
                if(item != null && item.toString().length() > 0){
                    //Toast.makeText(getActivity(), item.toString(), Toast.LENGTH_SHORT).show();
                    Intent detailIntent = new Intent(getActivity(), DetailActivity.class);
                    detailIntent.putExtra(Intent.EXTRA_TEXT, item.toString());
                    startActivity(detailIntent);
                }
            }
        });

        return rootView;
    }

    private void atualizarClima(){
        FetchWeatherTask task = new FetchWeatherTask(getActivity(), mForecastAdptador);
        //task.execute("94043");
        //task.execute("49030060");
        //SharedPreferences sharedPreferences = getActivity().getSharedPreferences(null,getActivity().MODE_PRIVATE);
        //String cep = sharedPreferences.getString(getString(R.string.pref_location_key), getString(R.string.pref_location_default));
        String location = PreferenceManager.getDefaultSharedPreferences(getActivity())
                .getString(getString(R.string.pref_location_key),
                        getString(R.string.pref_location_default));

        String unit = PreferenceManager.getDefaultSharedPreferences(getActivity())
                .getString(getString(R.string.pref_unit_key),
                        getString(R.string.pref_unit_default));

        //Log.v("LOG_CEP", "CEP: " + location);
        task.execute(location, unit);
    }

    @Override
    public void onStart() {
        super.onStart();
        atualizarClima();
    }
}
