package com.example.deepakmandhani.asynctaskgetrequest;

import android.os.AsyncTask;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by deepak.mandhani on 11/03/18.
 */

public class HttpPostRequestAsyncTask extends AsyncTask<String, Void, JSONObject> {

    HttpClient httpClient = new DefaultHttpClient();
    HttpPost httpPost;
    JSONObject jsonObject;

    @Override
    protected JSONObject doInBackground(String... params) {

        String url = params[0];
        return getUrlDataByPost(url);
    }

    public JSONObject getUrlDataByPost(String url) {

        httpPost = new HttpPost("url");
        BasicNameValuePair basicNameValuePair = new BasicNameValuePair("key", "value");
        ArrayList<NameValuePair> nameValuePairs = new ArrayList<>();
        nameValuePairs.add(basicNameValuePair);
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            HttpResponse httpResponse = httpClient.execute(httpPost);
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
        } catch (JSONException jsonException) {
            jsonException.printStackTrace();
        }
        return jsonObject;
    }

    @Override
    protected void onPostExecute(JSONObject jsonObject) {
        super.onPostExecute(jsonObject);
        // did nothing here
    }
}
