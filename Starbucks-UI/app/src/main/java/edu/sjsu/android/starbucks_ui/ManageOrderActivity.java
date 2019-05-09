package edu.sjsu.android.starbucks_ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class ManageOrderActivity extends AppCompatActivity {

    private EditText coffee_qty;
    private EditText donut_qty;
    private Button btn_manage_order;
    private TextView txt_amount;
    double item1_price = 5.0;
    double item2_price = 3.0;
    String stringdouble = " ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_order);
        Bundle bundle = getIntent().getExtras();
        String username = bundle.getString("Username");

        coffee_qty = (EditText)findViewById(R.id.enterCoffeeqty);
        donut_qty = (EditText)findViewById(R.id.enterDonutqty);
        btn_manage_order = (Button)findViewById(R.id.manageOrder);
        txt_amount = (TextView)findViewById(R.id.amount);

        btn_manage_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String item1_qty = coffee_qty.getText().toString();
                String item2_qty = donut_qty.getText().toString();

                double q1 = Double.parseDouble(item1_qty);
                double q2 = Double.parseDouble(item2_qty);
                double total_amount = (q1 * item1_price) + (q2 * item2_price);
                stringdouble= Double.toString(total_amount);
                txt_amount.setText("Total order amount: $"+stringdouble);



            }


        });

    }

    public void openOrderAmountActivity(View v){
        Intent intent = new Intent(ManageOrderActivity.this, TotalOrderAmountActivity.class);
        Bundle bundle = getIntent().getExtras();
        String username = bundle.getString("Username");
        intent.putExtra("OrderAmount",stringdouble);
        intent.putExtra("Username",username);
        startActivity(intent);
        finish();
    }

}