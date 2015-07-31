package com.sinapsedigital.clima_sinapse;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by Ivan on 12/06/2015.
 */
public class SettingsActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Display the fragment as the main content.
        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new SettingsFragment())
                .commit();
    }
}
