package com.example.fyp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Hashtable;

public class Profile extends AppCompatActivity {

    String name;
    String email;
    String dob;
    Hashtable count_info = new Hashtable();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extra_profile);

        TextView nameView = (TextView) findViewById(R.id.name_id);
        TextView emailView = (TextView) findViewById(R.id.email_id);
        TextView dobView = (TextView) findViewById(R.id.dob_id);


        if (savedInstanceState!=null) {

             name = (String) count_info.get("name");
            email = (String) count_info.get("email");

        } else {
             Intent intent = getIntent();
            name = intent.getStringExtra("name");
            email = intent.getStringExtra("email");
        }

        nameView.setText(name);
        emailView.setText(email);

        BottomNavigationView bottomNavigationView = findViewById(R.id.navigation);
        bottomNavigationView.setSelectedItemId(R.id.profile);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_home:
                        return true;
                    case R.id.nav_dashboard:
                        Intent intent = new Intent(getApplicationContext(),Course_dashboard.class);
                        intent.putExtra("email",email);
                        intent.putExtra("name",name);
                        startActivity(intent);
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.nav_logout:
                        showPopup();
                        return true;
                }
                return false;
            }
        });
    }

    private void showPopup() {
        AlertDialog.Builder alert = new AlertDialog.Builder(Profile.this);
        alert.setMessage("Are you sure?")
                .setPositiveButton("Logout", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {

                        logout(); // Last step. Logout function

                    }
                }).setNegativeButton("Cancel", null);

        AlertDialog alert1 = alert.create();
        alert1.show();
    }

    private void logout() {
        // startActivity(new Intent(this, MainActivity2.class));
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        this.startActivity(intent);
        finish();
        // onDestroy();

    }
    @Override
    protected void onDestroy() {
//        Toast.makeText(Profile.this, "Activity destroyed",
//                Toast.LENGTH_LONG).show();
        super.onDestroy();
    }

        @Override
    protected void onStop() {
//        Toast.makeText(Profile.this, "Activity STopped",
//                Toast.LENGTH_LONG).show();
        super.onStop();
    }

    @Override
    public void onSaveInstanceState(Bundle outstate) {
        super.onSaveInstanceState(outstate);

//        Toast.makeText(Profile.this, "Saved Instances",
//                Toast.LENGTH_LONG).show();
        // Save the user's current game state

        count_info.put("name",name);
        count_info.put("email",email);
        // Always call the superclass so it can save the view hierarchy state

    }


}