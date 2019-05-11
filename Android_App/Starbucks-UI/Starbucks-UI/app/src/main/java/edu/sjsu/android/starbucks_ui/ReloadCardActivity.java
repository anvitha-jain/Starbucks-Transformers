package edu.sjsu.android.starbucks_ui;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;


public class ReloadCardActivity extends AppCompatActivity {

    private EditText txtSelectUserCard, txt_balance;
    private Button reloadCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reload);

        Bundle bundle = getIntent().getExtras();
        String username = bundle.getString("Username");

        txtSelectUserCard = findViewById(R.id.cardno);
        txt_balance = findViewById(R.id.balance);
        ReloadCardActivity.HttpGetCardNoRequest runner = new ReloadCardActivity.HttpGetCardNoRequest();
        final String cardUrl = "http://starbucks-elb-1199172796.us-west-2.elb.amazonaws.com:8080/cardnumber?username="+username;
        runner.execute(cardUrl);

        reloadCard = findViewById(R.id.submit);
        reloadCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cardnum =  txtSelectUserCard.getText().toString();
                String balance = txt_balance.getText().toString();
                final String serviceURL = "http://starbucks-elb-1199172796.us-west-2.elb.amazonaws.com:8080/reloadcards?cardno="+cardnum+"&balance="+balance;
               ReloadCardActivity.HTTPAsyncTask runner = new ReloadCardActivity.HTTPAsyncTask();

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
                    return HttpPostBalance(urls[0]);
                } catch (JSONException e) {
                    e.printStackTrace();
                    return "Error!";
                }
            } catch (IOException e) {
                return "Unable to retrieve web page. URL may be invalid.";
            }
        }
        @Override
        protected void onPostExecute(String result) {

         if(result.equals("false"))
         {
             Toast.makeText(ReloadCardActivity.this, "Reload again--", Toast.LENGTH_SHORT).show();
         }
         else
         {
             Toast.makeText(ReloadCardActivity.this, "Reload successful", Toast.LENGTH_SHORT).show();
             Intent intent = new Intent(ReloadCardActivity.this, CardActivity.class);
             Bundle bundle = getIntent().getExtras();
             String username = bundle.getString("Username");
             intent.putExtra("Username", username);
             startActivity(intent);
             finish();
         }
        }
    }

    private String HttpPostBalance(String myUrl) throws IOException, JSONException {
        String result = "";

        String readLine = null;
        String apiResonse = null;

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
        int code = conn.getResponseCode();
        if (code == 200)
        {
            //Log.v("***func", "Message okay");
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuffer response = new StringBuffer();
            while ((readLine = in.readLine()) != null)
            {
                response.append(readLine);
            }
            in.close();
            apiResonse = response.toString();
        }


        Log.i(MainActivity.class.toString(), apiResonse + "   " + code);
        JSONObject response = new JSONObject(apiResonse);
        String message = response.getString("result");
        Log.i(MainActivity.class.toString(), message + "   YAYAYAYAYAY  " + code);
        return message;
    }

    private JSONObject buidJsonObject() throws JSONException {

        Bundle bundle = getIntent().getExtras();
        String username = bundle.getString("Username");
        String cardnum =  txtSelectUserCard.getText().toString();
        String balance = txt_balance.getText().toString();
        JSONObject jsonObject = new JSONObject();
        jsonObject.accumulate("cardno", cardnum);
        jsonObject.accumulate("balance", balance);

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

    public class HttpGetCardNoRequest extends AsyncTask<String, Void, String> {
        public static final String REQUEST_METHOD = "GET";
        public static final int READ_TIMEOUT = 15000;
        public static final int CONNECTION_TIMEOUT = 15000;

        @Override
        protected String doInBackground(String... params) {
            String stringUrl = params[0];

            try {
                try {
                    return HttpGETCardno(params[0]);
                } catch (JSONException e) {
                    e.printStackTrace();
                    return "Error!";
                }
            } catch (IOException e) {
                return "Unable to retrieve web page. URL may be invalid.";
            }
        }

        private String HttpGETCardno(String Url) throws IOException, JSONException {
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
            Log.i(MainActivity.class.toString(), apiResponse + " -^^^^^^^^^^^^^^^^^^^^  ");
            JSONObject response = new JSONObject(apiResponse);
            String message = response.getString("result");
            String cardno = response.getString("cardno");
            Log.i(MainActivity.class.toString(), message + "   YAYAYAYAYAY  ");
            Log.i(MainActivity.class.toString(), cardno + "   YAYAYAYAYAY00000000000000000  ");
            if(message.equals("true"))
            {
                return cardno;
            }
            return message;

        }

        protected void onPostExecute(String cardno) {

            txtSelectUserCard.setText(cardno);
        }

    }



    public void openCardActivity(View v) {
        Intent intent = new Intent(ReloadCardActivity.this, CardActivity.class);
        Bundle bundle = getIntent().getExtras();
        String username = bundle.getString("Username");
        intent.putExtra("Username", username);
        startActivity(intent);
        finish();

    }
}