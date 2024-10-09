package com.example.pinterest;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private Button con;
    private EditText emailInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inisialisasi Button dan EditText
        con = findViewById(R.id.btn_con);
        emailInput = findViewById(R.id.email);  // Ambil email dari EditText

        con.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Dapatkan input email dari EditText
                String email = emailInput.getText().toString().trim();

                // Validasi apakah email kosong atau tidak sesuai format email
                if (email.isEmpty()) {
                    // Jika kosong, tampilkan pesan "Please insert email"
                    Toast.makeText(MainActivity.this, "Please insert email", Toast.LENGTH_SHORT).show();
                } else if (!isValidEmail(email)) {
                    // Jika format email salah, tampilkan pesan "Please enter a valid email"
                    Toast.makeText(MainActivity.this, "Please enter a valid email", Toast.LENGTH_SHORT).show();
                } else {
                    // Jika email valid, lanjutkan ke Password activity dan kirim email
                    Intent intent = new Intent(MainActivity.this, Login.class);
                    intent.putExtra("email_key", email);  // Kirim email melalui intent
                    startActivity(intent);
                }
            }
        });
    }

    // Metode untuk memvalidasi format email
    private boolean isValidEmail(CharSequence email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}
