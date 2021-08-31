package com.example.medicineremindertwo.viewModel;

import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.medicineremindertwo.R;
import com.example.medicineremindertwo.adapters.RemindersAdapter;
import com.example.medicineremindertwo.databaseModels.DatabaseController;
import com.example.medicineremindertwo.model.ReminderModel;

import java.util.ArrayList;
import java.util.Calendar;

public class ListOfReminders extends AppCompatActivity {
    DatabaseController databaseController;
    ReminderModel model;
    RemindersAdapter adapter;
    ArrayList<ReminderModel> list;
    ListView listView;
    Integer hour, finalHour, minutes, finalMinutes;
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
        adapter.notifyDataSetChanged();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                LayoutInflater inflater = getLayoutInflater();
                View builderView = inflater.inflate(R.layout.edit_reminder, null);
                EditText d_name = (EditText) builderView.findViewById(R.id.drug_name_et);
                Button btnSetEditedTime = (Button) builderView.findViewById(R.id.btnSetEditedTime);
                Calendar calendar = Calendar.getInstance();
                hour = calendar.get(Calendar.HOUR_OF_DAY);
                minutes =calendar.get(Calendar.MINUTE);

                btnSetEditedTime.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        TimePickerDialog timePickerDialog = new TimePickerDialog(ListOfReminders.this, new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                finalHour = hourOfDay;
                                finalMinutes = minute;
                                btnSetEditedTime.setText("Alarm time set at "+finalHour.toString()+":"+finalMinutes.toString());
                            }
                        }, hour, minutes, true);
                        timePickerDialog.show();
                    }
                });

                d_name.setText(list.get(position).getDrugName().toString());
                AlertDialog.Builder adb=new AlertDialog.Builder(ListOfReminders.this);
                adb.setTitle("Edit Reminder");
                adb.setView(builderView);
                adb.setNegativeButton("Cancel", null);
                adb.setPositiveButton("Ok", new AlertDialog.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        databaseController.updateDrugData(ListOfReminders.this, list.get(position).getId(), d_name.getText().toString().trim(), finalHour, finalMinutes, "Days edited");
                        adapter.notifyDataSetChanged();
                    }});
                adb.show();
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder adb=new AlertDialog.Builder(ListOfReminders.this);
                adb.setTitle("Delete?");
                adb.setMessage("Are you sure you want to delete " + list.get(position).getDrugName().toString());
                adb.setNegativeButton("Cancel", null);
                adb.setPositiveButton("Ok", new AlertDialog.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        int result = databaseController.deleteReminder(list.get(position).getId());
                        if (result >0){
                            Toast.makeText(getApplicationContext(), "Deleted successfully...", Toast.LENGTH_SHORT).show();
                            adapter.notifyDataSetChanged();
                        }else {
                            Toast.makeText(getApplicationContext(), "Failed to delete reminder...", Toast.LENGTH_SHORT).show();
                        }
                    }});
                adb.show();
                return true;
            }
        });
    }

}