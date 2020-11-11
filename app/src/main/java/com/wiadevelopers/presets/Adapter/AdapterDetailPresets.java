package com.wiadevelopers.presets.Adapter;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.squareup.picasso.Picasso;
import com.wiadevelopers.presets.R;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class AdapterDetailPresets extends RecyclerView.Adapter<AdapterDetailPresets.ViewHolder> {
    public Context context;
    String[] modelListDng;
    String[] modelListAfter;
    String[] modelListBefore;
    String titleDetails;
    int clickFavorite;


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
        final int p = position + 1;
        holder.title.setText(titleDetails + "  " + p);
        Picasso.with(context).load(modelListAfter[position]).into(holder.image);
        Picasso.with(context).load(modelListBefore[position]).into(holder.imgBefore);

        holder.cardShowAfterShimmer.setVisibility(View.GONE);
        holder.shimmerFrameLayout.startShimmer();
        if (holder.image != null) {
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    holder.shimmerFrameLayout.stopShimmer();
                    holder.shimmerFrameLayout.setVisibility(View.GONE);
                    holder.cardShowAfterShimmer.setVisibility(View.VISIBLE);


                }
            }, 500);
        }


        holder.touch.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    holder.image.setVisibility(View.GONE);
                } else {
                    holder.image.setVisibility(View.VISIBLE);
                }
                return true;
            }
        });

        final int finalPosition1 = position;
        holder.dngDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, modelListDng[finalPosition1] + " Is Downloaded", Toast.LENGTH_SHORT).show();
            }
        });


        holder.favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (clickFavorite == 0){
                    holder.favorite.setImageResource(R.drawable.favorite_border_white);
                    clickFavorite = 1;
                }else if (clickFavorite == 1){
                    holder.favorite.setImageResource(R.drawable.favorite_white);
                    clickFavorite = 0;

                }




                String textForSaveFavorite = titleDetails + "  " + p;
                SharedPreferences sharedPreferences = context.getSharedPreferences("Save_User_Favorite", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("itemName", textForSaveFavorite);
                editor.apply();
            }

        });

    }
    @Override
    public int getItemCount() {
        return modelListDng.length;

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image, touch, favorite, imgBefore;
        TextView title, dngDownload;
        CardView cardShowAfterShimmer;
        ShimmerFrameLayout shimmerFrameLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgBefore = itemView.findViewById(R.id.item_image_before);
            image = itemView.findViewById(R.id.item_image);
            touch = itemView.findViewById(R.id.item_touch);
            favorite = itemView.findViewById(R.id.item_favorite);
            title = itemView.findViewById(R.id.item_title);
            dngDownload = itemView.findViewById(R.id.item_dng_download);
            cardShowAfterShimmer = itemView.findViewById(R.id.card_show_after_shimmer);
            shimmerFrameLayout = itemView.findViewById(R.id.shimmer);
        }
    }
}
