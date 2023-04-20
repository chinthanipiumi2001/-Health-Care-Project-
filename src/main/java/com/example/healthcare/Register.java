package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.DatabaseMetaData;

public class Register extends AppCompatActivity {
    EditText edusername,edEmail,edpassword,edconfirm;
    Button btn;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edusername=findViewById(R.id.Regusername);
        edEmail=findViewById(R.id.EmailAdd);
        edpassword=findViewById(R.id.Regpassword);
        edconfirm=findViewById(R.id.confirmpassword);
        btn=findViewById(R.id.Registerbtn);
        tv=findViewById(R.id.alreadyAcc);

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Register.this,login.class));
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edusername.getText().toString();
                String Email = edEmail.getText().toString();
                String password = edpassword.getText().toString();
                String confirmpassword = edconfirm.getText().toString();
                database db = new database(getApplicationContext(),"healthcare",null,1);
                if(username.length()==0 || password.length()==0){
                    Toast.makeText(getApplicationContext(),"Please fill all details",Toast.LENGTH_SHORT).show();
                }else{
                    if(password.compareTo(confirmpassword)==0){
                        if(isValied(password)){
                            db.register(username,Email,password);
                            Toast.makeText(getApplicationContext(),"Record insert successful",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(Register.this,login.class));
                        }
                        else{
                            Toast.makeText(getApplicationContext(),"Password must contain at least 8 characters,having letter,digit and speacial symbol",Toast.LENGTH_SHORT).show();
                        }

                    }else{
                        Toast.makeText(getApplicationContext(),"Password and confirm password isn't not match",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
    public static boolean isValied(String passwordhere) {
        int f1 = 0, f2 = 0, f3 = 0;
        if (passwordhere.length() < 8) {
            return false;
        } else {
            for (int p = 0; p < passwordhere.length(); p++) {
                if(Character.isLetter(passwordhere.charAt(p))){
                    f1=1;
                }

            }
            for(int r=0;r<passwordhere.length();r++){
                if(Character.isDigit(passwordhere.charAt(r))){
                    f2=1;

                }

            }
            for(int s=0;s< passwordhere.length();s++){
                char c= passwordhere.charAt(s);
                if(c>33&&c<=46||c==64){
                    f3=1;
                }

            }
            if(f1==1 && f2==1 && f3==1)
                return true;
        }

        return false;
    }

}

