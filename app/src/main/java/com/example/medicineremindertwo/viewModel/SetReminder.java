package com.example.medicineremindertwo.viewModel;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.medicineremindertwo.databaseModels.DatabaseController;
import com.example.medicineremindertwo.R;

import java.util.ArrayList;
import java.util.Calendar;


public class SetReminder extends AppCompatActivity {
    CheckBox every_day,dv_sunday,dv_monday,dv_tuesday,dv_wednesday,dv_thursday,dv_friday,dv_saturday;
    DatabaseController databaseController;
    EditText drug_name_editText;
    Button btnSetTime, btnSetAlarm, btnScheduleAlarm;
    Integer hour, minutes, finalHour, finalMinutes;
    TextView place_text;
    String weekDays = "";

    ArrayList<String> listOfSelectedDays;

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
                Intent reminderIntent = new Intent(SetReminder.this, ListOfReminders.class);
                startActivity(reminderIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_reminder);
        databaseController = new DatabaseController(SetReminder.this);

        listOfSelectedDays = new ArrayList<>();

        every_day = findViewById(R.id.every_day);
        dv_monday = findViewById(R.id.dv_monday);
        dv_tuesday = findViewById(R.id.dv_tuesday);
        dv_wednesday = findViewById(R.id.dv_wednesday);
        dv_thursday = findViewById(R.id.dv_thursday);
        dv_friday = findViewById(R.id.dv_friday);
        dv_saturday = findViewById(R.id.dv_saturday);
        dv_sunday = findViewById(R.id.dv_sunday);

        drug_name_editText= (EditText) findViewById(R.id.drug_name_editText);
        btnSetTime = (Button) findViewById(R.id.btnSetTime);
        btnSetAlarm = (Button) findViewById(R.id.btnSetAlarm);
        btnScheduleAlarm = (Button) findViewById(R.id.btnScheduleAlarm);
        place_text = (TextView) findViewById(R.id.place_text);

        btnScheduleAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //               place_text.setText(getSelectedDays(listOfSelectedDays));
                //                place_text.setText(listOfSelectedDays.get(0).toString());

            }
        });

        every_day.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    dv_monday.setChecked(true);
                    dv_tuesday.setChecked(true);
                    dv_wednesday.setChecked(true);
                    dv_thursday.setChecked(true);
                    dv_friday.setChecked(true);
                    dv_saturday.setChecked(true);
                    dv_sunday.setChecked(true);
                    //Add the arrays if the days are selected
                    listOfSelectedDays.add("Sun");
                    listOfSelectedDays.add("Mon");
                    listOfSelectedDays.add("Tue");
                    listOfSelectedDays.add("Wed");
                    listOfSelectedDays.add("Thu");
                    listOfSelectedDays.add("Fri");
                    listOfSelectedDays.add("Sat");
                }else{
                    dv_monday.setChecked(false);
                    dv_tuesday.setChecked(false);
                    dv_wednesday.setChecked(false);
                    dv_thursday.setChecked(false);
                    dv_friday.setChecked(false);
                    dv_saturday.setChecked(false);
                    dv_sunday.setChecked(false);
                    listOfSelectedDays.clear();
                }
            }
        });

        Calendar calendar = Calendar.getInstance();
        hour = calendar.get(Calendar.HOUR_OF_DAY);
        minutes =calendar.get(Calendar.MINUTE);

        btnSetTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(SetReminder.this, new TimePickerDialog.OnTimeSetListener() {
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
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                String name = drug_name_editText.getText().toString();
                //now copy you calendar for the other days
                //                Calendar calWednesday = (Calendar) calendar.clone();
                //                Calendar calFriday = (Calendar) calendar.clone();
                //                Calendar calTuesday = (Calendar) calendar.clone();
                //                Calendar calThursday = (Calendar) calendar.clone();
                //                Calendar calSaturday = (Calendar) calendar.clone();
                //                Calendar calSunday = (Calendar) calendar.clone();
                //
                //                calendar.set(Calendar.HOUR_OF_DAY, finalHour);
                //                calendar.set(Calendar.MINUTE, finalMinutes);
                //                //set week days
                //                calendar.set(Calendar.DAY_OF_WEEK, 2);
                //                calWednesday.set(Calendar.DAY_OF_WEEK, 4);
                //                calFriday.set(Calendar.DAY_OF_WEEK, 6);
                //                calTuesday.set(Calendar.DAY_OF_WEEK, 3);
                //                calSaturday.set(Calendar.DAY_OF_WEEK, 7);
                //                calSunday.set(Calendar.DAY_OF_WEEK, 1);

                //                AlarmManager alarmMgr = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
                //                alarmMgr.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pIntent(SetReminder.this, 7));
                //                alarmMgr.set(AlarmManager.RTC_WAKEUP, calWednesday.getTimeInMillis(), pIntent(SetReminder.this, 4));
                //                alarmMgr.set(AlarmManager.RTC_WAKEUP, calFriday.getTimeInMillis(), pIntent(SetReminder.this,6));

                if (!name.isEmpty()){
                    if (!btnSetTime.getText().equals("Set time")){
                        boolean result = databaseController.insertDrugData(name, finalHour, finalMinutes);
                        if (result){
                            drug_name_editText.setText("");
                            btnSetTime.setText("Set Time");
                            Toast.makeText(getApplicationContext(), "Alarm set", Toast.LENGTH_SHORT).show();
                            Intent alarm = new Intent(AlarmClock.ACTION_SET_ALARM);
                            alarm.putExtra(AlarmClock.EXTRA_HOUR, finalHour);
                            alarm.putExtra(AlarmClock.EXTRA_MINUTES, finalMinutes);
                            alarm.putExtra(AlarmClock.EXTRA_MESSAGE, "Take "+name);
                            startActivity(alarm);
                        }else {
                            Toast.makeText(getApplicationContext(), "Data not sent", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(getApplicationContext(), "Please set time", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(getApplicationContext(), "Please set drug name", Toast.LENGTH_SHORT).show();

                }
            }
        });



    }

    public void ifAllDaysChecked(){
        if(dv_monday.isChecked() && dv_tuesday.isChecked() && dv_wednesday.isChecked() &&
                dv_thursday.isChecked() && dv_friday.isChecked() && dv_saturday.isChecked() && dv_sunday.isChecked()){
            every_day.setChecked(true);
        }else {
            every_day.setChecked(false);
        }
    }

    public void onCheckboxClicked(View view){

        boolean checked = ((CheckBox) view).isChecked();
        //        checkIfTimeIsSet();

        // Check which checkbox was clicked
        switch(view.getId()) {
            case R.id.dv_monday:
                if (checked){
                    listOfSelectedDays.add(dv_monday.getText().toString());
                }else{
                    listOfSelectedDays.remove(listOfSelectedDays.indexOf(dv_monday.getText()));
                }


                break;
            case R.id.dv_tuesday:
                if (checked){

                    listOfSelectedDays.add(dv_tuesday.getText().toString());


                } else{
                    listOfSelectedDays.remove(listOfSelectedDays.indexOf(dv_tuesday.getText()));
                }
                break;

            case R.id.dv_wednesday:
                if (checked){
                    listOfSelectedDays.add(dv_wednesday.getText().toString());

                } else{
                    listOfSelectedDays.remove(listOfSelectedDays.indexOf(dv_wednesday.getText()));
                }
                break;

            case R.id.dv_thursday:
                if (checked){
                    listOfSelectedDays.add(dv_thursday.getText().toString());

                } else{
                    listOfSelectedDays.remove(listOfSelectedDays.indexOf(dv_thursday.getText()));
                }
                break;

            case R.id.dv_friday:
                if (checked){
                    listOfSelectedDays.add(dv_friday.getText().toString());
                } else{
                    listOfSelectedDays.remove(listOfSelectedDays.indexOf(dv_friday.getText()));
                }
                break;

            case R.id.dv_saturday:
                if (checked){
                    listOfSelectedDays.add(dv_saturday.getText().toString());

                } else{
                    listOfSelectedDays.remove(listOfSelectedDays.indexOf(dv_saturday.getText()));
                }
                break;

            case R.id.dv_sunday:
                if (checked){
                    listOfSelectedDays.add(dv_sunday.getText().toString());
                } else{
                    listOfSelectedDays.remove(listOfSelectedDays.indexOf(dv_sunday.getText()));
                }
                break;
        }
    }

    String getSelectedDays(ArrayList<String> days){
        String allDays = "";
        for (int x = 0; x < days.size(); x++){
            allDays = allDays + " "+days.get(x);
        }
        return allDays;
    }
    //this part is under construction
    public void checkIfTimeIsSet(){
        if (btnSetTime.getText().equals("Set time")){
            place_text.setText("Please set time...");
        }else {
            place_text.setText("");
        }
    }

    public void cloningTime(ArrayList<String> selectedDays, Calendar newCalendar){
        for (int i=0; i<selectedDays.size(); i++){

        }

    }


    //pIntent method
    //    public static PendingIntent pIntent(Context context, int id){
    //        Intent intent = new Intent();
    //        intent.setAction("myAlarm.intent.action.CLOCK");
    //
    //        return PendingIntent.getBroadcast(context, id, intent,    PendingIntent.FLAG_UPDATE_CURRENT);
    //    }
}