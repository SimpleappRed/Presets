package com.wiadevelopers.presets.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

public class FragmentFavorite extends Fragment {
    private RecyclerView recycleFragmentFavorite;
    List<AllPresetsModel> modelList = new ArrayList<>();
   public FragmentFavorite() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_favorite,container,false);
        recycleFragmentFavorite = view.findViewById(R.id.recycle_fragment_favorite);
        recycleFragmentFavorite.setLayoutManager(new GridLayoutManager(getActivity(),1));
        AdapterAllPresets adapterAllPresets = new  AdapterAllPresets(getActivity(),modelList);
        recycleFragmentFavorite.setAdapter(adapterAllPresets);
        return view;
    }

}
