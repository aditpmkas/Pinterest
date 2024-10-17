package com.example.pinterest;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class Register extends AppCompatActivity {

    private EditText editName, editEmail, editPassword, editPasswordConf;
    private Button btnRegister, btnLogin;
    private ProgressDialog progressDialog;
    private FirebaseAuth mAuth;
    private DatabaseHelper databaseHelper; // SQLite database helper

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editName = findViewById(R.id.name);
        editEmail = findViewById(R.id.email);
        editPassword = findViewById(R.id.password);
        editPasswordConf = findViewById(R.id.password_Confirm);
        btnRegister = findViewById(R.id.btn_up_register);
        btnLogin = findViewById(R.id.btn_back_login);

        mAuth = FirebaseAuth.getInstance();
        databaseHelper = new DatabaseHelper(this); // Initialize SQLite helper
        progressDialog = new ProgressDialog(Register.this);
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Please wait");
        progressDialog.setCancelable(false);

        btnLogin.setOnClickListener(view -> {
            finish();
        });

        btnRegister.setOnClickListener(view -> {
            if (editEmail.getText().length() > 0 && editName.getText().length() > 0 && editPassword.getText().length() > 0) {
                if (editPassword.getText().toString().equals(editPasswordConf.getText().toString())) {
                    registerWithFirebase(editName.getText().toString(), editEmail.getText().toString(), editPassword.getText().toString());
                } else {
                    Toast.makeText(getApplicationContext(), "Password not same", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(getApplicationContext(), "Please fill all the information", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void registerWithFirebase(String name, String email, String password) {
        progressDialog.show();
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressDialog.dismiss();
                if (task.isSuccessful() && task.getResult() != null) {
                    FirebaseUser firebaseUser = task.getResult().getUser();
                    if (firebaseUser != null) {
                        UserProfileChangeRequest request = new UserProfileChangeRequest.Builder()
                                .setDisplayName(name).build();
                        firebaseUser.updateProfile(request).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Toast.makeText(getApplicationContext(), "Registration Successful!", Toast.LENGTH_SHORT).show();
                                storeUserInSQLite(name, email, password); // Store in SQLite
                                sendEmailVerification(firebaseUser);
                                mAuth.signOut();
                                finish();
                            }
                        });
                    } else {
                        Toast.makeText(getApplicationContext(), "Registration Failed!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Registration Failed!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void storeUserInSQLite(String name, String email, String password) {
        if (!databaseHelper.checkUser(email)) {
            boolean insert = databaseHelper.insertUser(name, email, password);
            if (insert) {
                Toast.makeText(getApplicationContext(), "User Stored in SQLite", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "SQLite Insertion Failed", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void sendEmailVerification(FirebaseUser firebaseUser) {
        firebaseUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(getApplicationContext(), "Verification email sent", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
