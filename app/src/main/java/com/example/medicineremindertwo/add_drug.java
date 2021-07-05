package com.example.medicineremindertwo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.Toast;

public class add_drug extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    public static String[] drugs = {"Bactrime", "Amoxillyne","Cipro","Abendazole","Doxycyline"};

    NumberPicker picker;
    int pickerValue;
    Button btn_next_activity;
    Spinner spinner;
    public static Integer dd = 0;
    public static String drug;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_drug);

        picker= (NumberPicker) findViewById(R.id.number_picker);
        btn_next_activity= (Button) findViewById(R.id.btn_next_activity);
        picker.setMinValue(0);
        picker.setMaxValue(31);
        picker.setEnabled(true);

        spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, drugs);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(this);
        picker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                pickerValue = picker.getValue();
            }
        });

        btn_next_activity.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(add_drug.this,"Selected : ", Toast.LENGTH_SHORT).show();
               Intent intent = new Intent(add_drug.this, set_daily_intake.class);
                startActivity(intent);
                dd = pickerValue;
                getDrugsData();
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        drug = drugs[position];
        Toast.makeText(getApplicationContext(), drugs[position], Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    public void getDrugsData(){
        Toast.makeText(getApplicationContext(), "Clicked", Toast.LENGTH_SHORT).show();
    }
}