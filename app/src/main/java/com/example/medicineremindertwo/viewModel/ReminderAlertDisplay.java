package com.example.medicineremindertwo.viewModel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.medicineremindertwo.R;

public class ReminderAlertDisplay extends AppCompatActivity {
    Button openApp;
    TextView reminder_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder_alert_display)
        ;
        reminder_id = (TextView) findViewById(R.id.reminder_id);
        openApp = (Button) findViewById(R.id.open_app_id);

        openApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ReminderAlertDisplay.this, SetReminder.class));

            }
        });
    }
}