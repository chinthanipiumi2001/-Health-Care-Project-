package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.AlteredCharSequence;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class BookingAppoinment extends AppCompatActivity {
    EditText ed1,ed2,ed3,ed4;
    TextView tv;
    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;
    private Button dateButton,timeButton,BookAppoinmentbtn,BackAppbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_appoinment);

        tv=findViewById(R.id.textViewapptitle);
        ed1=findViewById(R.id.Appfullname);
        ed2=findViewById(R.id.AppAddress);
        ed3=findViewById(R.id.Appcontactno);
        ed4=findViewById(R.id.AppFees);
        dateButton=findViewById(R.id.datebtn);
        timeButton=findViewById(R.id.timebtn);
        BookAppoinmentbtn=findViewById(R.id.BookAppoinmentbtn);
        BackAppbtn=findViewById(R.id.BackAppbtn);

        ed1.setKeyListener(null);
        ed2.setKeyListener(null);
        ed3.setKeyListener(null);
        ed4.setKeyListener(null);

        Intent it =getIntent();
        String title = it.getStringExtra("text1");
        String fullname = it.getStringExtra("text2");
        String Address=it.getStringExtra("text3");
        String contact = it.getStringExtra("text4");
        String  fees= it.getStringExtra("text5");

        tv.setText(title);
        ed1.setText(fullname);
        ed2.setText(Address);
        ed1.setText(contact);
        ed4.setText("Cons Fess;"+fees+"/-");

        //datepicker
        intDatePicker();
        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.show();
            }
        });
        //timePicker

        timeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timePickerDialog.show();
            }
        });
        BackAppbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BookingAppoinment.this,FindDoctor.class));
            }
        });
        BookAppoinmentbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database db=new database(getApplicationContext(),"healthcare",null,1);
                SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs",Context.MODE_PRIVATE);
                String username= sharedPreferences.getString("username","").toString();
                if(db.checkAppoinmentExists(username,title+" =>"+fullname,Address,contact,dateButton.getText().toString(),timeButton.getText().toString())==1){
                    Toast.makeText(getApplicationContext(),"Appoinment already booked",Toast.LENGTH_LONG).show();
                }else{
                    db.addOrder(username,title+"=>"+fullname,Address,contact,0,dateButton.getText().toString(),timeButton.getText().toString(),Float.parseFloat(fees),"appoinment");
                    Toast.makeText(getApplicationContext(),"Your appoinment is done successfully",Toast.LENGTH_LONG).show();
                    startActivity(new Intent(BookingAppoinment.this,home.class));
                }
            }

        });
    }
    private void intDatePicker(){
        DatePickerDialog.OnDateSetListener dateSetListener= new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int i, int i1, int i2) {
                i1=i1-1;
                dateButton.setText(i2+"/"+i1+"/"+i);

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
                timeButton.setText(i+":"+i1);

            }
        };
        Calendar cal =Calendar.getInstance();
        int hrs= cal.get(Calendar.HOUR);
        int mins = cal.get(Calendar.MINUTE);

        int style =AlertDialog.THEME_HOLO_DARK;
        timePickerDialog = new TimePickerDialog(this,style,timeSetListener,hrs,mins,true);

    }

}