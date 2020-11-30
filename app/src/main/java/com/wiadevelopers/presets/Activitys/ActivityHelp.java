package com.wiadevelopers.presets.Activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.wiadevelopers.presets.R;

public class ActivityHelp extends AppCompatActivity {
    ImageView imgBack, txt_help_1, txt_help_2, txt_help_3, txt_help_4, txt_help_5
            , txt_help_6, txt_help_7, txt_help_8, txt_help_9, txt_help_10, txt_help_11
            ,videoCreate, videoSave;
    TextView lightRoomLink;
    String linkLightRoom = "https://play.google.com/store/apps/details?id=com.adobe.lrmobile";
    String linkCreateVideo = "https://www.instagram.com/p/CH-tBjopyxf/?igshid=cjlqf6des599";
    String linkLSaveVideo = "https://www.instagram.com/p/CH-sycCJBYZ/?igshid=1te0n99oiezsr";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        findViewMethod();

        imgBack = findViewById(R.id.img_back);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            }
        });
    }

    private void findViewMethod() {
        videoCreate = findViewById(R.id.help_video_create);
        videoSave = findViewById(R.id.help_video_save);
        videoCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Uri uri = Uri.parse(linkCreateVideo);
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                }catch (Exception e){
                    e.printStackTrace();
                    Toast.makeText(ActivityHelp.this, "Please Install Instagram", Toast.LENGTH_SHORT).show();

                }


            }
        });
        videoSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Uri uri = Uri.parse(linkLSaveVideo);
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                }catch (Exception e){
                    e.printStackTrace();
                    Toast.makeText(ActivityHelp.this, "Please Install Instagram", Toast.LENGTH_SHORT).show();
                }


            }
        });
        lightRoomLink = findViewById(R.id.txt_lightroom_link);
        lightRoomLink.setPaintFlags(lightRoomLink.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);
        lightRoomLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse(linkLightRoom); // missing 'http://' will cause crashed
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);

            }
        });
        Picasso
                .with(ActivityHelp.this)
                .load(R.drawable.video_ghide)
                .into(videoSave);

        Picasso
                .with(ActivityHelp.this)
                .load(R.drawable.video_ghide)
                .into(videoCreate);

        txt_help_1 = findViewById(R.id.txt_help_1);
        Picasso
                .with(ActivityHelp.this)
                .load(R.drawable.step_1)
                .into(txt_help_1);
       /* txt_help_2 = findViewById(R.id.txt_help_2);
        Picasso
                .with(ActivityHelp.this)
                .load(R.drawable.step_2)
                .into(txt_help_2);
        txt_help_3 = findViewById(R.id.txt_help_3);
        Picasso
                .with(ActivityHelp.this)
                .load(R.drawable.step_3)
                .into(txt_help_3);
        txt_help_4 = findViewById(R.id.txt_help_4);
        Picasso
                .with(ActivityHelp.this)
                .load(R.drawable.step_4)
                .into(txt_help_4);*/
        txt_help_5 = findViewById(R.id.txt_help_5);
        Picasso
                .with(ActivityHelp.this)
                .load(R.drawable.step_5)
                .into(txt_help_5);
        txt_help_6 = findViewById(R.id.txt_help_6);
        Picasso
                .with(ActivityHelp.this)
                .load(R.drawable.step_6)
                .into(txt_help_6);
        txt_help_7 = findViewById(R.id.txt_help_7);
        Picasso
                .with(ActivityHelp.this)
                .load(R.drawable.step_7)
                .into(txt_help_7);
        txt_help_8 = findViewById(R.id.txt_help_8);
        Picasso
                .with(ActivityHelp.this)
                .load(R.drawable.step_8)
                .into(txt_help_8);
        txt_help_9 = findViewById(R.id.txt_help_9);
        Picasso
                .with(ActivityHelp.this)
                .load(R.drawable.step_9)
                .into(txt_help_9);
        txt_help_10 = findViewById(R.id.txt_help_10);
        Picasso
                .with(ActivityHelp.this)
                .load(R.drawable.step_10)
                .into(txt_help_10);

        txt_help_11 = findViewById(R.id.txt_help_11);
        Picasso
                .with(ActivityHelp.this)
                .load(R.drawable.step_11)
                .into(txt_help_11);
    }

    //مربوط به دکمه بک خود گوشی
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

}