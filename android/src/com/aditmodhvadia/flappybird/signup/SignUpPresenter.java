package com.aditmodhvadia.flappybird.signup;

import android.net.Uri;
import android.support.annotation.NonNull;

import com.aditmodhvadia.flappybird.models.FirebaseNewUser;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class SignUpPresenter implements SignUpMvpPresenter {

    SignUpMvpView signUpMvpView;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();

    SignUpPresenter(SignUpMvpView signUpMvpView) {
        this.signUpMvpView = signUpMvpView;
    }

    @Override
    public void signUpUser(final FirebaseNewUser newUser) {

        mAuth.createUserWithEmailAndPassword(newUser.getEmail(), newUser.getPassword()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                final FirebaseUser currUser = authResult.getUser();
                UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                        .setDisplayName(newUser.getUserName())
                        .setPhotoUri(Uri.parse(newUser.getPhotoUrl()))
                        .build();

                currUser.updateProfile(profileUpdates)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    signUpMvpView.onSignUpSuccess(currUser);
                                } else {
                                    signUpMvpView.onSignUpError(task.getException());
                                }
                            }
                        });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                signUpMvpView.onSignUpError(e);
            }
        });
    }
}
