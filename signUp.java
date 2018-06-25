package com.example.lloyd.healthnutritiontool;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;

import com.google.firebase.database.*;

public class signUp extends AppCompatActivity {

    EditText name;
    EditText user;
    EditText pass;
    Spinner brgy;
    Button ups;
    Button checks;
    EditText pass2;
    DatabaseReference databaseReference;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent(signUp.this, LoginActivity.class);
        startActivity(intent);
        finish();
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        try {
            FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        }catch (Exception eee){

        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        brgy = (Spinner)findViewById(R.id.brgy);
        name = (EditText)findViewById(R.id.bnsname);
        user = (EditText)findViewById(R.id.usee);
        pass = (EditText)findViewById(R.id.passs);
        ups = (Button)findViewById(R.id.sign_ups);
        checks = (Button)findViewById(R.id.check);
        pass2 = (EditText)findViewById(R.id.passs1);

        user.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                ups.setBackgroundColor(Color.parseColor("#d3d3d3"));
                ups.setEnabled(false);
                user.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        ups.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String barangay = brgy.getSelectedItem().toString().trim();
                String bnsName = name.getText().toString().trim();
                String usernam = user.getText().toString().trim();
                String password = pass.getText().toString().trim();
                String password1 = pass2.getText().toString().trim();

                databaseReference = FirebaseDatabase.getInstance().getReference("Users");
                String id = databaseReference.push().getKey();

                if(!barangay.isEmpty()&&!bnsName.isEmpty()&&!usernam.isEmpty()&&!password.isEmpty()){
                        if(password1.equals(password)) {
                   signup_model signs = new signup_model(barangay, bnsName, usernam, password);
                   databaseReference.child(barangay).child(id).setValue(signs);
                   Toast.makeText(signUp.this, "Successfully Registered!", Toast.LENGTH_LONG).show();
                   Intent intent = new Intent(signUp.this, LoginActivity.class);
                   startActivity(intent);
                   SharedPreferences.Editor sharedPreferences = getSharedPreferences(LoginActivity.globalSharedPreferences, MODE_PRIVATE).edit();
                   sharedPreferences.clear();
                         }else{
                            Toast.makeText(signUp.this,"Password did not match",Toast.LENGTH_LONG).show();
                        }
                }else{
                    Toast.makeText(signUp.this,"All fields is required",Toast.LENGTH_LONG).show();
                }

            }
        });

        checks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    databaseReference = FirebaseDatabase.getInstance().getReference("Users");
                    String barangay = brgy.getSelectedItem().toString().trim();
                    String usernam = user.getText().toString().trim();

                    databaseReference.child(barangay).orderByChild("uname").equalTo(usernam).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            for (DataSnapshot ds : dataSnapshot.getChildren()) {
                                if (dataSnapshot.exists()) {

                                    ups.setBackgroundColor(Color.parseColor("#d3d3d3"));
                                    ups.setEnabled(false);
                                    user.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.error, 0);



                                }
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                    //
                ups.setBackgroundColor(Color.parseColor("#d67601"));
                ups.setEnabled(true);
                user.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.check, 0);
            }
        });

    }

}
