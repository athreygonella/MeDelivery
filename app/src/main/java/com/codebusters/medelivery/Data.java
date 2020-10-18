package com.codebusters.medelivery;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Data {
    private ArrayList<String> inventory = new ArrayList<>();
    private String appointmentTime;
    private String appointmentPlace;

    public void addToInventory(String s) {
        inventory.add(s);
    }

    public void setAppointmentTime(String time) {
        appointmentTime = time;
    }

    public void setAppointmentPlace(String place) {
        appointmentPlace = place;
    }

    public ArrayList<String> getInventory() {
        return inventory;
    }

    public String getAppointmentTime() {
        return appointmentTime;
    }

    public String getAppointmentPlace() {
        return appointmentPlace;
    }

}
