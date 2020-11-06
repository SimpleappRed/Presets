package com.wiadevelopers.presets.Activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.wiadevelopers.presets.R;

public class ActivitySetting extends AppCompatActivity {
    private TextView rate,inviteFriend,contactUs,privacyPolicy;
    ImageView imgBack;
    private LinearLayout theme,followUs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        findViewMethod();
        setOnClickMethod();

    }
    public void findViewMethod(){
        imgBack = findViewById(R.id.back_drawer);
        rate = findViewById(R.id.txt_rate_us);
        inviteFriend = findViewById(R.id.txt_invite_friend);
        contactUs = findViewById(R.id.txt_contact_us);
        privacyPolicy = findViewById(R.id.txt_privacy_policy);
        theme = findViewById(R.id.lnr_drawer_theme);
        followUs = findViewById(R.id.lnr_follow);





    }
    public void setOnClickMethod(){
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

            }
        });
        rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        inviteFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        contactUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        privacyPolicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        theme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        followUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });



    }
    // دکمه بک گوشی
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
}