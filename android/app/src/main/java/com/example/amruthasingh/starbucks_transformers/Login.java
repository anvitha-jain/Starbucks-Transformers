package com.example.amruthasingh.starbucks_transformers;

/**
 * Created by amruthasingh on 5/2/19.
 */

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    EditText e5,e6;
    Button b1;
   // DatabaseHelper db;
    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      //  db = new DatabaseHelper(this);
        e5 = (EditText)findViewById(R.id.citybox);
        e6 = (EditText)findViewById(R.id.passwordbox);
        b1 = (Button)findViewById(R.id.bregister);
     //   sharedpreferences = getSharedPreferences(MainActivity.MyPREFERENCES, Context.MODE_PRIVATE);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = e5.getText().toString();
                String password = e6.getText().toString();
                Boolean checkemailpass = db.checkEmailPassword(email, password);
                if(checkemailpass==true) {

                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putString(MainActivity.Email, email);
                    editor.commit();

                    Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Login.this, MainActivity.class);
                    startActivity(intent);

                }  else {
                    Toast.makeText(getApplicationContext(), "Wrong Email or Password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        String email = sharedpreferences.getString(MainActivity.Email, "");
        Boolean checkemail = db.checkEmail(email);
        if(checkemail == false) {
            Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Login.this, MainActivity.class);
            startActivity(intent);
        }
    }
}
