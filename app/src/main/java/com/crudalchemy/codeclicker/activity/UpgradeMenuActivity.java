package com.crudalchemy.codeclicker.activity;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.widget.ImageView;

import com.crudalchemy.codeclicker.R;
import com.crudalchemy.codeclicker.adapter.UpgradeMenuRecyclerViewAdapter;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class UpgradeMenuActivity extends AppCompatActivity {

    UpgradeMenuRecyclerViewAdapter upgradeMenuRecyclerViewAdapter;
   // List<String> upgradeArrayList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upgrade_menu);

    }

}