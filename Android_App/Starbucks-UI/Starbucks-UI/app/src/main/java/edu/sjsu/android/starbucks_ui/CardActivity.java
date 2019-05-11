package edu.sjsu.android.starbucks_ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class CardActivity extends AppCompatActivity {


    private Button btn_viewCard;

//  private Button btn_viewCard;

//    String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);
        btn_viewCard = (Button)findViewById(R.id.btn_viewCard);


//        Intent i = getIntent();
//        String username = i.getStringExtra("Username");
        Bundle bundle = getIntent().getExtras();
        String username = bundle.getString("Username");
        Log.i("the username isssssss",username);

    }

    public void openAddCardActivity(View v){
        Intent intent = new Intent(CardActivity.this, AddCardActivity.class);
        Bundle bundle = getIntent().getExtras();
        String username = bundle.getString("Username");
        intent.putExtra("Username",username);
        startActivity(intent);
        finish();
    }

    public void openDashboardActivity(View v){
        Intent intent = new Intent(CardActivity.this, DashboardActivity.class);
        Bundle bundle = getIntent().getExtras();
        String username = bundle.getString("Username");
        intent.putExtra("Username",username);
        startActivity(intent);
        finish();
    }


    public void openViewCardDeatilsActivity(View v){
        Intent intent = new Intent(CardActivity.this, ViewCardDetails.class);
        Bundle bundle = getIntent().getExtras();
        String username = bundle.getString("Username");
        Log.i("GGGGGGGGGGGGGGGGGGG-",username);
        intent.putExtra("Username",username);
        startActivity(intent);
        finish();
    }

    public void openReloadCardActivity(View v){
        Intent intent = new Intent(CardActivity.this, ReloadCardActivity.class);
        Bundle bundle = getIntent().getExtras();
        String username = bundle.getString("Username");
        intent.putExtra("Username",username);
        startActivity(intent);
        finish();
    }



}