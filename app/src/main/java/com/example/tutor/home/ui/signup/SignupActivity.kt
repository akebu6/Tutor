package com.example.tutor.home.ui.signup

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.tutor.MainActivity
import com.example.tutor.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SignupActivity : AppCompatActivity() {
    // variables for the ids of the views
    private lateinit var fullname: EditText
    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var confirmPassword: EditText
    private lateinit var signupButton: Button
    private lateinit var auth: FirebaseAuth
    private lateinit var emailString: String
    private lateinit var passwordString: String
    private lateinit var confirmPasswordString: String
    private lateinit var fullnameString: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        supportActionBar!!.setIcon(R.drawable.ic_arrow_back)

        auth = Firebase.auth

        // initializing the views
        fullname = findViewById(R.id.name)
        email = findViewById(R.id.email)
        password = findViewById(R.id.password)
        confirmPassword = findViewById(R.id.confirm_password)
        signupButton = findViewById(R.id.signup)

        signupButton.setOnClickListener {
            if (validate()) {
                newUser()
            }
        }
    }

    private fun validate(): Boolean {
        emailString = email.text.toString()
        passwordString = password.text.toString()
        confirmPasswordString = confirmPassword.text.toString()
        fullnameString = fullname.text.toString()

        if (emailString.isEmpty()) {
            email.error = "Please enter your email"
            return false
        }
        if (passwordString.isEmpty()) {
            password.error = "Please enter your password"
            return false
        }
        if (confirmPasswordString.isEmpty()) {
            confirmPassword.error = "Please confirm your password"
            return false
        }
        if (fullnameString.isEmpty()) {
            fullname.error = "Please enter your fullname"
            return false
        }
        if (passwordString != confirmPasswordString) {
            confirmPassword.error = "Passwords do not match"
            return false
        }
        return true
    }

    private fun newUser() {
        auth.signInWithEmailAndPassword(emailString, passwordString)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithEmail:success")
                    val user = auth.currentUser
                    updateUI(user)
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    this@SignupActivity.finish()
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                    Toast.makeText(
                        baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }.addOnFailureListener {
                Toast.makeText(this, "Failed to create user", Toast.LENGTH_SHORT).show()
            }
    }

    private fun updateUI(user: FirebaseUser?) {
        if (user != null) {
            Toast.makeText(this, "User created", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "User not created", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if (currentUser != null) {
            reload()
        }
    }

    private fun reload() {}
}
