package com.example.deepakmandhani.asynctaskgetrequest;

import android.os.AsyncTask;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by deepak.mandhani on 11/03/18.
 */

public class HttpGetRequestAsyncTask extends AsyncTask<String, Void, JSONObject> {

    HttpClient httpClient = new DefaultHttpClient();
    HttpGet httpGet;
    JSONObject jsonObject;

    @Override
    protected JSONObject doInBackground(String... strings) {
        String url = strings[0];
        httpGet = new HttpGet(url);
        try {
            HttpResponse httpResponse = httpClient.execute(httpGet);
            InputStream inputStream = httpResponse.getEntity().getContent();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            StringBuilder responseString = new StringBuilder();
            while ((line = bufferedReader.readLine()) != null) {
                responseString.append(line);
            }

            jsonObject = new JSONObject(responseString.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
}
