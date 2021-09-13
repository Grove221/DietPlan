package com.grove.diet.utils;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;

import android.media.RingtoneManager;
import android.net.Uri;

import com.grove.diet.R;
import com.grove.diet.activity.MainActivity;

import androidx.core.app.NotificationCompat;

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        GlobalFunctions.setAlarm(GlobalFunctions.getResetTime(System.currentTimeMillis()),context,intent.getStringExtra(Constants.mealName));
        showNotification(context,intent.getStringExtra(Constants.mealName));
     }


    public static void showNotification(Context context , String meal){

        String CHANNEL_ID = "diet";
        CharSequence names = "diet_notification";
        int importance = NotificationManager.IMPORTANCE_HIGH;

        int notificationNumber = (int) System.currentTimeMillis();

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = null;
        notificationBuilder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(context.getString(R.string.app_name))
                .setContentText(context.getString(R.string.time_for)+" "+meal)
                .setPriority(Notification.PRIORITY_MAX)
                .setAutoCancel(true)
                .setSound(defaultSoundUri);


        PackageManager pm = context.getPackageManager();
        Intent launchIntent = pm.getLaunchIntentForPackage("com.grove.diet");

        Intent notificationIntent = new Intent(context, MainActivity.class);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        PendingIntent contentIntent = PendingIntent.getActivity(context, 100,
                launchIntent, PendingIntent.FLAG_UPDATE_CURRENT );

        notificationBuilder.setContentIntent(contentIntent);
        notificationBuilder.setChannelId(CHANNEL_ID);
        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, names, importance);
            notificationManager.createNotificationChannel(channel);
        }
        notificationManager.notify(notificationNumber, notificationBuilder.build());
    }


}