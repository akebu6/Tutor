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
import com.example.tutor.home.ui.login.LoginActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

@SuppressLint("CustomSplashScreen")
class SplashScreen : AppCompatActivity() {
    private var imageView: ImageView? = null
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        auth = Firebase.auth

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        imageView = findViewById(R.id.logo)
        val animation = AnimationUtils.loadAnimation(this, R.anim.top_animation)
        imageView?.startAnimation(animation)

        if (auth.currentUser != null) {
            Handler().postDelayed({
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }, TIME_DELAY)
        } else {
            Handler().postDelayed({
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }, TIME_DELAY)
        }
    }

    companion object {
        private const val TIME_DELAY: Long = 3000L
    }
}
