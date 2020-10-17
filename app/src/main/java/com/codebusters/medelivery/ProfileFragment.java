package com.codebusters.medelivery;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileFragment extends Fragment {
    private static final String TAG = "ProfileFragment";

    private Button logoutButton;
    private TextView email;
    private String userEmail;
    private Button medicalProfileButton;
    
    private FirebaseAuth mAuth;

//        logoutButton.setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            mAuth.signOut();
//            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
//            startActivity(intent);
//        }
//    });
    @SuppressLint("SetTextI18n")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profile_fragment, container, false);
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        logoutButton = (Button) view.findViewById(R.id.logoutButton);
        email = (TextView) view.findViewById(R.id.emailTextView);
        userEmail = user.getEmail();
        email.setText("Email: " + userEmail);
        medicalProfileButton = (Button) view.findViewById(R.id.medicalFormButton);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                Intent intent = new Intent(getActivity().getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
        medicalProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(), MedicalProfile.class);
                startActivity(intent);
            }
        });
        return view;
    }
}
