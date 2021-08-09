package com.example.medicineremindertwo;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ListOfReminders extends AppCompatActivity {
    DatabaseController databaseController;
    ReminderModel model;
    RemindersAdapter adapter;
    ArrayList<ReminderModel> list;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminders_list);
        listView = (ListView) findViewById(R.id.reminders_list);
        databaseController = new DatabaseController(ListOfReminders.this);
        list = new ArrayList<ReminderModel>();

        Cursor cursor = databaseController.getDrugs();
        if (cursor.getCount() == 0){
            Toast.makeText(getApplicationContext(),"No reminders set yet", Toast.LENGTH_SHORT).show();
        }else {
            while (cursor.moveToNext()){
                list.add(new ReminderModel(cursor.getString(1).toString(), cursor.getInt(2), cursor.getInt(3), cursor.getInt(0)));
            }
        }

        adapter = new RemindersAdapter(ListOfReminders.this, R.layout.activity_reminders_list, list);
        listView.setAdapter(adapter);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), list.get(position).drugName.toString(), Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }
}