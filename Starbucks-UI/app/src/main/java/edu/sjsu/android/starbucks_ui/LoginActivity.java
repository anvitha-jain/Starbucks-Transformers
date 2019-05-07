package edu.sjsu.android.starbucks_ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void openDashboard(View v){
        Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
        startActivity(intent);
        finish();
    }

    public void openRegisterActivity(View v){
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
        finish();
    }


   /* public void enableStrictMode()
    {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);
    }*/


   /* public void finishActivity(View v){
        LoginActivity.this.GetAPIResponse();
    }

    public String GetAPIResponse()
    {
        Log.v("**Inside GetAPI***", "apiresponse");
        enableStrictMode();

        URL urlGetRequest = null;
        String apiResponse = null;
        try {
            urlGetRequest = new URL("https://54.185.174.206:5000/users");
            String readLine = null;
            Log.v("**Before HttpURLConn**", "connection");
            HttpURLConnection connection = (HttpURLConnection) urlGetRequest.openConnection();
            //connection.connect();
            Log.v("**After HttpURLConn**", "Connection obtained");
            connection.setRequestMethod("GET");
            Log.v("**After setRequest**", "setRequest");
            int responseCode = connection.getResponseCode();
            System.out.println("Response Code is: " + responseCode);
            if (responseCode == HttpURLConnection.HTTP_OK)
            {
                Log.v("HTTP_OK is 200", "connection successful");
                //Log.v("***func", "Message okay");
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuffer response = new StringBuffer();
                while ((readLine = in.readLine()) != null)
                {
                    response.append(readLine);
                }
                in.close();
                apiResponse = response.toString();
                Log.v("Api Response**********",apiResponse);
            }

        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return apiResponse;

    }*/
}
