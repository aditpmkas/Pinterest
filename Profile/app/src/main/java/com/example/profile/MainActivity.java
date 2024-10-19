package com.example.profile;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Pastikan ini sesuai dengan layout yang benar

        // Cek TabLayout
        TabLayout tabLayout = findViewById(R.id.tab_layout);
        if (tabLayout == null) {
            Log.e("MainActivity", "TabLayout is null. Check your layout XML file for correct ID.");
            return;
        }


        // Setup OnApplyWindowInsetsListener
        ViewCompat.setOnApplyWindowInsetsListener(tabLayout, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
