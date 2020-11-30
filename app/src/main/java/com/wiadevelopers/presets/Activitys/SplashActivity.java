package com.wiadevelopers.presets.Activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.widget.ImageView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.wiadevelopers.presets.R;

public class SplashActivity extends AppCompatActivity {
    ImageView splashLogo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);







        splashLogo = findViewById(R.id.splash_logo);

        YoYo.with(Techniques.ZoomIn)
                .duration(3000)
                .repeat(1)
                .playOn(findViewById(R.id.splash_logo));

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, ActivityAllPresets.class);
                startActivity(intent);
                finish();

            }
        },3000);
    }
}