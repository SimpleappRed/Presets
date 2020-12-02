package com.wiadevelopers.presets.Activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.viewpager.widget.ViewPager;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.getkeepsafe.taptargetview.TapTarget;
import com.getkeepsafe.taptargetview.TapTargetView;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.wiadevelopers.presets.Class.Util;
import com.wiadevelopers.presets.R;
import com.wiadevelopers.presets.fragments.FragmentAll;
import com.wiadevelopers.presets.fragments.FragmentNew;
import com.wiadevelopers.presets.fragments.FragmentFavorite;

public class ActivityAllPresets extends AppCompatActivity {
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private ImageView setting, help, backDrawer;
    private FragmentAll fragmentAll;
    private FragmentFavorite fragmentfavorite;
    private FragmentNew fragmentNew;
    public Context context;
    boolean isShowTapTargetView = false;
    SharedPreferences sharedPreferences;
    private FirebaseAnalytics mFirebaseAnalytics;
    private AdView mAdView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_presets);

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        // Obtain the FirebaseAnalytics instance.
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);




        context = getApplicationContext();

        fragmentAll = new FragmentAll();
        fragmentfavorite = new FragmentFavorite();
        fragmentNew = new FragmentNew();


        findViewMethod();
        setOnClickMethod();
        setUpViewPager(viewPager);
        sharedPreferences = getSharedPreferences("shared_prefs", MODE_PRIVATE);
        boolean showHelp = sharedPreferences.getBoolean("saveTapTargetView", false);
        if (showHelp==false) {
        final Drawable drawable = ContextCompat.getDrawable(this, R.drawable.ic_help);
        Typeface typeface = ResourcesCompat.getFont(this, R.font.irsans_font);
        assert typeface != null;
        TapTargetView.showFor(this,
                TapTarget.forView(findViewById(R.id.activity_all_presets_help), "How to use presets")
                        .outerCircleColor(R.color.grey_light)
                        .outerCircleAlpha(0.96f)
                        .targetCircleColor(R.color.white)
                        .titleTextSize(22)
                        .titleTextColor(R.color.white)
                        .descriptionTextSize(20)
                        .descriptionTextColor(R.color.white)
                        .textColor(R.color.white)
                        .textTypeface(typeface)
                        .dimColor(R.color.white)
                        .drawShadow(true)
                        .cancelable(true)
                        .transparentTarget(false)
                        .icon(drawable)
                        .targetRadius(60),
                new TapTargetView.Listener() {
                    @Override
                    public void onTargetClick(TapTargetView view) {
                        super.onTargetClick(view);

                    }
                });
        }
        isShowTapTargetView = true;
        sharedPreferences = getSharedPreferences("shared_prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("saveTapTargetView", isShowTapTargetView);
        editor.apply();

    }

    private void findViewMethod() {
        viewPager = findViewById(R.id.viewpager_all_presets);
        tabLayout = findViewById(R.id.tabs_all_presets);
        setting = findViewById(R.id.activity_all_presets_setting);
        help = findViewById(R.id.activity_all_presets_help);
        backDrawer = findViewById(R.id.back_drawer);


    }

    private void setUpViewPager(ViewPager viewPager) {
        Util.ViewPagerAdapter adapter = new Util.ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(fragmentAll, getResources().getString(R.string.All_Presets));
        adapter.addFragment(fragmentNew, getResources().getString(R.string.New_Presets));
        //adapter.addFragment(fragmentfavorite, getResources().getString(R.string.Favorite));
        viewPager.setAdapter(adapter);

        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#FFEB3B"));
        tabLayout.setTabTextColors(Color.parseColor("#727272"), Color.parseColor("#ffffff"));


    }

    private void setOnClickMethod() {

        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityAllPresets.this, ActivitySetting.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);

            }
        });
        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityAllPresets.this, ActivityHelp.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }



}