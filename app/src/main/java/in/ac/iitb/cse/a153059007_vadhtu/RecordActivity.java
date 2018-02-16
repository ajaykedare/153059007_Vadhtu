package in.ac.iitb.cse.a153059007_vadhtu;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by ajay on 23/1/18.
 */

public class RecordActivity extends AppCompatActivity {

    ArrayList<TextView> timestampTextViewList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);

        // get action bar
        ActionBar actionBar = getSupportActionBar();

        // Enabling Up / Back navigation
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setLogo(R.drawable.ic_iit_logo_small);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        // Hide the action bar title
        actionBar.setDisplayShowTitleEnabled(false);



        //Set on-off switch listner
        Switch onOffSwitch = (Switch)  findViewById(R.id.on_off_switch);
        onOffSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm a ");
                Date date = new Date();
                String datestr = dateFormat.format(date);
                System.out.println(dateFormat.format(date));

                if(isChecked){

                } else{

                    LinearLayout ll = (LinearLayout) findViewById(R.id.records_linearlayout);

                    //Create a temporary instance which will be added to the list
                    final TextView sampleTextView = new TextView(getApplicationContext());
                    int cnt = timestampTextViewList.size();
                    cnt++;
                    String ts = "File"+cnt+" :" + datestr;
                    sampleTextView.setText(ts);
                    sampleTextView.setTextSize(20);

                    if(cnt>=6){
                        ll.removeView(timestampTextViewList.get(cnt-6));

                    }
                    timestampTextViewList.add(sampleTextView);
                    ll.addView(sampleTextView);
                    Toast.makeText(getApplicationContext(),"New File Saved!", Toast.LENGTH_SHORT).show();
                }

            }

        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        getMenuInflater().inflate(R.menu.activity_main_actions, menu);

        MenuItem item = menu.findItem(R.id.action_spinner);
        item.setVisible(false);
        this.invalidateOptionsMenu();
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
    private void LaunchRecordActivity() {
        Intent i = new Intent(RecordActivity.this, RecordActivity.class);
        startActivity(i);
    }
}