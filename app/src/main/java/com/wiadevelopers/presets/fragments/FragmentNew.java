package com.wiadevelopers.presets.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wiadevelopers.presets.Activitys.ActivityAllPresets;
import com.wiadevelopers.presets.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class FragmentNew extends Fragment {
    private RecyclerView recycleFragmentNew;
   public FragmentNew() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_new,container,false);
       recycleFragmentNew = view.findViewById(R.id.recycle_fragment_new);
       recycleFragmentNew.setLayoutManager(new LinearLayoutManager(getActivity()));
        //AdapterNewAndCategoryDetailPresets adapterNewPresets = new AdapterNewAndCategoryDetailPresets(ActivityAllPresets.context,);
       // recycleFragmentNew.setAdapter(adapterNewPresets);
        return view;
    }
}
