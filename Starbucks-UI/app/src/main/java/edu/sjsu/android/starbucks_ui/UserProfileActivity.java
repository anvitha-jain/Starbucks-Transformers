package edu.sjsu.android.starbucks_ui;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class UserProfileActivity extends AppCompatActivity {
    Button user_profile;
    TextView userNm, passwd, firstNm, lastNm, phoneNo, City;

    String user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        userNm = findViewById(R.id.userName);
        passwd = findViewById(R.id.password);
        firstNm = findViewById(R.id.firstName);
        lastNm = findViewById(R.id.lastName);
        phoneNo = findViewById(R.id.Phone);
        City = findViewById(R.id.city);

        Intent i = getIntent();
        user = i.getStringExtra("Username");

        final String serviceURL = "http://starbucks-elb-1199172796.us-west-2.elb.amazonaws.com:8080/users?username="+user;
        user_profile = findViewById(R.id.get_user_details);

        user_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserProfileActivity.HttpGetRequest runner = new UserProfileActivity.HttpGetRequest();
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
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                return "Unable to retrieve web page. URL may be invalid.";
            }
            return null;
        }

        private String HttpGET(String Url) throws IOException, JSONException, ParseException {
            String apiResponse;
            String inputLine;

            URL myUrl = new URL(Url);
            Log.i("hello",Url + " -------------------------  " );
            //Create a connection
            HttpURLConnection connection =(HttpURLConnection)
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
            while((inputLine = reader.readLine()) != null){
                stringBuilder.append(inputLine);
            }

            //Close our InputStream and Buffered reader
            reader.close();
            streamReader.close();

            //Set our result equal to our stringBuilder
            apiResponse = stringBuilder.toString();
            Log.i(MainActivity.class.toString(), "API Response is: " + apiResponse + " -------------------------  " );
            return apiResponse;

        }

        protected void onPostExecute (String apiResponse){
            super.onPostExecute(apiResponse);
            Bundle bundle = getIntent().getExtras();
            String username = bundle.getString("Username");
            Log.v("***Inside postExecute", apiResponse);
            Object s = null;
            JSONArray array= null;
            try {
                array = new JSONArray(apiResponse);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            JSONObject object = null;
            try {
                object = array.getJSONObject(0);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                s = object.get("username");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            String name = s.toString();
            Log.v("***name is***", name);
            userNm.setText("Username:  " + user);
            try {
                s = object.get("password");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            String password = s.toString();
            Log.v("***password is***", password);
            passwd.setText("Password:  " + password);
            try {
                s = object.get("fname");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            String firstName = s.toString();
            Log.v("***first name is***", firstName);
            firstNm.setText("First Name:  " + firstName);
            try {
                s = object.get("lname");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            String lastName = s.toString();
            Log.v("***last name is***", lastName);
            lastNm.setText("Last Name:  " + lastName);
            try {
                s = object.get("phone");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            String phoneNumber = s.toString();
            Log.v("***Phone number is***", phoneNumber);
            phoneNo.setText("Phone:  " + phoneNumber);
            try {
                s = object.get("city");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            String city = s.toString();
            Log.v("***City is***", city);
            City.setText("City:  " + city);

        }
    }
    public void openDashboardActivity(View v){
        Intent intent = new Intent(UserProfileActivity.this, DashboardActivity.class);
        intent.putExtra("Username",user);
        startActivity(intent);
        finish();
    }

}

