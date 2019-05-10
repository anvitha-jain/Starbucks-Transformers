package edu.sjsu.android.starbucks_ui;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ViewCardDetails extends AppCompatActivity {

    private Button btn_view;
    private EditText txt_view_details, txt_cardno, txt_cardexpiry, txt_cardhname, txt_username, txt_cvv, txt_balance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_card_details);
        btn_view = (Button)findViewById(R.id.btn_view_details);

        txt_cardno = (EditText)findViewById(R.id.cardno);
        txt_cardexpiry = (EditText)findViewById(R.id.expiry);
        txt_cardhname = (EditText)findViewById(R.id.cardholder);
        txt_cvv = (EditText)findViewById(R.id.cvv);
        txt_username = (EditText)findViewById(R.id.username);
        txt_balance = (EditText)findViewById(R.id.balance);

        btn_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Bundle bundle = getIntent().getExtras();
                String username = bundle.getString("Username");
                final String serviceURL = "http://starbucks-elb-1199172796.us-west-2.elb.amazonaws.com:8080/cards?username="+username;

                ViewCardDetails.HttpGetRequest runner = new ViewCardDetails.HttpGetRequest();

                runner.execute(serviceURL);
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

            try {
                try {
                    return HttpGET(params[0]);
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

            return apiResponse;

        }

        protected void onPostExecute(String apiResponse) {
            try {

                super.onPostExecute(apiResponse);

                JSONArray array = new JSONArray(apiResponse);
                for (int i = 0; i < array.length(); i++) {
                    JSONObject myObj = array.getJSONObject(i);
                    String cardno = myObj.getString("cardno");
                    String expiry = myObj.getString("cardexpiry");
                    String cardholdername = myObj.getString("cardholdername");
                    String cvv = myObj.getString("cvv");
                    String username = myObj.getString("username");
                    String balance = myObj.getString("balance");


                    String message = "CardNo: " +cardno+"\n"+"Cardholdername: "+cardholdername+"\n"+"Card Expiry:" + expiry;
                    txt_cardno.setText(cardno);
                    txt_cardexpiry.setText(expiry);
                    txt_cardhname.setText(cardholdername);
                    txt_cvv.setText(cvv);
                    txt_username.setText(username);
                    txt_balance.setText(balance);


                    Log.v("My Users-------------", cardno + " " + cardholdername + " " + cvv + " " + username);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


    }
}

