package com.example.transgeo.fragment.translasi;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.transgeo.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SyaratTranslasiFragment extends Fragment {

    public SyaratTranslasiFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_syarat_translasi, container, false);
    }
}