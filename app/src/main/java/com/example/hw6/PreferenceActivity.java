/*
@venugopal
 */

package com.example.hw6;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Switch;
import android.widget.Toast;

public class PreferenceActivity extends AppCompatActivity
{
    private static final String TAG = "PreferenceActivity";
    private static final String PREFERENCE_FILE_KEY = "4180PreferenceActivity";
    private static final String UNIT_KEY = "UNIT";
    private static boolean startingValue;

    public static SharedPreferences sharedPreferences;
    public static boolean isFahrenheit = false;
    public static INeedsUpdateFromSettings activity;


    private Switch switchUnit;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preference);

        sharedPreferences = getSharedPreferences(PREFERENCE_FILE_KEY, MODE_PRIVATE);
        isFahrenheit = getSavedUnit();

        startingValue = isFahrenheit;

        switchUnit = (Switch) findViewById(R.id.switchUnit);
        switchUnit.setChecked(isFahrenheit);
        setSwitchText();



        switchUnit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                isFahrenheit = !isFahrenheit;
                setSwitchText();
                saveUnit();

                Toast.makeText(PreferenceActivity.this,
                        getString(R.string.lbl_toast_unit_changed) + " °" +  (isFahrenheit? "F" : "C"),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static void initSettings(Context context)
    {
        if(sharedPreferences == null)
            sharedPreferences =  context.getSharedPreferences(PREFERENCE_FILE_KEY, MODE_PRIVATE);

        isFahrenheit = getSavedUnit();
    }

    private void setSwitchText()
    {
        switchUnit.setText(isFahrenheit? "F" : "C");
    }

    private static void saveUnit()
    {
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putBoolean(UNIT_KEY, isFahrenheit);
        editor.commit();
    }

    private static boolean getSavedUnit()
    {
        return sharedPreferences.getBoolean(UNIT_KEY, false);
    }

    @Override
    public void onBackPressed()
    {
        if(startingValue != isFahrenheit && activity != null)
            activity.updateAfterSettingsChange();

        super.onBackPressed();
    }

    public interface INeedsUpdateFromSettings
    {
        public void updateAfterSettingsChange();
    }
}
