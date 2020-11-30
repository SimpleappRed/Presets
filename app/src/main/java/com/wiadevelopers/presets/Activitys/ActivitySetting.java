package com.wiadevelopers.presets.Activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.balysv.materialripple.MaterialRippleLayout;
import com.wiadevelopers.presets.Class.SettingsHelper;
import com.wiadevelopers.presets.R;

public class ActivitySetting extends AppCompatActivity {
    ImageView imgBack;
    private MaterialRippleLayout theme,followUs,rate,inviteFriend,otherApps,contactUs,privacyPolicy;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        findViewMethod();
        setOnClickMethod();

    }
    public void findViewMethod(){
        imgBack = findViewById(R.id.back_drawer);
        rate = findViewById(R.id.lnr_rate_us);
        inviteFriend = findViewById(R.id.lnr_invite_friend);
        contactUs = findViewById(R.id.lnr_contact_us);
        privacyPolicy = findViewById(R.id.lnr_privacy_policy);
        theme = findViewById(R.id.lnr_drawer_theme);
        followUs = findViewById(R.id.lnr_follow);
        otherApps = findViewById(R.id.lnr_other_app);





    }
    public void setOnClickMethod(){
        otherApps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SettingsHelper.otherApps(ActivitySetting.this);

            }
        });
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
               SettingsHelper.rateUsOnGooglePlay(ActivitySetting.this);

            }
        });
        inviteFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              SettingsHelper.shareApp(ActivitySetting.this);

            }
        });
        contactUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SettingsHelper.sendMail(ActivitySetting.this, "\n" +
                        "skilltory.a@gmail.com");

            }
        });
        privacyPolicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               SettingsHelper.openPrivacyPolicy(ActivitySetting.this,"https://docs.google.com/document/d/1rRLzkaBURLXpeROJJPOLV-EANMup1CoNRXg9A8CbKh8/edit?usp=sharing");

            }
        });
        theme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater layoutInflater = getLayoutInflater();
                View v2 = layoutInflater.inflate(R.layout.toastcustom, (ViewGroup) findViewById(R.id.lnr));
                Toast toast = new Toast(getApplicationContext());
                toast.setDuration(Toast.LENGTH_LONG);
                //toast.setGravity(Gravity.NO_GRAVITY, 0, 160);
                toast.setView(v2);
                toast.show();
            }
        });
        followUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("http://instagram.com/shine.presets.f");
                Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);

                likeIng.setPackage("com.instagram.android");

                try {
                    startActivity(likeIng);
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("http://instagram.com/shine.presets.f")));
                }
                //custom Toast
               /* LayoutInflater layoutInflater = getLayoutInflater();
                View v2 = layoutInflater.inflate(R.layout.toastcustom, (ViewGroup) findViewById(R.id.lnr));
                Toast toast = new Toast(getApplicationContext());
                toast.setDuration(Toast.LENGTH_LONG);
                //toast.setGravity(Gravity.NO_GRAVITY, 0, 160);
                toast.setView(v2);
                toast.show();*/
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