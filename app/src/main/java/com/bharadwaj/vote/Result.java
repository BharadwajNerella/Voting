package com.bharadwaj.vote;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.EventLogTags;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class Result extends AppCompatActivity {

    TextView p1, p2, p3;
    PieChart piechat;
    Button reset;
    String p1value,p2value,p3value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        final SharedPreferences preferences = getSharedPreferences("FILE_NAME", Context.MODE_PRIVATE);

        reset = (Button)findViewById(R.id.reset);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("p1", "0");
                editor.putString("p2", "0");
                editor.putString("p3", "0");
                editor.commit();
            }
        });
        p1 = (TextView) findViewById(R.id.p1);
         p1value = preferences.getString("p1", "");
        p1.setText(p1value);

        p2 = (TextView) findViewById(R.id.p2);
         p2value = preferences.getString("p2", "");
        p2.setText(p2value);

        p3 = (TextView) findViewById(R.id.p3);
         p3value = preferences.getString("p3", "");
        p3.setText(p3value);


        piechat = (PieChart) findViewById(R.id.piechat);
        piechat.setUsePercentValues(true);

//        Description description = new Description();
//        description.setText();

        List<PieEntry> values = new ArrayList<>();
        int a = Integer.parseInt(p1value);
        int b = Integer.parseInt(p2value);
        int c = Integer.parseInt(p3value);

        values.add(new PieEntry(a, "Candidate1"));
        values.add(new PieEntry(b, "Candidate2"));
        values.add(new PieEntry(c, "Candidate3"));

        PieDataSet pieDataSet = new PieDataSet(values, "Candidates Check Result");
        PieData pieData = new PieData(pieDataSet);

        piechat.setData(pieData);

        final int[] MY_COLORS = {
                Color.rgb(0, 255, 255),
                Color.rgb(65, 105, 225),
                Color.rgb(225, 225, 225)
        };

        ArrayList<Integer> colors = new ArrayList<>();

        for (int q : MY_COLORS) colors.add(q);

        pieDataSet.setColors(colors);

    }
}