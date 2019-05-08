package edu.sjsu.android.starbucks_ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;


public class TotalOrderAmountActivity extends AppCompatActivity {

    private EditText txt_total_amount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_total_order_amount);

        Bundle bundle = getIntent().getExtras();
        String amount = bundle.getString("OrderAmount");
        Log.i("---------------------",amount);
        txt_total_amount = (EditText)findViewById(R.id.populateAmount);
        txt_total_amount.setText("$"+amount);


    }

}
