package com.services.app.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.services.app.R;
import com.services.app.broadcasts.MyBroadcastReciever;
import com.services.app.services.MusicServiceForever;

public class AlarmManagerActivity extends AppCompatActivity implements View.OnClickListener{
    Button buttonStart, buttonStop, buttonNext;
    public static final int REQUEST_CODE=101;

    AlarmManager alarmManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_manager);
        alarmManager= (AlarmManager) getSystemService(ALARM_SERVICE);
        buttonStart = findViewById(R.id.buttonStart);
        buttonStop = findViewById(R.id.buttonStop);
        buttonNext = findViewById(R.id.buttonBack);

        buttonStart.setOnClickListener(this);
        buttonStop.setOnClickListener(this);
        buttonNext.setOnClickListener(this);


    }

    public void onClick(View src) {
        switch (src.getId()) {
            case R.id.buttonStart:
                // AlarmManager.INTERVAL_HOUR
                initAlarmManager(5000);
                break;
            case R.id.buttonStop:
                cancelAlarmManager();
                break;
            case R.id.buttonBack:
                finish();
                break;
        }
    }

    private void initAlarmManager(int timeInMilliSeconds){
        Intent intent=new Intent(this, MyBroadcastReciever.class);
        PendingIntent pendingIntent =  PendingIntent.getBroadcast(this,REQUEST_CODE,intent, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + timeInMilliSeconds, pendingIntent);
    }

    private void cancelAlarmManager(){
        Intent intent=new Intent(this, MyBroadcastReciever.class);
        PendingIntent pendingIntent =  PendingIntent.getBroadcast(this,REQUEST_CODE,intent, PendingIntent.FLAG_NO_CREATE);
        alarmManager.cancel(pendingIntent);
    }
}