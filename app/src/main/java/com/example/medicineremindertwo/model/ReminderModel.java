package com.example.medicineremindertwo.model;

public class ReminderModel {
    String drugName;
    Integer hour, minutes, id;



    public ReminderModel(String drugName, Integer hour, Integer minutes, Integer id) {
        this.drugName = drugName;
        this.hour = hour;
        this.minutes = minutes;
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDrugName() {
        return drugName;
    }

    public void setDrugName(String drugName) {
        this.drugName = drugName;
    }

    public Integer getHour() {
        return hour;
    }

    public void setHour(Integer hour) {
        this.hour = hour;
    }

    public Integer getMinutes() {
        return minutes;
    }

    public void setMinutes(Integer minutes) {
        this.minutes = minutes;
    }
}

