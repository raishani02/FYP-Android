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
import android.widget.Toast;

import com.example.fyp.DatabaseResult.LoginResult;
import com.example.fyp.DatabaseResult.RetrofitInterface;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    EditText email_view,pass_view;
    String email,password;
    TextView forget_password;
    Button button1;

    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    private String BASE_URL = "http://192.168.18.65:5000";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email_view = findViewById(R.id.inputEmail);
        pass_view = findViewById(R.id.inputPassword);


        button1= (Button)findViewById(R.id.login_btn);
        forget_password = findViewById(R.id.forgotPassword);

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofitInterface = retrofit.create(RetrofitInterface.class);

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

                HashMap<String, String> map = new HashMap<>();

                map.put("email", email);
                map.put("password", password);

                Call<LoginResult> call = retrofitInterface.executeLogin(map);

                call.enqueue(new Callback<LoginResult>() {
                    @Override
                    public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {

                        if (response.code() == 200) {

                            Toast.makeText(MainActivity.this, "Login Successfully",
                                    Toast.LENGTH_LONG).show();

                            LoginResult result = response.body();

                            Intent intent = new Intent(MainActivity.this,Profile.class);
                            intent.putExtra("email",result.getEmail());
                            intent.putExtra("name",result.getName());
                            startActivity(intent);
                        } else if (response.code() == 404) {
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
                    @Override
                    public void onFailure(Call<LoginResult> call, Throwable t) {
                        Toast.makeText(MainActivity.this, t.getMessage(),
                                Toast.LENGTH_LONG).show();

                    }
                });


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



