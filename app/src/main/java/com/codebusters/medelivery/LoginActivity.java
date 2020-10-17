package com.codebusters.medelivery;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private EditText email;
    private EditText password;
    private Button loginButton;
    private Button registerButton;
    private TextView message;
    private String emailText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("Login");

        // Initializes Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        // Initializes layout items
        email = (EditText) findViewById(R.id.emailEditText);
        password = (EditText) findViewById(R.id.passwordEditText);
        loginButton = (Button) findViewById((R.id.loginButton));
        registerButton = (Button) findViewById(R.id.registerButton);
        message = (TextView) findViewById(R.id.message);
    }

    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            Intent intent = new Intent(getApplicationContext(), DashboardActivity.class);
            startActivity(intent);
        }
    }

    public void createAccount(View v) {
        mAuth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    Toast.makeText(getApplicationContext(), "Successfully created an account!", Toast.LENGTH_SHORT).show();
                    FirebaseUser user = mAuth.getCurrentUser();
                    if (user != null) {
                        // Name, email address, and profile photo Url
                        emailText = user.getEmail();
                    }

                    Intent intent = new Intent(getApplicationContext(), DashboardActivity.class);
                    startActivity(intent);
                } else {
                    // If sign in fails, display a message to the user.
                    // NOTE: password must be more than 6 characters to successfully sign-up
                    FirebaseAuthException e = (FirebaseAuthException) task.getException();
                    Log.e("LoginActivity", "FailedRegistration", e);
                    Toast.makeText(getApplicationContext(), "Registration Failed!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void login(View v) {
        mAuth.signInWithEmailAndPassword(email.getText().toString(), password.getText().toString()).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    Toast.makeText(getApplicationContext(), "Successfully signed in!", Toast.LENGTH_SHORT).show();
                    FirebaseUser user = mAuth.getCurrentUser();
                    if (user != null) {
                        // Name, email address, and profile photo Url
                        emailText = user.getEmail();
                    }

                    Intent intent = new Intent(getApplicationContext(), DashboardActivity.class);
                    startActivity(intent);
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(getApplicationContext(), "Login Failed!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public String getEmail() {
        return emailText;
    }


}