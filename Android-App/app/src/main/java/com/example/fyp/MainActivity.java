package com.example.fyp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText email_view,pass_view;
    String email,password;
    TextView forget_password;
    Button button1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email_view = findViewById(R.id.inputEmail);
        pass_view = findViewById(R.id.inputPassword);


        button1= (Button)findViewById(R.id.login_btn);
        forget_password = findViewById(R.id.forgotPassword);
        forget_password.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,Email_Forgot_Password.class));
            }
        });

        button1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                email = email_view.getText().toString();
                password = pass_view.getText().toString();
                if(email.equals("raishani02@gmail.com")&&password.equals("arslan"))
                {
                    startActivity(new Intent(MainActivity.this,Profile.class));
                }
                else
                {
                    new AlertDialog.Builder(MainActivity.this)

                            .setMessage("Invalid Email or Password")
                            .setPositiveButton("Try again", new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog, int which)    {
//                                    startActivity(new Intent(MainActivity.this,MainActivity.class));
                                }
                                //do other stuff here

                            }).show();

                }

            }
        });
    }
    private void showPopup() {

    }

    @Override
    public void onBackPressed() {
        // TODO Auto-generated method stub

        new AlertDialog.Builder(this)
                .setTitle("Exit Alert")
                .setMessage("Are You sure?")
                .setNegativeButton("Cancel", null)
                .setPositiveButton("exit", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which)    {

                        MainActivity.super.onBackPressed();
                        finish();
                    }
                    //do other stuff here

                }).show();

    }


}



