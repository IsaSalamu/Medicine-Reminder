package com.example.medicineremindertwo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class set_daily_intake extends AppCompatActivity {

    TextView id_get_num_days;
    NumberPicker number_picker2;
    TextView times_days_id;
    Button btn_save_details;
    Button btn_set_alarm;

    Integer timesDays = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_daily_intake);
        times_days_id = (TextView) findViewById(R.id.times_days_id);
        times_days_id.setText(0+" time(s) per day");
        btn_save_details = (Button) findViewById(R.id.btn_save_details);

//        Toast.makeText(getApplicationContext(), add_drug.toString(),Toast.LENGTH_SHORT).show();
        id_get_num_days = (TextView) findViewById(R.id.id_get_num_days);

        //initialise button to go to alarm setting upon clicking
        Button btn_set_alarm = (Button) findViewById(R.id.btn_set_alarm);
        btn_set_alarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(set_daily_intake.this , set_alarms.class);
//                startActivity(intent);
            }
        });


//        id_get_num_days.setText("Taking "+add_drug.drug+" for "+add_drug.dd+" day(s).");


        btn_save_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        number_picker2 = ( NumberPicker ) findViewById(R.id.number_picker2);
        number_picker2.setMinValue(0);
        number_picker2.setMaxValue(10);
        number_picker2.setEnabled(true);

        number_picker2.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker2, int oldVal, int newVal) {
                timesDays = picker2.getValue();
                getTimesDays();
            }


        });
    }

    public void getTimesDays(){
        times_days_id.setText(timesDays+" times(s) per day.");
    }
}