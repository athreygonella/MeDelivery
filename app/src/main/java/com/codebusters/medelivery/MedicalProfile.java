package com.codebusters.medelivery;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class MedicalProfile extends AppCompatActivity {
    private EditText nameFill;
    private EditText dobFill;
    private EditText sexFill;
    private EditText addressFill;
    private EditText allergiesFill;
    private TextView diabetesFill;
    private EditText diabetesEditText;
    private TextView convulsionsFill;
    private EditText convulsionsEditText;
    private TextView heartTroubleFill;
    private EditText heartTroubleEditText;
    private Button backToDashboardButton;
    private FirebaseAuth mAuth;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private static final String TAG = "Medical Profile";
    private DocumentSnapshot document;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        String email = user.getEmail();

        DocumentReference docRef = db.collection("users").document(email);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    document = task.getResult();
                    if (document.exists()) {
                        Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                    } else {
                        Log.d(TAG, "No such document");
                            }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });
        setContentView(R.layout.activity_medical_profile);
        nameFill = (EditText) findViewById(R.id.editTextTextPersonName);
        dobFill = (EditText) findViewById(R.id.editTextDate);
        sexFill = (EditText) findViewById(R.id.sexEditText);
        addressFill = (EditText) findViewById(R.id.addressEditText);
        allergiesFill = (EditText) findViewById(R.id.allergiesEditText);
        diabetesFill = (TextView) findViewById(R.id.diabetesTextView);
        diabetesEditText = (EditText) findViewById(R.id.diabetesEditText);
        convulsionsFill = (TextView) findViewById(R.id.convulsionsTextView);
        convulsionsEditText = (EditText) findViewById(R.id.convulsionsEditText);
        heartTroubleFill = (TextView) findViewById(R.id.heartTroubleTextView);
        heartTroubleEditText = (EditText) findViewById(R.id.heartTroubleEditText);
        backToDashboardButton = (Button) findViewById(R.id.backToDashboardButton);

        if (document != null) {
            updateUserMedicalData();
        }

        backToDashboardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, Object> userData = new HashMap<>();
                userData.put("name", nameFill.toString());
                userData.put("dob", dobFill.toString());
                userData.put("sex", sexFill.toString());
                userData.put("address", addressFill.toString());
                userData.put("allergies", allergiesFill.toString());
                userData.put("diabetes", diabetesEditText.toString());
                userData.put("convulsions", convulsionsEditText.toString());
                userData.put("heart trouble", heartTroubleEditText.toString());

                db.collection("users")
                        .add(userData)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w(TAG, "Error adding document", e);
                            }
                        });
                Intent intent = new Intent(getApplicationContext(), DashboardActivity.class);
                startActivity(intent);
            }
        });
    }

    private void updateUserMedicalData() {
        nameFill.setText((String) document.get("name"));
        dobFill.setText((String) document.get("dob"));
        sexFill.setText((String) document.get("sex"));
        addressFill.setText((String) document.get("address"));
        allergiesFill.setText((String) document.get("allergies"));
        diabetesEditText.setText((String) document.get(("diabetes")));
        convulsionsEditText.setText((String) document.get("convulsion"));
        heartTroubleEditText.setText((String) document.get("heart trouble"));
    }
}