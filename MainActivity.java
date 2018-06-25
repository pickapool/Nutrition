package com.example.lloyd.healthnutritiontool;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Console;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        try {
            FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        }catch (Exception eee){

        }

        drawerLayout =(DrawerLayout)findViewById(R.id.drawer);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }

    public void registration(MenuItem menuItem){
        Intent intent = new Intent(MainActivity.this,Registration.class);
        Intent iii = getIntent();
        String value = iii.getStringExtra("brgy");
        intent.putExtra("brgy",value);
        startActivity(intent);
        finish();
        drawerLayout.closeDrawers();

    }
    public void goToList(MenuItem menuItem){
        Intent ii = new Intent(MainActivity.this,Listing.class);
        Intent iii = getIntent();
        String value = iii.getStringExtra("brgy");
        ii.putExtra("brgy",value);
        startActivity(ii);
        finish();
        }

        public void findID(MenuItem menuItem){
            Intent ii = new Intent(MainActivity.this,Listing1.class);
            Intent iii = getIntent();
            String value = iii.getStringExtra("brgy");
            ii.putExtra("brgy",value);
            startActivity(ii);
            finish();

        }
        public void exits(MenuItem menuItem){
            android.os.Process.killProcess(android.os.Process.myPid());
        }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(actionBarDrawerToggle.onOptionsItemSelected(item)){

            return true;
        }
        return super.onOptionsItemSelected(item);
    }



}
