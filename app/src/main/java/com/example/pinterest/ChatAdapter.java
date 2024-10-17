package com.example.pinterest;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatViewHolder> {

    private List<String> messages; // List of chat messages

    public ChatAdapter(List<String> messages) {
        this.messages = messages;
    }

    public static class ChatViewHolder extends RecyclerView.ViewHolder {
        public TextView messageTextView;

        public ChatViewHolder(View itemView) {
            super(itemView);
            messageTextView = itemView.findViewById(R.id.chat_message); // Change this if needed
        }
    }

    @NonNull
    @Override
    public ChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_chat_message, parent, false); // Inflate your chat message layout
        return new ChatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatViewHolder holder, int position) {
        String message = messages.get(position);
        holder.messageTextView.setText(message); // Set message text
    }

    @Override
    public int getItemCount() {
        return messages.size(); // Return the number of messages
    }
}
