package com.crudalchemy.codeclicker.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.crudalchemy.codeclicker.R;

public class KillscreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_killscreen);

        getSupportActionBar().hide();
    }
}