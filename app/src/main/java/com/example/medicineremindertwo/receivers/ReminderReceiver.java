package com.example.medicineremindertwo.receivers;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Vibrator;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.medicineremindertwo.R;
import com.example.medicineremindertwo.viewModel.ReminderAlertDisplay;
import com.example.medicineremindertwo.viewModel.SetReminder;

public class ReminderReceiver extends BroadcastReceiver {
    Vibrator vibrator;

    @Override
    public void onReceive(Context context, Intent intent) {
        vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(60*1000);

        Intent notificationCLickedIntent = new Intent(context.getApplicationContext(), ReminderAlertDisplay.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context.getApplicationContext(),20,notificationCLickedIntent,PendingIntent.FLAG_UPDATE_CURRENT);
        Uri alarmSound = RingtoneManager. getDefaultUri (RingtoneManager.TYPE_NOTIFICATION );
        MediaPlayer mp = MediaPlayer. create (context.getApplicationContext(), alarmSound);
        mp.start();

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "drug_reminder")
                .setSmallIcon(R.drawable.ic_baseline_access_alarms_24)
                .setContentTitle("Drug Reminder App")
                .setContentIntent(pendingIntent)
                .setContentText(SetReminder.drug_name)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
        notificationManagerCompat.notify(100, builder.build());
    }
}
