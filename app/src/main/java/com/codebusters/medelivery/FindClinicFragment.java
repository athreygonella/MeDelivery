package com.codebusters.medelivery;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.io.IOException;
import java.util.ArrayList;

public class FindClinicFragment extends Fragment {
    private static final String TAG = "FindClinicFragment";


    // ask them for their address
    // http request (geocoding - convert address/city to lat/long)
    // http request (get 5 closest clinics based on lat/long)
    // return the formatted 5 closest clincs

    private static final String API_KEY = "AIzaSyCttWOywG3gE8VfM17aGQyDl-306fMMPwE";

    private EditText streetAddress;
    private Button submitLocationButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.findclinic_fragment, container, false);

        streetAddress = (EditText) view.findViewById(R.id.streetAddress);

        submitLocationButton = (Button) view.findViewById(R.id.submitLocationButton);

        submitLocationButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String streetAddressString = streetAddress.getText().toString();
                try {
                    ArrayList<Hospital> hospitals = Parser.parse(streetAddressString);
                    System.out.println(hospitals);
                    int x = 1;
                } catch (Exception e) {
                    Toast.makeText(getActivity().getApplicationContext(), "Parsing Failed :)", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }
}
