package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LabtestDetails extends AppCompatActivity {
    TextView textViewpackages,textcost;
    EditText edDetails;
    Button Gocard,Back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_labtest_details);

        textViewpackages=findViewById(R.id.textViewpackages);
        textcost=findViewById(R.id.textMedicinecost);
        edDetails=findViewById(R.id.editTextLDTextMultiLine);
        Gocard=findViewById(R.id.ButtonLTGoTocard);
        Back=findViewById(R.id.btnBack);

        edDetails.setKeyListener(null);

        Intent intent=getIntent();
        textViewpackages.setText(intent.getStringExtra("text1"));
        edDetails.setText(intent.getStringExtra("text2"));
        textcost.setText("text cost : "+intent.getStringExtra("text3")+"/-");

        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LabtestDetails.this,Labtest.class));
            }
        });
        Gocard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username=sharedPreferences.getString("username","").toString();
                String product=textViewpackages.getText().toString();
                float price = Float.parseFloat(intent.getStringExtra("text3").toString());

                database db=new database(getApplicationContext(),"Healthcare",null,1);

                if(db.CheckCart(username,product)==1){
                    Toast.makeText(getApplicationContext(),"product Already added",Toast.LENGTH_SHORT).show();
                }else{
                    db.addCart(username,product,price,"lab");
                    Toast.makeText(getApplicationContext(),"Record Inserted to cart",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LabtestDetails.this,Labtest.class));
                }

            }
        });

    }
}