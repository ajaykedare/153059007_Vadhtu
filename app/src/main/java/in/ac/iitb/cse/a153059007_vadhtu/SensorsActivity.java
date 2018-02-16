package in.ac.iitb.cse.a153059007_vadhtu;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

/**
 * Created by ajay on 23/1/18.
 */

public class SensorsActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensors);

        // get action bar
        ActionBar actionBar = getSupportActionBar();

        // Enabling Up / Back navigation
       // actionBar.setDisplayHomeAsUpEnabled(true);

        actionBar.setLogo(R.drawable.ic_iit_logo_small);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        // Hide the action bar title
        actionBar.setDisplayShowTitleEnabled(false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        getMenuInflater().inflate(R.menu.activity_main_actions, menu);

        MenuItem item = menu.findItem(R.id.action_spinner);
        Spinner spinner = (Spinner) MenuItemCompat.getActionView(item);
        spinner.setGravity(0);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.spinner_list_item_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);
        spinner.setSelection(1);
        //spinner.setOnItemSelectedListener(myChangeListener);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> arg0, View v, int position, long id)
            {
                if (position==0){
                    Intent login_activity_intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(login_activity_intent);
                }
            }

            public void onNothingSelected(AdapterView<?> arg0)
            {
                //Toast.makeText(getApplicationContext(),"Nothig Item Selected", Toast.LENGTH_SHORT).show();
            }
        });
        return super.onCreateOptionsMenu(menu);
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


        switch(id){
            case R.id.action_record:
                LaunchRecordActivity();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }


    }



    /**
     * Launching new activity
     * */
    private void LaunchSensorsActivity() {
        Intent i = new Intent(SensorsActivity.this, SensorsActivity.class);
        startActivity(i);
    }

    /**
     * Launching new activity
     * */
    private void LaunchRecordActivity() {
        Intent i = new Intent(SensorsActivity.this, RecordActivity.class);
        startActivity(i);
    }

    /**
     * Launching new activity
     * */
    private void LaunchLoginActivity() {
        Intent i = new Intent(SensorsActivity.this, MainActivity.class);
        startActivity(i);
    }
}
