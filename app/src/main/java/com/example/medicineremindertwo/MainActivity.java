package com.example.medicineremindertwo;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    DrugsDatabase drugsDatabase;
    String[] drug_names = {"Bactrime", "Amoxillyne","Cipro","Abendazole","Doxycyline"};
    Integer number_of_drugs = 0;

    public static String[] predefined_times = {"m,e", "m,n,e","m,e","m,e","m,n,e"};
    Integer start_time = 0;
    Date starting_date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drugsDatabase = new DrugsDatabase(MainActivity.this);

        sendData(drug_names, predefined_times);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent addDrug = new Intent(MainActivity.this, new_add_medicine.class);
                startActivity(addDrug);
            }
        },3000);
    }

    public void sendData(String[] name, String[] predefined_time){
        for (int x = 0; x<name.length; x++){
            drugsDatabase.insertAllDrugs(name[x].toString(), predefined_time[x].toString());
        }
    }
}