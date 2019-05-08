package edu.sjsu.android.starbucks_ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class AddCardActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card);
        Bundle bundle = getIntent().getExtras();
        String username = bundle.getString("Username");
    }

    public void openCardActivity(View v){
        Intent intent = new Intent(AddCardActivity.this, CardActivity.class);
        startActivity(intent);
        finish();
    }

}
