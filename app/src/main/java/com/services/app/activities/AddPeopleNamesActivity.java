package com.services.app.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.services.app.R;
import com.services.app.services.AddingAllPeopleService;

public class AddPeopleNamesActivity extends AppCompatActivity implements View.OnClickListener{
    public static String ADD_ALL_PEOPLE = "add-all-people";
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
        Intent intent = new Intent(this, AddingAllPeopleService.class);
        switch (src.getId()) {
            case R.id.buttonStart:
                intent.setAction(ADD_ALL_PEOPLE);
                startService(intent);

                break;
            case R.id.buttonStop:
                stopService(intent);
                break;
            case R.id.buttonBack:
               finish();
                break;
        }
    }
}