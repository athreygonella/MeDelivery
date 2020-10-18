package com.codebusters.medelivery;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

public class AppointmentActivity extends AppCompatActivity {

    private Spinner appointmentSpinner;
    ArrayList<String> appointments = new ArrayList<>();

    private TextView appointmentLabel;
    private CheckBox device1CheckBox;
    private CheckBox device2CheckBox;
    private CheckBox device3CheckBox;
    private CheckBox device4CheckBox;
    private CheckBox device5CheckBox;
    private CheckBox device6CheckBox;
    private View view;

    private Button submitApptAndDevicesButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);
        for (int x = 9; x <= 12; x++) {
            if (x == 12) {
                appointments.add(x + " pm");
                appointments.add(x + ":30 pm");
            } else {
                appointments.add(x + " am");
                appointments.add(x + ":30 am");
            }
        }
        for (int x = 1; x <= 5; x++) {
            appointments.add(x + " pm");
            appointments.add(x + ":30 pm");
        }
        appointmentSpinner = (Spinner) findViewById(R.id.appointmentSpinner);
        appointmentLabel = (TextView) findViewById(R.id.appointmentLabel);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, appointments);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        appointmentSpinner.setAdapter(adapter);
        appointmentSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        device1CheckBox = (CheckBox) findViewById(R.id.device1CheckBox);
        device2CheckBox = (CheckBox) findViewById(R.id.device2CheckBox);
        device3CheckBox = (CheckBox) findViewById(R.id.device3CheckBox);
        device4CheckBox = (CheckBox) findViewById(R.id.device4CheckBox);
        device5CheckBox = (CheckBox) findViewById(R.id.device5CheckBox);
        device6CheckBox = (CheckBox) findViewById(R.id.device6CheckBox);
        if (device1CheckBox.isChecked()) {
            //put device in status page
        }
        if (device2CheckBox.isChecked()) {
            //put device in status page
        }
        if (device3CheckBox.isChecked()) {
            //put device in status page
        }
        if (device4CheckBox.isChecked()) {
            //put device in status page
        }
        if (device5CheckBox.isChecked()) {
            //put device in status page
        }
        if (device6CheckBox.isChecked()) {
            //put device in status page
        }

        submitApptAndDevicesButton = (Button) findViewById(R.id.submitApptAndDevicesButton);
        submitApptAndDevicesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Your appointment has been confirmed and your order for medical devices has been placed!",
                        Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), DashboardActivity.class);
                startActivity(intent);
            }
        });
    }
}