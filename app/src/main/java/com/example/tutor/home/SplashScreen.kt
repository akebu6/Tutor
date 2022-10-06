@file:Suppress("DEPRECATION")

package com.example.tutor.home

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.tutor.MainActivity
import com.example.tutor.R

@SuppressLint("CustomSplashScreen")
class SplashScreen : AppCompatActivity() {
    private var imageView: ImageView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        imageView = findViewById(R.id.logo)
        val animation = AnimationUtils.loadAnimation(this, R.anim.top_animation)
        imageView?.startAnimation(animation)

        Handler().postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, TIME_DELAY)
    }

    companion object {
        private const val TIME_DELAY: Long = 3000L
    }
}
