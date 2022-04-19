package com.crudalchemy.codeclicker.activity;

import static com.crudalchemy.codeclicker.activity.MainActivity.game;

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
        setupUpgradeItemRecyclerView();

    }

    public void setupUpgradeItemRecyclerView()
    {
        RecyclerView upgradeItemListRecyclerView = (RecyclerView) findViewById(R.id.upgrade_menu_list_recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        upgradeItemListRecyclerView.setLayoutManager(layoutManager);

        /*upgradeArrayList.add("Test Item One");
        upgradeArrayList.add("Test Item Two");
        upgradeArrayList.add("Test Item Three");
        upgradeArrayList.add("Test Item Four");
        upgradeArrayList.add("Test Item Five");
        upgradeArrayList.add("Test Item Six");
        upgradeArrayList.add("Test Item Seven");*/

        upgradeMenuRecyclerViewAdapter = new UpgradeMenuRecyclerViewAdapter(game.generatorList, this);
        upgradeItemListRecyclerView.setAdapter(upgradeMenuRecyclerViewAdapter);
    }
}