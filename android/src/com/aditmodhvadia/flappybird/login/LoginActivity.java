package com.aditmodhvadia.flappybird.login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.aditmodhvadia.flappybird.R;
import com.aditmodhvadia.flappybird.utils.AppUtils;
import com.crashlytics.android.Crashlytics;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity implements LoginMvpView, View.OnClickListener {

    LoginPresenter presenter;
    LinearLayout parentLayout;
    AppCompatButton btnLogin;
    EditText edtEmail, edtPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        presenter = new LoginPresenter(LoginActivity.this);
        parentLayout = findViewById(R.id.parentLayout);
        btnLogin = findViewById(R.id.btnLogin);
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);

        btnLogin.setOnClickListener(this);
        parentLayout.setOnClickListener(this);
    }



    @Override
    public void loginSuccess(FirebaseUser currUser) {
//        todo: display successful animation with welcome username
        Toast.makeText(this, currUser.getDisplayName(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loginError(String errorMessage) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.btnLogin:
//                todo: show loader
                AppUtils.hideKeyboard(LoginActivity.this);
                if(!AppUtils.isNetworkConnected(this)){
                    Toast.makeText(this, "No Internet", Toast.LENGTH_SHORT).show();
                }
                if(!AppUtils.isEmailValid(edtEmail.getText().toString())){
                    Toast.makeText(this, "Please enter valid email", Toast.LENGTH_SHORT).show();
                } else if(!AppUtils.isPasswordValid(edtPassword.getText().toString())){
                    Toast.makeText(this, "Please enter valid password", Toast.LENGTH_SHORT).show();
                } else {
                    presenter.performLogin(edtEmail.getText().toString(), edtPassword.getText().toString());
                }
                break;
            case R.id.parentLayout:
//                Crashlytics.getInstance().crash();
                AppUtils.hideKeyboard(LoginActivity.this);
        }
    }
}
