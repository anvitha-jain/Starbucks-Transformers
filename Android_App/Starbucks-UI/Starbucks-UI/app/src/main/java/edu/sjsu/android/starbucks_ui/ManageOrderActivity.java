package edu.sjsu.android.starbucks_ui;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class ManageOrderActivity extends AppCompatActivity {

    private EditText coffee_qty;
    private EditText donut_qty;
    private Button btn_manage_order, btn_item1_price, btn_item2_price;
    private TextView txt_amount, txt_price1, txt_price2;
    double item1_price = 5.0;
    double item2_price = 3.0;
    String stringdouble = "";
    String qty1 = " ";
    String qty2 = " ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_order);
        Bundle bundle = getIntent().getExtras();
        String username = bundle.getString("Username");

        coffee_qty = (EditText) findViewById(R.id.enterCoffeeqty);
        donut_qty = (EditText) findViewById(R.id.enterDonutqty);
        btn_manage_order = (Button) findViewById(R.id.manageOrder);
        btn_item1_price = (Button) findViewById(R.id.calculateCoffeePrice);
        btn_item2_price = (Button) findViewById(R.id.calculateDonutPrice);
        txt_amount = (TextView) findViewById(R.id.amount);
        txt_price1 = (TextView) findViewById(R.id.coffeePrice);
        txt_price2 = (TextView) findViewById(R.id.donutPrice);


        btn_item1_price.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                qty1 = coffee_qty.getText().toString();

                final String link = "http://starbucks-elb-1199172796.us-west-2.elb.amazonaws.com:8080/item1?item_qty="+qty1+"&item_name=coffee";
                // String item1_qty = coffee_qty.getText().toString();
                ManageOrderActivity.HttpGetRequest runner = new ManageOrderActivity.HttpGetRequest();

                runner.execute(link);
            }


        });

        btn_item2_price.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                qty2 = donut_qty.getText().toString();


                final String url = "http://starbucks-elb-1199172796.us-west-2.elb.amazonaws.com:8080/item2?item_qty="+qty2+"&item_name=expresso";
                // String item1_qty = coffee_qty.getText().toString();
                ManageOrderActivity.HttpGetPriceRequest runner = new ManageOrderActivity.HttpGetPriceRequest();

                runner.execute(url);
            }


        });

        btn_manage_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String price1 = txt_price1.getText().toString();
                String price2 = txt_price2.getText().toString();
                if(price1.equals("N/A") || price2.equals("N/A")){
                    Toast.makeText(getApplicationContext(), "Please enter correct item quantity", Toast.LENGTH_SHORT).show();
                }
                else{
                    double value1 = Double.parseDouble(price1);
                    double value2 = Double.parseDouble(price2);
                    double total_amount = value1 + value2;
                    stringdouble = Double.toString(total_amount);
                    txt_amount.setText("Total order amount: $"+stringdouble);
                }
            }


        });


    }

    public class HttpGetRequest extends AsyncTask<String, Void, String> {
        public static final String REQUEST_METHOD = "GET";
        public static final int READ_TIMEOUT = 15000;
        public static final int CONNECTION_TIMEOUT = 15000;

        @Override
        protected String doInBackground(String... params) {
            String stringUrl = params[0];
            Log.i("^^^^^^^^^^^^^^^^^^",stringUrl);
            try {
                try {
                    return HttpGET(stringUrl);
                } catch (JSONException e) {
                    e.printStackTrace();
                    return "Error!";
                }
            } catch (IOException e) {
                return "Unable to retrieve web page. URL may be invalid.";
            }
        }

        private String HttpGET(String Url) throws IOException, JSONException {
            String apiResponse;
            String inputLine;

            //Create a URL object holding our url
            URL myUrl = new URL(Url);
            Log.i(MainActivity.class.toString(), Url + " -------------------------  ");
            //Create a connection
            HttpURLConnection connection = (HttpURLConnection)
                    myUrl.openConnection();

            //Set methods and timeouts
            connection.setRequestMethod(REQUEST_METHOD);
            connection.setReadTimeout(READ_TIMEOUT);
            connection.setConnectTimeout(CONNECTION_TIMEOUT);

            //Connect to our url
            connection.connect();

            //Create a new InputStreamReader
            InputStreamReader streamReader = new
                    InputStreamReader(connection.getInputStream());

            //Create a new buffered reader and String Builder
            BufferedReader reader = new BufferedReader(streamReader);
            StringBuilder stringBuilder = new StringBuilder();

            //Check if the line we are reading is not null
            while ((inputLine = reader.readLine()) != null) {
                stringBuilder.append(inputLine);
            }

            //Close our InputStream and Buffered reader
            reader.close();
            streamReader.close();

            //Set our result equal to our stringBuilder
            apiResponse = stringBuilder.toString();
            Log.i(MainActivity.class.toString(), apiResponse + " -------------------------  ");
            JSONObject response = new JSONObject(apiResponse);
            String result = response.getString("result");
            String item_price = response.getString("value");

//            if(result.equals(true))
//            {
//
//                return item_price;
//            }
//            else
//            {
//
//                return "NA";
//            }
            return item_price;
        }

        protected void onPostExecute(String item_price) {

            if(item_price.equals("coffee"))
            {
                txt_price1.setText("N/A");
            }
            else {
                txt_price1.setText(item_price);
            }

        }
    }

    //code for expresso
    public class HttpGetPriceRequest extends AsyncTask<String, Void, String> {
        public static final String REQUEST_METHOD = "GET";
        public static final int READ_TIMEOUT = 15000;
        public static final int CONNECTION_TIMEOUT = 15000;

        @Override
        protected String doInBackground(String... params) {
            String stringUrl = params[0];

            try {
                try {
                    return HttpGETPrice(params[0]);
                } catch (JSONException e) {
                    e.printStackTrace();
                    return "Error!";
                }
            } catch (IOException e) {
                return "Unable to retrieve web page. URL may be invalid.";
            }
        }

        private String HttpGETPrice(String Url) throws IOException, JSONException {
            String apiResponse;
            String inputLine;

            //Create a URL object holding our url
            URL myUrl = new URL(Url);
            Log.i(MainActivity.class.toString(), Url + " -------------------------  ");
            //Create a connection
            HttpURLConnection connection = (HttpURLConnection)
                    myUrl.openConnection();

            //Set methods and timeouts
            connection.setRequestMethod(REQUEST_METHOD);
            connection.setReadTimeout(READ_TIMEOUT);
            connection.setConnectTimeout(CONNECTION_TIMEOUT);

            //Connect to our url
            connection.connect();

            //Create a new InputStreamReader
            InputStreamReader streamReader = new
                    InputStreamReader(connection.getInputStream());

            //Create a new buffered reader and String Builder
            BufferedReader reader = new BufferedReader(streamReader);
            StringBuilder stringBuilder = new StringBuilder();

            //Check if the line we are reading is not null
            while ((inputLine = reader.readLine()) != null) {
                stringBuilder.append(inputLine);
            }

            //Close our InputStream and Buffered reader
            reader.close();
            streamReader.close();

            //Set our result equal to our stringBuilder
            apiResponse = stringBuilder.toString();
            Log.i(MainActivity.class.toString(), apiResponse + " -------------------------  ");
            JSONObject response = new JSONObject(apiResponse);
            String result = response.getString("result");
            String price = response.getString("value");
            return price;
        }

        protected void onPostExecute(String price) {

            if(price.equals("expresso"))
            {
                txt_price2.setText("N/A");
            }
            else {
                txt_price2.setText(price);
            }

        }
    }



    public void openOrderAmountActivity (View v){
        Intent intent = new Intent(ManageOrderActivity.this, TotalOrderAmountActivity.class);
        Bundle bundle = getIntent().getExtras();
        String username = bundle.getString("Username");
        intent.putExtra("OrderAmount", stringdouble);
        intent.putExtra("Username", username);
        intent.putExtra("qty1", coffee_qty.getText().toString());
        intent.putExtra("qty2", donut_qty.getText().toString());
        startActivity(intent);
        finish();
    }


    public void openDashboardActivity(View v){
        Intent intent = new Intent(ManageOrderActivity.this, DashboardActivity.class);
        Bundle bundle = getIntent().getExtras();
        String username = bundle.getString("Username");
        intent.putExtra("OrderAmount",stringdouble);
        intent.putExtra("Username",username);
        startActivity(intent);
        finish();
    }


}

