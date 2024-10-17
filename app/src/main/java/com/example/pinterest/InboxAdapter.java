package com.example.inbox1;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class InboxAdapter extends RecyclerView.Adapter<InboxAdapter.InboxViewHolder> {

    private List<InboxMessage> messages;
    private Context context;

    public InboxAdapter(Context context, List<InboxMessage> messages) {
        this.context = context;
        this.messages = messages;
    }

    // ViewHolder class to hold each item view
    public static class InboxViewHolder extends RecyclerView.ViewHolder {
        public TextView senderTextView;
        public TextView previewTextView;
        public ImageView messageIcon;

        public InboxViewHolder(View itemView) {
            super(itemView);
            senderTextView = itemView.findViewById(R.id.sender_name);
            previewTextView = itemView.findViewById(R.id.message_preview);
            messageIcon = itemView.findViewById(R.id.message_icon);
        }
    }

    @NonNull
    @Override
    public InboxViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_inbox_message, parent, false);
        return new InboxViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InboxViewHolder holder, int position) {
        InboxMessage currentMessage = messages.get(position);

        holder.senderTextView.setText(currentMessage.getSender());
        holder.previewTextView.setText(currentMessage.getPreview());
        holder.messageIcon.setImageResource(currentMessage.getImageResource());

        // Set an OnClickListener to handle clicks on the item
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, ChatActivity.class);
            intent.putExtra("SENDER_NAME", currentMessage.getSender());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }
}
