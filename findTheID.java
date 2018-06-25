package com.example.lloyd.healthnutritiontool;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class findTheID extends AppCompatActivity {

    TextView names;
    TextView IDs;
    Button butt;
DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_the_id);

        names = (TextView)findViewById(R.id.name);
        IDs = (TextView)findViewById(R.id.ID);
        butt = (Button)findViewById(R.id.done);

        Intent iii = getIntent();
       final String get = iii.getStringExtra("names");
        final String get1 = iii.getStringExtra("brgy");
        databaseReference = FirebaseDatabase.getInstance().getReference("List_Infant_Info");
        databaseReference.orderByChild("names").equalTo(get).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds:dataSnapshot.getChildren()){
                    names.setText(get);
                    String the_id = ds.child("id").getValue(String.class);
                    IDs.setText(the_id);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent ii = new Intent(findTheID.this,MainActivity.class);
                ii.putExtra("brgy",get1);
                startActivity(ii);
                finish();
            }
        });
    }
}
