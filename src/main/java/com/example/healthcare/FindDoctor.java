package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class FindDoctor extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_doctor);

        CardView exit = findViewById(R.id.cardFDback);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FindDoctor.this,home.class));
            }
        });
        CardView familyphysician = findViewById(R.id.cardFDfamilyPhysician);
        familyphysician.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent (FindDoctor.this,Doctordetails.class);
                it.putExtra("title","familyphysicians");
                startActivity(it);


            }
        });
        CardView dietician = findViewById(R.id.cardFDdietician);
        dietician.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent (FindDoctor.this,Doctordetails.class);
                it.putExtra("title","dietician");
                startActivity(it);


            }
        });
        CardView dentist = findViewById(R.id.cardFDdentist);
        dentist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent (FindDoctor.this,Doctordetails.class);
                it.putExtra("title","dentist");
                startActivity(it);


            }
        });
        CardView surgeon = findViewById(R.id.cardFDsurgon);
        surgeon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent (FindDoctor.this,Doctordetails.class);
                it.putExtra("title","dentist");
                startActivity(it);


            }
        });
        CardView cardiologists = findViewById(R.id.cardFDCardiologists);
        cardiologists.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent (FindDoctor.this,Doctordetails.class);
                it.putExtra("title","cardiologists");
                startActivity(it);


            }
        });

    }
}