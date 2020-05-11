package com.example.healthy.Service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

import androidx.core.app.NotificationCompat;

public class Remainder extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        //creation de l'instance
        NotificationHelper notificationHelper = new NotificationHelper(context);
        //configurer la notification
        NotificationCompat.Builder nb = notificationHelper.getChannelNotification();
        // lancer notificaion
        notificationHelper.getManager().notify(1, nb.build());
    }
}
