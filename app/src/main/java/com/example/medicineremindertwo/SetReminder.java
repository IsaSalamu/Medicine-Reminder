package com.example.medicineremindertwo;


import android.app.TimePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class SetReminder extends AppCompatActivity {
    DatabaseController databaseController;
    EditText drug_name_editText;
    Button btnSetTime, btnSetAlarm, btnScheduleAlarm;
    Integer hour, minutes, finalHour, finalMinutes;
    TextView place_text;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.reminders_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.reminders:
                Intent reminderIntent = new Intent(com.example.medicineremindertwo.SetReminder.this, ListOfReminders.class);
                startActivity(reminderIntent);
                return true;
            default:

                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databaseController = new DatabaseController(com.example.medicineremindertwo.SetReminder.this);

        drug_name_editText= (EditText) findViewById(R.id.drug_name_editText);
        btnSetTime = (Button) findViewById(R.id.btnSetTime);
        btnSetAlarm = (Button) findViewById(R.id.btnSetAlarm);
        btnScheduleAlarm = (Button) findViewById(R.id.btnScheduleAlarm);
        place_text = (TextView) findViewById(R.id.place_text);


        btnScheduleAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showData();
            }
        });
        Calendar calendar = Calendar.getInstance();
        hour = calendar.get(Calendar.HOUR_OF_DAY);
        minutes =calendar.get(Calendar.MINUTE);
        btnSetTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(com.example.medicineremindertwo.SetReminder.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        finalHour = hourOfDay;
                        finalMinutes = minute;
                        btnSetTime.setText("Alarm time set at "+finalHour.toString()+":"+finalMinutes.toString());
                    }
                }, hour, minutes, true);
                timePickerDialog.show();
            }
        });

        btnSetAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = drug_name_editText.getText().toString();
                if (!name.isEmpty()){
                    boolean result = databaseController.insertDrugData(name, finalHour, finalMinutes);
                    if (result){
                        drug_name_editText.setText("");
                        btnSetTime.setText("Set Time");
//                        Toast.makeText(getApplicationContext(), "Data sent", Toast.LENGTH_SHORT).show();
                        Intent alarm = new Intent(AlarmClock.ACTION_SET_ALARM);
                        alarm.putExtra(AlarmClock.EXTRA_HOUR, finalHour);
                        alarm.putExtra(AlarmClock.EXTRA_MINUTES, finalMinutes);
                        alarm.putExtra(AlarmClock.EXTRA_MESSAGE, "Take "+name);
                        startActivity(alarm);
                    }else {
                        Toast.makeText(getApplicationContext(), "Data not sent", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    public void showData(){
        Cursor cursor = databaseController.getDrugs();
        if (cursor.getCount() == 0){
//                Toast.makeText(getApplicationContext(),"no data", Toast.LENGTH_SHORT).show();
        }else {
            while (cursor.moveToNext()){
                place_text.setText(cursor.getString(1).toString());
//                Toast.makeText(getApplicationContext(), cursor.getString(1).toString(), Toast.LENGTH_SHORT).show();
            }
        }
    }
}
