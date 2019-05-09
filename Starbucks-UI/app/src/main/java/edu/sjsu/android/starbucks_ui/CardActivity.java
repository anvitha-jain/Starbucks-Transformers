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

    String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);
        btn_viewCard = (Button)findViewById(R.id.btn_viewCard);


        Intent i = getIntent();
        username = i.getStringExtra("Username");

        Bundle bundle = getIntent().getExtras();
        String username1 = bundle.getString("Username");

//        btn_viewCard.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Intent intent = new Intent(CardActivity.this, ViewCardDetails.class);
//                 Log.i("GGGGGGGGGGGGGGGGGGG",username);
//                intent.putExtra("Username",username);
//                startActivity(intent);
//                finish();
//            }
//        });


    }

    public void openAddCardActivity(View v){
        Intent intent = new Intent(CardActivity.this, AddCardActivity.class);
        intent.putExtra("Username",username);
        startActivity(intent);
        finish();
    }


    public void openViewCardDeatilsActivity(View v){
        Intent intent = new Intent(CardActivity.this, ViewCardDetails.class);
        Bundle bundle = getIntent().getExtras();
         String username1 = bundle.getString("Username");
        Log.i("GGGGGGGGGGGGGGGGGGG-----------",username1);
        intent.putExtra("Username",username1);

        startActivity(intent);
        finish();
    }


}