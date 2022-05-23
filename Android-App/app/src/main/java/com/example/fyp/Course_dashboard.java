package com.example.fyp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Course_dashboard extends AppCompatActivity {

    RecyclerView rcv;
    RecAdapter adapter;
    ArrayList<Course_model> holder=new ArrayList<>();
    ArrayList<Course_model> temp_model=new ArrayList<>();
    List<Movie> movieList = new ArrayList<>();

    View itemView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_view);
        setTitle("Enrolled Courses");
        try {
            holder=dataqueue();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if(holder==null || holder.size()==0)
            holder=new ArrayList<>();

        buildrecyclerview();

//        Thread thread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    //Your code goes here
//                    buildrecyclerview();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });

        navigation_functionality();

    }
    public void buildrecyclerview()
    {
        adapter = new RecAdapter(movieList,this);
        RecyclerView recyclerView = findViewById(R.id.recviews);
        ((SimpleItemAnimator) recyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.fyp_menu,menu);
        MenuItem menuItem=menu.findItem(R.id.search);
        SearchView searchView=(SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapter.getFilter().filter(s);
                return true;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
    public ArrayList<Course_model> dataqueue() throws ParseException {

        Calendar calendar=Calendar.getInstance();
        String time = calendar.get(Calendar.HOUR_OF_DAY)+":"+calendar.get(Calendar.MINUTE)+":"+calendar.get(Calendar.SECOND);

        movieList.add(new Movie("CS540", "DB"," DataBase", "Prof Ishaq Raza","54"));
        movieList.add(new Movie("CS570", "OS","Operating Systems", "Dr. Ibrahim Nadir","38"));
        movieList.add(new Movie("CS540", "Algo","Algorithm", "Prof Sarim Baig","50"));
        movieList.add(new Movie("CS510", "ADB","Advanced DataBase", "Prof Ishaq Raza","40"));
        movieList.add(new Movie("CS521", "DS"," Data Structure", "Prof Osman","45"));
        movieList.add(new Movie("CS540", "SDA"," Software Design and Analysis", "Dr. Amir Iqbal","40"));
        movieList.add(new Movie("CS540", "SE"," Software Engineering", "Dr. Afzal Malik","54"));
        return holder;
    }

    private void navigation_functionality(){
        BottomNavigationView bottomNavigationView = findViewById(R.id.navigation);
        bottomNavigationView.setSelectedItemId(R.id.dashboard);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_home:
                        startActivity(new Intent(getApplicationContext(), Profile.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.nav_dashboard:
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
        AlertDialog.Builder alert = new AlertDialog.Builder(Course_dashboard.this);
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
