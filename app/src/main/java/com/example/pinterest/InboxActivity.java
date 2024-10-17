package com.example.pinterest;

import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class InboxActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inbox); // Make sure this is the correct layout

        // Step 1: Initialize RecyclerView
        RecyclerView recyclerView = findViewById(R.id.inboxRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Step 2: Create dummy data for the RecyclerView
        List<InboxMessage> inboxMessages = new ArrayList<>();
        inboxMessages.add(new InboxMessage("Jessica", "How was your day?", R.drawable.pict1)); // Image resource
        inboxMessages.add(new InboxMessage("John", "Are we meeting today?", R.drawable.pict2));
        inboxMessages.add(new InboxMessage("Emily", "Don't forget the meeting.", R.drawable.pict3));
        inboxMessages.add(new InboxMessage("Michael", "Happy birthday!", R.drawable.pict4));
        inboxMessages.add(new InboxMessage("Sarah", "Let's catch up soon.", R.drawable.pict5));// Different image resource

        // Log the number of messages
        Log.d("MainActivity", "Number of messages: " + inboxMessages.size());

        // Step 3: Initialize and set the adapter
        InboxAdapter adapter = new InboxAdapter(this, inboxMessages);
        recyclerView.setAdapter(adapter);  // Attach the adapter
    }
}
