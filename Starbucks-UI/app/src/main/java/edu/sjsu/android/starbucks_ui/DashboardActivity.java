package edu.sjsu.android.starbucks_ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class DashboardActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
    }

    public void openCardPage(View v){
        Intent intent = new Intent(DashboardActivity.this, CardActivity.class);
        startActivity(intent);
        finish();
    }

    public void openManageOrderActivity(View v){
        Intent intent = new Intent(DashboardActivity.this, ManageOrderActivity.class);
        startActivity(intent);
        finish();
    }

}
