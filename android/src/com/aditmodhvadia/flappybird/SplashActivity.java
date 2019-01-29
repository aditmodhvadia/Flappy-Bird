package com.aditmodhvadia.flappybird;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.aditmodhvadia.flappybird.login.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;

public class SplashActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        mAuth = FirebaseAuth.getInstance();
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(mAuth.getCurrentUser() != null)
                    startActivity(new Intent(SplashActivity.this, AndroidLauncher.class));
                else
                    startActivity(new Intent(SplashActivity.this, LoginActivity.class));

                finishAffinity();
            }
        }, 2000);
    }
}
