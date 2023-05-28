package com.example.basiclogintoapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.basiclogintoapp.R;


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>
{

    private final int count;
    String[] Location;
    String[] data2;
    String[] data;
    Context context;
    String[] picture;

    public RecyclerAdapter(Context context, String[] data, int count, String[] picture, String[] data2, String[] Location)
    {
        this.data = data;
        this.context= context;
        this.count= count;
        this.data2 = data2;
        this.picture=picture;
        this.Location=Location;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.recycler_add,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int pos) {
    int position=count-1-pos;
    //its in reverse order so the latest post comes on top
    //for undo reverse do(int position =pos;)
    holder.textView.setText(data[position]);
    holder.textView2.setText(data2[position]);
    holder.id.setText(Location[position]);
    Glide.with(context)
                .load(picture[position])
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(holder.imageView);
   holder.itemView.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View view) {
       }
   });

    }

    @Override
    public int getItemCount() {
        return count;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        TextView textView2;
        ImageView imageView;
        TextView id;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textinrecycler);
            textView2 = itemView.findViewById(R.id.description);
            imageView= itemView.findViewById(R.id.imageinrecycler);
            id = itemView.findViewById(R.id.author);
        }
    }
}