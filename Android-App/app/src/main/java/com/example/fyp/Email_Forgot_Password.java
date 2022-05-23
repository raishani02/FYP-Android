package com.example.fyp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class Email_Forgot_Password extends AppCompatActivity {

    String receiver;
    EditText mail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_forgot_password);
        TextView send = findViewById(R.id.bt_forget);
        mail = (EditText) findViewById(R.id.forget_password_email);
        Random rnd = new Random();
        int number = rnd.nextInt(999999);

        // this will convert any number sequence into 6 character.
        String OTP =  String.format("%06d", number);

        send.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                /// code for sending email
                receiver = mail.getText().toString().trim();
                if (receiver.length()>0) {
                    Thread thread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                //Your code goes here
                                GMailSender sender = new GMailSender("bestucation786@gmail.com", "Bestucation@786");
                                sender.sendMail("OTP For Reset Password",
                                        "Kindly Enter this OTP " + OTP + " for reset password.",
                                        "bestucation786@gmail.com",
                                        receiver);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });

                    thread.start();
                    Toast.makeText(getApplicationContext(), "OTP sent to provided email", Toast.LENGTH_SHORT).show();

                    Intent i = new Intent(Email_Forgot_Password.this, Reset_password.class);
                    i.putExtra("otp", OTP);
                    startActivity(i);
                }
                else
                {
                    mail.setError("Invalid Email");
                    mail.requestFocus();
                }
            }
        });
    }
}
