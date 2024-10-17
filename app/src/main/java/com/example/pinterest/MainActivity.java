package com.example.pinterest;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    private Button con;
    private EditText emailInput;
    private Button googleAuth;
    private FirebaseAuth auth;
    private DatabaseHelper databaseHelper; // SQLite database helper

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inisialisasi Button dan EditText
        con = findViewById(R.id.btn_con);
        emailInput = findViewById(R.id.email);  // Ambil email dari EditText
        auth = FirebaseAuth.getInstance();
        databaseHelper = new DatabaseHelper(this); // Initialize SQLite helper

        con.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Dapatkan input email dari EditText
                String email = emailInput.getText().toString().trim();

                // Validasi apakah email kosong atau tidak sesuai format email
                if (email.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please insert email", Toast.LENGTH_SHORT).show();
                } else if (!isValidEmail(email)) {
                    Toast.makeText(MainActivity.this, "Please enter a valid email", Toast.LENGTH_SHORT).show();
                } else {
                    checkEmailInFirebase(email);
                }
            }
        });

        // Initialize Google Auth button and other setup...
    }

    private void checkEmailInFirebase(String email) {
        // Check in Firebase
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
        reference.orderByChild("email").equalTo(email).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    // Email exists in Firebase, proceed to Login activity
                    Intent intent = new Intent(MainActivity.this, Login.class);
                    intent.putExtra("email_key", email);
                    startActivity(intent);
                } else {
                    // Email does not exist in Firebase, check in SQLite
                    if (databaseHelper.checkUser(email)) {
                        // Email exists in SQLite, proceed to Login activity
                        Intent intent = new Intent(MainActivity.this, Login.class);
                        intent.putExtra("email_key", email);
                        startActivity(intent);
                    } else {
                        // Email does not exist, proceed to Register activity
                        Intent intent = new Intent(MainActivity.this, Register.class);
                        intent.putExtra("email_key", email);
                        startActivity(intent);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MainActivity.this, "Database error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Metode untuk memvalidasi format email
    private boolean isValidEmail(CharSequence email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}
