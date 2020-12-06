package com.wiadevelopers.presets.Adapter;


import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Handler;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.squareup.picasso.Picasso;
import com.wiadevelopers.presets.R;
import com.wiadevelopers.presets.callback.SetAdListener;
import com.wiadevelopers.presets.callback.SetOnClickListener;

import org.xml.sax.InputSource;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.view.menu.MenuView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class AdapterDetailPresets extends RecyclerView.Adapter<AdapterDetailPresets.ViewHolder> {
    public Context context;
    String[] modelListDng;
    String[] modelListAfter;
    String[] modelListBefore;
    String titleDetails;
    private SetOnClickListener setOnClickListener;
    private SetAdListener setAdListener;


    public AdapterDetailPresets(Context context, SetAdListener setAdListene, SetOnClickListener setOnClickListener, String[] modelListDng, String[] modelListAfter, String[] modelListBefore, String presetsDetailsname, String titleDetails) {
        this.context = context;
        this.modelListDng = modelListDng;
        this.modelListAfter = modelListAfter;
        this.modelListBefore = modelListBefore;
        this.setOnClickListener = setOnClickListener;
        this.titleDetails = titleDetails;
        this.setAdListener = setAdListene;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_for_new_and_detail_category, parent, false));

    }

    @SuppressLint({"SetTextI18n", "ClickableViewAccessibility"})
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final int p = position + 1;
        final String titleAndPosition = titleDetails + "  " + p;
        holder.title.setText(titleAndPosition);
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
            }, 0);
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


        holder.dngDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(context, modelListDng[finalPosition1] + " Is Downloaded", Toast.LENGTH_SHORT).show();
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.custom_dialog);
                Button btnPositive = dialog.findViewById(R.id.dialog_positive_btn);
                Button btnCancel = dialog.findViewById(R.id.dialog_cancel_btn);
                btnPositive.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        setOnClickListener.onClick(modelListDng[position]);
                        setAdListener.onClick(view);

                    }
                });
                btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.cancel();
                    }
                });
                dialog.show();

            }
        });
    }

    @Override
    public int getItemCount() {
        return modelListDng.length;

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image, touch, imgBefore;
        TextView title, dngDownload;
        CardView cardShowAfterShimmer;
        ShimmerFrameLayout shimmerFrameLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgBefore = itemView.findViewById(R.id.item_image_before);
            image = itemView.findViewById(R.id.item_image);
            touch = itemView.findViewById(R.id.item_touch);
            title = itemView.findViewById(R.id.item_title);
            dngDownload = itemView.findViewById(R.id.item_dng_download);
            cardShowAfterShimmer = itemView.findViewById(R.id.card_show_after_shimmer);
            shimmerFrameLayout = itemView.findViewById(R.id.shimmer);
        }
    }

   /* private void shareImage(Bitmap bitmap) {
        String imgBitmapPath = MediaStore.Images.Media.insertImage(context.getContentResolver(), bitmap, "title", null);
        Uri imgBitmapUri = Uri.parse(imgBitmapPath);
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("image/*");
        shareIntent.putExtra(Intent.EXTRA_STREAM, imgBitmapUri);
        shareIntent.setPackage("com.adobe.lrmobile");
        // context.startActivity(Intent.createChooser(shareIntent, "share image using"));

        try {
            context.startActivity(shareIntent);
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(context, "your app not install", Toast.LENGTH_SHORT).show();
        }


    }*/
}
