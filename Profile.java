package com.example.lloyd.healthnutritiontool;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.zip.Inflater;

public class Profile extends AppCompatActivity {
    String namee;
    TabLayout tabLayout;
    View view;
    list_adapter listAdapter;
    DatabaseReference databaseReference;
    private PagerAdapter pagerAdapter;
    SearchView searchView;
    MenuItem menuItem;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        myDatabase db;
        db = new myDatabase(this);
        try {
            File database = this.getDatabasePath(myDatabase.DATABASE_NAME);
            if (false == database.exists()) {
                db.getReadableDatabase();
                if (copyDatabase(this)) {
                    Toast.makeText(this, "Success", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, "Error", Toast.LENGTH_LONG).show();
                    return;
                }
            }

        } catch (Exception ee) {
            Toast.makeText(this, ee.getMessage(), Toast.LENGTH_LONG).show();
        }


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        try {
            FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        } catch (Exception eee) {

        }

        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.user));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.list));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.chart));



        final ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        pagerAdapter = new PagerAdapter(getSupportFragmentManager());
        tabLayout.setupWithViewPager(viewPager);
        setUpViewPager(viewPager);


    }

    private void setUpViewPager(ViewPager viewPager) {
        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new tab1(), null);
        adapter.addFragment(new tab2(), null);
        adapter.addFragment(new tab3(), null);
        viewPager.setAdapter(adapter);
        tabLayout.getTabAt(0).setIcon(R.drawable.user);
        tabLayout.getTabAt(1).setIcon(R.drawable.list);
        tabLayout.getTabAt(2).setIcon(R.drawable.chart);


    }

    public void hidePersonal(View view) {
        CardView card = (CardView) findViewById(R.id.personal_card);
        card.setVisibility(View.GONE);
        CardView card1 = (CardView) findViewById(R.id.contact_card);
        card1.setVisibility(View.VISIBLE);
    }

    public void show_personal(View view) {
        CardView card = (CardView) findViewById(R.id.personal_card);
        card.setVisibility(View.VISIBLE);
        CardView card1 = (CardView) findViewById(R.id.contact_card);
        card1.setVisibility(View.GONE);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent(getBaseContext(), Listing.class);
        Intent iii = getIntent();
        String value = iii.getStringExtra("brgy");
        intent.putExtra("brgy",value);
        startActivity(intent);
        finish();


        return super.onOptionsItemSelected(item);
    }
    private boolean copyDatabase (Context context){

        try{
            InputStream inputStream = context.getAssets().open(myDatabase.DATABASE_NAME);
            String outFileName  = myDatabase.LOCATION + myDatabase.DATABASE_NAME;
            OutputStream outputStream = new FileOutputStream(outFileName);
            byte[] buff = new byte[1024];
            int lenth = 0;
            while ((lenth=inputStream.read(buff))>0){
                outputStream.write(buff,0,lenth);
            }
            outputStream.flush();
            outputStream.close();
            Log.w("Mainactivity","DB copied");
            return true;
        }catch (Exception ee){
            Toast.makeText(this,ee.getMessage(), Toast.LENGTH_LONG).show();
            return false;
        }
    }


}
