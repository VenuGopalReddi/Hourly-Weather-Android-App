/*
@venugopal
 */

package com.example.hw6;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;


public class WeatherUtil
{
    public static final String WEATHER_UTIL_DEBUG = "WeatherUtil";

    public static ArrayList<Weather> getDailyWeatherSummary(ArrayList<Weather> hourlyWeather)
    {
        ArrayList<Weather> dailyWeather = new ArrayList<>();

        double avgTemp = 0.0;
        int amountThisDay = 0;
        Weather currDayWeather;

        Date lastDate = hourlyWeather.get(0).getRealDate();

        for(Weather currWeater : hourlyWeather)
        {
            Date currDate = currWeater.getRealDate();

            if(!currDate.equals(lastDate))
            {
                currDayWeather = new Weather();
                currDayWeather.setRealDate(new SimpleDateFormat("yyyy-MM-dd").format(lastDate));
                currDayWeather.setTemp(avgTemp / amountThisDay);
                currDayWeather.setRealIconURI(currWeater.getIconURI());

                dailyWeather.add(currDayWeather);

                avgTemp = 0.0;
                amountThisDay = 0;
            }

            avgTemp += currWeater.getTemp();
            ++amountThisDay;

            lastDate = currDate;
        }

        currDayWeather = new Weather();
        currDayWeather.setRealDate(new SimpleDateFormat("yyyy-MM-dd").format(lastDate));
        currDayWeather.setTemp(avgTemp / amountThisDay);
        currDayWeather.setRealIconURI(hourlyWeather.get(hourlyWeather.size() - 1).getIconURI());

        dailyWeather.add(currDayWeather);

        return dailyWeather;
    }

    public static HashMap<Date, ArrayList<Weather>> getDailyWeatherFromHourly(ArrayList<Weather> hourlyWeather)
    {
        HashMap<Date, ArrayList<Weather>> dateToWeather = new HashMap<>();
        ArrayList<Weather> dailyWeather = new ArrayList<>();

        Date lastDate = hourlyWeather.get(0).getRealDate();

        for(Weather currWeater : hourlyWeather)
        {
            Date currDate = currWeater.getRealDate();

            if(!currDate.equals(lastDate))
            {
                dateToWeather.put(lastDate, dailyWeather);
                dailyWeather = new ArrayList<>();
            }

            dailyWeather.add(currWeater);

            lastDate = currDate;
        }

        dateToWeather.put(lastDate, dailyWeather);

        return dateToWeather;
    }

    public static class JSONParser
    {
        public static ArrayList<Weather> parse(String jsonString) throws JSONException
        {
            ArrayList<Weather> weather = new ArrayList<>();

            JSONArray weatherJSONArray = new JSONObject(jsonString).getJSONArray("list");

            for(int i = 0; i < weatherJSONArray.length(); i++)
            {
                JSONObject weatherJSONObject = weatherJSONArray.getJSONObject(i);
                Weather currWeather = new Weather();

                JSONObject mainWeather = weatherJSONObject.getJSONObject("main");
                currWeather.setTemp(mainWeather.getDouble("temp"));
                currWeather.setPressure(mainWeather.getString("pressure"));
                currWeather.setHumidity(mainWeather.getString("humidity"));

                JSONArray weatherArray = weatherJSONObject.getJSONArray("weather");
                currWeather.setCondition(weatherArray.getJSONObject(0).getString("main"));
                currWeather.setIconURI(weatherArray.getJSONObject(0).getString("icon"));

                JSONObject wind = weatherJSONObject.getJSONObject("wind");
                currWeather.setWindSpeed(wind.getString("speed"));
                currWeather.setWindDir(wind.getString("deg"));

                currWeather.setDate(weatherJSONObject.getString("dt_txt"));

                weather.add(currWeather);
            }

            return weather;
        }
    }
}