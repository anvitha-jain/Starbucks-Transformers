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

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class LoginActivity extends AppCompatActivity {


    private Button btn_login;
    private EditText txt_username;
    private EditText txt_password;

    String sBaseURL = "http://ec2-54-185-174-206.us-west-2.compute.amazonaws.com:5000/";
    String sEndpoint = "validUsers";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btn_login = findViewById(R.id.login);
        txt_username = findViewById(R.id.enterUserName);
        txt_password = findViewById(R.id.enterPassword);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String username = txt_username.getText().toString();
                String password = txt_password.getText().toString();
                
                if(isValid(username, password))
                {
                    String serviceURL = sBaseURL + sEndpoint + "?username="+username+ "&password="+password;

                    LoginActivity.HttpGetRequest runner = new LoginActivity.HttpGetRequest();

                    runner.execute(serviceURL);
                }
                else
                {
                    Toast.makeText(LoginActivity.this, "Please fill username & password.", Toast.LENGTH_SHORT).show();
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
                JSONObject response = new JSONObject(apiResponse);
                String message = response.getString("result");
                return message;

        }

        protected void onPostExecute(String result){
            super.onPostExecute(result);
            if(result.equals("Invalid credentials"))
            {
                Toast.makeText(getApplicationContext(), "Invalid credentials", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(getApplicationContext(), "Successfully Logged In", Toast.LENGTH_SHORT).show();
                String username = txt_username.getText().toString();

                Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
                intent.putExtra("Username",username);
                intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
                startActivity(intent);
                finish();
            }

        }

    }

    public void openRegisterActivity(View v){
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
        finish();
    }

    public boolean isValid(String userName, String password)
    {
        if(userName.isEmpty() || password.isEmpty())
            return false;

        return true;
    }


}
