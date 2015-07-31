package com.sinapsedigital.clima_sinapse;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends AppCompatActivity {
    private final String LOG_TAG = "MAIN_APP_CLIMA";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(LOG_TAG, "Passou por onCreate (created)");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
            Intent settingsIntent = new Intent(this, SettingsActivity.class);
            startActivity(settingsIntent);
            return  true;
        }

        if(id == R.id.action_map){
            opernPreferredLocationInMap();
            return  true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void opernPreferredLocationInMap(){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        String localizacao = sharedPreferences.getString(
                getString(R.string.pref_location_key),
                getString(R.string.pref_location_default));

        //Uri uriLocalizacao =  Uri.parse(String.format(Locale.ENGLISH, "geo:%f,%f", lat, lng));
        Uri geoLocalizacao = Uri.parse("geo:0,0?").buildUpon()
                .appendQueryParameter("q", localizacao)
                .build();

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(geoLocalizacao);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Log.e(LOG_TAG, "Não pode chamar " + localizacao);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(LOG_TAG, "Passou por onStart (visible)");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(LOG_TAG, "Passou por onPause (paused)");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(LOG_TAG, "Passou por onResume (resumed)");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(LOG_TAG, "Passou por onStop (stopped)");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // The activity is about to be destroyed.
        Log.d(LOG_TAG, "Passou por onDestroy (destroyed)");
    }

}
