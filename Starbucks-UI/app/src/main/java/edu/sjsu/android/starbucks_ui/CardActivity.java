package edu.sjsu.android.starbucks_ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class CardActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);

    }

    public void openAddCardActivity(View v){
        Intent intent = new Intent(CardActivity.this, AddCardActivity.class);
        Bundle bundle = getIntent().getExtras();
        String username = bundle.getString("Username");
        intent.putExtra("Username",username);
        startActivity(intent);
        finish();
    }


    public void openViewCardDeatilsActivity(View v){
        Intent intent = new Intent(CardActivity.this, ViewCardDetails.class);
//        Bundle bundle = getIntent().getExtras();
//        String username = bundle.getString("Username");
//        intent.putExtra("Username",username);
        startActivity(intent);
        finish();
    }
}
