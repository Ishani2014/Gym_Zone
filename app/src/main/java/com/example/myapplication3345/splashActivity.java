package com.example.myapplication3345;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class splashActivity extends AppCompatActivity {

    ImageView imageView;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();
        imageView = findViewById(R.id.splash_iv);
        textView = findViewById(R.id.splash_text);
        AlphaAnimation animation = new AlphaAnimation(0,1);
        animation.setDuration(1000);
        imageView.startAnimation(animation);
        textView.startAnimation(animation);

        SharedPreferences sharedPreferences = getSharedPreferences("MyAPP_GYM", MODE_PRIVATE);
        String strEmail = sharedPreferences.getString("KEY_PREF_EMAIL", "");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                /*Intent intent = new Intent(splashActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();*/
                if (strEmail.equals("")){
            Intent i = new Intent(splashActivity.this,LoginActivity.class);
            startActivity(i);
            finish();
        }else {
            Intent i = new Intent(splashActivity.this,HomeActivity.class);
            startActivity(i);
            finish();
        }
            }
        }, 1000);
    }
}