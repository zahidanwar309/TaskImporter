package com.crowdlab.taskimporter.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;
import com.crowdlab.taskimporter.utils.Logger;

public class SplashActivity extends Activity
{
    /** Called when the activity is first created.
     * @param savedInstanceState */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity);

        try {
            TextView versionNumber = (TextView) findViewById(R.id.txt_version_number);

            if (versionNumber != null) {
                versionNumber.setText("v- " + getPackageManager().getPackageInfo(getPackageName(), 0).versionName + "");
            }

        } catch (Exception e) {
            Logger.e("Could not set version number on splash screen", e);
        }
        
        
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent taskList = new Intent(SplashActivity.this, TaskListActivity.class);
                startActivity(taskList);
                Logger.d("Draw TaskListActivity activity");
                
            }
        }, 1000);
    }
}
