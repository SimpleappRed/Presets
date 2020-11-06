package com.wiadevelopers.presets.Adapter;


import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.wiadevelopers.presets.Activitys.ActivityDetailsCtegory;
import com.wiadevelopers.presets.Model.AllPresetsModel;
import com.wiadevelopers.presets.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AdapterDetailPresets extends RecyclerView.Adapter<AdapterDetailPresets.ViewHolder> {
    public Context context;
    String[] modelListDng;
    String[] modelListAfter;
    String[] modelListBefore;
    String titleDetails;


    public AdapterDetailPresets(Context context, String[] modelListDng, String[] modelListAfter, String[] modelListBefore, String titleDetails) {
        this.context = context;
        this.modelListDng = modelListDng;
        this.modelListAfter = modelListAfter;
        this.modelListBefore = modelListBefore;
        this.titleDetails = titleDetails;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_for_new_and_detail_category, parent, false));

    }

    @SuppressLint({"SetTextI18n", "ClickableViewAccessibility"})
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        int p = position+1;
        holder.title.setText(titleDetails + "  " + p);
        Picasso.with(context).load(modelListAfter[position]).into(holder.image);
        final int finalPosition = position;

       /* holder.touch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Picasso.with(context).load(modelListBefore[finalPosition]).into(holder.image);
            }
        });*/
        holder.touch.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return false;
            }
        });



        final int finalPosition1 = position;
        holder.dngDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, modelListDng[finalPosition1] + " Is Downloaded", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return modelListDng.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image, touch, favorite;
        TextView title, dngDownload;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.item_image);
            touch = itemView.findViewById(R.id.item_touch);
            favorite = itemView.findViewById(R.id.item_favorite);
            title = itemView.findViewById(R.id.item_title);
            dngDownload = itemView.findViewById(R.id.item_dng_download);
        }
    }

}
