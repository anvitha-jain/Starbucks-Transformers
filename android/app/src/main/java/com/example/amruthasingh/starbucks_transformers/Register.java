package com.example.amruthasingh.starbucks_transformers;

/**
 * Created by amruthasingh on 5/2/19.
 */


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {
    //DatabaseHelper db;
    EditText e1, e2, e3, e4, e5, e6;
    Button b1,b2;
    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String Email = "emailKey";
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
       // db=new DatabaseHelper(this);
        e1=(EditText)findViewById(R.id.firstnamebox);
        e2=(EditText)findViewById(R.id.lastnamebox);
        e3=(EditText)findViewById(R.id.phonenumberbox);
        e4=(EditText)findViewById(R.id.citybox);
        e5=(EditText)findViewById(R.id.emailbox);
        e6=(EditText)findViewById(R.id.passwordbox);

        b1=(Button)findViewById(R.id.register);
        b2=(Button)findViewById(R.id.bregister);
        sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,Login.class);
                startActivity(i);
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1 = e1.getText().toString();
                String s2 = e2.getText().toString();
                String s3 = e3.getText().toString();
                String s4 = e4.getText().toString();
                String s5 = e5.getText().toString();
                String s6 = e6.getText().toString();

                if(s1.equals("")||s2.equals("")||s3.equals("")||s4.equals("")||s5.equals("")||s6.equals("")){
                    Toast.makeText(getApplicationContext(),"Fields are empty", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if(s2.equals(s3)){
                        Boolean checkemail = db.checkEmail(s5);
                        if(checkemail==true){
                            Boolean insert = db.insert(s1,s2,s3,s4,s5,s6);
                            if(insert==true){
                                Toast.makeText(getApplicationContext(), "Registration Successful", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(getApplicationContext(), "Email ID already exists", Toast.LENGTH_SHORT).show();
                        }

                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Passwords do not match", Toast.LENGTH_SHORT).show();
                    }


                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        String email = sharedPreferences.getString(MainActivity.Email, "");
        Boolean checkemail = db.checkEmail(email);
        Log.i(":MainActivity", "checkEmail: " + checkemail);
        if(checkemail == false) {
            Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(getApplicationContext(), "Please login", Toast.LENGTH_SHORT).show();
        }
    }

    public void message(View view) {
    }
}