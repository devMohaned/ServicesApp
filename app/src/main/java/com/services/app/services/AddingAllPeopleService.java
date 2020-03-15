package com.services.app.services;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.services.app.R;
import com.services.app.activities.AddPeopleNamesActivity;
import com.services.app.activities.MusicForeverActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static com.services.app.activities.AddPeopleNamesActivity.ADD_ALL_PEOPLE;
// One Time Service
public class AddingAllPeopleService extends IntentService {
    public static final String CHANNEL_ID = "AddingAllPeopleServiceChannel";


    public AddingAllPeopleService() {
        super("AddingAllPeopleService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        if (intent != null && intent.getAction().equals(ADD_ALL_PEOPLE)) {

                BufferedReader reader = null;
                try {
                    reader = new BufferedReader(
                            new InputStreamReader(getAssets().open("names.txt"), "UTF-8"));

                    String mLine;
                    while ((mLine = reader.readLine()) != null) {
                        {
                            // Do something with this line
                            Handler handler = new Handler(Looper.getMainLooper());
                            final String finalMLine = mLine;
                            createNotificationChannel();
                            final Intent notificationIntent = new Intent(this, AddPeopleNamesActivity.class);
                            final PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);
                            handler.post(new Runnable(){
                                @Override
                                public void run() {
                                    Toast.makeText(getApplicationContext(), "El Fager: " + finalMLine, Toast.LENGTH_SHORT).show();

                                    Notification.Builder mBuilder =
                                            new Notification.Builder(getApplicationContext())
                                                    .setSmallIcon(R.mipmap.ic_launcher)
                                                    .setContentTitle("My notification")
                                                    .setContentText("Fager !" + finalMLine)
                                                    .setContentIntent(pendingIntent); //Required on Gingerbread and below
                                    NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                                    notificationManager.notify(0, mBuilder.build());
                                }
                            });

                        }
                    }

                } catch (IOException e) {
                    //log the exception
                } finally {
                    if (reader != null) {
                        try {
                            reader.close();
                        } catch (IOException e) {
                            //log the exception
                        }
                    }
            }
        }
    }


    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel serviceChannel = new NotificationChannel(
                    CHANNEL_ID,
                    "Background Service Channel",
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(serviceChannel);


        }

    }
}
