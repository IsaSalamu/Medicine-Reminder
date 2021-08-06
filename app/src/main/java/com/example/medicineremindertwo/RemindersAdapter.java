package com.example.medicineremindertwo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class RemindersAdapter extends ArrayAdapter<ReminderModel> {
    ArrayList<ReminderModel> rModel;
    Context rContext;
    int rResource;

    public RemindersAdapter(@NonNull Context context, int resource, @NonNull ArrayList<ReminderModel> objects) {
        super(context, resource, objects);
        this.rContext = context;
        this.rResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String name = getItem(position).getDrugName();
        Integer hour = getItem(position).getHour();
        Integer minutes = getItem(position).getMinutes();
        Integer id = getItem(position).getId();

        ReminderModel reminderModel = new ReminderModel(name, hour, minutes, id);
        LayoutInflater inflater = LayoutInflater.from(rContext);
        convertView = inflater.inflate(R.layout.reminder_item, parent, false);

        TextView descri = convertView.findViewById(R.id.alarm_descr);
        TextView hrs_minutes = convertView.findViewById(R.id.alarm_repeating_time);

        descri.setText(name);
        hrs_minutes.setText(hour+":"+minutes.toString());
        return convertView;

    }
}
