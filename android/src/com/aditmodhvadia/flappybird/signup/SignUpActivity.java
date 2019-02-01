package com.aditmodhvadia.flappybird.signup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.aditmodhvadia.flappybird.R;
import com.aditmodhvadia.flappybird.login.LoginActivity;
import com.aditmodhvadia.flappybird.models.FirebaseNewUser;
import com.aditmodhvadia.flappybird.utils.AppUtils;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener, SignUpMvpView {

    LinearLayout parentLayout;
    AppCompatButton btnSignUp;
    EditText edtEmail, edtPassword, edtConfirmPassword, edtUsername;
    FirebaseAuth mAuth;
    SignUpPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        parentLayout = findViewById(R.id.parentLayout);
        btnSignUp = findViewById(R.id.btnSignUp);
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        edtConfirmPassword = findViewById(R.id.edtConfirmPassword);
        edtUsername = findViewById(R.id.edtUsername);
        presenter = new SignUpPresenter(this);
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();

        switch (i){
            case R.id.btnSignUp:
//                todo: show loader
                AppUtils.hideKeyboard(SignUpActivity.this);
                if(!AppUtils.isNetworkConnected(this)){
                    Toast.makeText(this, "No Internet", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!AppUtils.isEmailValid(edtEmail.getText().toString())){
                    Toast.makeText(this, "Please enter valid email", Toast.LENGTH_SHORT).show();
                } else if(!AppUtils.isPasswordValid(edtPassword.getText().toString())){
                    Toast.makeText(this, "Please enter valid password", Toast.LENGTH_SHORT).show();
                } else if(!edtPassword.getText().toString().equals(edtConfirmPassword.getText().toString())){
                    Toast.makeText(this, "Both passwords should match", Toast.LENGTH_SHORT).show();
                } else {
//                    todo: perform sign up here, get avatar and set it to be default
//                    presenter.performLogin(edtEmail.getText().toString(), edtPassword.getText().toString());
                    FirebaseNewUser newUser = new FirebaseNewUser(edtEmail.getText().toString(),
                            edtPassword.getText().toString(),
                            edtUsername.getText().toString(),
                            "no-data",
                            "0");
                    presenter.signUpUser(newUser);
                }
                break;

            case R.id.parentLayout:
                AppUtils.hideKeyboard(SignUpActivity.this);
            case R.id.link_signup:
                startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
        }
    }

    @Override
    public void onSignUpSuccess(FirebaseUser currUser) {
//        todo: update ui from here, not implemented yet
        try {
            throw new NoSuchMethodException();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onSignUpError(Exception e) {
//        todo: update ui for error handling, hide loader also
        try {
            throw new NoSuchMethodException();
        } catch (NoSuchMethodException e1) {
            e1.printStackTrace();
        }
    }
}
