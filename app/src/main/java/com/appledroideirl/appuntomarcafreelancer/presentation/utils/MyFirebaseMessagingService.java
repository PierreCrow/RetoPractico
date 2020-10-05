package com.appledroideirl.appuntomarcafreelancer.presentation.utils;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.appledroideirl.appuntomarcafreelancer.R;
import com.appledroideirl.appuntomarcafreelancer.domain.model.Usuario;
import com.appledroideirl.appuntomarcafreelancer.presentation.ui.activities.MainActivity;
import com.appledroideirl.appuntomarcafreelancer.presentation.ui.activities.Splash;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {


    String cuerpo, titulo, mensajito, mensjito2;

    static Integer idNotificacion = 1;


    private static final String TAG = "FCM Service";


    @Override
    public void onNewToken(String s) {
        super.onNewToken(s);
        String tokenn = s;

        SharedPreferences preferenciasssee = getApplicationContext().getSharedPreferences("FCM", Context.MODE_PRIVATE);
        SharedPreferences.Editor editoriieei = preferenciasssee.edit();
        editoriieei.putString("tokenfcm", tokenn);
        editoriieei.apply();

        Usuario userPreference = Helper.getUserAppPreference(getApplicationContext());
        userPreference.setFcm(tokenn);
        Helper.saveUserAppPreference(getApplicationContext(), userPreference);

    }


    //aca es cuando se manda mensaje desde la consola firebase
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // TODO: Handle FCM messages here.

        cuerpo = remoteMessage.getNotification().getBody().toString();
        titulo = remoteMessage.getNotification().getTitle().toString();

        addNotification();

    }


    void addNotification() {

        int notifyID = 1;
        String CHANNEL_ID = "my_channel_01";// The id of the channel.
        CharSequence name = "channel_name";// The user-visible name of the channel.

        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);


        if (android.os.Build.VERSION.SDK_INT < Build.VERSION_CODES.O_MR1) {

            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                    //  .setSmallIcon(R.drawable.notification_icon)
                    .setContentTitle(titulo)
                    .setContentText(cuerpo)
                    .setContentIntent(pendingIntent)
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT);

            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

            // notificationId is a unique int for each notification that you must define
            notificationManager.notify(notifyID, builder.build());

        }


        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, name, importance);
            mChannel.enableVibration(true);
            mChannel.setLightColor(Color.BLUE);
            mChannel.enableLights(true);
/*
            channel.setSound(Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://" + getApplicationContext().getPackageName() + "/" + R.raw.notification),
                    new AudioAttributes.Builder()
                            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                            .setUsage(AudioAttributes.USAGE_NOTIFICATION)
                            .build());
            */

            // Create a notification and set the notification channel.
            Notification notification = new Notification.Builder(getApplicationContext())
                    .setContentTitle(titulo)
                    .setContentText(cuerpo)
                    .setContentIntent(pendingIntent)
                    .setSmallIcon(R.mipmap.logo_appunto)
                    //  .setLargeIcon(BitmapFactory.decodeResource(this.getResources(), R.mipmap.logo_appunto))
                    .setChannelId(CHANNEL_ID)
                    .build();

            NotificationManager mNotificationManager =
                    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            mNotificationManager.createNotificationChannel(mChannel);

            // Issue the notification.
            mNotificationManager.notify(notifyID, notification);


        }


    }


}
