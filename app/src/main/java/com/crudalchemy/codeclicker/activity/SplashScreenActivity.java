package com.crudalchemy.codeclicker.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.crudalchemy.codeclicker.R;

public class SplashScreenActivity extends AppCompatActivity {

    static int SPLASH_TIME_OUT = 5000;
    Animation wiggleAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        ImageView image = (ImageView) findViewById(R.id.splash_screen_activity_image_view_logo);
        wiggleAnimation = AnimationUtils.loadAnimation(this, R.anim.wiggle);
        image.startAnimation(wiggleAnimation);
        getSupportActionBar().hide();
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