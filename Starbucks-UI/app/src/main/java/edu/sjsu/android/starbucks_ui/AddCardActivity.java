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

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class AddCardActivity extends AppCompatActivity {
    private Button btn_add;
    private TextView txt_view;
    private EditText card_no, card_expiry, card_holder, card_cvv, card_balance;
    String cardholdername, username;
    int cardno, cardexpiry, cvv;
    double balance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card);



        card_no     =  (EditText)findViewById(R.id.enterCardNumber);
        card_expiry      = (EditText)findViewById(R.id.enterCardExpiry);
        card_holder      =   (EditText)findViewById(R.id.enterCardHolderName);
        card_cvv      =     (EditText)findViewById(R.id.enterCVV);
        card_balance      =  (EditText)findViewById(R.id.enterBalance);
        //userName =            findViewById(R.id.enterUserName);

       // txt_view.setText("Result");
        btn_add = findViewById(R.id.add);
        final String serviceURL = "http://ec2-54-185-174-206.us-west-2.compute.amazonaws.com:5000/cards";
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                HTTPAsyncTask runner = new HTTPAsyncTask();

                runner.execute(serviceURL);
            }
        });
    }

    private class HTTPAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
            // params comes from the execute() call: params[0] is the url.
            try {
                try {
                    return HttpPost(urls[0]);
                } catch (JSONException e) {
                    e.printStackTrace();
                    return "Error!";
                }
            } catch (IOException e) {
                return "Unable to retrieve web page. URL may be invalid.";
            }
        }
        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {
            if(result.equals("Card Number invalid"))
            {
                Toast.makeText(getApplicationContext(), "Card Number invalid", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(getApplicationContext(), "Successfully added", Toast.LENGTH_SHORT).show();
            }
            Intent intent = new Intent(AddCardActivity.this, CardActivity.class);
            startActivity(intent);
            finish();
        }
    }

    private String HttpPost(String myUrl) throws IOException, JSONException {
        String result = "";

        URL url = new URL(myUrl);

        // 1. create HttpURLConnection
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json; charset=utf-8");

        // 2. build JSON object
        JSONObject jsonObject = buidJsonObject();

        // 3. add JSON content to POST request body
        setPostRequestContent(conn, jsonObject);

        // 4. make POST request to the given URL
        conn.connect();

        // 5. return response messag
        String responseMsg = conn.getResponseMessage();
        int code = conn.getResponseCode();
        Log.v("Response Msg: ", responseMsg);
        responseMsg += "";

        Log.i(MainActivity.class.toString(), responseMsg + "    " + code);

        return responseMsg;
    }

    private JSONObject buidJsonObject() throws JSONException {

        Bundle bundle = getIntent().getExtras();
        String username = bundle.getString("Username");

        JSONObject jsonObject = new JSONObject();
        jsonObject.accumulate("cardno", card_no.getText().toString());
        jsonObject.accumulate("cardexpiry", card_expiry.getText().toString());
        jsonObject.accumulate("cardholdername", card_holder.getText().toString());
        jsonObject.accumulate("cvv", card_cvv.getText().toString());
        jsonObject.accumulate("username", username);
//        String userNm = userName.getText().toString();
//        Log.v("***Username***", userNm);
        jsonObject.accumulate("balance", card_balance.getText().toString());
        return jsonObject;
    }

    private void setPostRequestContent(HttpURLConnection conn,
                                       JSONObject jsonObject) throws IOException {

        OutputStream os = conn.getOutputStream();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
        writer.write(jsonObject.toString());
        Log.i(MainActivity.class.toString(), jsonObject.toString());
        writer.flush();
        writer.close();
        os.close();

    }

    public void openCardActivity(View v){
        Intent intent = new Intent(AddCardActivity.this, CardActivity.class);
        startActivity(intent);
        finish();
    }

}
