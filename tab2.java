package com.example.lloyd.healthnutritiontool;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class tab2 extends Fragment{
    ListView listView;
    list_adapter list_adapters;
    List<list_stats> list_statsList;
    SearchView searchView;
    MenuItem menuItem;
    EditText search;
String val2;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle saveInstanceState){
        View view = inflater.inflate(R.layout.tab2,container,false);

        return view;

    }




    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


               final View mview = getLayoutInflater().inflate(R.layout.calculation_dialog, null);
        search = (EditText)getActivity().findViewById(R.id.search);
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                list_adapters.getFilter().filter(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
            Intent iii = getActivity().getIntent();
            String idss = iii.getStringExtra("the_id");
            String value1 = iii.getStringExtra("brgy");

             list_statsList = new ArrayList<>();
            listView = (ListView) getActivity().findViewById(R.id.list_view);
            listView.setHorizontalScrollBarEnabled(true);

            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Status");
            databaseReference.child(value1).child(idss).child("stats").orderByKey().addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    try {
                        list_statsList.clear();
                        for(DataSnapshot ds:dataSnapshot.getChildren()) {
                            int total = (int) dataSnapshot.getChildrenCount();

                            String val1 = ds.child("date_of_recordings").getValue(String.class);
                            val2 = ds.child("child_age_now").getValue(String.class);
                            String val3 = ds.child("child_height").getValue(String.class);
                            String val4 = ds.child("child_weight").getValue(String.class);
                            String val5 = ds.child("midUpArmCir").getValue(String.class);
                            String val6 = ds.child("weight_for_age").getValue(String.class);
                            String val7 = ds.child("height_for_age").getValue(String.class);
                            String val8 = ds.child("height_fow_weight").getValue(String.class);
                            String val9 = ds.child("midUpArmCir_stats").getValue(String.class);
                            String val10 = ds.child("representative").getValue(String.class);

                            list_statsList.add(new list_stats(val1, val2, val3, val4, val5, val6, val7, val8,val9,val10));
                            list_adapters = new list_adapter(getContext(), list_statsList);
                        }
                        listView.setAdapter(list_adapters);


                    }catch (Exception ee){
                        Toast.makeText(getContext(),ee.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        final AlertDialog[] dialog = new AlertDialog[1];
        final CheckBox check1 = (CheckBox)mview.findViewById(R.id.recumbent);
        final CheckBox check2 = (CheckBox)mview.findViewById(R.id.checkbox_w);
        check1.setChecked(true);

        EditText dates = (EditText)mview.findViewById(R.id.date_now);
        dates.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                try {
                    Intent ii = getActivity().getIntent();
                    String get_dob = ii.getStringExtra("value_dob");
                    TextView wew = (TextView) mview.findViewById(R.id.childs_dob);
                    EditText wew1 = (EditText) mview.findViewById(R.id.date_now);
                    EditText wew2 =(EditText)mview.findViewById(R.id.child_age);

                    String wewwew = wew.getText().toString();
                    String wewwew1 = wew1.getText().toString();
                    Date date1 = new Date(get_dob);
                    Date date2 = new Date(wewwew1);
                    long difference = (long) ((date2.getTime() - date1.getTime()));
                    long cc = difference / 1000 / 60 / 60 / 24/30;
                    wew2.setText(String .valueOf(cc));


                }catch (Exception ee){
                 //   Toast.makeText(getContext(),ee.getMessage(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        check1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                try {
                    check2.setChecked(false);
                    check1.setEnabled(false);
                    check2.setEnabled(true);
                }
                catch (Exception eee){

                }
            }
        });
        check2.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                try {
                    check1.setChecked(false);
                    check2.setEnabled(false);
                    check1.setEnabled(true);
                }catch (Exception eee){

                }
            }
        });

    FloatingActionButton floatingActionButton = (FloatingActionButton) getActivity().findViewById(R.id.fab);
    floatingActionButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            try {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setView(mview);
                dialog[0] = builder.create();


                if(mview.getParent()!=null){
                    ((ViewGroup)mview.getParent()).removeView(mview);
                }
                dialog[0].show();
            }catch (Exception ee){
                Toast.makeText(getContext(),ee.getMessage(),Toast.LENGTH_LONG).show();
            }
            try {
                Intent iii = getActivity().getIntent();
                final String name = iii.getStringExtra("value1");
                String value1 = iii.getStringExtra("brgy");

                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference(value1 + "_Infant_Info");
                databaseReference.orderByChild("firstname_last").equalTo(name).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                            String val = ds.child("date_of_birth").getValue(String.class);
                            String mydate = DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
                            EditText dates = (EditText) mview.findViewById(R.id.date_now);
                            dates.setText(mydate);
                            TextView paste_dob = (TextView) mview.findViewById(R.id.childs_dob);
                            paste_dob.setText(val);

                            try {
                                String wew = paste_dob.getText().toString();
                                int x = 1000 * 60 * 60 * 24 * 30;
                                Date date1 = new Date(wew);
                                Date date2 = new Date(mydate);
                                long difference = (long) ((date2.getTime() - date1.getTime()));
                                long cc = difference / 1000 / 60 / 60 / 24 / 30;

                                String convert = String.valueOf(cc);

                            } catch (Exception ee) {
                                Toast.makeText(getContext(), ee.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

                TextView childs_name = (TextView) mview.findViewById(R.id.the_child_name);
                childs_name.setText(name);
            } catch (Exception eee) {
                Toast.makeText(getContext(), eee.getMessage(), Toast.LENGTH_LONG).show();
            }
        }
    });
        Button butt = (Button) mview.findViewById(R.id.calculate);
        butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {




                try {


                    EditText the_age = (EditText) mview.findViewById(R.id.child_age);
                    EditText the_weight = (EditText) mview.findViewById(R.id.weight);
                    EditText the_height = (EditText) mview.findViewById(R.id.the_height);
                    EditText the_MUAC = (EditText) mview.findViewById(R.id.MUAC);

                    String get_Ages = the_age.getText().toString();
                    String get_Weight = the_weight.getText().toString();
                    String get_Height = the_height.getText().toString();
                    String get_MUAC = the_MUAC.getText().toString();

                    TextView status1 = (TextView) mview.findViewById(R.id.status_1);
                    TextView status2 = (TextView) mview.findViewById(R.id.status_2);
                    TextView status3 = (TextView) mview.findViewById(R.id.status_3);
                    TextView status4 = (TextView) mview.findViewById(R.id.status_4);


                        Integer ages = Integer.parseInt(String.valueOf(get_Ages));
                        Double weights = Double.parseDouble(String.valueOf(get_Weight));
                        Double heights = Double.parseDouble(String.valueOf(get_Height));
                        Double MUACs = Double.parseDouble(String.valueOf(get_MUAC));

                    Button but = (Button)mview.findViewById(R.id.status_save);
                    if(get_Ages.isEmpty()&&get_Weight.isEmpty()&&get_Height.isEmpty()&&get_MUAC.isEmpty()){
                        Toast.makeText(getContext(), "All fields are required", Toast.LENGTH_LONG).show();
                    }
                    else{
                        but.setEnabled(true);
                    }




                    myDatabase db = new myDatabase(getContext());


                    Intent iii = getActivity().getIntent();
                    String get_Gender = iii.getStringExtra("value_gender");
                    // Toast.makeText(getContext(),String.valueOf(ages), Toast.LENGTH_LONG).show();
                    // Toast.makeText(getContext(),String.valueOf(get_Gender), Toast.LENGTH_LONG).show();

                    if (ages >= 6 && ages <= 71) {
                        if (MUACs < 11.5) {
                            status4.setText("SAM");
                        } else if (MUACs >= 11.6 && MUACs <= 12.5) {
                            status4.setText("MAM");
                        } else if (MUACs >= 12.6) {
                            status4.setText("Normal");
                        }
                    } else {
                        status4.setText("Not Applicable");
                        the_MUAC.setText("0");
                    }


                    if (get_Gender.equals("Male")) {

                        Double[] WFA_SU = {2.1, 2.9, 3.8, 4.4, 4.9, 5.3, 5.7, 5.9, 6.2, 6.4, 6.6, 6.8, 6.9, 7.1, 7.2, 7.4, 7.5, 7.7, 7.8, 8.0, 8.1, 8.2, 8.3, 8.4, 8.5, 8.6, 8.7, 8.9, 9.0, 9.1, 9.2, 9.3, 9.4, 9.5, 9.6, 9.7, 9.8, 9.9, 10.0, 10.1, 10.2, 10.3, 10.4, 10.5, 10.6, 10.7, 10.8, 10.9, 11.0, 11.1, 11.2, 11.3, 11.4, 11.5, 11.6, 11.7, 11.8, 11.9, 12.0, 12.1, 12.1, 12.2, 12.3, 12.4, 12.7, 12.8, 12.9, 13.0, 13.1, 13.2, 13.3, 13.4, 13.6, 13.7, 13.8, 13.9};
                        Double[] WFA_U2 = {2.4, 3.3, 4.2, 4.9, 5.5, 5.9, 6.3, 6.6, 6.8, 7.0, 7.3, 7.5, 7.6, 7.8, 8.0, 8.2, 8.3, 8.5, 8.7, 8.8, 9.0, 9.1, 9.3, 9.4, 9.6, 9.7, 9.9, 10.0, 10.1, 10.3, 10.4, 10.6, 10.7, 10.8, 10.9, 11.1, 11.2, 11.3, 11.4, 11.5, 11.7, 11.8, 11.9, 12.0, 12.1, 12.3, 12.4, 12.5, 12.6, 12.7, 12.8, 13.0, 13.1, 13.2, 13.3, 13.4, 13.5, 13.6, 13.7, 13.9, 14.0, 14.3, 14.4, 14.5, 14.7, 14.8, 14.9, 15.1, 15.2, 15.3, 15.5, 15.6};
                        Double[] WFA_N2 = {4.4, 5.8, 7.1, 8.0, 8.7, 9.3, 9.8, 10.3, 10.7, 11.0, 11.4, 11.7, 12.0, 12.3, 12.6, 12.8, 13.1, 13.4, 13.7, 13.9, 14.2, 14.5, 14.7, 15.0, 15.3, 15.5, 15.8, 16.1, 16.3, 16.6, 16.9, 17.1, 17.4, 17.6, 17.8, 18.1, 18.3, 18.6, 18.8, 19.0, 19.3, 19.5, 19.7, 20.0, 20.2, 20.5, 20.7, 20.9, 21.2, 21.4, 21.7, 21.9, 22.2, 22.4, 22.7, 22.9, 23.2, 23.4, 23.7, 23.9, 24.2, 24.3, 24.4, 24.7, 24.9, 25.2, 25.5, 25.7, 25.5, 25.7, 26.0, 26.3, 26.6, 26.8};

                        Double WFA_severe = WFA_SU[ages];
                        Double WFA_under1 = WFA_SU[ages] + 0.1;
                        Double WFA_under2 = WFA_U2[ages];
                        Double WFA_norm1 = WFA_U2[ages] + 0.1;
                        Double WFA_norm2 = WFA_N2[ages];
                        Double WFA_over = WFA_N2[ages] + 0.1;

                        if (weights <= WFA_severe) {
                            status1.setText("Severely underweight");
                        } else if (weights >= WFA_under1 && weights <= WFA_under2) {
                            status1.setText("Underweight");
                        } else if (weights >= WFA_norm1 && weights <= WFA_norm2) {
                            status1.setText("Normal");
                        } else if (weights >= WFA_over) {
                            status1.setText("Overweight");
                        }
                        Double[] HFA_severely = {44.1, 48.8, 52.3, 55.5, 57.5, 59.5, 61.1, 62.6, 63.9, 65.1, 66.3, 67.5, 68.5, 69.5, 70.5, 71.5, 72.4, 73.2, 74.1, 74.9, 75.7, 76.4, 77.1, 77.9, 78.5, 79.2, 79.8, 80.4, 81.0, 81.6, 82.2, 82.7, 83.3, 83.8, 84.3, 84.9, 85.4, 85.9, 86.4, 86.9, 87.4, 87.9, 88.3, 88.8, 89.3, 89.7, 90.2, 90.6, 91.1, 91.5, 92.0, 92.4, 92.9, 93.3, 93.8, 94.2, 94.6, 95.1, 95.5, 96.0, 96.4, 96.8, 97.3, 97.7, 98.1, 98.6, 99.0, 99.4, 99.8, 100.3, 100.7};
                        Double[] HFA_Stunted_To = {46.0, 50.7, 54.3, 57.2, 59.6, 61.6, 63.2, 64.7, 66.1, 67.4, 68.6, 69.8, 70.9, 72.0, 73.0, 74.0, 74.9, 75.9, 76.8, 77.6, 78.5, 79.3, 80.1, 80.9, 80.9, 81.6, 82.4, 83.0, 83.7, 84.4, 85.0, 85.6, 86.3, 86.8, 87.4, 88.0, 88.6, 89.1, 89.7, 90.2, 90.8, 91.3, 91.8, 92.3, 92.9, 93.4, 93.9, 94.3, 94.8, 95.3, 95.8, 96.3, 96.8, 97.3, 97.7, 98.2, 98.7, 99.2, 99.6, 100.1, 100.6, 101.0, 101.5, 101.9, 102.4, 102.9, 103.3, 103.8, 104.2, 104.7, 105.1, 105.6};
                        Double[] HFA_normal_To = {53.7, 58.6, 62.4, 65.5, 68.0, 70.1, 71.9, 73.5, 75.0, 76.5, 77.9, 79.2, 80.5, 81.8, 83.0, 84.2, 85.4, 86.5, 87.7, 88.8, 89.8, 90.9, 91.9, 92.9, 93.2, 94.2, 95.2, 96.1, 97.0, 97.9, 98.7, 99.6, 100.4, 101.2, 102.0, 102.7, 103.5, 104.2, 105.0, 105.7, 106.4, 107.1, 107.8, 108.5, 109.1, 109.8, 110.4, 111.1, 111.7, 112.4, 113.0, 113.6, 114.2, 114.9, 115.5, 116.1, 116.7, 177.4, 118.0, 118.6, 119.2, 119.4, 120.0, 120.6, 121.2, 121.8, 122.4, 123.0, 123.6, 124.1, 124.7, 125.2};

                        Double HFA_severe = HFA_severely[ages];
                        Double HFA_stunted1 = HFA_severely[ages] + 0.1;
                        Double HFA_stunted2 = HFA_Stunted_To[ages];
                        Double HFA_norm1 = HFA_normal_To[ages] + 0.1;
                        Double HFA_norm2 = HFA_normal_To[ages];
                        Double HFA_tall = HFA_normal_To[ages] + 0.1;


                        if (ages > -1 && ages <= 23) {

                            if (check2.isChecked()) {
                                Double length = heights + 0.7;
                                if (length <= HFA_severe) {
                                    status2.setText("Severely Stunted");
                                } else if (length >= HFA_stunted1 && heights <= HFA_stunted2) {
                                    status2.setText("Stunted");
                                } else if (length >= HFA_norm1 && heights <= HFA_norm2) {
                                    status2.setText("Normal");
                                } else if (length >= HFA_tall) {
                                    status2.setText("Tall");
                                }

                                if (length < 44.9) {
                                    status3.setText("Severely Wasted");
                                }
                                if (length > 110.0) {
                                    status3.setText("Obese");
                                }

                                String numberAsString = String.format("%.1f", length);
                                int aa = numberAsString.length();
                                int x = aa - 1;
                                String get_sub = numberAsString.substring(x, aa);


                                if (get_sub.equals("3") || get_sub.equals("4") || get_sub.equals("6") || get_sub.equals("7")) {
                                    String try1 = numberAsString.replace(get_sub, "0");
                                    Double xx = Double.valueOf(Double.parseDouble(try1));
                                    Double add = xx + 0.5;
                                    String the_length = String.valueOf(add);
                                    String sql = "Select * from WFL_Male_0_23 where Length='" + the_length + "'";
                                    //Toast.makeText(getContext(), String.valueOf(add), Toast.LENGTH_LONG).show();
                                    db.OpenDatabase();
                                    Cursor cursor = db.getWritableDatabase().rawQuery(sql, null);
                                    while (cursor.moveToNext()) {
                                        Double val1 = Double.parseDouble(cursor.getString(1));
                                        Double val2 = Double.parseDouble(cursor.getString(2));
                                        Double val3 = Double.parseDouble(cursor.getString(3));
                                        Double val4 = Double.parseDouble(cursor.getString(4));
                                        Double val5 = Double.parseDouble(cursor.getString(5));
                                        Double val6 = Double.parseDouble(cursor.getString(6));
                                        Double val7 = Double.parseDouble(cursor.getString(7));
                                        Double val8 = Double.parseDouble(cursor.getString(8));
                                        Toast.makeText(getContext(), String.valueOf(val1), Toast.LENGTH_LONG).show();
                                        if (weights <= val1) {
                                            status3.setText("Severely Wasted");
                                        } else if (weights >= val2 && weights <= val3) {
                                            status3.setText("Wasted");
                                        } else if (weights >= val4 && weights <= val5) {
                                            status3.setText("Normal");
                                        } else if (weights >= val6 && weights <= val7) {
                                            status3.setText("Overweight");
                                        } else if (weights >= val8) {
                                            status3.setText("Obese");
                                        }
                                    }
                                    db.closeDatabase();
                                } else if (get_sub.equals("2") || get_sub.equals("1")) {
                                    String try2 = numberAsString.replace(get_sub, "0");

                                    String the_length = String.valueOf(try2);
                                    String sql = "Select * from WFL_Male_0_23 where Length='" + the_length + "'";
                                    //Toast.makeText(getContext(), String.valueOf(try2), Toast.LENGTH_LONG).show();
                                    db.OpenDatabase();
                                    Cursor cursor = db.getWritableDatabase().rawQuery(sql, null);
                                    while (cursor.moveToNext()) {
                                        Double val1 = Double.parseDouble(cursor.getString(1));
                                        Double val2 = Double.parseDouble(cursor.getString(2));
                                        Double val3 = Double.parseDouble(cursor.getString(3));
                                        Double val4 = Double.parseDouble(cursor.getString(4));
                                        Double val5 = Double.parseDouble(cursor.getString(5));
                                        Double val6 = Double.parseDouble(cursor.getString(6));
                                        Double val7 = Double.parseDouble(cursor.getString(7));
                                        Double val8 = Double.parseDouble(cursor.getString(8));
                                        Toast.makeText(getContext(), String.valueOf(val1), Toast.LENGTH_LONG).show();
                                        if (weights <= val1) {
                                            status3.setText("Severely Wasted");
                                        } else if (weights >= val2 && weights <= val3) {
                                            status3.setText("Wasted");
                                        } else if (weights >= val4 && weights <= val5) {
                                            status3.setText("Normal");
                                        } else if (weights >= val6 && weights <= val7) {
                                            status3.setText("Overweight");
                                        } else if (weights >= val8) {
                                            status3.setText("Obese");
                                        }
                                    }
                                    db.closeDatabase();

                                } else if (get_sub.equals("8") || get_sub.equals("9")) {
                                    String try1 = numberAsString.replace(get_sub, "0");
                                    Double xx = Double.valueOf(Double.parseDouble(try1));
                                    Double add = xx + 1.0;
                                    Toast.makeText(getContext(), String.valueOf(add), Toast.LENGTH_LONG).show();
                                    String the_length = String.valueOf(add);
                                    String sql = "Select * from WFL_Male_0_23 where Length='" + the_length + "'";
                                    // Toast.makeText(getContext(), String.valueOf(add), Toast.LENGTH_LONG).show();
                                    db.OpenDatabase();
                                    Cursor cursor = db.getWritableDatabase().rawQuery(sql, null);
                                    while (cursor.moveToNext()) {
                                        Double val1 = Double.parseDouble(cursor.getString(1));
                                        Double val2 = Double.parseDouble(cursor.getString(2));
                                        Double val3 = Double.parseDouble(cursor.getString(3));
                                        Double val4 = Double.parseDouble(cursor.getString(4));
                                        Double val5 = Double.parseDouble(cursor.getString(5));
                                        Double val6 = Double.parseDouble(cursor.getString(6));
                                        Double val7 = Double.parseDouble(cursor.getString(7));
                                        Double val8 = Double.parseDouble(cursor.getString(8));
                                        Toast.makeText(getContext(), String.valueOf(val1), Toast.LENGTH_LONG).show();
                                        if (weights <= val1) {
                                            status3.setText("Severely Wasted");
                                        } else if (weights >= val2 && weights <= val3) {
                                            status3.setText("Wasted");
                                        } else if (weights >= val4 && weights <= val5) {
                                            status3.setText("Normal");
                                        } else if (weights >= val6 && weights <= val7) {
                                            status3.setText("Overweight");
                                        } else if (weights >= val8) {
                                            status3.setText("Obese");
                                        }
                                    }
                                    db.closeDatabase();
                                } else if (get_sub.equals("0") || get_sub.equals("5")) {
                                    String the_length = String.valueOf(length);
                                    String sql = "Select * from WFL_Male_0_23 where Length='" + the_length + "'";
                                    //Toast.makeText(getContext(), String.valueOf(length), Toast.LENGTH_LONG).show();
                                    db.OpenDatabase();
                                    Cursor cursor = db.getWritableDatabase().rawQuery(sql, null);
                                    while (cursor.moveToNext()) {
                                        Double val1 = Double.parseDouble(cursor.getString(1));
                                        Double val2 = Double.parseDouble(cursor.getString(2));
                                        Double val3 = Double.parseDouble(cursor.getString(3));
                                        Double val4 = Double.parseDouble(cursor.getString(4));
                                        Double val5 = Double.parseDouble(cursor.getString(5));
                                        Double val6 = Double.parseDouble(cursor.getString(6));
                                        Double val7 = Double.parseDouble(cursor.getString(7));
                                        Double val8 = Double.parseDouble(cursor.getString(8));
                                        Toast.makeText(getContext(), String.valueOf(val1), Toast.LENGTH_LONG).show();
                                        if (weights <= val1) {
                                            status3.setText("Severely Wasted");
                                        } else if (weights >= val2 && weights <= val3) {
                                            status3.setText("Wasted");
                                        } else if (weights >= val4 && weights <= val5) {
                                            status3.setText("Normal");
                                        } else if (weights >= val6 && weights <= val7) {
                                            status3.setText("Overweight");
                                        } else if (weights >= val8) {
                                            status3.setText("Obese");
                                        }
                                    }
                                    db.closeDatabase();
                                }

                            } else if (check1.isChecked()) {
                                if (heights <= HFA_severe) {
                                    status2.setText("Severely Stunted");
                                } else if (heights >= HFA_stunted1 && heights <= HFA_stunted2) {
                                    status2.setText("Stunted");
                                } else if (heights >= HFA_norm1 && heights <= HFA_norm2) {
                                    status2.setText("Normal");
                                } else if (heights >= HFA_tall) {
                                    status2.setText("Tall");
                                }

                                if (heights < 44.9) {
                                    status3.setText("Severely Wasted");
                                }
                                if (heights > 110.0) {
                                    status3.setText("Obese");
                                }

                                String numberAsString = String.format("%.1f", heights);
                                int aa = numberAsString.length();
                                int x = aa - 1;
                                String get_sub = numberAsString.substring(x, aa);
                                if (get_sub.equals("3") || get_sub.equals("4") || get_sub.equals("6") || get_sub.equals("7")) {
                                    String try1 = numberAsString.replace(get_sub, "0");
                                    Double xx = Double.valueOf(Double.parseDouble(try1));
                                    Double add = xx + 0.5;
                                    String the_length = String.valueOf(add);
                                    String sql = "Select * from WFL_Male_0_23 where Length='" + the_length + "'";
                                    //Toast.makeText(getContext(), String.valueOf(add), Toast.LENGTH_LONG).show();
                                    db.OpenDatabase();
                                    Cursor cursor = db.getWritableDatabase().rawQuery(sql, null);
                                    while (cursor.moveToNext()) {
                                        Double val1 = Double.parseDouble(cursor.getString(1));
                                        Double val2 = Double.parseDouble(cursor.getString(2));
                                        Double val3 = Double.parseDouble(cursor.getString(3));
                                        Double val4 = Double.parseDouble(cursor.getString(4));
                                        Double val5 = Double.parseDouble(cursor.getString(5));
                                        Double val6 = Double.parseDouble(cursor.getString(6));
                                        Double val7 = Double.parseDouble(cursor.getString(7));
                                        Double val8 = Double.parseDouble(cursor.getString(8));
                                        Toast.makeText(getContext(), String.valueOf(val1), Toast.LENGTH_LONG).show();
                                        if (weights <= val1) {
                                            status3.setText("Severely Wasted");
                                        } else if (weights >= val2 && weights <= val3) {
                                            status3.setText("Wasted");
                                        } else if (weights >= val4 && weights <= val5) {
                                            status3.setText("Normal");
                                        } else if (weights >= val6 && weights <= val7) {
                                            status3.setText("Overweight");
                                        } else if (weights >= val8) {
                                            status3.setText("Obese");
                                        }
                                    }
                                    db.closeDatabase();
                                } else if (get_sub.equals("2") || get_sub.equals("1")) {
                                    String try2 = numberAsString.replace(get_sub, "0");

                                    String the_length = String.valueOf(try2);
                                    String sql = "Select * from WFL_Male_0_23 where Length='" + the_length + "'";
                                    //Toast.makeText(getContext(), String.valueOf(try2), Toast.LENGTH_LONG).show();
                                    db.OpenDatabase();
                                    Cursor cursor = db.getWritableDatabase().rawQuery(sql, null);
                                    while (cursor.moveToNext()) {
                                        Double val1 = Double.parseDouble(cursor.getString(1));
                                        Double val2 = Double.parseDouble(cursor.getString(2));
                                        Double val3 = Double.parseDouble(cursor.getString(3));
                                        Double val4 = Double.parseDouble(cursor.getString(4));
                                        Double val5 = Double.parseDouble(cursor.getString(5));
                                        Double val6 = Double.parseDouble(cursor.getString(6));
                                        Double val7 = Double.parseDouble(cursor.getString(7));
                                        Double val8 = Double.parseDouble(cursor.getString(8));
                                        Toast.makeText(getContext(), String.valueOf(val1), Toast.LENGTH_LONG).show();
                                        if (weights <= val1) {
                                            status3.setText("Severely Wasted");
                                        } else if (weights >= val2 && weights <= val3) {
                                            status3.setText("Wasted");
                                        } else if (weights >= val4 && weights <= val5) {
                                            status3.setText("Normal");
                                        } else if (weights >= val6 && weights <= val7) {
                                            status3.setText("Overweight");
                                        } else if (weights >= val8) {
                                            status3.setText("Obese");
                                        }
                                    }
                                    db.closeDatabase();

                                } else if (get_sub.equals("8") || get_sub.equals("9")) {
                                    String try1 = numberAsString.replace(get_sub, "0");
                                    Double xx = Double.valueOf(Double.parseDouble(try1));
                                    Double add = xx + 1.0;
                                    Toast.makeText(getContext(), String.valueOf(add), Toast.LENGTH_LONG).show();
                                    String the_length = String.valueOf(add);
                                    String sql = "Select * from WFL_Male_0_23 where Length='" + the_length + "'";
                                    // Toast.makeText(getContext(), String.valueOf(add), Toast.LENGTH_LONG).show();
                                    db.OpenDatabase();
                                    Cursor cursor = db.getWritableDatabase().rawQuery(sql, null);
                                    while (cursor.moveToNext()) {
                                        Double val1 = Double.parseDouble(cursor.getString(1));
                                        Double val2 = Double.parseDouble(cursor.getString(2));
                                        Double val3 = Double.parseDouble(cursor.getString(3));
                                        Double val4 = Double.parseDouble(cursor.getString(4));
                                        Double val5 = Double.parseDouble(cursor.getString(5));
                                        Double val6 = Double.parseDouble(cursor.getString(6));
                                        Double val7 = Double.parseDouble(cursor.getString(7));
                                        Double val8 = Double.parseDouble(cursor.getString(8));
                                        Toast.makeText(getContext(), String.valueOf(val1), Toast.LENGTH_LONG).show();
                                        if (weights <= val1) {
                                            status3.setText("Severely Wasted");
                                        } else if (weights >= val2 && weights <= val3) {
                                            status3.setText("Wasted");
                                        } else if (weights >= val4 && weights <= val5) {
                                            status3.setText("Normal");
                                        } else if (weights >= val6 && weights <= val7) {
                                            status3.setText("Overweight");
                                        } else if (weights >= val8) {
                                            status3.setText("Obese");
                                        }
                                    }
                                    db.closeDatabase();
                                } else if (get_sub.equals("0") || get_sub.equals("5")) {

                                    String sql = "Select * from WFL_Male_0_23 where Length='" + heights + "'";
                                    //Toast.makeText(getContext(), String.valueOf(length), Toast.LENGTH_LONG).show();
                                    db.OpenDatabase();
                                    Cursor cursor = db.getWritableDatabase().rawQuery(sql, null);
                                    while (cursor.moveToNext()) {
                                        Double val1 = Double.parseDouble(cursor.getString(1));
                                        Double val2 = Double.parseDouble(cursor.getString(2));
                                        Double val3 = Double.parseDouble(cursor.getString(3));
                                        Double val4 = Double.parseDouble(cursor.getString(4));
                                        Double val5 = Double.parseDouble(cursor.getString(5));
                                        Double val6 = Double.parseDouble(cursor.getString(6));
                                        Double val7 = Double.parseDouble(cursor.getString(7));
                                        Double val8 = Double.parseDouble(cursor.getString(8));
                                        Toast.makeText(getContext(), String.valueOf(val1), Toast.LENGTH_LONG).show();
                                        if (weights <= val1) {
                                            status3.setText("Severely Wasted");
                                        } else if (weights >= val2 && weights <= val3) {
                                            status3.setText("Wasted");
                                        } else if (weights >= val4 && weights <= val5) {
                                            status3.setText("Normal");
                                        } else if (weights >= val6 && weights <= val7) {
                                            status3.setText("Overweight");
                                        } else if (weights >= val8) {
                                            status3.setText("Obese");
                                        }
                                    }
                                    db.closeDatabase();
                                }

                            }

                        } else if (ages >= 24 && ages <= 71) {
                            Double length = heights - 0.7;

                            if (check1.isChecked()) {

                                if (length <= HFA_severe) {
                                    status2.setText("Severely Stunted");
                                } else if (length >= HFA_stunted1 && length <= HFA_stunted2) {
                                    status2.setText("Stunted");
                                } else if (length >= HFA_norm1 && length <= HFA_norm2) {
                                    status2.setText("Normal");
                                } else if (length >= HFA_tall) {
                                    status2.setText("Tall");
                                }


                                if (length < 65.0) {
                                    status3.setText("Severely Stunted");
                                }
                                if (length > 120.0) {
                                    status3.setText("Obese");
                                }
                                String numberAsString = String.format("%.1f", length);
                                int aa = numberAsString.length();
                                int x = aa - 1;
                                String get_sub = numberAsString.substring(x, aa);
                                if (get_sub.equals("3") || get_sub.equals("4") || get_sub.equals("6") || get_sub.equals("7")) {
                                    String try1 = numberAsString.replace(get_sub, "0");
                                    Double xx = Double.valueOf(Double.parseDouble(try1));
                                    Double add = xx + 0.5;
                                    String the_length = String.valueOf(add);
                                    String sql = "Select * from WFL_Male_24_71 where Length='" + the_length + "'";
                                    //Toast.makeText(getContext(), String.valueOf(add), Toast.LENGTH_LONG).show();
                                    db.OpenDatabase();
                                    Cursor cursor = db.getWritableDatabase().rawQuery(sql, null);
                                    while (cursor.moveToNext()) {
                                        Double val1 = Double.parseDouble(cursor.getString(1));
                                        Double val2 = Double.parseDouble(cursor.getString(2));
                                        Double val3 = Double.parseDouble(cursor.getString(3));
                                        Double val4 = Double.parseDouble(cursor.getString(4));
                                        Double val5 = Double.parseDouble(cursor.getString(5));
                                        Double val6 = Double.parseDouble(cursor.getString(6));
                                        Double val7 = Double.parseDouble(cursor.getString(7));
                                        Double val8 = Double.parseDouble(cursor.getString(8));
                                        Toast.makeText(getContext(), String.valueOf(val1), Toast.LENGTH_LONG).show();
                                        if (weights <= val1) {
                                            status3.setText("Severely Wasted");
                                        } else if (weights >= val2 && weights <= val3) {
                                            status3.setText("Wasted");
                                        } else if (weights >= val4 && weights <= val5) {
                                            status3.setText("Normal");
                                        } else if (weights >= val6 && weights <= val7) {
                                            status3.setText("Overweight");
                                        } else if (weights >= val8) {
                                            status3.setText("Obese");
                                        }
                                    }
                                    db.closeDatabase();
                                } else if (get_sub.equals("2") || get_sub.equals("1")) {
                                    String try2 = numberAsString.replace(get_sub, "0");

                                    String the_length = String.valueOf(try2);
                                    String sql = "Select * from WFL_Male_24_71 where Length='" + the_length + "'";
                                    //Toast.makeText(getContext(), String.valueOf(try2), Toast.LENGTH_LONG).show();
                                    db.OpenDatabase();
                                    Cursor cursor = db.getWritableDatabase().rawQuery(sql, null);
                                    while (cursor.moveToNext()) {
                                        Double val1 = Double.parseDouble(cursor.getString(1));
                                        Double val2 = Double.parseDouble(cursor.getString(2));
                                        Double val3 = Double.parseDouble(cursor.getString(3));
                                        Double val4 = Double.parseDouble(cursor.getString(4));
                                        Double val5 = Double.parseDouble(cursor.getString(5));
                                        Double val6 = Double.parseDouble(cursor.getString(6));
                                        Double val7 = Double.parseDouble(cursor.getString(7));
                                        Double val8 = Double.parseDouble(cursor.getString(8));
                                        Toast.makeText(getContext(), String.valueOf(val1), Toast.LENGTH_LONG).show();
                                        if (weights <= val1) {
                                            status3.setText("Severely Wasted");
                                        } else if (weights >= val2 && weights <= val3) {
                                            status3.setText("Wasted");
                                        } else if (weights >= val4 && weights <= val5) {
                                            status3.setText("Normal");
                                        } else if (weights >= val6 && weights <= val7) {
                                            status3.setText("Overweight");
                                        } else if (weights >= val8) {
                                            status3.setText("Obese");
                                        }
                                    }
                                    db.closeDatabase();

                                } else if (get_sub.equals("8") || get_sub.equals("9")) {
                                    String try1 = numberAsString.replace(get_sub, "0");
                                    Double xx = Double.valueOf(Double.parseDouble(try1));
                                    Double add = xx + 1.0;
                                    Toast.makeText(getContext(), String.valueOf(add), Toast.LENGTH_LONG).show();
                                    String the_length = String.valueOf(add);
                                    String sql = "Select * from WFL_Male_24_71 where Length='" + the_length + "'";
                                    // Toast.makeText(getContext(), String.valueOf(add), Toast.LENGTH_LONG).show();
                                    db.OpenDatabase();
                                    Cursor cursor = db.getWritableDatabase().rawQuery(sql, null);
                                    while (cursor.moveToNext()) {
                                        Double val1 = Double.parseDouble(cursor.getString(1));
                                        Double val2 = Double.parseDouble(cursor.getString(2));
                                        Double val3 = Double.parseDouble(cursor.getString(3));
                                        Double val4 = Double.parseDouble(cursor.getString(4));
                                        Double val5 = Double.parseDouble(cursor.getString(5));
                                        Double val6 = Double.parseDouble(cursor.getString(6));
                                        Double val7 = Double.parseDouble(cursor.getString(7));
                                        Double val8 = Double.parseDouble(cursor.getString(8));
                                        Toast.makeText(getContext(), String.valueOf(val1), Toast.LENGTH_LONG).show();
                                        if (weights <= val1) {
                                            status3.setText("Severely Wasted");
                                        } else if (weights >= val2 && weights <= val3) {
                                            status3.setText("Wasted");
                                        } else if (weights >= val4 && weights <= val5) {
                                            status3.setText("Normal");
                                        } else if (weights >= val6 && weights <= val7) {
                                            status3.setText("Overweight");
                                        } else if (weights >= val8) {
                                            status3.setText("Obese");
                                        }
                                    }
                                    db.closeDatabase();
                                } else if (get_sub.equals("0") || get_sub.equals("5")) {
                                    String the_length = String.valueOf(length);
                                    String sql = "Select * from WFL_Male_24_71 where Length='" + the_length + "'";
                                    //Toast.makeText(getContext(), String.valueOf(length), Toast.LENGTH_LONG).show();
                                    db.OpenDatabase();
                                    Cursor cursor = db.getWritableDatabase().rawQuery(sql, null);
                                    while (cursor.moveToNext()) {
                                        Double val1 = Double.parseDouble(cursor.getString(1));
                                        Double val2 = Double.parseDouble(cursor.getString(2));
                                        Double val3 = Double.parseDouble(cursor.getString(3));
                                        Double val4 = Double.parseDouble(cursor.getString(4));
                                        Double val5 = Double.parseDouble(cursor.getString(5));
                                        Double val6 = Double.parseDouble(cursor.getString(6));
                                        Double val7 = Double.parseDouble(cursor.getString(7));
                                        Double val8 = Double.parseDouble(cursor.getString(8));
                                        Toast.makeText(getContext(), String.valueOf(val1), Toast.LENGTH_LONG).show();
                                        if (weights <= val1) {
                                            status3.setText("Severely Wasted");
                                        } else if (weights >= val2 && weights <= val3) {
                                            status3.setText("Wasted");
                                        } else if (weights >= val4 && weights <= val5) {
                                            status3.setText("Normal");
                                        } else if (weights >= val6 && weights <= val7) {
                                            status3.setText("Overweight");
                                        } else if (weights >= val8) {
                                            status3.setText("Obese");
                                        }
                                    }
                                    db.closeDatabase();
                                }

                            } else if (check2.isChecked()) {

                                if (heights <= HFA_severe) {
                                    status2.setText("Severely Stunted");
                                } else if (heights >= HFA_stunted1 && heights <= HFA_stunted2) {
                                    status2.setText("Stunted");
                                } else if (heights >= HFA_norm1 && heights <= HFA_norm2) {
                                    status2.setText("Normal");
                                } else if (heights >= HFA_tall) {
                                    status2.setText("Tall");
                                }
                                String numberAsString = String.format("%.1f", heights);
                                int aa = numberAsString.length();
                                int x = aa - 1;
                                String get_sub = numberAsString.substring(x, aa);
                                if (get_sub.equals("3") || get_sub.equals("4") || get_sub.equals("6") || get_sub.equals("7")) {
                                    String try1 = numberAsString.replace(get_sub, "0");
                                    Double xx = Double.valueOf(Double.parseDouble(try1));
                                    Double add = xx + 0.5;
                                    String the_length = String.valueOf(add);
                                    String sql = "Select * from WFL_Male_24_71 where Length='" + the_length + "'";
                                    //Toast.makeText(getContext(), String.valueOf(add), Toast.LENGTH_LONG).show();
                                    db.OpenDatabase();
                                    Cursor cursor = db.getWritableDatabase().rawQuery(sql, null);
                                    while (cursor.moveToNext()) {
                                        Double val1 = Double.parseDouble(cursor.getString(1));
                                        Double val2 = Double.parseDouble(cursor.getString(2));
                                        Double val3 = Double.parseDouble(cursor.getString(3));
                                        Double val4 = Double.parseDouble(cursor.getString(4));
                                        Double val5 = Double.parseDouble(cursor.getString(5));
                                        Double val6 = Double.parseDouble(cursor.getString(6));
                                        Double val7 = Double.parseDouble(cursor.getString(7));
                                        Double val8 = Double.parseDouble(cursor.getString(8));
                                        Toast.makeText(getContext(), String.valueOf(val1), Toast.LENGTH_LONG).show();
                                        if (weights <= val1) {
                                            status3.setText("Severely Wasted");
                                        } else if (weights >= val2 && weights <= val3) {
                                            status3.setText("Wasted");
                                        } else if (weights >= val4 && weights <= val5) {
                                            status3.setText("Normal");
                                        } else if (weights >= val6 && weights <= val7) {
                                            status3.setText("Overweight");
                                        } else if (weights >= val8) {
                                            status3.setText("Obese");
                                        }
                                    }
                                    db.closeDatabase();
                                } else if (get_sub.equals("2") || get_sub.equals("1")) {
                                    String try2 = numberAsString.replace(get_sub, "0");

                                    String the_length = String.valueOf(try2);
                                    String sql = "Select * from WFL_Male_24_71 where Length='" + the_length + "'";
                                    //Toast.makeText(getContext(), String.valueOf(try2), Toast.LENGTH_LONG).show();
                                    db.OpenDatabase();
                                    Cursor cursor = db.getWritableDatabase().rawQuery(sql, null);
                                    while (cursor.moveToNext()) {
                                        Double val1 = Double.parseDouble(cursor.getString(1));
                                        Double val2 = Double.parseDouble(cursor.getString(2));
                                        Double val3 = Double.parseDouble(cursor.getString(3));
                                        Double val4 = Double.parseDouble(cursor.getString(4));
                                        Double val5 = Double.parseDouble(cursor.getString(5));
                                        Double val6 = Double.parseDouble(cursor.getString(6));
                                        Double val7 = Double.parseDouble(cursor.getString(7));
                                        Double val8 = Double.parseDouble(cursor.getString(8));
                                        Toast.makeText(getContext(), String.valueOf(val1), Toast.LENGTH_LONG).show();
                                        if (weights <= val1) {
                                            status3.setText("Severely Wasted");
                                        } else if (weights >= val2 && weights <= val3) {
                                            status3.setText("Wasted");
                                        } else if (weights >= val4 && weights <= val5) {
                                            status3.setText("Normal");
                                        } else if (weights >= val6 && weights <= val7) {
                                            status3.setText("Overweight");
                                        } else if (weights >= val8) {
                                            status3.setText("Obese");
                                        }
                                    }
                                    db.closeDatabase();

                                } else if (get_sub.equals("8") || get_sub.equals("9")) {
                                    String try1 = numberAsString.replace(get_sub, "0");
                                    Double xx = Double.valueOf(Double.parseDouble(try1));
                                    Double add = xx + 1.0;
                                    Toast.makeText(getContext(), String.valueOf(add), Toast.LENGTH_LONG).show();
                                    String the_length = String.valueOf(add);
                                    String sql = "Select * from WFL_Male_24_71 where Length='" + the_length + "'";
                                    // Toast.makeText(getContext(), String.valueOf(add), Toast.LENGTH_LONG).show();
                                    db.OpenDatabase();
                                    Cursor cursor = db.getWritableDatabase().rawQuery(sql, null);
                                    while (cursor.moveToNext()) {
                                        Double val1 = Double.parseDouble(cursor.getString(1));
                                        Double val2 = Double.parseDouble(cursor.getString(2));
                                        Double val3 = Double.parseDouble(cursor.getString(3));
                                        Double val4 = Double.parseDouble(cursor.getString(4));
                                        Double val5 = Double.parseDouble(cursor.getString(5));
                                        Double val6 = Double.parseDouble(cursor.getString(6));
                                        Double val7 = Double.parseDouble(cursor.getString(7));
                                        Double val8 = Double.parseDouble(cursor.getString(8));
                                        Toast.makeText(getContext(), String.valueOf(val1), Toast.LENGTH_LONG).show();
                                        if (weights <= val1) {
                                            status3.setText("Severely Wasted");
                                        } else if (weights >= val2 && weights <= val3) {
                                            status3.setText("Wasted");
                                        } else if (weights >= val4 && weights <= val5) {
                                            status3.setText("Normal");
                                        } else if (weights >= val6 && weights <= val7) {
                                            status3.setText("Overweight");
                                        } else if (weights >= val8) {
                                            status3.setText("Obese");
                                        }
                                    }
                                    db.closeDatabase();
                                } else if (get_sub.equals("0") || get_sub.equals("5")) {
                                    String the_length = String.valueOf(heights);
                                    String sql = "Select * from WFL_Male_24_71 where Length='" + the_length + "'";
                                    //Toast.makeText(getContext(), String.valueOf(length), Toast.LENGTH_LONG).show();
                                    db.OpenDatabase();
                                    Cursor cursor = db.getWritableDatabase().rawQuery(sql, null);
                                    while (cursor.moveToNext()) {
                                        Double val1 = Double.parseDouble(cursor.getString(1));
                                        Double val2 = Double.parseDouble(cursor.getString(2));
                                        Double val3 = Double.parseDouble(cursor.getString(3));
                                        Double val4 = Double.parseDouble(cursor.getString(4));
                                        Double val5 = Double.parseDouble(cursor.getString(5));
                                        Double val6 = Double.parseDouble(cursor.getString(6));
                                        Double val7 = Double.parseDouble(cursor.getString(7));
                                        Double val8 = Double.parseDouble(cursor.getString(8));
                                        Toast.makeText(getContext(), String.valueOf(val1), Toast.LENGTH_LONG).show();
                                        if (weights <= val1) {
                                            status3.setText("Severely Wasted");
                                        } else if (weights >= val2 && weights <= val3) {
                                            status3.setText("Wasted");
                                        } else if (weights >= val4 && weights <= val5) {
                                            status3.setText("Normal");
                                        } else if (weights >= val6 && weights <= val7) {
                                            status3.setText("Overweight");
                                        } else if (weights >= val8) {
                                            status3.setText("Obese");
                                        }
                                    }
                                    db.closeDatabase();
                                }
                            }

                        }

                    }
                    //Female
                    if (get_Gender.equals("Female")) {
                        Double[] WFA_SU = {2.0, 2.7, 3.4, 4.0, 4.4, 4.8, 5.1, 5.3, 5.6, 5.8, 5.9, 6.1, 6.3, 6.4, 6.6, 6.7, 6.9, 7.0, 7.2, 7.3, 7.5, 7.6, 7.8, 7.9, 8.1, 8.2, 8.4, 8.5, 8.6, 8.8, 8.9, 9.0, 9.1, 9.3, 9.4, 9.5, 9.6, 9.7, 9.8, 9.9, 10.1, 10.2, 10.3, 10.4, 10.5, 10.6, 10.7, 10.8, 10.9, 11.0, 11.1, 11.2, 11.3, 11.4, 11.5, 11.6, 11.7, 11.8, 11.9, 12.0, 12.1, 12.4, 12.5, 12.6, 12.7, 12.8, 12.9, 13.0, 13.1, 13.2, 13.3, 13.4};
                        Double[] WFA_U2 = {2.3, 3.1, 3.8, 4.4, 4.9, 5.3, 5.6, 5.9, 6.2, 6.4, 6.6, 6.8, 6.9, 7.1, 7.3, 7.5, 7.6, 7.8, 8.0, 8.1, 8.3, 8.5, 8.6, 8.8, 8.9, 9.1, 9.3, 9.4, 9.6, 9.7, 9.9, 10.0, 10.2, 10.3, 10.4, 10.6, 10.7, 10.8, 11.0, 11.1, 11.2, 11.4, 11.5, 11.6, 11.7, 11.9, 12.0, 12.1, 12.2, 12.3, 12.4, 12.6, 12.7, 12.8, 12.9, 13.1, 13.2, 13.3, 13.4, 13.5, 13.6, 13.9, 14.0, 14.1, 14.2, 14.3, 14.5, 14.6, 14.7, 14.8, 14.9, 15.1};
                        Double[] WFA_N2 = {4.2, 5.5, 6.6, 7.5, 8.2, 8.8, 9.3, 9.8, 10.2, 10.5, 10.9, 11.2, 11.5, 11.8, 12.1, 12.4, 12.6, 12.9, 13.2, 13.5, 13.7, 14.0, 14.3, 14.6, 14.8, 15.1, 15.4, 15.7, 16.0, 16.2, 16.5, 16.8, 17.1, 17.3, 17.6, 17.9, 18.1, 18.4, 18.7, 19.0, 19.2, 19.5, 19.8, 20.1, 20.4, 20.7, 20.9, 21.2, 21.5, 21.8, 22.1, 22.4, 22.6, 22.9, 23.2, 23.5, 23.8, 24.1, 24.4, 24.6, 24.7, 24.8, 25.1, 25.4, 25.6, 25.9, 26.2, 26.5, 26.7, 27.0, 27.3, 27.6};
                        Double WFA_severe = WFA_SU[ages];
                        Double WFA_under1 = WFA_SU[ages] + 0.1;
                        Double WFA_under2 = WFA_U2[ages];
                        Double WFA_norm1 = WFA_U2[ages] + 0.1;
                        Double WFA_norm2 = WFA_N2[ages];
                        Double WFA_over = WFA_N2[ages] + 0.1;

                        if (weights <= WFA_severe) {
                            status1.setText("Severely underweight");
                        } else if (weights >= WFA_under1 && weights <= WFA_under2) {
                            status1.setText("Underweight");
                        } else if (weights >= WFA_norm1 && weights <= WFA_norm2) {
                            status1.setText("Normal");
                        } else if (weights >= WFA_over) {
                            status1.setText("Overweight");
                        }

                        Double[] HFA_severely = {43.5, 47.7, 48.9, 53.4, 55.5, 57.3, 58.8, 60.2, 61.6, 62.8, 64.0, 65.1, 66.2, 67.2, 68.2, 69.2, 70.1, 71.0, 71.9, 72.7, 73.6, 74.4, 75.1, 75.9, 75.9, 76.7, 77.4, 78.0, 78.7, 79.4, 80.0, 80.6, 81.2, 81.8, 82.4, 83.0, 83.5, 84.1, 84.6, 85.2, 85.7, 86.2, 86.7, 87.3, 87.8, 88.3, 88.8, 89.2, 89.7, 90.2, 90.6, 91.1, 91.6, 92.0, 92.5, 92.9, 93.3, 93.8, 94.2, 94.6, 95.1, 95.2, 95.6, 96.0, 96.4, 97.3, 97.7, 98.1, 98.5, 98.9, 99.3};
                        Double[] HFA_Stunted_To = {45.3, 49.7, 52.9, 55.5, 57.7, 59.5, 61.1, 62.6, 63.9, 65.2, 66.4, 67.6, 68.8, 69.9, 70.9, 71.9, 72.9, 73.9, 74.8, 75.7, 76.6, 77.4, 78.3, 79.1, 79.2, 79.9, 80.7, 81.4, 82.1, 82.8, 83.5, 84.2, 84.8, 85.5, 86.1, 86.7, 87.3, 87.9, 88.5, 89.1, 89.7, 90.3, 90.8, 91.4, 91.9, 92.4, 93.0, 93.5, 94.0, 94.5, 95.0, 95.5, 96.0, 96.5, 97.0, 97.5, 98.0, 98.4, 98.9, 99.4, 99.8, 100.0, 100.4, 100.9, 101.3, 101.8, 102.2, 102.6, 103.1, 103.5, 103.9, 104.};
                        Double[] HFA_normal_To = {52.9, 57.6, 61.1, 64.0, 66.4, 68.5, 70.3, 71.9, 73.5, 75.0, 76.4, 77.8, 79.2, 80.5, 81.7, 83.0, 84.2, 85.4, 86.5, 87.6, 88.7, 89.8, 90.8, 91.9, 92.2, 93.1, 94.1, 95.0, 96.0, 96.9, 97.7, 98.6, 99.4, 100.3, 101.1, 101.9, 102.7, 103.4, 104.2, 105.0, 105.7, 106.4, 107.2, 107.9, 108.6, 109.3, 110.0, 110.7, 111.3, 112.0, 112.7,113.3, 114.0, 114.6, 115.2, 115.9, 116.5, 117.1, 117.7, 118.3, 118.9, 119.1, 119.7, 120.3, 120.9, 121.5, 122.0, 122.6, 123.2, 123.7, 124.3, 124.8};

                        Double HFA_severe = HFA_severely[ages];
                        Double HFA_stunted1 = HFA_severely[ages] + 0.1;
                        Double HFA_stunted2 = HFA_Stunted_To[ages];
                        Double HFA_norm1 = HFA_normal_To[ages] + 0.1;
                        Double HFA_norm2 = HFA_normal_To[ages];
                        Double HFA_tall = HFA_normal_To[ages] + 0.1;

                        if (ages >=0 && ages <= 23) {
                            Double length = heights + 0.7;

                            if (check2.isChecked()) {
                                if (length <= HFA_severe) {
                                    status2.setText("Severely Stunted");
                                } else if (length >= HFA_stunted1 && heights <= HFA_stunted2) {
                                    status2.setText("Stunted");
                                } else if (length >= HFA_norm1 && heights <= HFA_norm2) {
                                    status2.setText("Normal");
                                } else if (length >= HFA_tall) {
                                    status2.setText("Tall");
                                }

                                if (length < 44.9) {
                                    status3.setText("Severely Wasted");
                                }
                                if (length > 110.0) {
                                    status3.setText("Obese");
                                }

                                String numberAsString = String.format("%.1f", length);
                                int aa = numberAsString.length();
                                int x = aa - 1;
                                String get_sub = numberAsString.substring(x, aa);


                                if (get_sub.equals("3") || get_sub.equals("4") || get_sub.equals("6") || get_sub.equals("7")) {
                                    String try1 = numberAsString.replace(get_sub, "0");
                                    Double xx = Double.valueOf(Double.parseDouble(try1));
                                    Double add = xx + 0.5;
                                    String the_length = String.valueOf(add);
                                    String sql = "Select * from WFL_Female_0_23 where Length='" + the_length + "'";
                                    //Toast.makeText(getContext(), String.valueOf(add), Toast.LENGTH_LONG).show();
                                    db.OpenDatabase();
                                    Cursor cursor = db.getWritableDatabase().rawQuery(sql, null);
                                    while (cursor.moveToNext()) {
                                        Double val1 = Double.parseDouble(cursor.getString(1));
                                        Double val2 = Double.parseDouble(cursor.getString(2));
                                        Double val3 = Double.parseDouble(cursor.getString(3));
                                        Double val4 = Double.parseDouble(cursor.getString(4));
                                        Double val5 = Double.parseDouble(cursor.getString(5));
                                        Double val6 = Double.parseDouble(cursor.getString(6));
                                        Double val7 = Double.parseDouble(cursor.getString(7));
                                        Double val8 = Double.parseDouble(cursor.getString(8));
                                        Toast.makeText(getContext(), String.valueOf(val1), Toast.LENGTH_LONG).show();
                                        if (weights <= val1) {
                                            status3.setText("Severely Wasted");
                                        } else if (weights >= val2 && weights <= val3) {
                                            status3.setText("Wasted");
                                        } else if (weights >= val4 && weights <= val5) {
                                            status3.setText("Normal");
                                        } else if (weights >= val6 && weights <= val7) {
                                            status3.setText("Overweight");
                                        } else if (weights >= val8) {
                                            status3.setText("Obese");
                                        }
                                    }
                                    db.closeDatabase();
                                } else if (get_sub.equals("2") || get_sub.equals("1")) {
                                    String try2 = numberAsString.replace(get_sub, "0");

                                    String the_length = String.valueOf(try2);
                                    String sql = "Select * from WFL_Female_0_23 where Length='" + the_length + "'";
                                    //Toast.makeText(getContext(), String.valueOf(try2), Toast.LENGTH_LONG).show();
                                    db.OpenDatabase();
                                    Cursor cursor = db.getWritableDatabase().rawQuery(sql, null);
                                    while (cursor.moveToNext()) {
                                        Double val1 = Double.parseDouble(cursor.getString(1));
                                        Double val2 = Double.parseDouble(cursor.getString(2));
                                        Double val3 = Double.parseDouble(cursor.getString(3));
                                        Double val4 = Double.parseDouble(cursor.getString(4));
                                        Double val5 = Double.parseDouble(cursor.getString(5));
                                        Double val6 = Double.parseDouble(cursor.getString(6));
                                        Double val7 = Double.parseDouble(cursor.getString(7));
                                        Double val8 = Double.parseDouble(cursor.getString(8));
                                        Toast.makeText(getContext(), String.valueOf(val1), Toast.LENGTH_LONG).show();
                                        if (weights <= val1) {
                                            status3.setText("Severely Wasted");
                                        } else if (weights >= val2 && weights <= val3) {
                                            status3.setText("Wasted");
                                        } else if (weights >= val4 && weights <= val5) {
                                            status3.setText("Normal");
                                        } else if (weights >= val6 && weights <= val7) {
                                            status3.setText("Overweight");
                                        } else if (weights >= val8) {
                                            status3.setText("Obese");
                                        }
                                    }
                                    db.closeDatabase();

                                } else if (get_sub.equals("8") || get_sub.equals("9")) {
                                    String try1 = numberAsString.replace(get_sub, "0");
                                    Double xx = Double.valueOf(Double.parseDouble(try1));
                                    Double add = xx + 1.0;
                                    Toast.makeText(getContext(), String.valueOf(add), Toast.LENGTH_LONG).show();
                                    String the_length = String.valueOf(add);
                                    String sql = "Select * from WFL_Female_0_23 where Length='" + the_length + "'";
                                    // Toast.makeText(getContext(), String.valueOf(add), Toast.LENGTH_LONG).show();
                                    db.OpenDatabase();
                                    Cursor cursor = db.getWritableDatabase().rawQuery(sql, null);
                                    while (cursor.moveToNext()) {
                                        Double val1 = Double.parseDouble(cursor.getString(1));
                                        Double val2 = Double.parseDouble(cursor.getString(2));
                                        Double val3 = Double.parseDouble(cursor.getString(3));
                                        Double val4 = Double.parseDouble(cursor.getString(4));
                                        Double val5 = Double.parseDouble(cursor.getString(5));
                                        Double val6 = Double.parseDouble(cursor.getString(6));
                                        Double val7 = Double.parseDouble(cursor.getString(7));
                                        Double val8 = Double.parseDouble(cursor.getString(8));
                                        Toast.makeText(getContext(), String.valueOf(val1), Toast.LENGTH_LONG).show();
                                        if (weights <= val1) {
                                            status3.setText("Severely Wasted");
                                        } else if (weights >= val2 && weights <= val3) {
                                            status3.setText("Wasted");
                                        } else if (weights >= val4 && weights <= val5) {
                                            status3.setText("Normal");
                                        } else if (weights >= val6 && weights <= val7) {
                                            status3.setText("Overweight");
                                        } else if (weights >= val8) {
                                            status3.setText("Obese");
                                        }
                                    }
                                    db.closeDatabase();
                                } else if (get_sub.equals("0") || get_sub.equals("5")) {
                                    String the_length = String.valueOf(length);
                                    String sql = "Select * from WFL_Female_0_23 where Length='" + the_length + "'";
                                    //Toast.makeText(getContext(), String.valueOf(length), Toast.LENGTH_LONG).show();
                                    db.OpenDatabase();
                                    Cursor cursor = db.getWritableDatabase().rawQuery(sql, null);
                                    while (cursor.moveToNext()) {
                                        Double val1 = Double.parseDouble(cursor.getString(1));
                                        Double val2 = Double.parseDouble(cursor.getString(2));
                                        Double val3 = Double.parseDouble(cursor.getString(3));
                                        Double val4 = Double.parseDouble(cursor.getString(4));
                                        Double val5 = Double.parseDouble(cursor.getString(5));
                                        Double val6 = Double.parseDouble(cursor.getString(6));
                                        Double val7 = Double.parseDouble(cursor.getString(7));
                                        Double val8 = Double.parseDouble(cursor.getString(8));
                                        Toast.makeText(getContext(), String.valueOf(val1), Toast.LENGTH_LONG).show();
                                        if (weights <= val1) {
                                            status3.setText("Severely Wasted");
                                        } else if (weights >= val2 && weights <= val3) {
                                            status3.setText("Wasted");
                                        } else if (weights >= val4 && weights <= val5) {
                                            status3.setText("Normal");
                                        } else if (weights >= val6 && weights <= val7) {
                                            status3.setText("Overweight");
                                        } else if (weights >= val8) {
                                            status3.setText("Obese");
                                        }
                                    }
                                    db.closeDatabase();
                                }
                            } else if (check1.isChecked()) {

                                if (heights <= HFA_severe) {
                                    status2.setText("Severely Stunted");
                                } else if (heights >= HFA_stunted1 && heights <= HFA_stunted2) {
                                    status2.setText("Stunted");
                                } else if (heights >= HFA_norm1 && heights <= HFA_norm2) {
                                    status2.setText("Normal");
                                } else if (heights >= HFA_tall) {
                                    status2.setText("Tall");
                                }

                                if (heights < 44.9) {
                                    status3.setText("Severely Wasted");
                                }
                                if (heights > 110.0) {
                                    status3.setText("Obese");
                                }

                                String numberAsString = String.format("%.1f", heights);
                                int aa = numberAsString.length();
                                int x = aa - 1;
                                String get_sub = numberAsString.substring(x, aa);
                                if (get_sub.equals("3") || get_sub.equals("4") || get_sub.equals("6") || get_sub.equals("7")) {
                                    String try1 = numberAsString.replace(get_sub, "0");
                                    Double xx = Double.valueOf(Double.parseDouble(try1));
                                    Double add = xx + 0.5;
                                    String the_length = String.valueOf(add);
                                    String sql = "Select * from WFL_Female_0_23 where Length='" + the_length + "'";
                                    //Toast.makeText(getContext(), String.valueOf(add), Toast.LENGTH_LONG).show();
                                    db.OpenDatabase();
                                    Cursor cursor = db.getWritableDatabase().rawQuery(sql, null);
                                    while (cursor.moveToNext()) {
                                        Double val1 = Double.parseDouble(cursor.getString(1));
                                        Double val2 = Double.parseDouble(cursor.getString(2));
                                        Double val3 = Double.parseDouble(cursor.getString(3));
                                        Double val4 = Double.parseDouble(cursor.getString(4));
                                        Double val5 = Double.parseDouble(cursor.getString(5));
                                        Double val6 = Double.parseDouble(cursor.getString(6));
                                        Double val7 = Double.parseDouble(cursor.getString(7));
                                        Double val8 = Double.parseDouble(cursor.getString(8));
                                        Toast.makeText(getContext(), String.valueOf(val1), Toast.LENGTH_LONG).show();
                                        if (weights <= val1) {
                                            status3.setText("Severely Wasted");
                                        } else if (weights >= val2 && weights <= val3) {
                                            status3.setText("Wasted");
                                        } else if (weights >= val4 && weights <= val5) {
                                            status3.setText("Normal");
                                        } else if (weights >= val6 && weights <= val7) {
                                            status3.setText("Overweight");
                                        } else if (weights >= val8) {
                                            status3.setText("Obese");
                                        }
                                    }
                                    db.closeDatabase();
                                } else if (get_sub.equals("2") || get_sub.equals("1")) {
                                    String try2 = numberAsString.replace(get_sub, "0");

                                    String the_length = String.valueOf(try2);
                                    String sql = "Select * from WFL_Female_0_23 where Length='" + the_length + "'";
                                    //Toast.makeText(getContext(), String.valueOf(try2), Toast.LENGTH_LONG).show();
                                    db.OpenDatabase();
                                    Cursor cursor = db.getWritableDatabase().rawQuery(sql, null);
                                    while (cursor.moveToNext()) {
                                        Double val1 = Double.parseDouble(cursor.getString(1));
                                        Double val2 = Double.parseDouble(cursor.getString(2));
                                        Double val3 = Double.parseDouble(cursor.getString(3));
                                        Double val4 = Double.parseDouble(cursor.getString(4));
                                        Double val5 = Double.parseDouble(cursor.getString(5));
                                        Double val6 = Double.parseDouble(cursor.getString(6));
                                        Double val7 = Double.parseDouble(cursor.getString(7));
                                        Double val8 = Double.parseDouble(cursor.getString(8));
                                        Toast.makeText(getContext(), String.valueOf(val1), Toast.LENGTH_LONG).show();
                                        if (weights <= val1) {
                                            status3.setText("Severely Wasted");
                                        } else if (weights >= val2 && weights <= val3) {
                                            status3.setText("Wasted");
                                        } else if (weights >= val4 && weights <= val5) {
                                            status3.setText("Normal");
                                        } else if (weights >= val6 && weights <= val7) {
                                            status3.setText("Overweight");
                                        } else if (weights >= val8) {
                                            status3.setText("Obese");
                                        }
                                    }
                                    db.closeDatabase();

                                } else if (get_sub.equals("8") || get_sub.equals("9")) {
                                    String try1 = numberAsString.replace(get_sub, "0");
                                    Double xx = Double.valueOf(Double.parseDouble(try1));
                                    Double add = xx + 1.0;
                                    Toast.makeText(getContext(), String.valueOf(add), Toast.LENGTH_LONG).show();
                                    String the_length = String.valueOf(add);
                                    String sql = "Select * from WFL_Female_0_23 where Length='" + the_length + "'";
                                    // Toast.makeText(getContext(), String.valueOf(add), Toast.LENGTH_LONG).show();
                                    db.OpenDatabase();
                                    Cursor cursor = db.getWritableDatabase().rawQuery(sql, null);
                                    while (cursor.moveToNext()) {
                                        Double val1 = Double.parseDouble(cursor.getString(1));
                                        Double val2 = Double.parseDouble(cursor.getString(2));
                                        Double val3 = Double.parseDouble(cursor.getString(3));
                                        Double val4 = Double.parseDouble(cursor.getString(4));
                                        Double val5 = Double.parseDouble(cursor.getString(5));
                                        Double val6 = Double.parseDouble(cursor.getString(6));
                                        Double val7 = Double.parseDouble(cursor.getString(7));
                                        Double val8 = Double.parseDouble(cursor.getString(8));
                                        Toast.makeText(getContext(), String.valueOf(val1), Toast.LENGTH_LONG).show();
                                        if (weights <= val1) {
                                            status3.setText("Severely Wasted");
                                        } else if (weights >= val2 && weights <= val3) {
                                            status3.setText("Wasted");
                                        } else if (weights >= val4 && weights <= val5) {
                                            status3.setText("Normal");
                                        } else if (weights >= val6 && weights <= val7) {
                                            status3.setText("Overweight");
                                        } else if (weights >= val8) {
                                            status3.setText("Obese");
                                        }
                                    }
                                    db.closeDatabase();
                                } else if (get_sub.equals("0") || get_sub.equals("5")) {
                                    String the_length = String.valueOf(length);
                                    String sql = "Select * from WFL_Female_0_23 where Length='" + heights + "'";
                                    //Toast.makeText(getContext(), String.valueOf(length), Toast.LENGTH_LONG).show();
                                    db.OpenDatabase();
                                    Cursor cursor = db.getWritableDatabase().rawQuery(sql, null);
                                    while (cursor.moveToNext()) {
                                        Double val1 = Double.parseDouble(cursor.getString(1));
                                        Double val2 = Double.parseDouble(cursor.getString(2));
                                        Double val3 = Double.parseDouble(cursor.getString(3));
                                        Double val4 = Double.parseDouble(cursor.getString(4));
                                        Double val5 = Double.parseDouble(cursor.getString(5));
                                        Double val6 = Double.parseDouble(cursor.getString(6));
                                        Double val7 = Double.parseDouble(cursor.getString(7));
                                        Double val8 = Double.parseDouble(cursor.getString(8));
                                        Toast.makeText(getContext(), String.valueOf(val1), Toast.LENGTH_LONG).show();
                                        if (weights <= val1) {
                                            status3.setText("Severely Wasted");
                                        } else if (weights >= val2 && weights <= val3) {
                                            status3.setText("Wasted");
                                        } else if (weights >= val4 && weights <= val5) {
                                            status3.setText("Normal");
                                        } else if (weights >= val6 && weights <= val7) {
                                            status3.setText("Overweight");
                                        } else if (weights >= val8) {
                                            status3.setText("Obese");
                                        }
                                    }
                                    db.closeDatabase();
                                }

                            }


                        } else if (ages >= 24 && ages <= 71) {
                            Double length = heights - 0.7;

                            if (check1.isChecked()) {

                                if (length <= HFA_severe) {
                                    status2.setText("Severely Stunted");
                                } else if (length >= HFA_stunted1 && length <= HFA_stunted2) {
                                    status2.setText("Stunted");
                                } else if (length >= HFA_norm1 && length <= HFA_norm2) {
                                    status2.setText("Normal");
                                } else if (length >= HFA_tall) {
                                    status2.setText("Tall");
                                }

                                if (length < 65.0) {
                                    status3.setText("Severely Wasted");
                                }
                                if (length > 120.0) {
                                    status3.setText("Obese");
                                }


                                String numberAsString = String.format("%.1f", length);
                                int aa = numberAsString.length();
                                int x = aa - 1;
                                String get_sub = numberAsString.substring(x, aa);
                                if (get_sub.equals("3") || get_sub.equals("4") || get_sub.equals("6") || get_sub.equals("7")) {
                                    String try1 = numberAsString.replace(get_sub, "0");
                                    Double xx = Double.valueOf(Double.parseDouble(try1));
                                    Double add = xx + 0.5;
                                    String the_length = String.valueOf(add);
                                    String sql = "Select * from WFL_Female_24_71 where Length='" + the_length + "'";
                                    //Toast.makeText(getContext(), String.valueOf(add), Toast.LENGTH_LONG).show();
                                    db.OpenDatabase();
                                    Cursor cursor = db.getWritableDatabase().rawQuery(sql, null);
                                    while (cursor.moveToNext()) {
                                        Double val1 = Double.parseDouble(cursor.getString(1));
                                        Double val2 = Double.parseDouble(cursor.getString(2));
                                        Double val3 = Double.parseDouble(cursor.getString(3));
                                        Double val4 = Double.parseDouble(cursor.getString(4));
                                        Double val5 = Double.parseDouble(cursor.getString(5));
                                        Double val6 = Double.parseDouble(cursor.getString(6));
                                        Double val7 = Double.parseDouble(cursor.getString(7));
                                        Double val8 = Double.parseDouble(cursor.getString(8));
                                        Toast.makeText(getContext(), String.valueOf(val1), Toast.LENGTH_LONG).show();
                                        if (weights <= val1) {
                                            status3.setText("Severely Wasted");
                                        } else if (weights >= val2 && weights <= val3) {
                                            status3.setText("Wasted");
                                        } else if (weights >= val4 && weights <= val5) {
                                            status3.setText("Normal");
                                        } else if (weights >= val6 && weights <= val7) {
                                            status3.setText("Overweight");
                                        } else if (weights >= val8) {
                                            status3.setText("Obese");
                                        }
                                    }
                                    db.closeDatabase();
                                } else if (get_sub.equals("2") || get_sub.equals("1")) {
                                    String try2 = numberAsString.replace(get_sub, "0");

                                    String the_length = String.valueOf(try2);
                                    String sql = "Select * from WFL_Female_24_71 where Length='" + the_length + "'";
                                    //Toast.makeText(getContext(), String.valueOf(try2), Toast.LENGTH_LONG).show();
                                    db.OpenDatabase();
                                    Cursor cursor = db.getWritableDatabase().rawQuery(sql, null);
                                    while (cursor.moveToNext()) {
                                        Double val1 = Double.parseDouble(cursor.getString(1));
                                        Double val2 = Double.parseDouble(cursor.getString(2));
                                        Double val3 = Double.parseDouble(cursor.getString(3));
                                        Double val4 = Double.parseDouble(cursor.getString(4));
                                        Double val5 = Double.parseDouble(cursor.getString(5));
                                        Double val6 = Double.parseDouble(cursor.getString(6));
                                        Double val7 = Double.parseDouble(cursor.getString(7));
                                        Double val8 = Double.parseDouble(cursor.getString(8));
                                        Toast.makeText(getContext(), String.valueOf(val1), Toast.LENGTH_LONG).show();
                                        if (weights <= val1) {
                                            status3.setText("Severely Wasted");
                                        } else if (weights >= val2 && weights <= val3) {
                                            status3.setText("Wasted");
                                        } else if (weights >= val4 && weights <= val5) {
                                            status3.setText("Normal");
                                        } else if (weights >= val6 && weights <= val7) {
                                            status3.setText("Overweight");
                                        } else if (weights >= val8) {
                                            status3.setText("Obese");
                                        }
                                    }
                                    db.closeDatabase();

                                } else if (get_sub.equals("8") || get_sub.equals("9")) {
                                    String try1 = numberAsString.replace(get_sub, "0");
                                    Double xx = Double.valueOf(Double.parseDouble(try1));
                                    Double add = xx + 1.0;
                                    Toast.makeText(getContext(), String.valueOf(add), Toast.LENGTH_LONG).show();
                                    String the_length = String.valueOf(add);
                                    String sql = "Select * from WFL_Female_24_71 where Length='" + the_length + "'";
                                    // Toast.makeText(getContext(), String.valueOf(add), Toast.LENGTH_LONG).show();
                                    db.OpenDatabase();
                                    Cursor cursor = db.getWritableDatabase().rawQuery(sql, null);
                                    while (cursor.moveToNext()) {
                                        Double val1 = Double.parseDouble(cursor.getString(1));
                                        Double val2 = Double.parseDouble(cursor.getString(2));
                                        Double val3 = Double.parseDouble(cursor.getString(3));
                                        Double val4 = Double.parseDouble(cursor.getString(4));
                                        Double val5 = Double.parseDouble(cursor.getString(5));
                                        Double val6 = Double.parseDouble(cursor.getString(6));
                                        Double val7 = Double.parseDouble(cursor.getString(7));
                                        Double val8 = Double.parseDouble(cursor.getString(8));
                                        Toast.makeText(getContext(), String.valueOf(val1), Toast.LENGTH_LONG).show();
                                        if (weights <= val1) {
                                            status3.setText("Severely Wasted");
                                        } else if (weights >= val2 && weights <= val3) {
                                            status3.setText("Wasted");
                                        } else if (weights >= val4 && weights <= val5) {
                                            status3.setText("Normal");
                                        } else if (weights >= val6 && weights <= val7) {
                                            status3.setText("Overweight");
                                        } else if (weights >= val8) {
                                            status3.setText("Obese");
                                        }
                                    }
                                    db.closeDatabase();
                                } else if (get_sub.equals("0") || get_sub.equals("5")) {
                                    String the_length = String.valueOf(length);
                                    String sql = "Select * from WFL_Female_24_71 where Length='" + the_length + "'";
                                    //Toast.makeText(getContext(), String.valueOf(length), Toast.LENGTH_LONG).show();
                                    db.OpenDatabase();
                                    Cursor cursor = db.getWritableDatabase().rawQuery(sql, null);
                                    while (cursor.moveToNext()) {
                                        Double val1 = Double.parseDouble(cursor.getString(1));
                                        Double val2 = Double.parseDouble(cursor.getString(2));
                                        Double val3 = Double.parseDouble(cursor.getString(3));
                                        Double val4 = Double.parseDouble(cursor.getString(4));
                                        Double val5 = Double.parseDouble(cursor.getString(5));
                                        Double val6 = Double.parseDouble(cursor.getString(6));
                                        Double val7 = Double.parseDouble(cursor.getString(7));
                                        Double val8 = Double.parseDouble(cursor.getString(8));
                                        Toast.makeText(getContext(), String.valueOf(val1), Toast.LENGTH_LONG).show();
                                        if (weights <= val1) {
                                            status3.setText("Severely Wasted");
                                        } else if (weights >= val2 && weights <= val3) {
                                            status3.setText("Wasted");
                                        } else if (weights >= val4 && weights <= val5) {
                                            status3.setText("Normal");
                                        } else if (weights >= val6 && weights <= val7) {
                                            status3.setText("Overweight");
                                        } else if (weights >= val8) {
                                            status3.setText("Obese");
                                        }
                                    }
                                    db.closeDatabase();
                                }

                            } else if (check2.isChecked()) {

                                if (heights <= HFA_severe) {
                                    status2.setText("Severely Stunted");
                                } else if (heights >= HFA_stunted1 && heights <= HFA_stunted2) {
                                    status2.setText("Stunted");
                                } else if (heights >= HFA_norm1 && heights <= HFA_norm2) {
                                    status2.setText("Normal");
                                } else if (heights >= HFA_tall) {
                                    status2.setText("Tall");
                                }

                                if (heights < 65.0) {
                                    status3.setText("Severely Wasted");
                                }
                                if (heights > 120.0) {
                                    status3.setText("Obese");
                                }


                                String numberAsString = String.format("%.1f", heights);
                                int aa = numberAsString.length();
                                int x = aa - 1;
                                String get_sub = numberAsString.substring(x, aa);
                                if (get_sub.equals("3") || get_sub.equals("4") || get_sub.equals("6") || get_sub.equals("7")) {
                                    String try1 = numberAsString.replace(get_sub, "0");
                                    Double xx = Double.valueOf(Double.parseDouble(try1));
                                    Double add = xx + 0.5;
                                    String the_length = String.valueOf(add);
                                    String sql = "Select * from WFL_Female_24_71 where Length='" + the_length + "'";
                                    //Toast.makeText(getContext(), String.valueOf(add), Toast.LENGTH_LONG).show();
                                    db.OpenDatabase();
                                    Cursor cursor = db.getWritableDatabase().rawQuery(sql, null);
                                    while (cursor.moveToNext()) {
                                        Double val1 = Double.parseDouble(cursor.getString(1));
                                        Double val2 = Double.parseDouble(cursor.getString(2));
                                        Double val3 = Double.parseDouble(cursor.getString(3));
                                        Double val4 = Double.parseDouble(cursor.getString(4));
                                        Double val5 = Double.parseDouble(cursor.getString(5));
                                        Double val6 = Double.parseDouble(cursor.getString(6));
                                        Double val7 = Double.parseDouble(cursor.getString(7));
                                        Double val8 = Double.parseDouble(cursor.getString(8));
                                        Toast.makeText(getContext(), String.valueOf(val1), Toast.LENGTH_LONG).show();
                                        if (weights <= val1) {
                                            status3.setText("Severely Wasted");
                                        } else if (weights >= val2 && weights <= val3) {
                                            status3.setText("Wasted");
                                        } else if (weights >= val4 && weights <= val5) {
                                            status3.setText("Normal");
                                        } else if (weights >= val6 && weights <= val7) {
                                            status3.setText("Overweight");
                                        } else if (weights >= val8) {
                                            status3.setText("Obese");
                                        }
                                    }
                                    db.closeDatabase();
                                } else if (get_sub.equals("2") || get_sub.equals("1")) {
                                    String try2 = numberAsString.replace(get_sub, "0");

                                    String the_length = String.valueOf(try2);
                                    String sql = "Select * from WFL_Female_24_71 where Length='" + the_length + "'";
                                    //Toast.makeText(getContext(), String.valueOf(try2), Toast.LENGTH_LONG).show();
                                    db.OpenDatabase();
                                    Cursor cursor = db.getWritableDatabase().rawQuery(sql, null);
                                    while (cursor.moveToNext()) {
                                        Double val1 = Double.parseDouble(cursor.getString(1));
                                        Double val2 = Double.parseDouble(cursor.getString(2));
                                        Double val3 = Double.parseDouble(cursor.getString(3));
                                        Double val4 = Double.parseDouble(cursor.getString(4));
                                        Double val5 = Double.parseDouble(cursor.getString(5));
                                        Double val6 = Double.parseDouble(cursor.getString(6));
                                        Double val7 = Double.parseDouble(cursor.getString(7));
                                        Double val8 = Double.parseDouble(cursor.getString(8));
                                        Toast.makeText(getContext(), String.valueOf(val1), Toast.LENGTH_LONG).show();
                                        if (weights <= val1) {
                                            status3.setText("Severely Wasted");
                                        } else if (weights >= val2 && weights <= val3) {
                                            status3.setText("Wasted");
                                        } else if (weights >= val4 && weights <= val5) {
                                            status3.setText("Normal");
                                        } else if (weights >= val6 && weights <= val7) {
                                            status3.setText("Overweight");
                                        } else if (weights >= val8) {
                                            status3.setText("Obese");
                                        }
                                    }
                                    db.closeDatabase();

                                } else if (get_sub.equals("8") || get_sub.equals("9")) {
                                    String try1 = numberAsString.replace(get_sub, "0");
                                    Double xx = Double.valueOf(Double.parseDouble(try1));
                                    Double add = xx + 1.0;
                                    Toast.makeText(getContext(), String.valueOf(add), Toast.LENGTH_LONG).show();
                                    String the_length = String.valueOf(add);
                                    String sql = "Select * from WFL_Female_24_71 where Length='" + the_length + "'";
                                    // Toast.makeText(getContext(), String.valueOf(add), Toast.LENGTH_LONG).show();
                                    db.OpenDatabase();
                                    Cursor cursor = db.getWritableDatabase().rawQuery(sql, null);
                                    while (cursor.moveToNext()) {
                                        Double val1 = Double.parseDouble(cursor.getString(1));
                                        Double val2 = Double.parseDouble(cursor.getString(2));
                                        Double val3 = Double.parseDouble(cursor.getString(3));
                                        Double val4 = Double.parseDouble(cursor.getString(4));
                                        Double val5 = Double.parseDouble(cursor.getString(5));
                                        Double val6 = Double.parseDouble(cursor.getString(6));
                                        Double val7 = Double.parseDouble(cursor.getString(7));
                                        Double val8 = Double.parseDouble(cursor.getString(8));
                                        Toast.makeText(getContext(), String.valueOf(val1), Toast.LENGTH_LONG).show();
                                        if (weights <= val1) {
                                            status3.setText("Severely Wasted");
                                        } else if (weights >= val2 && weights <= val3) {
                                            status3.setText("Wasted");
                                        } else if (weights >= val4 && weights <= val5) {
                                            status3.setText("Normal");
                                        } else if (weights >= val6 && weights <= val7) {
                                            status3.setText("Overweight");
                                        } else if (weights >= val8) {
                                            status3.setText("Obese");
                                        }
                                    }
                                    db.closeDatabase();
                                } else if (get_sub.equals("0") || get_sub.equals("5")) {
                                    String the_length = String.valueOf(length);
                                    String sql = "Select * from WFL_Female_24_71 where Length='" + heights + "'";
                                    //Toast.makeText(getContext(), String.valueOf(length), Toast.LENGTH_LONG).show();
                                    db.OpenDatabase();
                                    Cursor cursor = db.getWritableDatabase().rawQuery(sql, null);
                                    while (cursor.moveToNext()) {
                                        Double val1 = Double.parseDouble(cursor.getString(1));
                                        Double val2 = Double.parseDouble(cursor.getString(2));
                                        Double val3 = Double.parseDouble(cursor.getString(3));
                                        Double val4 = Double.parseDouble(cursor.getString(4));
                                        Double val5 = Double.parseDouble(cursor.getString(5));
                                        Double val6 = Double.parseDouble(cursor.getString(6));
                                        Double val7 = Double.parseDouble(cursor.getString(7));
                                        Double val8 = Double.parseDouble(cursor.getString(8));
                                        Toast.makeText(getContext(), String.valueOf(val1), Toast.LENGTH_LONG).show();
                                        if (weights <= val1) {
                                            status3.setText("Severely Wasted");
                                        } else if (weights >= val2 && weights <= val3) {
                                            status3.setText("Wasted");
                                        } else if (weights >= val4 && weights <= val5) {
                                            status3.setText("Normal");
                                        } else if (weights >= val6 && weights <= val7) {
                                            status3.setText("Overweight");
                                        } else if (weights >= val8) {
                                            status3.setText("Obese");
                                        }
                                    }
                                    db.closeDatabase();
                                }
                            }
                        }
                    }
                } catch (Exception ee) {
                    Toast.makeText(getContext(), "Invalid inputs", Toast.LENGTH_LONG).show();
                }
            }

        });
        final Button save = (Button)mview.findViewById(R.id.status_save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {

                    SharedPreferences sharedPreferences = getContext().getSharedPreferences(LoginActivity.globalSharedPreferences, Context.MODE_PRIVATE);
                    String name1 = sharedPreferences.getString("name","No name");

                    //global
                  /*  final GlobalClass globalVariable = (GlobalClass) getActivity().getApplicationContext();
                    final String name  = globalVariable.getNamesssss();
*/
                    Intent iii = getActivity().getIntent();
                    String get_Gender = iii.getStringExtra("value_gender");

                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Status");
                    DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().getReference("bar_stats");
                    DatabaseReference databaseReference2 = FirebaseDatabase.getInstance().getReference("form");

                    String child_id = iii.getStringExtra("the_id");
                    String brgy = iii.getStringExtra("brgy");
                    TextView names = (TextView)mview.findViewById(R.id.the_child_name);
                    TextView dobs = (TextView)mview.findViewById(R.id.childs_dob);
                    TextView date = (TextView) mview.findViewById(R.id.date_now);
                    EditText age = (EditText) mview.findViewById(R.id.child_age);
                    EditText child_weight = (EditText) mview.findViewById(R.id.weight);
                    EditText child_height = (EditText) mview.findViewById(R.id.the_height);
                    EditText child_MUAC = (EditText) mview.findViewById(R.id.MUAC);
                    TextView stat1 = (TextView) mview.findViewById(R.id.status_1);
                    TextView stat22 = (TextView) mview.findViewById(R.id.status_2);
                    TextView stat3 = (TextView) mview.findViewById(R.id.status_3);
                    TextView stat4 = (TextView) mview.findViewById(R.id.status_4);

                    String id = databaseReference.push().getKey();
                    int xx = Integer.parseInt(age.getText().toString());
                    save_status saveStatus = new save_status(child_id, date.getText().toString(), age.getText().toString(), child_weight.getText().toString(), child_height.getText().toString(),child_MUAC.getText().toString(), stat1.getText().toString(), stat22.getText().toString(), stat3.getText().toString(), stat4.getText().toString(),name1);
                    bar bars = new bar(child_id,date.getText().toString(),stat1.getText().toString(),stat22.getText().toString(),stat3.getText().toString(),stat4.getText().toString());
                    databaseReference.child(brgy).child(child_id).child("stats").child(id).setValue(saveStatus);
                    databaseReference1.child(age.getText().toString()).child(id).setValue(bars);

                    Calendar calendar = Calendar.getInstance();
                    int year = calendar.get(Calendar.YEAR);
                    String dte = date.getText().toString();
                    String sub = dte.substring(0,3);
                    form4 form = new form4(names.getText().toString(),get_Gender,dobs.getText().toString(),age.getText().toString(),child_weight.getText().toString(),stat1.getText().toString(),child_height.getText().toString(),stat22.getText().toString(),stat4.getText().toString());
                    databaseReference2.child(sub+"-"+year).child(id).setValue(form);


                    Toast.makeText(getContext(), "Saved", Toast.LENGTH_LONG).show();
                    save.setEnabled(false);
                    if(xx>=24&&xx<=71){
                        hfw_24 saved = new hfw_24(child_id,child_height.getText().toString(),child_weight.getText().toString());
                        databaseReference.child(brgy).child(child_id).child("HFW_24_71").child(id).setValue(saved);
                        stat1.setText("");
                        stat22.setText("");
                        stat3.setText("");
                        stat4.setText("");
                        child_height.setText("");
                        child_MUAC.setText("");
                        child_weight.setText("");
                        save.setEnabled(false);
                    }
                    if(xx>=0&&xx<=23){
                        hfw_24 saved = new hfw_24(child_id,child_height.getText().toString(),child_weight.getText().toString());
                        databaseReference.child(brgy).child(child_id).child("HFW_0_23").child(id).setValue(saved);
                        stat1.setText("");
                        stat22.setText("");
                        stat3.setText("");
                        stat4.setText("");
                        child_height.setText("");
                        child_MUAC.setText("");
                        child_weight.setText("");
                        save.setEnabled(false);
                    }

                }catch (Exception ee){
                    Toast.makeText(getContext(), ee.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }



}


