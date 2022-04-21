package com.crudalchemy.codeclicker.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.crudalchemy.codeclicker.R;

public class SplashScreenActivity extends AppCompatActivity {

    static int SPLASH_TIME_OUT = 5000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                goToMainActivity();
            }
        }, SPLASH_TIME_OUT);
    }

    private void goToMainActivity()
    {
        Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
}