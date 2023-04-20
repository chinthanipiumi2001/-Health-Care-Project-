package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class Labtest extends AppCompatActivity {
    private String[][] packages=
            {
                    {"pakage 1: Full body Checkup","","","","999"},
                    {"pakage 2: Blood glucose Fasting","","","","299"},
                    {"pakage 3: Covid 19 Antibody - 1gG","","","","899"},
                    {"pakage 4: Thyroid Check","","","","499"},
                    {"pakage 5: Imunity Check","","","","699"}
            };
    private String[] package_details={
            "Blood Glucose Fasting\n"+
                    "complete Hemogram\n"+
                    "HbA1c\n"+
                    "Iron Studies\n"+
                    "Kidney function Test\n"+
                    "LDH Lactate Dehydrogenase, Serum\n"+
                    "Lipid Profile\n"+
                    "Liver Function Test",
            "Blood Glucose Fasting",
            "Covid-19 Antibody -1gG",
            "Thyroid profile-Total (T3,T4 & TSH Ultra-sensitive",
            "Complete Hemogram\n"+
                    "CRP (C Reactive protetin)Quantitative, Serum\n"+
                    "Iron Studies\n"+
                    "Kidney Function Test\n"+
                    "Vitamin D Total-25 Hydroxy\n"+
                    "Liver Function Test\n"+
                    "Lipid Profile"
    };
    HashMap<String,String>item;
    ArrayList list;
    SimpleAdapter sa;
    Button Gocard,Back;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_labtest);

        Gocard=findViewById(R.id.ButtonGoTocard);
        Back=(findViewById(R.id.btnBack));
        listView=(findViewById(R.id.ListViewLT));

        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Labtest.this,home.class));
            }
        });
        list=new ArrayList();
        for(int i=0;i<packages.length;i++){
            item=new HashMap<String, String>();
            item.put( "line1",packages[i][0]);
            item.put( "line2",packages[i][1]);
            item.put( "line3",packages[i][2]);
            item.put( "line4",packages[i][3]);
            item.put("line5","Total Cost:"+packages[i][4]+"/-");
            list.add(item);

        }
        sa = new SimpleAdapter(this,list,
                R.layout.multilines,
                new String[]{"line1","line2","line3","line4","line5"},
                new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e});
        listView.setAdapter(sa);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                Intent it=new Intent(Labtest.this,LabtestDetails.class);

                it.putExtra("text1",packages[i][0]);
                it.putExtra("text2",package_details[i]);
                it.putExtra("text3",packages[i][4]);
                startActivity(it);
            }
        });

        Gocard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Labtest.this,cartLab.class));
            }
        });
    }
}