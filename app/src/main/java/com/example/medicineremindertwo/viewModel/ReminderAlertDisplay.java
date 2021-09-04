package com.example.medicineremindertwo.viewModel;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.medicineremindertwo.R;

public class ReminderAlertDisplay extends AppCompatActivity {
    Button openApp;
    TextView reminder_id;
    Vibrator vibrator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder_alert_display);

        reminder_id = (TextView) findViewById(R.id.reminder_id);
        openApp = (Button) findViewById(R.id.open_app_id);
        openApp.setVisibility(View.GONE);
        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(60 * 1000);
        Uri alarmSound = RingtoneManager. getDefaultUri (RingtoneManager. TYPE_NOTIFICATION );
        MediaPlayer mp = MediaPlayer. create (getApplicationContext(), alarmSound);
        mp.start();
        mp.stop();
        startActivity(new Intent(ReminderAlertDisplay.this, ListOfReminders.class));
        vibrator.cancel();

    }
}