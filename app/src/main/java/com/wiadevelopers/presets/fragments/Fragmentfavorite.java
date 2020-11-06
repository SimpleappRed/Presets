package com.wiadevelopers.presets.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wiadevelopers.presets.Activitys.ActivityAllPresets;
import com.wiadevelopers.presets.Adapter.AdapterAllPresets;
import com.wiadevelopers.presets.Model.AllPresetsModel;
import com.wiadevelopers.presets.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class Fragmentfavorite extends Fragment {
    private RecyclerView recycleFragmentFvorite;
    List<AllPresetsModel> modelList = new ArrayList<>();
   public Fragmentfavorite() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_favorite,container,false);
        recycleFragmentFvorite = view.findViewById(R.id.recycle_fragment_favorite);
        recycleFragmentFvorite.setLayoutManager(new GridLayoutManager(getActivity(),1));
        AdapterAllPresets adapterAllPresets = new  AdapterAllPresets(getActivity(),modelList);
        recycleFragmentFvorite.setAdapter(adapterAllPresets);
        return view;
    }
}
