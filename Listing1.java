package com.example.lloyd.healthnutritiontool;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

public class Listing1 extends AppCompatActivity {

    ArrayAdapter<String> arrayAdapter;
    DatabaseReference databaseReference;
    ArrayList<String> arrayList = new ArrayList<>();
    ArrayList<Integer> arrayList1 = new ArrayList<>();
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listing1);

        Intent iii = getIntent();
        String value = iii.getStringExtra("brgy");


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        try {
            FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        }catch (Exception eee){

        }

        lv = (ListView)findViewById(R.id.list_view1);
        arrayAdapter = new ArrayAdapter<String>(Listing1.this,android.R.layout.simple_list_item_1,arrayList);
        databaseReference = FirebaseDatabase.getInstance().getReference(value+"_Infant_Info");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                arrayList.clear();
                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    String name = ds.child("firstname_last").getValue(String.class);
                    try {
                        arrayList.add(name);
                        Collections.sort(arrayList);
                        lv.setAdapter(arrayAdapter);

                    }catch (Exception ee){
                        Toast.makeText(Listing1.this, ee.getMessage(), Toast.LENGTH_LONG).show();
                    }

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater  = getMenuInflater();
        inflater.inflate(R.menu.search_menu,menu);
        MenuItem menuItem = menu.findItem(R.id.menu_search);

        final ListView listView = (ListView)findViewById(R.id.list_view1);
        listView.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int i, long l) {
                try {

                    final Intent iii = new Intent(Listing1.this, findTheID.class);
                    final Object obj = listView.getAdapter().getItem(i);
                    final String names = obj.toString();
                    final Intent iiii = getIntent();
                    final String value = iiii.getStringExtra("brgy");


                    databaseReference.orderByChild("firstname_last").equalTo(names).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            for (DataSnapshot ds : dataSnapshot.getChildren()) {

                                String child_id = ds.child("infant_ID").getValue().toString();
                                iii.putExtra("the_id", child_id);
                                iii.putExtra("brgy", value);
                                iii.putExtra("names",names);

                                startActivity(iii);
                                finish();
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }

                    });
                }catch (Exception ee){
                    Toast.makeText(Listing1.this,ee.getMessage(),Toast.LENGTH_LONG).show();
                }
            }
        });


        SearchView searchView = (SearchView) menuItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
            arrayAdapter.getFilter().filter(s);

                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent(getBaseContext(),MainActivity.class);
        Intent iii = getIntent();
        String value = iii.getStringExtra("brgy");
        intent.putExtra("brgy",value);
        startActivity(intent);
        finish();


        return super.onOptionsItemSelected(item);
    }
}
