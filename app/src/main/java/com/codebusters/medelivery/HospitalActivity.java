package com.codebusters.medelivery;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class HospitalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital);
        setTitle("Hospitals and Clinics Near Me");
    }
}