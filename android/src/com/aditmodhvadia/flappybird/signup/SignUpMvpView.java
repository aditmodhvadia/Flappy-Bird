package com.aditmodhvadia.flappybird.signup;

import com.google.firebase.auth.FirebaseUser;

public interface SignUpMvpView {

    void onSignUpSuccess(FirebaseUser currUser);

    void onSignUpError(Exception e);
}
