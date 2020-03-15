package com.services.app.broadcasts;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.widget.Toast;

import com.services.app.R;
import com.services.app.activities.AddPeopleNamesActivity;
import com.services.app.activities.AlarmManagerActivity;

public class MyBroadcastReciever extends BroadcastReceiver {
    public static final String CHANNEL_ID = "MyBroadcastRecieverChannel";

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Alarm Triggered", Toast.LENGTH_SHORT).show();

        createNotificationChannel(context);
        final Intent notificationIntent = new Intent(context, AlarmManagerActivity.class);
        final PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, notificationIntent, 0);

                Notification.Builder mBuilder =
                        new Notification.Builder(context)
                                .setSmallIcon(R.mipmap.ic_launcher)
                                .setContentTitle("ALAAAAAAAAAAAAAAAAARM")
                                .setContentText("ALARM ya 7biby !")
                                .setContentIntent(pendingIntent); //Required on Gingerbread and below
                NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
                notificationManager.notify(0, mBuilder.build());

    }

    private void createNotificationChannel(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel serviceChannel = new NotificationChannel(
                    CHANNEL_ID,
                    "Background Service Channel",
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            NotificationManager manager = context.getSystemService(NotificationManager.class);
            manager.createNotificationChannel(serviceChannel);


        }

    }

}
