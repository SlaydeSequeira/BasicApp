package com.example.basiclogintoapp.adapter;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.basiclogintoapp.CaseDetailActivity;
import com.example.basiclogintoapp.Model.Case;
import com.example.basiclogintoapp.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
public class CaseAdapter extends FirebaseRecyclerAdapter<Case, CaseAdapter.CaseViewHolder> {

    public CaseAdapter(@NonNull FirebaseRecyclerOptions<Case> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull CaseViewHolder holder, int position, @NonNull Case model) {
        // Bind data to ViewHolder
        holder.bind(model);
    }

    @NonNull
    @Override
    public CaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate ViewHolder layout
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_case, parent, false);
        return new CaseViewHolder(view);
    }

    public static class CaseViewHolder extends RecyclerView.ViewHolder {
        private TextView titleTextView;
        private TextView descriptionTextView;
        private ProgressBar progressBar;

        public CaseViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            descriptionTextView = itemView.findViewById(R.id.descriptionTextView);
            progressBar = itemView.findViewById(R.id.progressBar);
        }

        public void bind(Case caseItem) {
            // Bind data to UI elements
            titleTextView.setText(caseItem.getTitle());
            descriptionTextView.setText(caseItem.getDescription());

            // Update ProgressBar based on the status
            int progress = calculateProgress(convertStatusToInt(caseItem.getProgress()));
            progressBar.setProgress(progress);

            // Set color filter based on progress
            int color = calculateColor(progress);
            progressBar.getProgressDrawable().setColorFilter(color, PorterDuff.Mode.SRC_IN);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), CaseDetailActivity.class);
                    intent.putExtra("caseTitle", caseItem.getTitle());
                    intent.putExtra("caseDescription", caseItem.getDescription());
                    intent.putExtra("caseStatus", caseItem.getProgress());
                    // Add more fields as needed
                    v.getContext().startActivity(intent);
                }
            });
        }
        private int calculateColor(int progress) {
            // Calculate and return the color based on the progress
            // You can customize this method to set the color gradient as needed
            int green = (int) (255 * (progress / 100.0));
            int red = 255 - green;
            return Color.rgb(red, green, 0);
        }

        private int calculateProgress(int status) {
            switch (status) {
                case 1:
                    return 25;
                case 2:
                    return 50;
                case 3:
                    return 75;
                case 4:
                    return 100;
                default:
                    return 0;
            }
        }
        private int convertStatusToInt(String status) {
            try {
                return Integer.parseInt(status);
            } catch (NumberFormatException e) {
                // Handle the case where the status is not a valid integer
                e.printStackTrace();
                return 0; // or another default value
            }
        }
    }

}
