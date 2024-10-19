package com.example.pinterestgrid;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import com.example.pinterestgrid.SpacesItemDecoration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        List<Photos> mList;
        mList = new ArrayList<>();
        mList.add(new Photos(R.drawable.image1, "flower"));
        mList.add(new Photos(R.drawable.image2, "cat"));
        mList.add(new Photos(R.drawable.image3, "moon"));
        mList.add(new Photos(R.drawable.image4, "meme"));
        mList.add(new Photos(R.drawable.image5, "cat"));
        mList.add(new Photos(R.drawable.image6, "cat"));
        mList.add(new Photos(R.drawable.image7, "cat"));
        mList.add(new Photos(R.drawable.image8, "moon"));
        mList.add(new Photos(R.drawable.image9, "flower"));
        mList.add(new Photos(R.drawable.image10, "flower"));
        StaggeredGridLayoutManager staggeredGridLayoutManager =
                new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL);

        // Set the item decoration for spacing
        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.item_spacing);
        recyclerView.addItemDecoration(new SpacesItemDecoration(spacingInPixels));


        recyclerView.setLayoutManager(staggeredGridLayoutManager);
                StagAdapter stagAdapter = new StagAdapter(MainActivity.this,mList);
                recyclerView.setAdapter(stagAdapter);

    }
}