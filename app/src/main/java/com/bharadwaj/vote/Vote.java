package com.bharadwaj.vote;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.List;

public class Vote extends AppCompatActivity {

    ToggleButton toggle;
    Button vote;
    String[] text1 = {"Select candidate", "Candidate1", "Candidate2", "Candidate3"};
    int[] val1 = {0, 1, 2, 3};
    EditText name, id;
    Spinner spinner1;
    TextView textView1;
    String value, nameC, idC;
    String TAG = "VOTE ONCREATE CHECK PERSON : ";
    int p1, p2, p3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote);
        toggle = findViewById(R.id.toggle);
        vote = findViewById(R.id.vote);

        textView1 = (TextView) findViewById(R.id.text1);
        spinner1 = (Spinner) findViewById(R.id.spinner1);
        ArrayAdapter<String> adapter1 =
                new ArrayAdapter<String>(Vote.this,
                        android.R.layout.simple_spinner_item, text1);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);
        spinner1.setOnItemSelectedListener(onItemSelectedListener1);
        name = (EditText) findViewById(R.id.name);
        nameC = name.getText().toString();
        id = (EditText) findViewById(R.id.id);
        idC = id.getText().toString();

        vote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if ((TextUtils.isEmpty(name.getText().toString()))&& (TextUtils.isEmpty(id.getText().toString()))){
                    Toast.makeText(Vote.this, "PLEASE ENTER NAME AND ID", Toast.LENGTH_LONG).show();
                }else if (toggle.isChecked()) {
                    value = (String) textView1.getText().toString();
                    Log.d(TAG, value);
                    voteFunction(value);
                } else {
                    Toast.makeText(Vote.this, "YOU HAVE TO ACCEPT THE CONDITIONS", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    public void voteFunction(String value) {
        SharedPreferences preferences = getSharedPreferences("FILE_NAME", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        switch (value) {
            case "1":
                p1 = p1 + 1;
                editor.putString("p1", String.valueOf(p1));
                editor.apply();
                Log.d(TAG, String.valueOf(p1));
                break;
            case "2":
                p2 = p2 + 1;
                editor.putString("p2", String.valueOf(p2));
                editor.apply();
                Log.d(TAG, String.valueOf(p2));
                break;
            case "3":
                p3 = p3 + 1;
                editor.putString("p3", String.valueOf(p3));
                editor.apply();
                Log.d(TAG, String.valueOf(p3));
                break;
            default:
                Toast.makeText(Vote.this, "PLEASE SELECT THE CANDIDATE", Toast.LENGTH_LONG).show();
        }
    }

    AdapterView.OnItemSelectedListener onItemSelectedListener1 =
            new AdapterView.OnItemSelectedListener() {

                @Override
                public void onItemSelected(AdapterView<?> parent, View view,
                                           int position, long id) {
                    String s1 = String.valueOf(val1[position]);
                    textView1.setText(s1);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }

            };

}