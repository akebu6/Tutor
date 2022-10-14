package com.example.tutor.login.ui.signup;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tutor.MainActivity;
import com.example.tutor.R;
import com.google.firebase.auth.FirebaseAuth;

public class SignupActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    EditText fullName, email, password, confirmPassword;
    Button signup;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        fullName = findViewById(R.id.name);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        confirmPassword = findViewById(R.id.confirm_password);
        signup = findViewById(R.id.log_in);
        back = findViewById(R.id.back);

        back.setOnClickListener(v -> finish());

        mAuth = FirebaseAuth.getInstance();

        signup.setOnClickListener(v -> {
            String txt_email = email.getText().toString();
            String txt_password = password.getText().toString();

            if (TextUtils.isEmpty(txt_email) || TextUtils.isEmpty(txt_password)){
                Toast.makeText(
                        SignupActivity.this,
                        "Empty Credentials",
                        Toast.LENGTH_SHORT)
                        .show();
            }else if (txt_password.length() < 6){
                Toast.makeText(
                        SignupActivity.this,
                        "Password is too short",
                        Toast.LENGTH_SHORT)
                        .show();
            }else {
                registerUser(txt_email, txt_password);
                Intent intent = new Intent(SignupActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void registerUser(String email, String password) {

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(SignupActivity.this, task -> {
                    if (task.isSuccessful()){
                        Toast.makeText(
                                SignupActivity.this,
                                "User registration successful!",
                                Toast.LENGTH_SHORT)
                                .show();
                        startActivity(new Intent(SignupActivity.this, MainActivity.class));
                        finish();
                    }else {
                        Toast.makeText(
                                SignupActivity.this,
                                "Registration failed!", Toast.LENGTH_SHORT)
                                .show();
                    }
                });
    }
}