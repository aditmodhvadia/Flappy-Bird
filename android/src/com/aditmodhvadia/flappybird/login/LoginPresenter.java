package com.aditmodhvadia.flappybird.login;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginPresenter implements LoginMvpPresenter {

    private LoginMvpView loginView;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();

    LoginPresenter(LoginMvpView loginView) {
        this.loginView = loginView;
    }


    @Override
    public void performLogin(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                loginView.loginSuccess(authResult.getUser());
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                loginView.loginError(e.getMessage());
            }
        });
    }
}
