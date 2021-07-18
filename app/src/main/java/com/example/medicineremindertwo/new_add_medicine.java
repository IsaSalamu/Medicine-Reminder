package com.example.medicineremindertwo;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

public class new_add_medicine extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText add_drug, id_number_of_days, drugs_quantities;
    Spinner spinner;
    String[] drugs = {"Bactrime", "Amoxillyne","Cipro","Abendazole","Doxycyline"};

    String date, time;
    String final_date_format, final_time_format, am_pm, predef_time;
    String drug;
    DrugsDatabase drugsDatabase;

    /*button to sete time and alarm*/

    Button set_time, set_alarm;

    /*declaring alarm variables*/
    int hr, min, hour, minutes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_add_medicine);

        /*calender settings*/
        Calendar calendar = Calendar.getInstance();
        hr = calendar.get(Calendar.HOUR_OF_DAY);
        min=calendar.get(Calendar.MINUTE);

        drugsDatabase = new DrugsDatabase(this);

        spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, drugs);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(this);

        add_drug = (EditText) findViewById(R.id.add_drug);
        id_number_of_days = (EditText) findViewById(R.id.id_number_of_days);
        drugs_quantities = (EditText) findViewById(R.id.drugs_quantities);

        set_alarm = (Button) findViewById(R.id.set_alarm);
        set_time = (Button) findViewById(R.id.set_time);

        set_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getApplicationContext(),"clicked", Toast.LENGTH_SHORT).show();
                setAlarmTime();
            }
        });

        set_alarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAlarm();
//                setDateAndTime();
//                String name = add_drug.getText().toString();

//                String num_of_days = id_number_of_days.getText().toString();

//                String drugs_quantity = drugs_quantities.getText().toString();

//                if (!num_of_days.isEmpty()) {
//                    drugsDatabase.insertDrugs(drug, Integer.parseInt(num_of_days), Integer.parseInt(drugs_quantity), Integer.parseInt(final_time_format), Integer.parseInt(final_date_format), predef_time);
//                    add_drug.setText(drug);
//                    Toast.makeText(getApplicationContext(), "Uploaded goodly", Toast.LENGTH_SHORT).show();
//                }else {
//                    Toast.makeText(getApplicationContext(), "Uploaded badly", Toast.LENGTH_SHORT).show();
//
//                }
            }
        });

        /* calling alarm method*/




    }



    public void setDateAndTime(){
        date =  DateFormat.getDateInstance().format(new Date());
        time = DateFormat.getTimeInstance().format(new Date());

        String[] newDate = date.split(",");
        String[] new_date_format = newDate[0].split(" ");
        final_date_format = new_date_format[1];

        String[] newTime = time.split(" ");
        am_pm = newTime[1];
        String[] new_time_format = newTime[0].split(":");
        final_time_format = new_time_format[0];
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        drug = drugs[position].toString();
        predef_time = MainActivity.predefined_times[position].toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    /*method of time picker to set time*/
    public void setAlarmTime(){
        TimePickerDialog timePickerDialog = new TimePickerDialog(new_add_medicine.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                hour = hourOfDay;
                minutes = minute;
            }
        }, hr,min,true);
        timePickerDialog.show();
    }

    //method to set alarm

    public void setAlarm(){
        Intent alarm = new Intent(AlarmClock.ACTION_SET_ALARM);
        alarm.putExtra(AlarmClock.EXTRA_HOUR, hour);
        alarm.putExtra(AlarmClock.EXTRA_MINUTES, minutes);
        alarm.putExtra(AlarmClock.EXTRA_MESSAGE,"please take your medicine");
        startActivity(alarm);

    }




}