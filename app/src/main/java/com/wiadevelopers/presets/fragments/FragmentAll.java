package com.wiadevelopers.presets.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.google.gson.Gson;
import com.wiadevelopers.presets.Adapter.AdapterAllPresets;
import com.wiadevelopers.presets.Class.MySingleton;
import com.wiadevelopers.presets.Model.AllPresetsModel;
import com.wiadevelopers.presets.R;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class FragmentAll extends Fragment {
    private RecyclerView recycleFragmentAll;
    AdapterAllPresets adapterAllPresets;
    List<AllPresetsModel> modelList = new ArrayList<>();


    public FragmentAll() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_all, container, false);
        recycleFragmentAll = view.findViewById(R.id.recycle_fragment_all);
        recycleFragmentAll.setLayoutManager(new LinearLayoutManager(getActivity()));
        recycleFragmentAll.setHasFixedSize(true);
       recycleFragmentAll.setItemViewCacheSize(20);
        recycleFragmentAll.setAlwaysDrawnWithCacheEnabled(true);
        recycleFragmentAll.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        adapterAllPresets = new AdapterAllPresets(getActivity(), modelList);
        recycleFragmentAll.setAdapter(adapterAllPresets);
        getPresetsInfoFromServer();
        return view;
    }

    private void getPresetsInfoFromServer() {
        String url = "http://www.wiadevelopers.ir/google_play/nahid/Presets/api/api.json";
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("load...");
        progressDialog.show();

        Response.Listener<JSONArray> listener = new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                // Toast.makeText(getActivity(), response + "", Toast.LENGTH_SHORT).show();
                if (response != null) {
                    try {
                        Gson gson = new Gson();
                        AllPresetsModel[] model = gson.fromJson(response.toString(), AllPresetsModel[].class);
                        for (int i = 0; i < model.length; i++) {
                            modelList.add(model[i]);
                            adapterAllPresets.notifyDataSetChanged();
                            progressDialog.dismiss();

                        }


                    } catch (Exception e) {
                        e.printStackTrace();

                    }
                }


            }
        };
        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(),"Not connected to the Internet", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();

            }
        };
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET,url,null,listener,errorListener);
        MySingleton.getInstance(getActivity()).addToRequestQueue(request);


    }
}
