package com.crudalchemy.codeclicker.activity;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.crudalchemy.codeclicker.R;
import com.crudalchemy.codeclicker.adapter.GeneratorMenuRecyclerViewAdapter;

public class UpgradeMenuActivity extends AppCompatActivity {

    GeneratorMenuRecyclerViewAdapter generatorMenuRecyclerViewAdapter;
   // List<String> upgradeArrayList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upgrade_menu);

    }

}