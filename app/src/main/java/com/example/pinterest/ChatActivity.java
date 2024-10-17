package com.example.pinterest;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity {

    private RecyclerView chatRecyclerView;
    private ChatAdapter chatAdapter;
    private List<String> chatMessages; // List to hold chat messages
    private EditText chatInput;
    private Button sendButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        // Initialize UI components
        chatRecyclerView = findViewById(R.id.chat_recycler_view);
        chatInput = findViewById(R.id.chat_input);
        sendButton = findViewById(R.id.send_button);

        // Initialize the chat messages list
        chatMessages = new ArrayList<>();

        // Set up RecyclerView
        chatRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        chatAdapter = new ChatAdapter(chatMessages);
        chatRecyclerView.setAdapter(chatAdapter);

        // Handle the send button click
        sendButton.setOnClickListener(v -> sendMessage());
    }

    private void sendMessage() {
        String message = chatInput.getText().toString().trim(); // Get input text

        if (!message.isEmpty()) {
            chatMessages.add(message); // Add the message to the list
            chatAdapter.notifyItemInserted(chatMessages.size() - 1); // Notify the adapter
            chatInput.setText(""); // Clear the input field
            chatRecyclerView.scrollToPosition(chatMessages.size() - 1); // Scroll to the latest message
        }
    }
}
