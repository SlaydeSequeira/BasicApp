package com.example.basiclogintoapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;
// OrderAdapter.java
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.basiclogintoapp.Model.OrderItem;
import com.example.basiclogintoapp.R;

import java.util.List;
public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {

    private List<OrderItem> data; // Use this list for data binding
    private Context context;

    public OrderAdapter(Context context, List<OrderItem> orderItems) {
        this.context = context;
        this.data = orderItems; // Initialize data with the original list
    }

    public void setData(List<OrderItem> newData) {
        this.data = newData;
        notifyDataSetChanged(); // Notify adapter when data changes
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_order, parent, false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        OrderItem orderItem = data.get(position); // Use the 'data' list

        // Set data to views using the OrderItem model
        holder.textViewTitle.setText(orderItem.getTitle());
        holder.textViewDescription.setText(orderItem.getDescription());
        holder.textViewPrice.setText(orderItem.getPrice());

        // Load image using Glide
        Glide.with(context)
                .load(orderItem.getImageUrl())
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return data.size(); // Use the size of the 'data' list
    }

    public static class OrderViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textViewTitle;
        TextView textViewDescription;
        TextView textViewPrice;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewDescription = itemView.findViewById(R.id.textViewDescription);
            textViewPrice = itemView.findViewById(R.id.textViewPrice);
        }
    }
}
