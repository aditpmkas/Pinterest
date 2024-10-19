package com.example.pinterestgrid;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_image);

        ImageView imageView = findViewById(R.id.detail_image_view);

        // Ambil data gambar yang dikirim dari intent
        int imageRes = getIntent().getIntExtra("image_resource", 0);

        // Set gambar di ImageView
        imageView.setImageResource(imageRes);
    }
}
