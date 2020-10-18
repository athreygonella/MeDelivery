package com.codebusters.medelivery;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class StatusFragment extends Fragment {
    private static final String TAG = "StatusFragment";

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.status_fragment, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.medical_kits);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);

        TextView time = view.findViewById(R.id.time);

        Bundle bundlefromPrev = getActivity().getIntent().getExtras();
        if (bundlefromPrev != null) {
            String appointmentTime = bundlefromPrev.getString("Appointment_Time");
            time.setText(appointmentTime);

            ArrayList<String> medicalDevices = bundlefromPrev.getStringArrayList("Medical_Devices");
            ArrayList<String> myDataset = setupData(medicalDevices);
            mAdapter = new MyAdapter(myDataset);
        }

        recyclerView.setAdapter(mAdapter);

        return view;
    }

    private ArrayList<String> setupData(ArrayList<String> list) {
        ArrayList<String> devices = new ArrayList<>();
        for (String item : list) {
            devices.add(item);
        }
        return devices;
    }
}

