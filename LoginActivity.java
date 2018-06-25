package com.example.lloyd.healthnutritiontool;


import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {
    ProgressDialog progressDialog;
        DatabaseReference databaseReference;
        TextView signup;
        public static String globalSharedPreferences="com.morales.profile";
        Spinner spin;
        EditText edit;
        EditText edit2;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        try {
            FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        }catch (Exception eee){

        }
        signup = (TextView)findViewById(R.id.sign_up);
        editor = getSharedPreferences(globalSharedPreferences,MODE_PRIVATE).edit();
        signup.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iii = new Intent(LoginActivity.this,signUp.class);
                startActivity(iii);
                finish();
            }
        });
        try {
            FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        }catch (Exception eee){

        }
         databaseReference = FirebaseDatabase.getInstance().getReference("Users");
        spin = (Spinner)findViewById(R.id.brgy);
        edit = (EditText)findViewById(R.id.username);
        edit2 = (EditText)findViewById(R.id.password);





        Button log= (Button)findViewById(R.id.sign_in);
        log.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    //global
                   // final GlobalClass globalVariable = (GlobalClass) getApplicationContext();
                    String aa = spin.getSelectedItem().toString().trim();
                    String bb = edit.getText().toString();
                    progressDialog = new ProgressDialog(LoginActivity.this);
                    progressDialog.setTitle("Logging in...");
                    progressDialog.show();

                    databaseReference.child(aa).orderByChild("uname").equalTo(bb).addListenerForSingleValueEvent(new ValueEventListener() {

                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if (dataSnapshot.exists()) {
                                for (DataSnapshot ds : dataSnapshot.getChildren()) {


                                    String val1 = ds.child("uname").getValue(String.class);
                                    String val2 = ds.child("pass").getValue(String.class);
                                    String val3 = ds.child("name").getValue(String.class);
                                    if (edit.getText().toString().equals(val1) && edit2.getText().toString().equals(val2)) {
                                        progressDialog.dismiss();
                                        Intent iii = new Intent(LoginActivity.this, MainActivity.class);
                                        Intent iiii = new Intent(LoginActivity.this, Registration.class);
                                        Object obj = spin.getSelectedItem().toString();
                                        String brgay = obj.toString();
                                        iii.putExtra("brgy", brgay);
                                        iiii.putExtra("brgy", brgay);
                                        startActivity(iii);
                                        //global
                                        // globalVariable.setNamesssss(val3);
                                        editor.putString("name", val3);
                                        editor.commit();
                                        edit.setText("");
                                        edit2.setText("");
                                    } else {
                                        progressDialog.dismiss();
                                        Toast.makeText(LoginActivity.this, "Invalid username or password!", Toast.LENGTH_SHORT).show();
                                    }

                                }
                            }else{
                                progressDialog.dismiss();
                                Toast.makeText(LoginActivity.this, "This account is not exist!", Toast.LENGTH_SHORT).show();
                            }
                        }
                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            progressDialog.dismiss();
                        }
                    });


                } catch (Exception ee) {
                    Toast.makeText(LoginActivity.this,ee.getMessage(), Toast.LENGTH_SHORT).show();

                }
            }
        });

    }
}

