package com.example.fyp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

import java.io.BufferedReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Reset_password extends AppCompatActivity {
    TextInputEditText res_otp;
    EditText res_pass,res_con_pass;
    Button forget_password;
    String reset_otp,password,confirm_password,otp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.setTitle("Reset Password");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        forget_password = findViewById(R.id.bt_forget);
        res_otp =  (TextInputEditText)findViewById(R.id.et_code);
        res_pass =  (EditText) findViewById(R.id.et_password);
        res_con_pass =  (EditText) findViewById(R.id.et_repeat_password);
        Intent intent = getIntent();
        otp = intent.getStringExtra("otp");

        forget_password.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                reset_otp = res_otp.getText().toString();
                password = res_pass.getText().toString();
                confirm_password = res_con_pass.getText().toString();

                if(!otp.contentEquals(reset_otp))
                {
                    res_otp.setError("Invalid OTP");
                    res_otp.requestFocus();
                }
                else
                {
                    if(isValidPassword(password))
                    {
                        if(!password.contentEquals(confirm_password))
                        {
                            res_con_pass.setError("Password mismatch");
                            res_con_pass.requestFocus();
                        }
                        else
                        {
                            startActivity(new Intent(Reset_password.this,MainActivity.class));
                        }
                    }
                    else
                    {
                        res_pass.setError("Password must contain Capital letter and special character");
                        res_pass.requestFocus();
                    }
                }

            }
        });

    }

    public boolean isValidPassword(final String password) {

        Pattern pattern;
        Matcher matcher;

        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$";

        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);

        return matcher.matches();

    }
}