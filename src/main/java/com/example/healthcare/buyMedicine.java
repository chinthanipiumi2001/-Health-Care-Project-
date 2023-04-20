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

public class buyMedicine extends AppCompatActivity {
    private String[][] packages=
            {
                    {"Uprise-03 1000IU Capsule","","","","50"},
                    {"HealthVit Chronium picolinate 200mcg Capsule","","","","305"},
                    {"Vitamin B Complex Capsules","","","","448"},
                    {"Inlife Vitamin E Wheat Grem Oil Capsule","","","","539"},
                    {"Dolo 650 Tablet","","","","30"},
                    {"Crocin 650 Advance Tablet","","","","50"},
                    {"Strepsils Medicated Lozenges for sore Throat","","","","40"},
                    {"Feronia -XT Tablet","","","","130"}
            };
    private String []packagedetails={
            "Building and keeping the bones and teeth strong\n"+
                    "Reducing Fatigue/strees and muscular pains\n"+
                    "Boosting immunity and increasing resistance againt infection ",
            "Chromium is an essential trace mineral that plays an important role in helping insulin regulation\n"+
                    "provides relif from vitamin B dificiencies\n"+
                    "Helps in formation of red blood cells\n"+
                    "Maintains healthy nervous system",
            "If promotes health as well as skin benifit.\n"+
                    "It helps reduce skin blemish and pigmentation\n"+
                    "It act as safeguard the skin from the harsh UVA and UVB sun rays\n",
    };
    HashMap<String,String>item;
    ArrayList list;
    SimpleAdapter sa;
    ListView lst;
    Button BtnBuyTGoTocard,BackBuyMedicine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_medicine);

        lst =findViewById(R.id.ListViewLTbuyMedicine);
        BtnBuyTGoTocard=findViewById(R.id.BtnBuyTGoTocard);
        BackBuyMedicine=findViewById(R.id.BackBuyMedicine);

        BtnBuyTGoTocard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(buyMedicine.this,CartBuyMedicine.class));
            }
        });

        BackBuyMedicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(buyMedicine.this,home.class));
            }
        });
        list=new ArrayList();
        for(int i=0;i<packages.length;i++){
            item=new HashMap<String,String>();
            item.put("line1",packages[i][0]);
            item.put("line2",packages[i][1]);
            item.put("line3",packages[i][2]);
            item.put("line4",packages[i][3]);
            item.put("line5","Total Cost:"+packages[i][4]+"/-");
            list.add(item);
        }

        sa = new SimpleAdapter(this,list,
                R.layout.multilines,
                new String[]{"line1","line2","line3","line4","line5"},
                new int[] {R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e});
        lst.setAdapter(sa);

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                Intent it= new Intent(buyMedicine.this,buyMedicineDetails.class);
                it.putExtra("text1",packages[i][0]);
                it.putExtra("text2",packagedetails[i]);
                it.putExtra("text3",packages[i][4]);

                startActivity(it);

            }
        });

        }
    }
