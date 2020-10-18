package com.codebusters.medelivery;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class FindClinicFragment extends Fragment {
    private static final String TAG = "FindClinicFragment";

    private FirebaseAuth mAuth;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private DocumentSnapshot document;

    String streetAddressString;
    ArrayList<Hospital> hospitals = new ArrayList<>();

    private Button submitLocationButton;
    private EditText addressEditText;
    private HospitalAdapter hospitalAdapter;
    private RecyclerView hospitalRecyclerView;

    // ask them for their address
    // http request (geocoding - convert address/city to lat/long)
    // http request (get 5 closest clinics based on lat/long)
    // return the formatted 5 closest clincs

    // private static final String API_KEY = "AIzaSyCttWOywG3gE8VfM17aGQyDl-306fMMPwE";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.findclinic_fragment, container, false);

//        submitLocationButton = (Button) view.findViewById(R.id.submitLocationButton);
//        addressEditText = (EditText) view.findViewById(R.id.streetAddress);

//        submitLocationButton.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v) {
//                streetAddressString = addressEditText.getText().toString();
//                try {
//                    hospitals = Parser.parse(streetAddressString);
//                } catch (Exception e) {
//                    Toast.makeText(getActivity().getApplicationContext(), "Parsing Failed :)", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });



        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        String email = user.getEmail();

        hospitalAdapter = new HospitalAdapter(hospitals);
        hospitalRecyclerView = (RecyclerView) view.findViewById(R.id.rvHospital);
        hospitalRecyclerView.setAdapter(hospitalAdapter);

        DocumentReference docRef = db.collection("users").document(email);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    document = task.getResult();
                    if (document.exists()) {
                        Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                        streetAddressString = (String) document.getData().get("address");

                        Log.d(TAG, streetAddressString);
                        Log.d(TAG, "DocumentSnapshot data: " + document.getData());

                            Thread t = new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        hospitals.clear();
                                        hospitals.addAll(Parser.parse(streetAddressString));
                                        getActivity().runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                hospitalAdapter.notifyDataSetChanged();
                                                Log.d(TAG, Integer.toString(hospitals.size()));
                                            }
                                        });
                                    } catch (Exception e) {
                                        Log.e(TAG, "Parser failed", e);
                                        getActivity().runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                Toast.makeText(getActivity().getApplicationContext(), "Parsing Failed :)", Toast.LENGTH_SHORT);
                                            }
                                        });
                                    }
                                }
                            });
                            t.start();
                    } else {
                        Log.d(TAG, "No such document");
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });




//        submitLocationButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String address = addressEditText.getText().toString();
//
//            }
//        });

        //Log.d(TAG, streetAddressString);
        return view;
    }

//    public ArrayList<Hospital> getHospitals() {
//        String address = addressEditText.getText().toString();
//        try {
//            hospitals = Parser.parse(address);
//        } catch (Exception e) {
//            Toast.makeText(getActivity().getApplicationContext(), "Parsing Failed :)", Toast.LENGTH_SHORT).show();
//        }
//        return hospitals;
//    }
}
