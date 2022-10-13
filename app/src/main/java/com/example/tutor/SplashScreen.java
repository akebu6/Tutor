package com.example.tutor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.tutor.database.OnCompleteListener;
import com.example.tutor.database.DatabaseQuery;
import com.example.tutor.login.ui.login.LoginActivity;

public class SplashScreen extends AppCompatActivity {
    private static int SPLASH_SCREEN = 5000;

    // variables
    Animation topAnim;
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );

        //Animations
        topAnim = AnimationUtils.loadAnimation(this,R.anim.top_animation);

        //Hooks
        image = findViewById(R.id.logo);

        image.setAnimation(topAnim);

        DatabaseQuery.loadCategories(new OnCompleteListener() {
            @Override
            public void onSuccess() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(SplashScreen.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }, SPLASH_SCREEN);
            }

            @Override
            public void onFailure() {

            }
        });

    }
}