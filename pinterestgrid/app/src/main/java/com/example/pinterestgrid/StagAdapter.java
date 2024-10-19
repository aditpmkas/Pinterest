package com.example.pinterestgrid;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class StagAdapter extends RecyclerView.Adapter<StagAdapter.ViewHolder> {
    private List<Photos> mList;  // Menggunakan List<Photos>
    Context context;

    // Ubah constructor untuk menerima List<Photos>
    public StagAdapter(Context context, List<Photos> mList) {
        this.context = context;
        this.mList = mList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_view);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.stag_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Photos photo = mList.get(position);

        holder.imageView.setImageResource(photo.getImage());

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, DetailImageActivity.class);
            intent.putExtra("image_resource", photo.getImage());
            intent.putExtra("keyword", photo.getKeyword());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}
