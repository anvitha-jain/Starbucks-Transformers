package edu.sjsu.android.starbucks_ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

public class DashboardActivity extends AppCompatActivity {
    String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
<<<<<<< HEAD
        Intent i = getIntent();
        username = i.getStringExtra("Username");
        Log.v("username is: ", username);
=======
        Bundle bundle = getIntent().getExtras();
        String username = bundle.getString("Username");
>>>>>>> 6b93fb891320467a76383546b4d0eb759c60a1fb

    }

    public void openCardPage(View v){
        Intent intent = new Intent(DashboardActivity.this, CardActivity.class);
        Log.i(MainActivity.class.toString(), username + " ------uuuuuuu------ " );
        intent.putExtra("Username",username);
        startActivity(intent);
        finish();
    }

    public void openManageOrderActivity(View v){
        Intent intent = new Intent(DashboardActivity.this, ManageOrderActivity.class);
<<<<<<< HEAD
=======
        Bundle bundle = getIntent().getExtras();
        String username = bundle.getString("Username");
>>>>>>> 6b93fb891320467a76383546b4d0eb759c60a1fb
        intent.putExtra("Username",username);
        startActivity(intent);
        finish();
    }

    public void openUserProfileActivity(View v){
        Intent intent = new Intent(DashboardActivity.this, UserProfileActivity.class);
        intent.putExtra("Username",username);
        startActivity(intent);
        finish();
    }

}