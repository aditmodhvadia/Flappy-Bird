package com.aditmodhvadia.flappybird.login;

import com.google.firebase.auth.FirebaseUser;

public interface LoginMvpView {

    void loginSuccess(FirebaseUser currUser);

    void loginError(String errorMessage);

}
