package com.wiadevelopers.presets.Adapter;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.squareup.picasso.Picasso;
import com.wiadevelopers.presets.Activitys.ActivityDetailsCtegory;
import com.wiadevelopers.presets.Model.AllPresetsModel;
import com.wiadevelopers.presets.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class AdapterAllPresets extends RecyclerView.Adapter<AdapterAllPresets.AllPresetsViewHolder> {
    public Context context;
    List<AllPresetsModel> allPresetsModels;




    public AdapterAllPresets(Context context, List<AllPresetsModel> allPresetsModels) {
        this.context = context;
        this.allPresetsModels = allPresetsModels;
    }

    @NonNull
    @Override
    public AllPresetsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AllPresetsViewHolder(LayoutInflater.from(context).inflate(R.layout.item_for_recycle_fragment_all, parent, false));

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull final AllPresetsViewHolder holder, int position) {
        final AllPresetsModel allPresetsModel = allPresetsModels.get(position);
        holder.textTitle.setText( allPresetsModel.getPresetsAfter().length + " " + allPresetsModel.getPresetCategoryName() );


        Picasso
                .with(context)
                .load(allPresetsModel.getPhotoForCategoryBackground())
                .into(holder.Image);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context.getApplicationContext(), ActivityDetailsCtegory.class);
                intent.putExtra("model", allPresetsModel);
                context.startActivity(intent);
            }
        });

        holder.frameAfterShimmer.setVisibility(View.GONE);
        holder.shimmerFrameLayout.startShimmer();
        if (holder.Image!=null){
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    holder.shimmerFrameLayout.stopShimmer();
                    holder.shimmerFrameLayout.setVisibility(View.GONE);
                    holder.frameAfterShimmer.setVisibility(View.VISIBLE);


                }
            },500);
        }


    }

    @Override
    public int getItemCount() {
        return allPresetsModels.size();
    }

    public static class AllPresetsViewHolder extends RecyclerView.ViewHolder {
        ImageView Image;
        TextView textTitle;
        CardView cardView;
        ShimmerFrameLayout shimmerFrameLayout;
        FrameLayout frameAfterShimmer;


        public AllPresetsViewHolder(@NonNull View itemView) {
            super(itemView);
            Image = itemView.findViewById(R.id.item_fragment_all_image);
            textTitle = itemView.findViewById(R.id.item_fragment_all_title_banner);
            cardView = itemView.findViewById(R.id.card_item_all_category);
            shimmerFrameLayout = itemView.findViewById(R.id.shimmer);
            frameAfterShimmer = itemView.findViewById(R.id.frame_after_shimmer);

        }
    }

}
