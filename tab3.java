package com.example.lloyd.healthnutritiontool;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.Viewport;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.Arrays;


public class tab3 extends Fragment {

    GraphView wfa_graph, hfa_graph, hfw_graph, muac_graph,hfw1_graph;
    CardView card;
    LineGraphSeries<DataPoint> wfa_boys_su;
    LineGraphSeries<DataPoint> wfa_boy_uFrom;
    LineGraphSeries<DataPoint> wfa_boys_uTo;
    LineGraphSeries<DataPoint> wfa_boys_nFrom;
    LineGraphSeries<DataPoint> wfa_boys_nTo;
    LineGraphSeries<DataPoint> wfa_boys_o;
    LineGraphSeries<DataPoint> wfa_boys;

    LineGraphSeries<DataPoint> hfa_boys_ss;
    LineGraphSeries<DataPoint> hfa_boy_sFrom;
    LineGraphSeries<DataPoint> hfa_boys_sTo;
    LineGraphSeries<DataPoint> hfa_boys_nFrom;
    LineGraphSeries<DataPoint> hfa_boys_nTo;
    LineGraphSeries<DataPoint> hfa_boys_T;
    LineGraphSeries<DataPoint> hfa_boys;

    LineGraphSeries<DataPoint> hfw_sw;
    LineGraphSeries<DataPoint> hfw_wFrom;
    LineGraphSeries<DataPoint> hfw_wTo;
    LineGraphSeries<DataPoint> hfw_nFrom;
    LineGraphSeries<DataPoint> hfw_nTo;
    LineGraphSeries<DataPoint> hfw_oTo;
    LineGraphSeries<DataPoint> hfw_oFrom;
    LineGraphSeries<DataPoint> hfw_o;
    LineGraphSeries<DataPoint> hfw;

    LineGraphSeries<DataPoint> hfw1_sw;
    LineGraphSeries<DataPoint> hfw1_wFrom;
    LineGraphSeries<DataPoint> hfw1_wTo;
    LineGraphSeries<DataPoint> hfw1_nFrom;
    LineGraphSeries<DataPoint> hfw1_nTo;
    LineGraphSeries<DataPoint> hfw1_oTo;
    LineGraphSeries<DataPoint> hfw1_oFrom;
    LineGraphSeries<DataPoint> hfw1_o;
    LineGraphSeries<DataPoint> hfw1;

    LineGraphSeries<DataPoint> muac_low;
    LineGraphSeries<DataPoint> muac_mid;
    LineGraphSeries<DataPoint> muac_mid2;
    LineGraphSeries<DataPoint> muac_high;
    LineGraphSeries<DataPoint> muac;


    LineGraphSeries<DataPoint> wfa_girls_su;
    LineGraphSeries<DataPoint> wfa_girls_uFrom;
    LineGraphSeries<DataPoint> wfa_girls_uTo;
    LineGraphSeries<DataPoint> wfa_girls_nFrom;
    LineGraphSeries<DataPoint> wfa_girls_nTo;
    LineGraphSeries<DataPoint> wfa_girls_o;
    LineGraphSeries<DataPoint> wfa_girls;

    LineGraphSeries<DataPoint> hfa_girls_ss;
    LineGraphSeries<DataPoint> hfa_girls_sFrom;
    LineGraphSeries<DataPoint> hfa_girls_sTo;
    LineGraphSeries<DataPoint> hfa_girls_nFrom;
    LineGraphSeries<DataPoint> hfa_girls_nTo;
    LineGraphSeries<DataPoint> hfa_girls_T;
    LineGraphSeries<DataPoint> hfa_girls;

    DatabaseReference databaseReference;
    myDatabase db;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle saveInstanceState){
        View view = inflater.inflate(R.layout.tab3,container,false);

        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);





        card = (CardView)getActivity().findViewById(R.id.card_hfw_24_71);

        Intent iii = getActivity().getIntent();
        final String idss = iii.getStringExtra("the_id");
        String value1 = iii.getStringExtra("brgy");
        final String get_Gender = iii.getStringExtra("value_gender");


        wfa_graph = (GraphView)getActivity().findViewById(R.id.graph_wfa);
        wfa_graph.setTitle("Weight for Age 0 - 71 months");
        hfa_graph = (GraphView)getActivity().findViewById(R.id.graph_hfa);
        hfa_graph.setTitle("Height for Age 0 - 71 months");
        hfw_graph = (GraphView)getActivity().findViewById(R.id.graph_hfw);
        hfw_graph.setTitle("Height for weight 0 - 71 months");
        muac_graph = (GraphView)getActivity().findViewById(R.id.graph_muac);
        muac_graph.setTitle("Mid-Upper-Arm-Circumference 6 - 59 months");
        hfw1_graph = (GraphView)getActivity().findViewById(R.id.graph_hfw_24_71);
        hfw1_graph.setTitle("Height for Weight 24 - 71 months");

// male
        wfa_boys_su = new LineGraphSeries<DataPoint>();
        wfa_boy_uFrom = new LineGraphSeries<DataPoint>();
        wfa_boys_uTo = new LineGraphSeries<DataPoint>();
        wfa_boys_nFrom = new LineGraphSeries<DataPoint>();
        wfa_boys_nTo = new LineGraphSeries<DataPoint>();
        wfa_boys_o = new LineGraphSeries<DataPoint>();
        wfa_boys = new LineGraphSeries<DataPoint>();

        hfa_boys_ss = new LineGraphSeries<DataPoint>();
        hfa_boy_sFrom = new LineGraphSeries<DataPoint>();
        hfa_boys_sTo = new LineGraphSeries<DataPoint>();
        hfa_boys_nFrom = new LineGraphSeries<DataPoint>();
        hfa_boys_nTo = new LineGraphSeries<DataPoint>();
        hfa_boys_T = new LineGraphSeries<DataPoint>();
        hfa_boys = new LineGraphSeries<DataPoint>();
//female
        wfa_girls_su = new LineGraphSeries<DataPoint>();
        wfa_girls_uFrom = new LineGraphSeries<DataPoint>();
        wfa_girls_uTo = new LineGraphSeries<DataPoint>();
        wfa_girls_nFrom = new LineGraphSeries<DataPoint>();
        wfa_girls_nTo = new LineGraphSeries<DataPoint>();
        wfa_girls_o = new LineGraphSeries<DataPoint>();
        wfa_girls = new LineGraphSeries<DataPoint>();

        hfa_girls_ss = new LineGraphSeries<DataPoint>();
        hfa_girls_sFrom = new LineGraphSeries<DataPoint>();
        hfa_girls_sTo = new LineGraphSeries<DataPoint>();
        hfa_girls_nFrom = new LineGraphSeries<DataPoint>();
        hfa_girls_nTo = new LineGraphSeries<DataPoint>();
        hfa_girls_T = new LineGraphSeries<DataPoint>();
        hfa_girls = new LineGraphSeries<DataPoint>();


//both male female
        hfw_sw = new LineGraphSeries<DataPoint>();
        hfw_wFrom = new LineGraphSeries<DataPoint>();
        hfw_wTo = new LineGraphSeries<DataPoint>();
        hfw_nFrom = new LineGraphSeries<DataPoint>();
        hfw_nTo = new LineGraphSeries<DataPoint>();
        hfw_oFrom = new LineGraphSeries<DataPoint>();
        hfw_oTo = new LineGraphSeries<DataPoint>();
        hfw_o = new LineGraphSeries<DataPoint>();
        hfw = new LineGraphSeries<DataPoint>();

        hfw1_sw = new LineGraphSeries<DataPoint>();
        hfw1_wFrom = new LineGraphSeries<DataPoint>();
        hfw1_wTo = new LineGraphSeries<DataPoint>();
        hfw1_nFrom = new LineGraphSeries<DataPoint>();
        hfw1_nTo = new LineGraphSeries<DataPoint>();
        hfw1_oFrom = new LineGraphSeries<DataPoint>();
        hfw1_oTo = new LineGraphSeries<DataPoint>();
        hfw1_o = new LineGraphSeries<DataPoint>();
        hfw1 = new LineGraphSeries<DataPoint>();

        muac_low =new  LineGraphSeries<DataPoint>();
        muac_mid =new  LineGraphSeries<DataPoint>();
        muac_mid2 =new  LineGraphSeries<DataPoint>();
        muac_high =new  LineGraphSeries<DataPoint>();
        muac =new  LineGraphSeries<DataPoint>();

        Double low = 11.5;
        Double mid = 11.6;
        Double mid2 = 12.5;
        Double high = 12.6;







        for (int i=6;i<71;i++){
            muac_low.appendData(new DataPoint(i,low),false,75);
            muac_mid.appendData(new DataPoint(i,mid),false,75);
            muac_mid2.appendData(new DataPoint(i,mid2),false,75);
            muac_high.appendData(new DataPoint(i,high),false,75);

        }


        //hfw for girls and boys
        try {
            db = new myDatabase(getContext());
            db.OpenDatabase();
            String sql = "Select * from WFL_" + get_Gender + "_0_23";
            Cursor cursor = db.getWritableDatabase().rawQuery(sql, null);
            String sql1 = "Select * from WFL_" + get_Gender + "_24_71";
            Cursor cursor1 = db.getWritableDatabase().rawQuery(sql1, null);
            while (cursor.moveToNext()) {
                Double val1 = Double.parseDouble(cursor.getString(0));
                Double val2 = Double.parseDouble(cursor.getString(1));
                Double val3 = Double.parseDouble(cursor.getString(2));
                Double val4 = Double.parseDouble(cursor.getString(3));
                Double val5 = Double.parseDouble(cursor.getString(4));
                Double val6 = Double.parseDouble(cursor.getString(5));
                Double val7 = Double.parseDouble(cursor.getString(6));
                Double val8 = Double.parseDouble(cursor.getString(7));
                Double val9 = Double.parseDouble(cursor.getString(8));

                hfw_sw.appendData(new DataPoint(val1, val2), false, 150);
                hfw_wFrom.appendData(new DataPoint(val1, val3), false, 150);
                hfw_wTo.appendData(new DataPoint(val1, val4), false, 150);
                hfw_nFrom.appendData(new DataPoint(val1, val5), false, 150);
                hfw_nTo.appendData(new DataPoint(val1, val6), false, 150);
                hfw_oFrom.appendData(new DataPoint(val1, val7), false, 150);
                hfw_oTo.appendData(new DataPoint(val1, val8), false, 150);
                hfw_o.appendData(new DataPoint(val1, val9), false, 150);

            }
            while (cursor1.moveToNext()){
                Double val1 = Double.parseDouble(cursor1.getString(0));
                Double val2 = Double.parseDouble(cursor1.getString(1));
                Double val3 = Double.parseDouble(cursor1.getString(2));
                Double val4 = Double.parseDouble(cursor1.getString(3));
                Double val5 = Double.parseDouble(cursor1.getString(4));
                Double val6 = Double.parseDouble(cursor1.getString(5));
                Double val7 = Double.parseDouble(cursor1.getString(6));
                Double val8 = Double.parseDouble(cursor1.getString(7));
                Double val9 = Double.parseDouble(cursor1.getString(8));
                hfw1_sw.appendData(new DataPoint(val1, val2), false, 150);
                hfw1_wFrom.appendData(new DataPoint(val1,val3), false, 150);
                hfw1_wTo.appendData(new DataPoint(val1, val4), false, 150);
                hfw1_nFrom.appendData(new DataPoint(val1, val5), false, 150);
                hfw1_nTo.appendData(new DataPoint(val1, val6), false, 150);
                hfw1_oFrom.appendData(new DataPoint(val1, val7), false, 150);
                hfw1_oTo.appendData(new DataPoint(val1, val8), false, 150);
                hfw1_o.appendData(new DataPoint(val1, val9), false, 150);
            }
            db.closeDatabase();
        }catch (Exception ee){
            Toast.makeText(getContext(),ee.getMessage(),Toast.LENGTH_LONG).show();
        }

        databaseReference = FirebaseDatabase.getInstance().getReference("Status");
        databaseReference.child(value1).child(idss).child("stats").orderByKey().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                try {
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        String wfa = ds.child("child_weight").getValue(String.class);
                        String age = ds.child("child_age_now").getValue(String.class);
                        String height = ds.child("child_height").getValue(String.class);
                        String muacs = ds.child("midUpArmCir").getValue(String.class);

                        Double get_age = Double.parseDouble(age);
                        Double get_wfa = Double.parseDouble(wfa);
                        Double get_height = Double.parseDouble(height);
                        Double get_muac = Double.parseDouble(muacs);




                       // for (int i = 0; i < example.size(); i++) {
                        if(get_Gender.equals("Male")) {
                            wfa_boys.appendData(new DataPoint(get_age, get_wfa), false, 72);
                            hfa_boys.appendData(new DataPoint(get_age, get_height), false, 71);
                            wfa_graph.addSeries(wfa_boys);
                            hfa_graph.addSeries(hfa_boys);

                        }else {
                            wfa_girls.appendData(new DataPoint(get_age, get_wfa), false, 72);
                            hfa_girls.appendData(new DataPoint(get_age, get_height), false, 71);
                            wfa_graph.addSeries(wfa_girls);
                            hfa_graph.addSeries(hfa_girls);

                        }
                            muac.appendData(new DataPoint(get_age, get_muac), false, 71);
                      //  }

                    }

                }catch (Exception ee){
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        //hfw 0 23
        databaseReference.child(value1).child(idss).child("HFW_0_23").orderByChild("height_child").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds:dataSnapshot.getChildren()) {
                    try {
                        String a = ds.child("height_child").getValue(String.class);
                        String aa = ds.child("weight_child").getValue(String.class);

                        Double a1 = Double.parseDouble(String.valueOf(a));
                        Double aa2 = Double.parseDouble(String.valueOf(aa));

                        hfw.appendData(new DataPoint(a1, aa2), false, 23);

                    }catch (Exception ee){

                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        //hfw 24 71
        databaseReference.child(value1).child(idss).child("HFW_24_71").orderByKey().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds:dataSnapshot.getChildren()) {
                    try {
                        String a = ds.child("height_child").getValue(String.class);
                        String aa = ds.child("weight_child").getValue(String.class);

                        Double a1 = Double.parseDouble(String.valueOf(a));
                        Double aa2 = Double.parseDouble(String.valueOf(aa));

                        hfw1.appendData(new DataPoint(a1, aa2), false, 23);

                    }catch (Exception ee){

                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        //if male
        if(get_Gender.equals("Male")){

            //wfa graph
            Double[] WFA_SU = {2.1, 2.9, 3.8, 4.4, 4.9, 5.3, 5.7, 5.9, 6.2, 6.4, 6.6, 6.8, 6.9, 7.1, 7.2, 7.4, 7.5, 7.7, 7.8, 8.0, 8.1, 8.2, 8.3, 8.4, 8.5, 8.6, 8.7, 8.9, 9.0, 9.1, 9.2, 9.3, 9.4, 9.5, 9.6, 9.7, 9.8, 9.9, 10.0, 10.1, 10.2, 10.3, 10.4, 10.5, 10.6, 10.7, 10.8, 10.9, 11.0, 11.1, 11.2, 11.3, 11.4, 11.5, 11.6, 11.7, 11.8, 11.9, 12.0, 12.1, 12.1, 12.2, 12.3, 12.4, 12.7, 12.8, 12.9, 13.0, 13.1, 13.2, 13.3, 13.4, 13.6, 13.7, 13.8, 13.9};
            Double[] WFA_U2 = {2.4, 3.3, 4.2, 4.9, 5.5, 5.9, 6.3, 6.6, 6.8, 7.0, 7.3, 7.5, 7.6, 7.8, 8.0, 8.2, 8.3, 8.5, 8.7, 8.8, 9.0, 9.1, 9.3, 9.4, 9.6, 9.7, 9.9, 10.0, 10.1, 10.3, 10.4, 10.6, 10.7, 10.8, 10.9, 11.1, 11.2, 11.3, 11.4, 11.5, 11.7, 11.8, 11.9, 12.0, 12.1, 12.3, 12.4, 12.5, 12.6, 12.7, 12.8, 13.0, 13.1, 13.2, 13.3, 13.4, 13.5, 13.6, 13.7, 13.9, 14.0, 14.3, 14.4, 14.5, 14.7, 14.8, 14.9, 15.1, 15.2, 15.3, 15.5, 15.6};
            Double[] WFA_N2 = {4.4, 5.8, 7.1, 8.0, 8.7, 9.3, 9.8, 10.3, 10.7, 11.0, 11.4, 11.7, 12.0, 12.3, 12.6, 12.8, 13.1, 13.4, 13.7, 13.9, 14.2, 14.5, 14.7, 15.0, 15.3, 15.5, 15.8, 16.1, 16.3, 16.6, 16.9, 17.1, 17.4, 17.6, 17.8, 18.1, 18.3, 18.6, 18.8, 19.0, 19.3, 19.5, 19.7, 20.0, 20.2, 20.5, 20.7, 20.9, 21.2, 21.4, 21.7, 21.9, 22.2, 22.4, 22.7, 22.9, 23.2, 23.4, 23.7, 23.9, 24.2, 24.3, 24.4, 24.7, 24.9, 25.2, 25.5, 25.7, 26.0, 26.3, 26.6, 26.8};

            for (int i=0;i<71;i++) {
                wfa_boys_su.appendData(new DataPoint(i, WFA_SU[i]), false, 100);
                wfa_boy_uFrom.appendData(new DataPoint(i, WFA_SU[i]+0.1), false, 100);
                wfa_boys_uTo.appendData(new DataPoint(i, WFA_U2[i]), false, 100);
                wfa_boys_nFrom.appendData(new DataPoint(i, WFA_U2[i]+0.1), false, 100);
                wfa_boys_nTo.appendData(new DataPoint(i, WFA_N2[i]), false, 100);
                wfa_boys_o.appendData(new DataPoint(i, WFA_N2[i]+0.1), false, 100);
            }
            Double[] HFA_severely = {44.1, 48.8, 52.3, 55.5, 57.5, 59.5, 61.1, 62.6, 63.9, 65.1, 66.3, 67.5, 68.5, 69.5, 70.5, 71.5, 72.4, 73.2, 74.1, 74.9, 75.7, 76.4, 77.1, 77.9, 78.5, 79.2, 79.8, 80.4, 81.0, 81.6, 82.2, 82.7, 83.3, 83.8, 84.3, 84.9, 85.4, 85.9, 86.4, 86.9, 87.4, 87.9, 88.3, 88.8, 89.3, 89.7, 90.2, 90.6, 91.1, 91.5, 92.0, 92.4, 92.9, 93.3, 93.8, 94.2, 94.6, 95.1, 95.5, 96.0, 96.4, 96.8, 97.3, 97.7, 98.1, 98.6, 99.0, 99.4, 99.8, 100.3, 100.7};
            Double[] HFA_Stunted_To = {46.0, 50.7, 54.3, 57.2, 59.6, 61.6, 63.2, 64.7, 66.1, 67.4, 68.6, 69.8, 70.9, 72.0, 73.0, 74.0, 74.9, 75.9, 76.8, 77.6, 78.5, 79.3, 80.1, 80.9, 80.9, 81.6, 82.4, 83.0, 83.7, 84.4, 85.0, 85.6, 86.3, 86.8, 87.4, 88.0, 88.6, 89.1, 89.7, 90.2, 90.8, 91.3, 91.8, 92.3, 92.9, 93.4, 93.9, 94.3, 94.8, 95.3, 95.8, 96.3, 96.8, 97.3, 97.7, 98.2, 98.7, 99.2, 99.6, 100.1, 100.6, 101.0, 101.5, 101.9, 102.4, 102.9, 103.3, 103.8, 104.2, 104.7, 105.1, 105.6};
            Double[] HFA_normal_To = {53.7, 58.6, 62.4, 65.5, 68.0, 70.1, 71.9, 73.5, 75.0, 76.5, 77.9, 79.2, 80.5, 81.8, 83.0, 84.2, 85.4, 86.5, 87.7, 88.8, 89.8, 90.9, 91.9, 92.9, 93.2, 94.2, 95.2, 96.1, 97.0, 97.9, 98.7, 99.6, 100.4, 101.2, 102.0, 102.7, 103.5, 104.2, 105.0, 105.7, 106.4, 107.1, 107.8, 108.5, 109.1, 109.8, 110.4, 111.1, 111.7, 112.4, 113.0, 113.6, 114.2, 114.9, 115.5, 116.1, 116.7, 117.4, 118.0, 118.6, 119.2, 119.4, 120.0, 120.6, 121.2, 121.8, 122.4, 123.0, 123.6, 124.1, 124.7, 125.2};
            for (int i=0;i<71;i++) {
                hfa_boys_ss.appendData(new DataPoint(i, HFA_severely[i]), false, 100);
                hfa_boy_sFrom.appendData(new DataPoint(i, HFA_severely[i]+0.1), false, 100);
                hfa_boys_sTo.appendData(new DataPoint(i, HFA_Stunted_To[i]), false, 100);
                hfa_boys_nFrom.appendData(new DataPoint(i, HFA_Stunted_To[i]+0.1), false, 100);
                hfa_boys_nTo.appendData(new DataPoint(i, HFA_normal_To[i]), false, 100);
                hfa_boys_T.appendData(new DataPoint(i, HFA_normal_To[i]+0.1), false, 100);
            }

            wfa_graph.addSeries(wfa_boys_su);
            wfa_graph.addSeries(wfa_boy_uFrom);
            wfa_graph.addSeries(wfa_boys_uTo);
            wfa_graph.addSeries(wfa_boys_nFrom);
            wfa_graph.addSeries(wfa_boys_nTo);
            wfa_graph.addSeries(wfa_boys_o);


            hfa_graph.addSeries(hfa_boys_ss);
            hfa_graph.addSeries(hfa_boy_sFrom);
            hfa_graph.addSeries(hfa_boys_sTo);
            hfa_graph.addSeries(hfa_boys_nFrom);
            hfa_graph.addSeries(hfa_boys_nTo);
            hfa_graph.addSeries(hfa_boys_T);


        }else if(get_Gender.equals("Female")){
            Double[] WFA_SU = {2.0, 2.7, 3.4, 4.0, 4.4, 4.8, 5.1, 5.3, 5.6, 5.8, 5.9, 6.1, 6.3, 6.4, 6.6, 6.7, 6.9, 7.0, 7.2, 7.3, 7.5, 7.6, 7.8, 7.9, 8.1, 8.2, 8.4, 8.5, 8.6, 8.8, 8.9, 9.0, 9.1, 9.3, 9.4, 9.5, 9.6, 9.7, 9.8, 9.9, 10.1, 10.2, 10.3, 10.4, 10.5, 10.6, 10.7, 10.8, 10.9, 11.0, 11.1, 11.2, 11.3, 11.4, 11.5, 11.6, 11.7, 11.8, 11.9, 12.0, 12.1, 12.4, 12.5, 12.6, 12.7, 12.8, 12.9, 13.0, 13.1, 13.2, 13.3, 13.4};
            Double[] WFA_U2 = {2.3, 3.1, 3.8, 4.4, 4.9, 5.3, 5.6, 5.9, 6.2, 6.4, 6.6, 6.8, 6.9, 7.1, 7.3, 7.5, 7.6, 7.8, 8.0, 8.1, 8.3, 8.5, 8.6, 8.8, 8.9, 9.1, 9.3, 9.4, 9.6, 9.7, 9.9, 10.0, 10.2, 10.3, 10.4, 10.6, 10.7, 10.8, 11.0, 11.1, 11.2, 11.4, 11.5, 11.6, 11.7, 11.9, 12.0, 12.1, 12.2, 12.3, 12.4, 12.6, 12.7, 12.8, 12.9, 13.1, 13.2, 13.3, 13.4, 13.5, 13.6, 13.9, 14.0, 14.1, 14.2, 14.3, 14.5, 14.6, 14.7, 14.8, 14.9, 15.1};
            Double[] WFA_N2 = {4.2, 5.5, 6.6, 7.5, 8.2, 8.8, 9.3, 9.8, 10.2, 10.5, 10.9, 11.2, 11.5, 11.8, 12.1, 12.4, 12.6, 12.9, 13.2, 13.5, 13.7, 14.0, 14.3, 14.6, 14.8, 15.1, 15.4, 15.7, 16.0, 16.2, 16.5, 16.8, 17.1, 17.3, 17.6, 17.9, 18.1, 18.4, 18.7, 19.0, 19.2, 19.5, 19.8, 20.1, 20.4, 20.7, 20.9, 21.2, 21.5, 21.8, 22.1, 22.4, 22.6, 22.9, 23.2, 23.5, 23.8, 24.1, 24.4, 24.6, 24.7, 24.8, 25.1, 25.4, 25.6, 25.9, 26.2, 26.5, 26.7, 27.0, 27.3, 27.6};

            for(int i=0;i<71;i++){
                wfa_girls_su.appendData(new DataPoint(i,WFA_SU[i]),false,100);
                wfa_girls_uFrom.appendData(new DataPoint(i,WFA_SU[i]+0.1),false,100);
                wfa_girls_uTo.appendData(new DataPoint(i,WFA_U2[i]),false,100);
                wfa_girls_nFrom.appendData(new DataPoint(i,WFA_U2[i]+0.1),false,100);
                wfa_girls_nTo.appendData(new DataPoint(i,WFA_N2[i]),false,100);
                wfa_girls_o.appendData(new DataPoint(i,WFA_N2[i]+0.1),false,100);
            }
            Double[] HFA_severely = {43.5, 47.7, 48.9, 53.4, 55.5, 57.3, 58.8, 60.2, 61.6, 62.8, 64.0, 65.1, 66.2, 67.2, 68.2, 69.2, 70.1, 71.0, 71.9, 72.7, 73.6, 74.4, 75.1, 75.9, 75.9, 76.7, 77.4, 78.0, 78.7, 79.4, 80.0, 80.6, 81.2, 81.8, 82.4, 83.0, 83.5, 84.1, 84.6, 85.2, 85.7, 86.2, 86.7, 87.3, 87.8, 88.3, 88.8, 89.2, 89.7, 90.2, 90.6, 91.1, 91.6, 92.0, 92.5, 92.9, 93.3, 93.8, 94.2, 94.6, 95.1, 95.2, 95.6, 96.0, 96.4, 97.3, 97.7, 98.1, 98.5, 98.9, 99.3};
            Double[] HFA_Stunted_To = {45.3, 49.7, 52.9, 55.5, 57.7, 59.5, 61.1, 62.6, 63.9, 65.2, 66.4, 67.6, 68.8, 69.9, 70.9, 71.9, 72.9, 73.9, 74.8, 75.7, 76.6, 77.4, 78.3, 79.1, 79.2, 79.9, 80.7, 81.4, 82.1, 82.8, 83.5, 84.2, 84.8, 85.5, 86.1, 86.7, 87.3, 87.9, 88.5, 89.1, 89.7, 90.3, 90.8, 91.4, 91.9, 92.4, 93.0, 93.5, 94.0, 94.5, 95.0, 95.5, 96.0, 96.5, 97.0, 97.5, 98.0, 98.4, 98.9, 99.4, 99.8, 100.0, 100.4, 100.9, 101.3, 101.8, 102.2, 102.6, 103.1, 103.5, 103.9, 104.};
            Double[] HFA_normal_To = {52.9, 57.6, 61.1, 64.0, 66.4, 68.5, 70.3, 71.9, 73.5, 75.0, 76.4, 77.8, 79.2, 80.5, 81.7, 83.0, 84.2, 85.4, 86.5, 87.6, 88.7, 89.8, 90.8, 91.9, 92.2, 93.1, 94.1, 95.0, 96.0, 96.9, 97.7, 98.6, 99.4, 100.3, 101.1, 101.9, 102.7, 103.4, 104.2, 105.0, 105.7, 106.4, 107.2, 107.9, 108.6, 109.3, 110.0, 110.7, 111.3, 112.0, 112.7, 113.3, 114.0, 114.6, 115.2, 115.9, 116.5, 117.1, 117.7, 118.3, 118.9, 119.1, 119.7, 120.3, 120.9, 121.5, 122.0, 122.6, 123.2, 123.7, 124.3, 124.8};

            for(int i=0;i<71;i++){
                hfa_girls_ss.appendData(new DataPoint(i, HFA_severely[i]), false, 100);
                hfa_girls_sFrom.appendData(new DataPoint(i, HFA_severely[i]+0.1), false, 100);
                hfa_girls_sTo.appendData(new DataPoint(i, HFA_Stunted_To[i]), false, 100);
                hfa_girls_nFrom.appendData(new DataPoint(i, HFA_Stunted_To[i]+0.1), false, 100);
                hfa_girls_nTo.appendData(new DataPoint(i, HFA_normal_To[i]), false, 100);
                hfa_girls_T.appendData(new DataPoint(i, HFA_normal_To[i]+0.1), false, 100);
            }

            wfa_graph.addSeries(wfa_girls_su);
            wfa_graph.addSeries(wfa_girls_uFrom);
            wfa_graph.addSeries(wfa_girls_uTo);
            wfa_graph.addSeries(wfa_girls_nFrom);
            wfa_graph.addSeries(wfa_girls_nTo);
            wfa_graph.addSeries(wfa_girls_o);

            hfa_graph.addSeries(hfa_girls_ss);
            hfa_graph.addSeries(hfa_girls_sFrom);
            hfa_graph.addSeries(hfa_girls_sTo);
            hfa_graph.addSeries(hfa_girls_nFrom);
            hfa_graph.addSeries(hfa_girls_nTo);
            hfa_graph.addSeries(hfa_girls_T);

        }

        try {
            //add series
            hfw_graph.addSeries(hfw_sw);
            hfw_graph.addSeries(hfw_wFrom);
            hfw_graph.addSeries(hfw_wTo);
            hfw_graph.addSeries(hfw_nFrom);
            hfw_graph.addSeries(hfw_nTo);
            hfw_graph.addSeries(hfw_oFrom);
            hfw_graph.addSeries(hfw_oTo);
            hfw_graph.addSeries(hfw_o);
            hfw_graph.addSeries(hfw);

            hfw1_graph.addSeries(hfw1_sw);
            hfw1_graph.addSeries(hfw1_wFrom);
            hfw1_graph.addSeries(hfw1_wTo);
            hfw1_graph.addSeries(hfw1_nFrom);
            hfw1_graph.addSeries(hfw1_nTo);
            hfw1_graph.addSeries(hfw1_oFrom);
            hfw1_graph.addSeries(hfw1_oTo);
            hfw1_graph.addSeries(hfw1_o);
            hfw1_graph.addSeries(hfw1);

            muac_graph.addSeries(muac_low);
            muac_graph.addSeries(muac_mid);
            muac_graph.addSeries(muac_mid2);
            muac_graph.addSeries(muac_high);
            muac_graph.addSeries(muac);


            //colors
            wfa_boys_su.setColor(Color.RED);
            wfa_boy_uFrom.setColor(Color.YELLOW);
            wfa_boys_uTo.setColor(Color.YELLOW);
            wfa_boys_nFrom.setColor(Color.GREEN);
            wfa_boys_nTo.setColor(Color.GREEN);
            wfa_boys_o.setColor(Color.BLACK);
            wfa_boys.setColor(Color.BLUE);

            hfa_boys_ss.setColor(Color.RED);
            hfa_boy_sFrom.setColor(Color.YELLOW);
            hfa_boys_sTo.setColor(Color.YELLOW);
            hfa_boys_nFrom.setColor(Color.GREEN);
            hfa_boys_nTo.setColor(Color.GREEN);
            hfa_boys_T.setColor(Color.BLACK);
            hfa_boys.setColor(Color.BLUE);

            wfa_girls_su.setColor(Color.RED);
            wfa_girls_uFrom.setColor(Color.YELLOW);
            wfa_girls_uTo.setColor(Color.YELLOW);
            wfa_girls_nFrom.setColor(Color.GREEN);
            wfa_girls_nTo.setColor(Color.GREEN);
            wfa_girls_o.setColor(Color.BLACK);
            wfa_girls.setColor(Color.BLUE);

            hfa_girls_ss.setColor(Color.RED);
            hfa_girls_sFrom.setColor(Color.YELLOW);
            hfa_girls_sTo.setColor(Color.YELLOW);
            hfa_girls_nFrom.setColor(Color.GREEN);
            hfa_girls_nTo.setColor(Color.GREEN);
            hfa_girls_T.setColor(Color.BLACK);
            hfa_girls.setColor(Color.BLUE);



            hfw_sw.setColor(Color.RED);
            hfw_wFrom.setColor(Color.YELLOW);
            hfw_wTo.setColor(Color.YELLOW);
            hfw_nFrom.setColor(Color.GREEN);
            hfw_nTo.setColor(Color.GREEN);
            hfw_oFrom.setColor(Color.DKGRAY);
            hfw_oTo.setColor(Color.DKGRAY);
            hfw_o.setColor(Color.BLACK);

            hfw1_sw.setColor(Color.RED);
            hfw1_wFrom.setColor(Color.YELLOW);
            hfw1_wTo.setColor(Color.YELLOW);
            hfw1_nFrom.setColor(Color.GREEN);
            hfw1_nTo.setColor(Color.GREEN);
            hfw1_oFrom.setColor(Color.DKGRAY);
            hfw1_oTo.setColor(Color.DKGRAY);
            hfw1_o.setColor(Color.BLACK);


            muac_low.setColor(Color.RED);
            muac_mid.setColor(Color.GREEN);
            muac_mid2.setColor(Color.GREEN);
            muac_high.setColor(Color.BLACK);

            muac.setDrawBackground(true);


        }catch (Exception ee){
            Toast.makeText(getContext(),ee.getMessage(),Toast.LENGTH_LONG).show();
        }
       wfa_graph.getLegendRenderer().setVisible(true);
        wfa_boys_su.setTitle("Below - Severely Underweight");
        wfa_boy_uFrom.setTitle("Above - Underweight");
        wfa_boys_uTo.setTitle("Below - Underweight");
        wfa_boys_nFrom.setTitle("Above - Normal");
        wfa_boys_nTo.setTitle("Below - Normal");
        wfa_boys_o.setTitle("Above - Overweight");
        wfa_boys.setTitle("Child Weight");
        wfa_graph.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);
        wfa_graph.getLegendRenderer().setWidth(12);

        hfa_graph.getLegendRenderer().setVisible(true);
        hfa_boys_ss.setTitle("Below - Severely Stunted");
        hfa_boy_sFrom.setTitle("Above -Stunted");
        hfa_boys_sTo.setTitle("Below - Stunted");
        hfa_boys_nFrom.setTitle("Above - Normal");
        hfa_boys_nTo.setTitle("Below - Normal");
        hfa_boys_T.setTitle("Above - Tall");
        hfa_graph.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);

        //viewports
        Viewport viewport_wfa = wfa_graph.getViewport();
        viewport_wfa.setScrollable(true);
        viewport_wfa.setScalableY(true);
        viewport_wfa.setScalable(true);

        viewport_wfa.setYAxisBoundsManual(true);
        viewport_wfa.setXAxisBoundsManual(true);
        viewport_wfa.setMinY(0);
        viewport_wfa.setMaxY(10);
        viewport_wfa.setMinX(0);
        viewport_wfa.setMaxX(10);

        Viewport viewport_hfa = hfa_graph.getViewport();
        viewport_hfa.setScrollable(true);
        viewport_hfa.setScalableY(true);
        viewport_hfa.setScalable(true);


        viewport_hfa.setYAxisBoundsManual(true);
        viewport_hfa.setXAxisBoundsManual(true);
        viewport_hfa.setMinY(0);
        viewport_hfa.setMaxY(100);
        viewport_hfa.setMinX(0);
        viewport_hfa.setMaxX(10);

        Viewport viewport_hfw = hfw_graph.getViewport();
        viewport_hfw.setScrollable(true);
        viewport_hfw.setScalableY(true);
        viewport_hfw.setScalable(true);

        viewport_hfw.setYAxisBoundsManual(true);
        viewport_hfw.setXAxisBoundsManual(true);
        viewport_hfw.setMinY(0);
        viewport_hfw.setMaxY(10);
        viewport_hfw.setMinX(45);
        viewport_hfw.setMaxX(70);

        Viewport viewport_hfw1 = hfw1_graph.getViewport();
        viewport_hfw1.setScrollable(true);
        viewport_hfw1.setScalableY(true);
        viewport_hfw1.setScalable(true);


        viewport_hfw1.setYAxisBoundsManual(true);
        viewport_hfw1.setXAxisBoundsManual(true);
        viewport_hfw1.setMinY(0);
        viewport_hfw1.setMaxY(10);
        viewport_hfw1.setMinX(65);
        viewport_hfw1.setMaxX(100);

        Viewport muacport = muac_graph.getViewport();
        muacport.setScrollable(true);
        muacport.setScalableY(true);
        muacport.setScalable(true);

        muacport.setYAxisBoundsManual(true);
        muacport.setXAxisBoundsManual(true);
        muacport.setMinY(10);
        muacport.setMaxY(14);
        muacport.setMinX(6);
        muacport.setMaxX(75);




        TabLayout tabs = (TabLayout)getActivity().findViewById(R.id.tab_summary);
        tabs.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case 0:
                        wfa_graph.setVisibility(View.VISIBLE);
                        break;
                    case 1:
                        hfa_graph.setVisibility(View.VISIBLE);
                        break;
                    case 2:
                        hfw_graph.setVisibility(View.VISIBLE);
                        card.setVisibility(View.VISIBLE);
                        hfw_graph.setTitle("Height for weight 0 - 23 months");
                        break;
                    case 3:
                        muac_graph.setVisibility(View.VISIBLE);
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case 0:
                        wfa_graph.setVisibility(View.GONE);
                        break;
                    case 1:
                        hfa_graph.setVisibility(View.GONE);
                        break;
                    case 2:
                        hfw_graph.setVisibility(View.GONE);
                        card.setVisibility(View.GONE);
                        break;
                    case 3:
                        muac_graph.setVisibility(View.GONE);
                        break;
                }
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        wfa_graph.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter(){
            @Override
                    public String formatLabel(double value, boolean isValueX){
                if(isValueX){
                    return super.formatLabel(value,isValueX);

                }
                return super.formatLabel(value,isValueX);

            }
        });



}
public class agesss{
    Integer ag;
    public  agesss(Integer ag){
        this.ag = ag;
        }

}

    }


