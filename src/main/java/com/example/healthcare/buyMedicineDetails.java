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

public class buyMedicineDetails extends AppCompatActivity {
    TextView tvPackageName,tvMedicinecost;
    EditText edDetails;
    Button btnBack,btnAddToCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_medicine_details);

        tvPackageName=findViewById(R.id.TitlebuyMedicine);
        edDetails=findViewById(R.id.editTextbuyMedicineMultiLine);
        edDetails.setKeyListener(null);
        tvMedicinecost=findViewById(R.id.Medicinecost);
        btnBack=findViewById(R.id.MedicineBack);
        btnAddToCart=findViewById(R.id.MedicineGocard);

        Intent intent=getIntent();
        tvPackageName.setText(intent.getStringExtra("text1"));
        edDetails.setText(intent.getStringExtra("text2"));
        tvMedicinecost.setText("Total cost:"+intent.getStringExtra("text3")+"/-");

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(buyMedicineDetails.this,buyMedicineDetails.class));
            }
        });
        btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences=getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username = sharedPreferences.getString("username","").toString();
                String product=tvPackageName.getText().toString();
                float price = Float.parseFloat(intent.getStringExtra("text3").toString());

                database db=new database(getApplicationContext(),"healthcare",null,1);
                if(db.CheckCart(username,product)==1){
                    Toast.makeText(getApplicationContext(),"Product Already added",Toast.LENGTH_SHORT).show();
                    
                }else{
                    db= new database(getApplicationContext(),"healthcare",null,1);
                    db.addCart(username,product,price,"medicine");
                    Toast.makeText(getApplicationContext(),"Record insert to cart",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(buyMedicineDetails.this,buyMedicine.class));
                }
            }
        });
    }
}