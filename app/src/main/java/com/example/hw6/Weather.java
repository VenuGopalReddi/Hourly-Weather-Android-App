/*
@venugopal
 */

package com.example.hw6;

import android.util.Log;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Weather implements Serializable
{
    private static final String DEBUG_TAG = "WEATHER";

    private String pressure, humidity, condition;
    private String date, hour, windSpeed, windDir;
    private String iconURI;
    private double temp;

    public String getIconURI()
    {
        return iconURI;
    }

    // don't ask, thanks
    public void setRealIconURI(String iconURI)
    {
        this.iconURI = iconURI;
    }

    public void setIconURI(String iconURI)
    {
        this.iconURI = "http://openweathermap.org/img/w/" + iconURI + ".png";
    }

    public double getTemp()
    {
        return temp;
    }

    public void setTemp(double temp)
    {
        this.temp = temp;
    }

    public String getPressure()
    {
        return pressure;
    }

    public void setPressure(String pressure)
    {
        this.pressure = pressure;
    }

    public String getHumidity()
    {
        return humidity;
    }

    public void setHumidity(String humidity)
    {
        this.humidity = humidity;
    }

    public String getCondition()
    {
        return condition;
    }

    public void setCondition(String condition)
    {
        this.condition = condition;
    }

    public Date getRealDate()
    {
        try
        {
            return new SimpleDateFormat("yyyy-MM-dd").parse(date);
        } catch (ParseException e)
        {
            return new Date(0);
        }
    }

    public String getDate()
    {
        return date;
    }

    // don't ask thanks
    public void setRealDate(String date)
    {
        this.date = date;
    }

    public void setDate(String date)
    {
        int space = date.indexOf(" "), endHour = date.indexOf(":");
        String realDate = date.substring(0, space);

        this.date = realDate;
        this.hour = date.substring(space + 1, endHour);
    }

    public String getHour()
    {
        return hour;
    }

    public String getWindSpeed()
    {
        return windSpeed;
    }

    public void setWindSpeed(String windSpeed)
    {
        this.windSpeed = windSpeed;
    }

    public String getWindDir()
    {
        return windDir + "°";
    }

    public void setWindDir(String windDir)
    {
        this.windDir = windDir;
    }

    public String getTempFahrenheit()
    {
        double fahrenheit = 1.8 * (temp - 273.15) + 32; // Kelvin -> Fahrenheit

        return String.format( "%.2f", fahrenheit) + "° F";
    }

    public String getTempCelsius()
    {
        double celsius = temp - 273.15; // Kelvin -> Celsius

        return String.format( "%.2f", celsius) + "° C";
    }

    public String getFormattedDate()
    {
        String formattedDate;

        try
        {
            formattedDate = new SimpleDateFormat("MMM dd, yyyy")
                    .format(new SimpleDateFormat("yyyy-MM-dd").parse(date));
        } catch (ParseException e)
        {
            Log.w(DEBUG_TAG, "Unable to getFormattedDate!");
            return date;  // We tried.. :(
        }

        return formattedDate;
    }

    public String getFormattedHour()
    {
        int intHour = Integer.parseInt(hour);

        int realHour = intHour == 0 || intHour == 12 ? 12 : intHour % 12;

        return realHour + ":00 " + ((intHour >= 12) ? "PM" : "AM");

    }

    @Override
    public String toString()
    {
        return "Weather{" +
                "temp='" + temp + '\'' +
                ", pressure='" + pressure + '\'' +
                ", humidity='" + humidity + '\'' +
                ", condition='" + condition + '\'' +
                ", date='" + date + '\'' +
                ", hour='" + hour + '\'' +
                ", windSpeed='" + windSpeed + '\'' +
                ", windDir='" + windDir + '\'' +
                ", iconURI='" + iconURI + '\'' +
                '}' + '\n';
    }
}