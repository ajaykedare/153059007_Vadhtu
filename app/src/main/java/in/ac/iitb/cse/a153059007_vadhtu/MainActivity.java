package in.ac.iitb.cse.a153059007_vadhtu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // UI references.
    private AutoCompleteTextView mEmailView;
    private AutoCompleteTextView mAgeView;
    private AutoCompleteTextView mMobileNoView;
    private AutoCompleteTextView mFirstnameView;
    private AutoCompleteTextView mLastnameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setLogo(R.drawable.ic_iit_logo_small);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        // Hide the action bar title
        actionBar.setDisplayShowTitleEnabled(false);

        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);
        mFirstnameView = (AutoCompleteTextView) findViewById(R.id.firstname);
        mLastnameView= (AutoCompleteTextView) findViewById(R.id.lastname);
        mMobileNoView = (AutoCompleteTextView) findViewById(R.id.phone);
        mAgeView = (AutoCompleteTextView) findViewById(R.id.age);
        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signInClickFunction();
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        getMenuInflater().inflate(R.menu.activity_main_actions, menu);

        MenuItem item = menu.findItem(R.id.action_spinner);
        Spinner spinner = (Spinner) MenuItemCompat.getActionView(item);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.spinner_list_item_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);
        spinner.setSelection(0);
        //spinner.setOnItemSelectedListener(myChangeListener);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> arg0, View v, int position, long id)
            {
                if (position==1){
                    Intent login_activity_intent = new Intent(getApplicationContext(), SensorsActivity.class);
                    startActivity(login_activity_intent);
                }
            }

            public void onNothingSelected(AdapterView<?> arg0)
            {

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
        Intent i = new Intent(MainActivity.this, SensorsActivity.class);
        startActivity(i);
    }

    /**
     * Launching new activity
     * */
    private void LaunchRecordActivity() {
        Intent i = new Intent(MainActivity.this, RecordActivity.class);
        startActivity(i);
    }

    /**
     * Launching new activity
     * */
    private void LaunchLoginActivity() {
        Intent i = new Intent(MainActivity.this, MainActivity.class);
        startActivity(i);
    }

    public void signInClickFunction()
    {

        String email = mEmailView.getText().toString();
        String firstname =  mFirstnameView.getText().toString();
        String lastname=  mLastnameView.getText().toString();
        String mobile=  mMobileNoView.getText().toString();
        String age =  mAgeView.getText().toString();

        mEmailView.setError(null);
        mFirstnameView.setError(null);
        mLastnameView.setError(null);
        mMobileNoView.setError(null);
        mAgeView.setError(null);

        boolean isAllValid = true;
        View focusView = null;

        //Email
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            isAllValid = false;
        } else if (!isEmailValid(email)) {
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
            isAllValid = false;
        }

        //Firstname
        if (TextUtils.isEmpty(firstname)) {
            mFirstnameView.setError(getString(R.string.error_field_required));
            focusView = mFirstnameView;
            isAllValid = false;
        }

        //LastName
        if (TextUtils.isEmpty(lastname)) {
            mLastnameView.setError(getString(R.string.error_field_required));
            focusView = mLastnameView;
            isAllValid = false;
        }

        //Mobile
        if (TextUtils.isEmpty(mobile)) {
            mMobileNoView.setError(getString(R.string.error_field_required));
            focusView = mMobileNoView;
            isAllValid = false;
        } else if (!isMobileValid(mobile)) {
            mMobileNoView.setError(getString(R.string.error_invalid_mobile));
            focusView = mMobileNoView;
            isAllValid = false;
        }

        //Age
        if (TextUtils.isEmpty(age)) {
            mAgeView.setError(getString(R.string.error_field_required));
            focusView = mAgeView;
            isAllValid = false;
        }


        if (!isAllValid) {
            focusView.requestFocus();
        } else {
            Intent intent = new Intent(getApplicationContext(), SensorsActivity.class);
            startActivity(intent);
        }

    }

    boolean isEmailValid(String e){
        return e.contains("@");
    }

    boolean isMobileValid(String m){
        return m.length()==10;
    }

}
