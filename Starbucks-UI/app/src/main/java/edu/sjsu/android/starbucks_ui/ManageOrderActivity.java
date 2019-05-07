package edu.sjsu.android.starbucks_ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class ManageOrderActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_order);
    }

    public void openOrderAmountActivity(View v){
        Intent intent = new Intent(ManageOrderActivity.this, TotalOrderAmountActivity.class);
        startActivity(intent);
        finish();
    }

}
