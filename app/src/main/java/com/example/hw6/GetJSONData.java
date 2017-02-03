/*
@venugopal
 */

package com.example.hw6;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class GetJSONData extends AsyncTask<String, Void, ArrayList<Weather>>
{
    private IJSONActivity activity;

    public GetJSONData(IJSONActivity activity)
    {
        this.activity = activity;
    }

    @Override
    protected void onPostExecute(ArrayList<Weather> weather) {
        super.onPostExecute(weather);

        activity.setJSONList(weather);
    }

    @Override
    protected ArrayList<Weather> doInBackground(String... params)
    {
        try
        {
            URL url = new URL(params[0]);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            if(connection.getResponseCode() == HttpURLConnection.HTTP_OK)
            {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(connection.getInputStream()));
                StringBuilder sb = new StringBuilder();

                String line = reader.readLine();
                while(line != null)
                {
                    sb.append(line);
                    line = reader.readLine();
                }

                return WeatherUtil.JSONParser.parse(sb.toString());
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return null;
    }

    public interface IJSONActivity
    {
        void setJSONList(ArrayList<Weather> weather);
    }
}