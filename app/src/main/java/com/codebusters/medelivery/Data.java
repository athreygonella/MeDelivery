package com.codebusters.medelivery;

import java.util.ArrayList;

public class Data {
    private ArrayList<String> inventory = new ArrayList<>();
    private String appointmentTime;
    private String appointmentPlace;

    public void add(String s) {
        inventory.add(s);
    }

    public void setAppointmentTime(String time) {
        appointmentTime = time;
    }

    public void setAppointmentPlace(String place) {
        appointmentPlace = place;
    }
}
