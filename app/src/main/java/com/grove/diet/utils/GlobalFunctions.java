package com.grove.diet.utils;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import java.util.Calendar;

public class GlobalFunctions {

    public static  long getTime(int hr, int min, int day, boolean add){
        Calendar cal = Calendar.getInstance();

        cal.set(Calendar.HOUR_OF_DAY, hr);
        cal.set(Calendar.MINUTE, min);

        cal.set(Calendar.DAY_OF_WEEK,day);
        if(add){
            cal.add(Calendar.DAY_OF_MONTH, 7);
        }
//2-> Monday,//4->Wed // 5->Thrus
        return cal.getTimeInMillis()- 300000;

    }

    public static long getResetTime(long time){
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        calendar.add(Calendar.DAY_OF_MONTH, 7);
        return calendar.getTimeInMillis();
    }

    public static void setAlarm(long timeAlarm, Context activity,String name) {
        AlarmManager alarmManager = (AlarmManager) activity.getSystemService(Context.ALARM_SERVICE);
        Intent notificationIntent = new Intent(activity, AlarmReceiver.class);
        notificationIntent.putExtra(Constants.mealName,name);
        try {
            PendingIntent broadcast = PendingIntent.getBroadcast(activity, 2000, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            if (Build.VERSION.SDK_INT >= 23) {
                alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, (timeAlarm), broadcast);
            } else {
                alarmManager.setExact(AlarmManager.RTC_WAKEUP, (timeAlarm), broadcast);
            }
        } catch (Exception ee) {
            ee.printStackTrace();
        }
    }

    public static void disableAlarm(Activity activity){
        try {
            AlarmManager alarmManager = (AlarmManager) activity.getSystemService(Context.ALARM_SERVICE);
            Intent alarmIntent = new Intent(activity, AlarmReceiver.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(activity, 2000 , alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            alarmManager.cancel(pendingIntent);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }


}
