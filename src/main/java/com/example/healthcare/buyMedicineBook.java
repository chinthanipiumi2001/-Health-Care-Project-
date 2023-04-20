package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class buyMedicineBook extends AppCompatActivity {
    EditText edname,edaddress,edcontact,edpincode;
    Button BMbtnBooking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_medicine_book);

        edname= findViewById(R.id.BMBookFullname);
        edaddress=findViewById(R.id.BMBookAddress);
        edcontact=findViewById(R.id.BMBookcontactno);
        edpincode= findViewById(R.id.BMBookPincode);
        BMbtnBooking=findViewById(R.id.BMBtnBooking);

        Intent Intent =getIntent();
        String[] price = getIntent().getStringArrayExtra("price").toString().split(java.util.regex.Pattern.quote(":"));
        String date= getIntent().getStringExtra("date");
        //String time=getIntent().getStringExtra("time");

        BMbtnBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences=getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username=sharedPreferences.getString("username","").toString();

                database db = new database(getApplicationContext(),"healthcare",null,1);
                db.addOrder(username,edname.getText().toString(),edaddress.getText().toString(),edcontact.getText().toString(),Integer.parseInt(edpincode.getText().toString()),date.toString(),"",Float.parseFloat(price[1].toString()),"medicine");
                db.removeCart(username,"medicine");
                Toast.makeText(getApplicationContext(),"Your Booking is done successfully",Toast.LENGTH_LONG);
                startActivity(new Intent(buyMedicineBook.this,home.class));
            }
        });
    }
}