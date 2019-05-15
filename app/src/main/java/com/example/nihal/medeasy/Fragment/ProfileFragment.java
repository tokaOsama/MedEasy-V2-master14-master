package com.example.nihal.medeasy.Fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nihal.medeasy.Models.UserModel;
import com.example.nihal.medeasy.R;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IFillFormatter;
import com.github.mikephil.charting.interfaces.dataprovider.LineDataProvider;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    private com.github.mikephil.charting.charts.LineChart chart;
    private com.github.mikephil.charting.charts.LineChart chart1;
    CircleImageView profile_image;
    TextView name,gender,weight,height,age,blood_type,relation;

    private DatabaseReference mDatabase;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_profile, container, false);
        mDatabase = FirebaseDatabase.getInstance().getReference();

        //declartion
        chart = view.findViewById(R.id.chart1);
        chart1 = view.findViewById(R.id.chart1);
        profile_image=view.findViewById(R.id.profile_image);
        name=view.findViewById(R.id.name);
        gender=view.findViewById(R.id.gender);
        weight=view.findViewById(R.id.weight);
        height=view.findViewById(R.id.height);
        age=view.findViewById(R.id.age);
        blood_type=view.findViewById(R.id.blood_type);
        relation=view.findViewById(R.id.relation);
        //returnData();
        draw_chart1();
        draw_chart2();
        return view;
    }
    private void returnData(){
        mDatabase.child("").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    UserModel userModel=dataSnapshot.getValue(UserModel.class);
                    name.setText(userModel.getUserName()+"");
                    gender.setText(userModel.getUserName()+"");
                    weight.setText(userModel.getWeight());
                    height.setText(userModel.getHeight());
                    age.setText((Calendar.getInstance().get(Calendar.YEAR))-Integer.parseInt(userModel.getYearOfBirth()));
                    //blood_type.setText(userModel.get());
                    //relation.setText(userModel.get());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void draw_chart1(){
        chart.setViewPortOffsets(0, 0, 0, 0);
        chart.setBackgroundResource(R.color.colorPrimary);


        // no description text
        chart.getDescription().setEnabled(false);

        // enable touch gestures
        chart.setTouchEnabled(true);

        // enable scaling and dragging
        chart.setDragEnabled(true);
        chart.setScaleEnabled(true);

        // if disabled, scaling can be done on x- and y-axis separately
        chart.setPinchZoom(false);

        chart.setDrawGridBackground(false);
        chart.setMaxHighlightDistance(300);

        XAxis x = chart.getXAxis();
        x.setEnabled(false);

        YAxis y = chart.getAxisLeft();
        // y.setTypeface(tfLight);
        y.setLabelCount(6, false);
        y.setTextColor(Color.WHITE);
        y.setPosition(YAxis.YAxisLabelPosition.INSIDE_CHART);
        y.setDrawGridLines(false);
        y.setAxisLineColor(Color.WHITE);

        chart.getAxisRight().setEnabled(true);



        chart.getLegend().setEnabled(true);

        chart.animateXY(2000, 2000);

        // don't forget to refresh the drawing
        setData1(11,12);

        chart.invalidate();

    }
    private void draw_chart2(){
        chart1.setViewPortOffsets(0, 0, 0, 0);
        chart1.setBackgroundResource(R.color.colorPrimary);
        // no description text
        chart1.getDescription().setEnabled(false);
        // enable touch gestures
        chart1.setTouchEnabled(true);
        // enable scaling and dragging
        chart1.setDragEnabled(true);
        chart1.setScaleEnabled(true);
        // if disabled, scaling can be done on x- and y-axis separately
        chart1.setPinchZoom(false);
        chart1.setDrawGridBackground(false);
        chart1.setMaxHighlightDistance(300);
        XAxis x = chart1.getXAxis();
        x.setEnabled(false);
        YAxis y = chart1.getAxisLeft();
        // y.setTypeface(tfLight);
        y.setLabelCount(6, false);
        y.setTextColor(Color.WHITE);
        y.setPosition(YAxis.YAxisLabelPosition.INSIDE_CHART);
        y.setDrawGridLines(false);
        y.setAxisLineColor(Color.WHITE);

        chart1.getAxisRight().setEnabled(true);



        chart1.getLegend().setEnabled(true);

        chart1.animateXY(2000, 2000);

        // don't forget to refresh the drawing
        setData2(11,12);

        chart1.invalidate();

    }

    private void setData1(int count, float range) {

        ArrayList<Entry> values = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            float val = (float) (Math.random() * (range + 1)) + 20;
            values.add(new Entry(i, val));
        }

        LineDataSet set1;

        if (chart.getData() != null &&
                chart.getData().getDataSetCount() > 0) {
            set1 = (LineDataSet) chart.getData().getDataSetByIndex(0);
            set1.setValues(values);
            chart.getData().notifyDataChanged();
            chart.notifyDataSetChanged();
        } else {
            // create a dataset and give it a type
            set1 = new LineDataSet(values, "DataSet 1");
            set1.setMode(LineDataSet.Mode.CUBIC_BEZIER);
            set1.setCubicIntensity(0.2f);
            set1.setDrawFilled(true);
            set1.setLineWidth(1.8f);
            set1.setCircleRadius(4f);
            set1.setCircleColor(Color.WHITE);
            set1.setHighLightColor(Color.rgb(244, 117, 117));
            set1.setColor(Color.WHITE);
            set1.setFillColor(Color.WHITE);
            set1.setFillAlpha(100);
            set1.setDrawHorizontalHighlightIndicator(false);
            set1.setFillFormatter(new IFillFormatter() {
                @Override
                public float getFillLinePosition(ILineDataSet dataSet, LineDataProvider dataProvider) {
                    return chart.getAxisLeft().getAxisMinimum();
                }
            });
            set1.setDrawCircles(true);
            chart.animateXY(2000, 2000);
            // create a data object with the data sets
            LineData data = new LineData(set1);
            //data.setValueTypeface(tfLight);
            data.setValueTextSize(9f);
            data.setDrawValues(true);
            // set data
            chart.setData(data);
        }
    }
    private void setData2(int count, float range) {

        ArrayList<Entry> values = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            float val = (float) (Math.random() * (range + 1)) + 20;
            values.add(new Entry(i, val));
        }

        LineDataSet set1;

        if (chart1.getData() != null &&
                chart1.getData().getDataSetCount() > 0) {
            set1 = (LineDataSet) chart1.getData().getDataSetByIndex(0);
            set1.setValues(values);
            chart1.getData().notifyDataChanged();
            chart1.notifyDataSetChanged();
        } else {
            // create a dataset and give it a type
            set1 = new LineDataSet(values, "DataSet 1");
            set1.setMode(LineDataSet.Mode.CUBIC_BEZIER);
            set1.setCubicIntensity(0.2f);
            set1.setDrawFilled(true);
            set1.setLineWidth(1.8f);
            set1.setCircleRadius(4f);
            set1.setCircleColor(Color.WHITE);
            set1.setHighLightColor(Color.rgb(244, 117, 117));
            set1.setColor(Color.WHITE);
            set1.setFillColor(Color.WHITE);
            set1.setFillAlpha(100);
            set1.setDrawHorizontalHighlightIndicator(false);
            set1.setFillFormatter(new IFillFormatter() {
                @Override
                public float getFillLinePosition(ILineDataSet dataSet, LineDataProvider dataProvider) {
                    return chart1.getAxisLeft().getAxisMinimum();
                }
            });
            set1.setDrawCircles(true);
            chart1.animateXY(2000, 2000);
            // create a data object with the data sets
            LineData data = new LineData(set1);
            //data.setValueTypeface(tfLight);
            data.setValueTextSize(9f);
            data.setDrawValues(true);
            // set data
            chart1.setData(data);
        }
    }
}
