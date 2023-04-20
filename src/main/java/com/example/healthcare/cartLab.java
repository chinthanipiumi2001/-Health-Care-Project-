package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class cartLab extends AppCompatActivity {
    HashMap<String,String> item;
    SimpleAdapter sa;
    TextView totalcost;
    ListView lst;
    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;
    private Button checkDate,checkTime,cardCheckout,cardBackbtn;
    private String[][]packages={};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_lab);

        checkDate.findViewById(R.id.checkDate);
        checkTime.findViewById(R.id.checkTime);
        cardCheckout.findViewById(R.id.cardCheckout);
        cardBackbtn.findViewById(R.id.cardBackbtn);
        totalcost.findViewById(R.id.totalcost);
        lst.findViewById(R.id.TextViewcard);

        SharedPreferences sharedPreferences = getSharedPreferences("shared_perfs", Context.MODE_PRIVATE);
        String usrname= sharedPreferences.getString("username","").toString();

        database db=new database(getApplicationContext(),"healthcare",null,1);

        float totalAmmount=0;
        ArrayList dbdata = db.getCardData(usrname,"lab");
        //Toast.makeText(getApplicationContext(),""+dbdata,Toast.LENGTH_LONG).show();
        packages=new String[dbdata.size()][];
        for(int i=0;i<packages.length;i++){
            packages[i]=new String[5];
        }
        for (int i=0;i<dbdata.size();i++){
            String arrData = dbdata.get(1).toString();
            String[] strdata=arrData.split(java.util.regex.Pattern.quote("$"));
            packages[i][0]=strdata[0];
            packages[i][4]="cost:"+strdata[1]+"/-";
            totalAmmount=totalAmmount+Float.parseFloat(strdata[1]);
        }
        totalcost.setText("total Cost :"+totalAmmount);

        List list = new ArrayList();
        for (int i=0;i<packages.length;i++){
            item= new HashMap<String,String>();
            item.put("line 1",packages[1][0]);
            item.put("line 2",packages[1][1]);
            item.put("line 3",packages[1][2]);
            item.put("line 4",packages[1][3]);
            item.put("line 5",packages[i][4]);
            list.add(item);
        }
        sa=new SimpleAdapter(this,list,
                R.layout.multilines,
        new String[]{"line 1","line 2","line 3","line 4","line 5"},
                new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d});
        lst.setAdapter(sa);

        cardBackbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(cartLab.this,Labtest.class));
            }
        });

        cardCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(cartLab.this,LabTestBook.class);
                it.putExtra("price",totalcost.getText());
                it.putExtra("date",checkDate.getText());
                it.putExtra("time",checkTime.getText());
                startActivity(it);
            }
        });

        //datepicker
        intDatePicker();
        checkDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.show();
            }
        });
        //timePicker

        checkTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timePickerDialog.show();
            }
        });
    }
    private void intDatePicker(){
        DatePickerDialog.OnDateSetListener dateSetListener= new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int i, int i1, int i2) {
                i1=i1-1;
                checkDate.setText(i2+"/"+i1+"/"+i);

            }
        };
        Calendar cal = Calendar.getInstance();
        int year= cal.get(Calendar.YEAR);
        int mounth = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style= AlertDialog. THEME_HOLO_DARK;
        datePickerDialog = new DatePickerDialog(this,dateSetListener,year,mounth,day);
        datePickerDialog.getDatePicker().setMinDate(cal.getTimeInMillis()+86400000);

    }
    private void initTimePicker(){
        TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int i, int i1) {
                checkTime.setText(i+":"+i1);

            }
        };
        Calendar cal =Calendar.getInstance();
        int hrs= cal.get(Calendar.HOUR);
        int mins = cal.get(Calendar.MINUTE);

        int style =AlertDialog.THEME_HOLO_DARK;
        timePickerDialog = new TimePickerDialog(this,style,timeSetListener,hrs,mins,true);

    }
}