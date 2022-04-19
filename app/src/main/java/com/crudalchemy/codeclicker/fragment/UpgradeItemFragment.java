package com.crudalchemy.codeclicker.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.crudalchemy.codeclicker.R;

public class UpgradeItemFragment extends Fragment {

    public UpgradeItemFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static UpgradeItemFragment newInstance(String param1, String param2) {
        UpgradeItemFragment fragment = new UpgradeItemFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_upgrade_item, container, false);
    }
}