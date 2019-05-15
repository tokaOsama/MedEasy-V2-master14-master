package com.example.nihal.medeasy;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Random;

import cn.iwgang.countdownview.CountdownView;

public class DoctorLogin extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference myRef;
    EditText loginPhone, loginPassWord;
    Button login;
    String flag = "0";
    ProgressDialog progressDialog;
    String mobile="",password="";
    CountdownView mCvCountdownView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_login);
        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        loginPassWord = findViewById(R.id.loginPassWord);
        loginPhone = findViewById(R.id.loginPhone);
        login = findViewById(R.id.login);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Users");

         mCvCountdownView = (CountdownView)findViewById(R.id.cv_countdownViewTest1);
        //mCvCountdownView.start(1800000); // Millisecond
        mCvCountdownView.setOnCountdownEndListener(new CountdownView.OnCountdownEndListener() {
            @Override
            public void onEnd(CountdownView cv) {
                //Toast.makeText(DoctorLogin.this, "teeeeeesssssssttttt", Toast.LENGTH_SHORT).show();
                generateRandomNum(mobile,password);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d("TTTT", "onClick:final result "+ check_pass("" + loginPhone.getText().toString()
                        , "" + loginPassWord.getText().toString()));
            }
        });


    }

    private void generateRandomNum(final String number, final String pass) {
        final int min = 10000;
        final int max = 99999;
        final int random = new Random().nextInt((max - min) + 1) + min;
        //Toast.makeText(this, ""+random, Toast.LENGTH_SHORT).show();
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (final DataSnapshot dataSnap : dataSnapshot.getChildren()) {

                    Log.d("TTTT", "onDataChange: " + dataSnap.getKey());
                    myRef.child("" + dataSnap.getKey()).child("Info")
                            .child("phoneNumber").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            Log.d("ttt", "onDataChange: " + dataSnapshot.getValue());
                            if ((number + "@gmail.com").equals(dataSnapshot.getValue())) {
                                myRef.child("" + dataSnap.getKey()).child("Info").child("Pass").setValue(""+random);
                                finish();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("TTT", "onCancelled: ");
            }
            //
        });


    }

    private String check_pass(final String number, final String pass) {
        mobile=number;
        password=pass;
        progressDialog.show();
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (final DataSnapshot dataSnap : dataSnapshot.getChildren()) {

                    Log.d("TTTT", "onDataChange: " + dataSnap.getKey());
                     myRef.child("" + dataSnap.getKey()).child("Info")
                            .child("phoneNumber").addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    Log.d("ttt", "onDataChange: " + dataSnapshot.getValue());
                                    if ((number + "@gmail.com").equals(dataSnapshot.getValue())) {
                                        myRef.child("" + dataSnap.getKey()).child("Info")
                                                .child("pass").addValueEventListener(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                Log.d("TTT", "onDataChange: "+dataSnapshot.getValue());
                                                if ((pass).equals(dataSnapshot.getValue()+"")) {
                                                    progressDialog.dismiss();
                                                    Log.d("TTTT", "onDataChange: trueeeeeee");
                                                    flag = "1";
                                                    Toast.makeText(DoctorLogin.this, "trueeeeeeeeeeee", Toast.LENGTH_SHORT).show();
                                                    Log.d("TTTT", "onDataChange: "+flag);
                                                    mCvCountdownView.start(5000); // Millisecond

                                                } else {
                                                    progressDialog.dismiss();

                                                    flag = "0";
                                                    Log.d("TTTT", "onDataChange: "+flag);

                                                }
                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError databaseError) {

                                            }
                                        });
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("TTT", "onCancelled: ");
            }
        });

        return flag;
    }

}
