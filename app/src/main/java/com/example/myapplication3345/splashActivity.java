package com.example.myapplication3345;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

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


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(splashActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }, 1000);
    }
}