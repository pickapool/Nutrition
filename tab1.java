package com.example.lloyd.healthnutritiontool;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.net.URI;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.zip.Inflater;


public class tab1 extends Fragment {
TextView view;
DatabaseReference databaseReference,databaseReference1;
TextView full_name;
TextView gender;
TextView date_of_birth;
TextView guardian;
TextView contact_num;
TextView addres;
TextView house_num;

    ImageView pick;
    ImageView profilepic;
    private StorageReference storageReference;
private static final int GALLERY_INTENT = 2;
ProgressDialog progressDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle saveInstanceState){
        View view = inflater.inflate(R.layout.tab1,container,false);

        return view;


    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, final Intent data) {
        Intent iii = getActivity().getIntent();
        final String ids =iii.getStringExtra("the_id");
        super.onActivityResult(requestCode,resultCode,data);

        databaseReference1 = FirebaseDatabase.getInstance().getReference("Photo");


        profilepic= (ImageView) getActivity().findViewById(R.id.pic);
        if(requestCode == GALLERY_INTENT && resultCode == getActivity().RESULT_OK){
            progressDialog = new ProgressDialog(getContext());
            progressDialog.setTitle("Uploading picture...");
            progressDialog.show();

            Uri uri = data.getData();
            StorageReference filepath = storageReference.child("Photo").child(ids).child(uri.getLastPathSegment());

            filepath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Toast.makeText(getContext(), "Upload Finished!", Toast.LENGTH_LONG).show();

                    final Uri downuri = taskSnapshot.getDownloadUrl();
                    databaseReference1.child(ids).push().child("image").setValue(downuri.toString());
                    Picasso.with(getContext()).load(downuri).fit().centerCrop().into(profilepic);

progressDialog.dismiss();
                }
                });


        }

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        final TextView textView = (TextView) getActivity().findViewById(R.id.child_name);
        Intent iii = getActivity().getIntent();
        String value = iii.getStringExtra("value1");
        String value1 = iii.getStringExtra("brgy");
        final String ids =iii.getStringExtra("the_id");
        textView.setText(value);

        storageReference = FirebaseStorage.getInstance().getReference("Photo");
        profilepic = (ImageView)getActivity().findViewById(R.id.pic);

    pick = (ImageView)getActivity().findViewById(R.id.pic_image);
    pick.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            startActivityForResult(intent,GALLERY_INTENT);
        }
    });

    databaseReference1 = FirebaseDatabase.getInstance().getReference("Photo");

        databaseReference1.child(ids).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds: dataSnapshot.getChildren()){
                    final String get = ds.child("image").getValue(String.class);
                    profilepic = (ImageView)getActivity().findViewById(R.id.pic);
                   // Toast.makeText(getContext(),get,Toast.LENGTH_LONG).show();
                    Picasso.with(getContext()).load(get).fit().centerCrop().into(profilepic, new Callback() {
                        @Override
                        public void onSuccess() {
                            Picasso.with(getContext()).load(get).fit().centerCrop().into(profilepic);
                        }

                        @Override
                        public void onError() {
                            Picasso.with(getContext()).load(get).fit().centerCrop().into(profilepic);
                        }
                    });
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



        databaseReference = FirebaseDatabase.getInstance().getReference(value1+"_Infant_Info");
        databaseReference.orderByChild("firstname_last").equalTo(value).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    try {
                    String val1 = ds.child("firstname_last").getValue(String.class);
                    String val2 = ds.child("gender").getValue(String.class);
                    String val3 = ds.child("date_of_birth").getValue(String.class);
                    String val4 = ds.child("parent").getValue(String.class);
                    String val5 = ds.child("contact_number").getValue(String.class);
                    String val6 = ds.child("address").getValue(String.class);
                    String val7 = ds.child("house_number").getValue(String.class);

                    full_name = (TextView)getActivity().findViewById(R.id.child_name);
                    gender = (TextView)getActivity().findViewById(R.id.gen);
                    date_of_birth = (TextView)getActivity().findViewById(R.id.dob);
                    guardian = (TextView)getActivity().findViewById(R.id.guard);
                    contact_num = (TextView)getActivity().findViewById(R.id.con);
                    addres = (TextView)getActivity().findViewById(R.id.add);
                    house_num = (TextView)getActivity().findViewById(R.id.num);

                    full_name.setText(val1);
                    gender.setText(val2);
                    date_of_birth.setText(val3);
                    guardian.setText(val4);
                    contact_num.setText(val5);
                    addres.setText(val6);
                    house_num.setText(val7);


                    String mydate = DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());

                    Date date1 = new Date(val3);
                    Date date2 = new Date(mydate);

                    long difference = (long) ((date2.getTime() - date1.getTime()));
                    long cc = difference / 1000 / 60 / 60 / 24 / 30;


                    View mview = getLayoutInflater().inflate(R.layout.calculation_dialog,null);
                    String convert = String.valueOf(cc);


                }catch (Exception ee){
                    Toast.makeText(getContext(),ee.getMessage(),Toast.LENGTH_LONG).show();
                }
                }

            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });


    }

}
