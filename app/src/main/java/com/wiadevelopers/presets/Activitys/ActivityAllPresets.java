package com.wiadevelopers.presets.Activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.tabs.TabLayout;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_presets);


        context = getApplicationContext();

        fragmentAll = new FragmentAll();
        fragmentfavorite = new FragmentFavorite();
        fragmentNew = new FragmentNew();


        findViewMethod();
        setOnClickMethod();
        setUpViewPager(viewPager);

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
        adapter.addFragment(fragmentfavorite, getResources().getString(R.string.Favorite));
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