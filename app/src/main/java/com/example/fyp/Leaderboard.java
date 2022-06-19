package com.example.fyp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Leaderboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.leaderboard);
        Bundle extras = getIntent().getExtras();
        String value=null;
        if (extras != null) {
            value = extras.getString("key");
            //The key argument here must match that used in the other activity
        }
        setTitle(value);
        Button button1= (Button)findViewById(R.id.course);
        Button button2= (Button)findViewById(R.id.project);
        button1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent1=getIntent();
                Intent intent = new Intent(getApplicationContext(),Course_leader_board.class);
                intent.putExtra("email", intent1.getStringExtra("email"));
                intent.putExtra("name",intent1.getStringExtra("name"));
                startActivity(intent);
            }
        });
        button2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent1=getIntent();
                Intent intent = new Intent(getApplicationContext(),Project_Leader_board.class);
                intent.putExtra("email", intent1.getStringExtra("email"));
                intent.putExtra("name",intent1.getStringExtra("name"));
                startActivity(intent);
            }
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.navigation_leaderboard);
        bottomNavigationView.setSelectedItemId(R.id.leader_board);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_home:
                        Intent intent1=getIntent();
                        Intent intent = new Intent(getApplicationContext(),Profile.class);
                        intent.putExtra("email", intent1.getStringExtra("email"));
                        intent.putExtra("name",intent1.getStringExtra("name"));
                        startActivity(intent);
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.nav_dashboard:
                        Intent intent2=getIntent();
                        Intent intent3 = new Intent(getApplicationContext(),Course_dashboard.class);
                        intent3.putExtra("email", intent2.getStringExtra("email"));
                        intent3.putExtra("name",intent2.getStringExtra("name"));
                        startActivity(intent3);
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.nav_leaderboard:
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
        AlertDialog.Builder alert = new AlertDialog.Builder(Leaderboard.this);
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
        super.onDestroy();
    }
}