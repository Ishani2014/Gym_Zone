package com.example.myapplication3345;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    String TAG = LoginActivity.class.getSimpleName();
    EditText email, password;
    Button login;
    LinearLayout createAccount;

    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    String PasswordPattern = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        email = findViewById(R.id.login_email);
        password = findViewById(R.id.login_password);
        login = findViewById(R.id.login_button);

        createAccount = findViewById(R.id.login_create_account);


        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e(TAG, "onClick: Create_Account");
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);
            //  new Comman_Method(LoginActivity.this, "SignUP");
            }
        });


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (email.getText().toString().trim().equals("")) {
                    email.setError("Email id Required");
                } else if (!email.getText().toString().trim().matches(emailPattern)) {
                    email.setError("Valid Email Id Required ");
                } else if (password.getText().toString().trim().equals("")) {
                    password.setError("password Required");
                } else if (!password.getText().toString().trim().matches(PasswordPattern)) {
                    password.setError("strong password Required");
                } else {
                    Log.e(TAG, "onClick: Login_Account");
                    if (email.getText().toString().trim().equals("dhruvil@gmail.com")
                            && password.getText().toString().equalsIgnoreCase("Dhruvil@2014")) {
                        Log.d("Login", "Login Successfully");
                        new Comman_Method(LoginActivity.this, HomeActivity.class);
                    } else {
                        Log.d("Login", "Login Unsuccessfully");
                    }
                }
            }

        });
    }

    public void ShowHidePass(View view) {

        if (view.getId() == R.id.show_pass_btn) {

            if (password.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())) {
                ((ImageView) (view)).setImageResource(R.drawable.ic_baseline_visibility_off_24);

                //Show Password
                password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            } else {
                ((ImageView) (view)).setImageResource(R.drawable.ic_baseline_remove_red_eye_24);

                //Hide Password
                password.setTransformationMethod(PasswordTransformationMethod.getInstance());

            }
        }
    }



}