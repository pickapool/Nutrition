package com.example.lloyd.healthnutritiontool;

import android.app.ActionBar;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;

public class Registration extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{

    DatabaseReference databaseReference,databaseReference1;
    EditText first_name;
    EditText middle_name;
    EditText last_name;
    Spinner gender;
    EditText dob;
    EditText parent;
    EditText contact;
    EditText address;
    EditText house;
    Button savee;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            Intent iii = getIntent();
            String value = iii.getStringExtra("brgy");
            address = (EditText)findViewById(R.id.address);
            address.setText(value);
            address.setEnabled(false);


        EditText editText = (EditText) findViewById(R.id.date_picker);
        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePicker(view);
            }
        });

databaseReference = FirebaseDatabase.getInstance().getReference(value+"_Infant_Info");
        databaseReference1 = FirebaseDatabase.getInstance().getReference("List_Infant_Info");
        first_name = (EditText) findViewById(R.id.fname);
        middle_name = (EditText)findViewById(R.id.mname);
        last_name = (EditText) findViewById(R.id.lname);
        gender = (Spinner)findViewById(R.id.spin);
        dob = (EditText)findViewById(R.id.date_picker);
        parent = (EditText)findViewById(R.id.parent);
        contact = (EditText)findViewById(R.id.contact);
        address = (EditText)findViewById(R.id.address);
        house = (EditText)findViewById(R.id.house);
        savee = (Button)findViewById(R.id.save);

        savee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                saveInformation();
            }
        });
    }
    private void saveInformation(){
try {
    Random n =new Random();
    int ID = n.nextInt(197765388*2+3);
    String val1 = first_name.getText().toString().trim();
    String mnames = middle_name.getText().toString().trim();
    String val2 = last_name.getText().toString().trim();
    String val3 = gender.getSelectedItem().toString().trim();
    String val4 = dob.getText().toString().trim();
    String val5 = parent.getText().toString().trim();
    String val6 = contact.getText().toString().trim();
    String val7 = address.getText().toString().trim();
    String val8 = house.getText().toString().trim();
    String id = databaseReference.push().getKey();
    Register reg = new Register(ID,val1+" "+mnames+" "+val2, val3, val4, val5, val6, val7, val8);
    datas dat = new datas(String.valueOf(ID),val1+" "+mnames+" "+val2);


    Date date1 = new Date(val4);
    String date2 = DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
    Date date3 = new Date(date2);
    long difference = (long) ((date3.getTime() - date1.getTime()));
    long cc = difference / 1000 / 60 / 60 / 24/30;
    if(cc>=0){
        databaseReference1.child(id).setValue(dat);
        databaseReference.child(id).setValue(reg);
        Toast.makeText(Registration.this, "Data has been saved!", Toast.LENGTH_SHORT).show();
    }else {
        Toast.makeText(Registration.this, "Age must not be less than to 0", Toast.LENGTH_SHORT).show();
    }


}catch (Exception ee){
    Toast.makeText(Registration.this, ee.getMessage(), Toast.LENGTH_SHORT).show();

}
    }



    public void datePicker(View view){
        DatePickerFragment fragment = new DatePickerFragment();
        fragment.show(getFragmentManager(),"date");
    }

    private void setDate(final Calendar calendar){
        final DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM);
        ((EditText)findViewById(R.id.date_picker)).setText(dateFormat.format(calendar.getTime()));
    }
    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        Calendar calendar = new GregorianCalendar(year,month,day);
        setDate(calendar);
    }
    public static class DatePickerFragment extends DialogFragment {
        @Override
        public Dialog onCreateDialog(Bundle saveInstanceState){
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            return  new DatePickerDialog(getActivity(),(DatePickerDialog.OnDateSetListener)getActivity(),year,month,day);
        }
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
