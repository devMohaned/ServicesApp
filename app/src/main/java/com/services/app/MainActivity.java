package com.services.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.services.app.activities.AddPeopleNamesActivity;
import com.services.app.activities.AlarmManagerActivity;
import com.services.app.activities.MusicForeverActivity;
import com.services.app.activities.MusicNotForeverActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        CardView mIntentService = findViewById(R.id.intent_serv);
        mIntentService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddPeopleNamesActivity.class);
                startActivity(intent);
            }
        });

        CardView mAlarmManager = findViewById(R.id.intent_alarm);
        mAlarmManager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AlarmManagerActivity.class);
                startActivity(intent);
            }
        });

        CardView mMusicNotForever = findViewById(R.id.music_nfev);
        mMusicNotForever.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MusicNotForeverActivity.class);
                startActivity(intent);
            }
        });

        CardView mMusicForever = findViewById(R.id.music_fev);
        mMusicForever.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MusicForeverActivity.class);
                startActivity(intent);
            }
        });


    }
}
