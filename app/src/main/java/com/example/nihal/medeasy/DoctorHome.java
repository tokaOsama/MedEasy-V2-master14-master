package com.example.nihal.medeasy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.nihal.medeasy.Adapters.CategoriesAdapter;
import com.example.nihal.medeasy.Adapters.DoctorAdapter;
import com.example.nihal.medeasy.Models.DoctorHomeModel;
import com.orhanobut.hawk.Hawk;

import java.util.ArrayList;

public class DoctorHome extends AppCompatActivity {

    DoctorAdapter doctorAdapter;
    ArrayList<DoctorHomeModel> docModel ;
    RecyclerView recyclerView ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_home);
        docModel=new ArrayList<>();
        recyclerView=findViewById(R.id.recyclerView);
//        docModel.add(new DoctorHomeModel(R.drawable.doctor_medical_history,"Medical History"));
//        docModel.add(new DoctorHomeModel(R.drawable.doctor_medical_history,"Medicine"));
//        docModel.add(new DoctorHomeModel(R.drawable.doctor_medical_history,"Analysis"));
//        docModel.add(new DoctorHomeModel(R.drawable.doctor_medical_history,"Profile"));
//        docModel.add(new DoctorHomeModel(R.drawable.doctor_medical_history,"Surgeries"));
//        docModel.add(new DoctorHomeModel(R.drawable.doctor_medical_history,"Medical Prescriptions"));
        doctorAdapter = new DoctorAdapter(docModel, this, new DoctorAdapter.OnItemClick() {
            @Override
            public void setOnItemClick(int position) {

                if(position==0){

                    Toast.makeText(DoctorHome.this, "0", Toast.LENGTH_SHORT).show();
                }else if(position==1){
                    Toast.makeText(DoctorHome.this, "1", Toast.LENGTH_SHORT).show();

                }
            }
        });
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setAdapter(doctorAdapter);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(Hawk.contains("Key")) {
            Hawk.delete("Key");
        }

    }
}
