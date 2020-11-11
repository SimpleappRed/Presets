package com.wiadevelopers.presets.Activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.wiadevelopers.presets.Adapter.AdapterAllPresets;
import com.wiadevelopers.presets.Adapter.AdapterDetailPresets;
import com.wiadevelopers.presets.Class.MySingleton;
import com.wiadevelopers.presets.Model.AllPresetsModel;
import com.wiadevelopers.presets.R;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ActivityDetailsCtegory extends AppCompatActivity {
    RecyclerView recycle_detail_category;
    AdapterDetailPresets adapter;
    AllPresetsModel presetModel;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_ctegory);



        getIntentMethod();


        recycle_detail_category = findViewById(R.id.recycle_detail_category);
        recycle_detail_category.setLayoutManager(new LinearLayoutManager(ActivityDetailsCtegory.this));
        adapter = new AdapterDetailPresets(ActivityDetailsCtegory.this
                , presetModel.getPresetsDNG_Format()
                , presetModel.getPresetsAfter()
                , presetModel.getPresetsBefore()
                , presetModel.getPresetsDetailsname());
        recycle_detail_category.setAdapter(adapter);



    }


    public void getIntentMethod() {

        presetModel = (AllPresetsModel) getIntent().getSerializableExtra("model");


    }

    // داده های فیک برای تست
    private List<AllPresetsModel> generateItem() {
        List<AllPresetsModel> models = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            AllPresetsModel itemModel = new AllPresetsModel();
            itemModel.setPresetCategoryName("fall");
            itemModel.setPhotoForCategoryBackground("http://192.168.1.101/presets/presets_background-photp/fall.jpg");

            models.add(itemModel);

        }
        return models;
    }


}