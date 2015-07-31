package com.sinapsedigital.clima_sinapse;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ShareActionProvider;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

//import android.widget.ShareActionProvider;

//import android.support.v7.app.ActionBarActivity;


public class DetailActivity extends AppCompatActivity {//ActionBarActivity {
    private static final String LOG_TAG = DetailActivity.class.getSimpleName();

    private static final String FORECAST_SHARE_HASH_TAG = " #CLIMA_SINAPSE";
    private String mForecastStr;
    private ShareActionProvider mShareActionProvider;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent intent = getIntent();

        mForecastStr = intent.getStringExtra(Intent.EXTRA_TEXT);

        TextView textView = (TextView)findViewById(R.id.textviewClima);
        textView.setText(mForecastStr);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detail, menu);

        MenuItem item = menu.findItem(R.id.menu_item_share);

        mShareActionProvider = new ShareActionProvider(this);
        Intent shareIntent = getShareIntent();
        MenuItemCompat.setActionProvider(item, mShareActionProvider);

        if (mShareActionProvider != null) {
            mShareActionProvider.setShareIntent(shareIntent);
        } else {
            Log.d(LOG_TAG, "Share Action Provider é null?");
        }

        return true;
    }

    private Intent getShareIntent() {
//        TextView textView = (TextView)findViewById(R.id.textviewClima);
  //      String texto = textView.getText() + " #CLIMA_SINAPSE";

        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        sendIntent.putExtra(Intent.EXTRA_TEXT, mForecastStr + FORECAST_SHARE_HASH_TAG);
        sendIntent.setType("text/plain");
        //startActivity(sendIntent);
        //setShareIntent(sendIntent);
        return sendIntent;
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

        /*if(id == R.id.menu_item_share){
            TextView textView = (TextView)findViewById(R.id.textviewClima);
            String texto = textView.getText() + " #CLIMA_SINAPSE";

            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, texto);
            sendIntent.setType("text/plain");
            //startActivity(sendIntent);
            setShareIntent(sendIntent);
        }*/

        return super.onOptionsItemSelected(item);
    }



}
