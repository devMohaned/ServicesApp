package com.services.app.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.services.app.R;
import com.services.app.services.MusicServiceForever;
import com.services.app.services.MusicServiceNotForever;

public class MusicNotForeverActivity extends AppCompatActivity implements View.OnClickListener{
    Button buttonStart, buttonStop,buttonNext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_forever);

        buttonStart = findViewById(R.id.buttonStart);
        buttonStop = findViewById(R.id.buttonStop);
        buttonNext =  findViewById(R.id.buttonBack);

        buttonStart.setOnClickListener(this);
        buttonStop.setOnClickListener(this);
        buttonNext.setOnClickListener(this);


    }
    public void onClick(View src) {
        switch (src.getId()) {
            case R.id.buttonStart:
                startService(new Intent(this, MusicServiceNotForever.class));
                break;
            case R.id.buttonStop:
                stopService(new Intent(this, MusicServiceNotForever.class));
                break;
            case R.id.buttonBack:
               finish();
                break;
        }
    }

    // It's preferred to use onStop instead of onDestroy.
    @Override
    protected void onDestroy() {
        stopService(new Intent(this, MusicServiceNotForever.class));
        super.onDestroy();
    }
}