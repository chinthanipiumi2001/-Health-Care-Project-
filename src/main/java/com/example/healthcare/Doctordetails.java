package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class Doctordetails extends AppCompatActivity {
    private String [][] doctordetails1= {
            {"Doctor Name : Ajith Gunaseena","Hospital Address: Asiri Hospital","Exp:5 yrs","Mobile:077395361","600"},
            {"Doctor Name : Nimal karunanayaka","Hospital Address: Asiri Hospital","Exp:15 yrs","Mobile:0783479966","900"},
            {"Doctor Name : S. Perera","Hospital Address: Colombo Hospital","Exp:8 yrs","Mobile:077100231","300"},
            {"Doctor Name : Kamal Frenando","Hospital Address: Asiri Hospital","Exp:6 yrs","Mobile:0712208222","500"},
            {"Doctor Name : Ashok D","Hospital Address: Delman Hospital","Exp:7 yrs","Mobile:070199865","600"},
    };
    private String [][] doctordetails2= {
            {"Doctor Name : A Gunaseena","Hospital Address: Asiri Hospital","Exp:5 yrs","Mobile:077395361","600"},
            {"Doctor Name : N karunanayaka","Hospital Address: Asiri Hospital","Exp:15 yrs","Mobile:0783479966","900"},
            {"Doctor Name : Saman Perera","Hospital Address: Colombo Hospital","Exp:8 yrs","Mobile:077100231","300"},
            {"Doctor Name : Kamal Frenando","Hospital Address: Asiri Hospital","Exp:6 yrs","Mobile:0712208222","500"},
            {"Doctor Name : nimal D","Hospital Address: Delman Hospital","Exp:7 yrs","Mobile:070199865","600"},
    };
    private String [][] doctordetails3= {
            {"Doctor Name : Amal Gunaseena","Hospital Address: Asiri Hospital","Exp:5 yrs","Mobile:077395361","600"},
            {"Doctor Name : Nimal Frenando","Hospital Address: Asiri Hospital","Exp:15 yrs","Mobile:0783479966","900"},
            {"Doctor Name : Lalith Perera","Hospital Address: Colombo Hospital","Exp:8 yrs","Mobile:077100231","300"},
            {"Doctor Name : Kamal Frenando","Hospital Address: Asiri Hospital","Exp:6 yrs","Mobile:0712208222","500"},
            {"Doctor Name : Kaushalya W","Hospital Address: Delman Hospital","Exp:7 yrs","Mobile:070199865","600"},
    };
    private String [][] doctordetails4= {
            {"Doctor Name :  p Gunaseena","Hospital Address: Asiri Hospital","Exp:5 yrs","Mobile:077395361","600"},
            {"Doctor Name : Nirmani karunanayaka","Hospital Address: Asiri Hospital","Exp:15 yrs","Mobile:0783479966","900"},
            {"Doctor Name : SP Perera","Hospital Address: Colombo Hospital","Exp:8 yrs","Mobile:077100231","300"},
            {"Doctor Name : K Frenando","Hospital Address: Asiri Hospital","Exp:6 yrs","Mobile:0712208222","500"},
            {"Doctor Name : Ashok Amarasingha","Hospital Address: Delman Hospital","Exp:7 yrs","Mobile:070199865","600"},
    };
    private String [][] doctordetails5= {
            {"Doctor Name : AP Gunaseena","Hospital Address: Asiri Hospital","Exp:5 yrs","Mobile:077395361","600"},
            {"Doctor Name : NS karunanayaka","Hospital Address: Asiri Hospital","Exp:15 yrs","Mobile:0783479966","900"},
            {"Doctor Name : SJ. Perera","Hospital Address: Colombo Hospital","Exp:8 yrs","Mobile:077100231","300"},
            {"Doctor Name : Vimal Frenando","Hospital Address: Asiri Hospital","Exp:6 yrs","Mobile:0712208222","500"},
            {"Doctor Name : Eshani D","Hospital Address: Delman Hospital","Exp:7 yrs","Mobile:070199865","600"},
    };


    TextView tv;
    Button btn;
    String [][] Doctordetails ={};
    HashMap<String,String>item;
    ArrayList list;
    SimpleAdapter sa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctordetails);

        tv=findViewById(R.id.textViewdoctortitle);
        btn = findViewById(R.id.Back);

        Intent it = getIntent();
        String title = it.getStringExtra("title");
        tv.setText(title);
        if(title.compareTo("family physicians")==0)
            Doctordetails = doctordetails1;
        else
        if(title.compareTo("dietician")==0)
            Doctordetails = doctordetails2;
        else
        if(title.compareTo("dentist")==0)
            Doctordetails = doctordetails3;
        else
        if(title.compareTo("Surgeon")==0)
            Doctordetails = doctordetails4;

        else
            Doctordetails = doctordetails5;


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Doctordetails.this,FindDoctor.class));
            }
        });
        list=new ArrayList();
        for(int i=0;i<Doctordetails.length;i++){
            item=new HashMap<String,String>();
            item.put ("line1",Doctordetails[i][0]);
            item.put ("line2",Doctordetails[i][1]);
            item.put ("line3",Doctordetails[i][2]);
            item.put ("line4",Doctordetails[i][3]);
            item.put("line5","Cons Fees;"+Doctordetails[1][4]+"/-");
            list.add(item);
        }
        sa = new SimpleAdapter(this,list,
                R.layout.multilines,
                new String[]{"line1","line2","line3","line4","line5"},
                new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e}
                );
        ListView lst = findViewById(R.id.ListViewdoctordetails);
        lst.setAdapter(sa);

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                Intent it = new Intent(Doctordetails.this,BookingAppoinment.class);

                it.putExtra("text1",title);
                it.putExtra("text2",Doctordetails[i][0]);
                it.putExtra ("text3",Doctordetails[i][1]);
                it.putExtra ("text4",Doctordetails[i][3]);
                it.putExtra ("text5",Doctordetails[i][4]);
                startActivity(it);

            }
        });
    }
}