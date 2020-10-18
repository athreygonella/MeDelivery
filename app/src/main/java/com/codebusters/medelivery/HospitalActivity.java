package com.codebusters.medelivery;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class HospitalActivity extends AppCompatActivity {
    private static final String TAG = "HospitalActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital);
        setTitle("Hospitals and Clinics Near Me");

        String nhsAddress = "10625 Parsons Rd";

        try {
            ArrayList<Hospital> hospitals = Parser.parse(nhsAddress);
            if (hospitals == null) {
                Log.d(TAG, "Null");
            } else {
                Log.d(TAG, "Not null");
            }
        } catch (Exception e) {
            Log.d(TAG, e.getStackTrace().toString());
        }
    }
}