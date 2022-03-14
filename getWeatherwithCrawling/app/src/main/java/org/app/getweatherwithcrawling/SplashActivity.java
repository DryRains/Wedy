package org.app.getweatherwithcrawling;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class SplashActivity extends AppCompatActivity {//로딩화면

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ImageView gif_image = (ImageView) findViewById(R.id.gif_image);
        Glide.with(this).load(R.drawable.loadingicon).into(gif_image);

        ImageView gif_logo = (ImageView) findViewById(R.id.gif_logo);
        Glide.with(this).load(R.drawable.logo).into(gif_logo);


        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent main = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(main);
                finish();
            }
        }, 3000); // 로딩시간을 4초로 설정

    }

}