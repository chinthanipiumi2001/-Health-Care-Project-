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

public class HealthArticles extends AppCompatActivity {
    private String [][] health_details =
            {
                    {"Walking Daily","","","","Click more details"},
                    {"Home care of Covid 19","","","","Click more Details"},
                    {"Stop smoking","","","","Click more Details"},
                    {"Menstrual Cramps","","","","Click more Details"},
                    {"Healthy Gut","","","","Click more Details"},
            };
    private int [] images={
            R.drawable.health1,
            R.drawable.health2,
            R.drawable.health3,
            R.drawable.health4,
            R.drawable.health5,

    };
    HashMap<String,String>item;
    ArrayList list;
    SimpleAdapter sa;

    Button BackArticlebtn;
    ListView lst;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_articles);

        lst=findViewById(R.id.HealthArticlesDetails);
        BackArticlebtn=findViewById(R.id.BackArticlebtn);

        BackArticlebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HealthArticles.this,home.class));
            }
        });
        list =new ArrayList();
        for(int i=0;i<health_details.length;i++){
            item= new HashMap<String, String>();
            item.put("line1",health_details[i][0]);
            item.put("line2",health_details[i][1]);
            item.put("line3",health_details[i][2]);
            item.put("line5",health_details[i][3]);
            item.put("line5",health_details[i][4]);
            list.add(item);

            lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                    Intent it=new Intent(HealthArticles.this,HealthArticleDetails.class);
                    it.putExtra("text1",health_details[i][0]);
                    it.putExtra("text2",images[i]);
                    startActivity(it);
                }
            });
        }
    }
}