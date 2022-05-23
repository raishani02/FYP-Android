package com.example.fyp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;

public class Project_Leader_board extends AppCompatActivity {
    RecyclerView rcv;
    Project_Leader_board_adapter adapter;
    ArrayList<Project_Leader_board_model> holder=new ArrayList<>();
    ArrayList<Project_Leader_board_model> temp_model=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_leader_board);
        setTitle("Project LeaderBoard");
        try {
            holder=dataqueue();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if(holder==null || holder.size()==0)
            holder=new ArrayList<>();
        buildrecyclerview();

        BottomNavigationView bottomNavigationView = findViewById(R.id.navigation_leaderboard);
        bottomNavigationView.setSelectedItemId(R.id.leader_board);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_home:
                        startActivity(new Intent(getApplicationContext(), Profile.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.nav_dashboard:
                        startActivity(new Intent(getApplicationContext(), Course_dashboard.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.nav_leaderboard:
                        startActivity(new Intent(getApplicationContext(), Leaderboard.class));
                        overridePendingTransition(0,0);
                    case R.id.nav_logout:
                        showPopup();
                        return true;
                }
                return false;
            }
        });
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
    public void buildrecyclerview()
    {
        rcv = (RecyclerView) findViewById(R.id.project_leader_board);
        rcv.setLayoutManager(new LinearLayoutManager(this));
        if(holder!=null) {
            adapter = new Project_Leader_board_adapter(holder, getApplicationContext());
            rcv.setAdapter(adapter);
            /*adapter.setOnItemClickListner(new Course_adapter.onItemClickListner() {
                @Override
                public void onItemClick(int position) {

                    //startActivity(new Intent(Course.this, Student_Leaderboard.class));
                }
            });*/
        }
    }

    private void showPopup() {
        AlertDialog.Builder alert = new AlertDialog.Builder(Project_Leader_board.this);
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

    public ArrayList<Project_Leader_board_model> dataqueue() throws ParseException {

        Calendar calendar=Calendar.getInstance();
        String time = calendar.get(Calendar.HOUR_OF_DAY)+":"+calendar.get(Calendar.MINUTE)+":"+calendar.get(Calendar.SECOND);

        Project_Leader_board_model ob1=new Project_Leader_board_model();
        ob1.set_Gr_id("G1");
        ob1.set_task_status("S");
        ob1.set_Sr_no("1");
        ob1.set_St_id("18L-1182");
        ob1.set_t_id("D1");
        ob1.set_Badge_image(R.drawable.diamond);
        holder.add(ob1);

        ob1=new Project_Leader_board_model();
        ob1.set_Gr_id("G1");
        ob1.set_task_status("U");
        ob1.set_Sr_no("2");
        ob1.set_St_id("18L-1183");
        ob1.set_t_id("D1");
        ob1.set_Badge_image(R.drawable.gold);
        holder.add(ob1);

        ob1=new Project_Leader_board_model();
        ob1.set_Gr_id("G1");
        ob1.set_task_status("S");
        ob1.set_Sr_no("3");
        ob1.set_St_id("18L-1184");
        ob1.set_t_id("D3");
        ob1.set_Badge_image(R.drawable.silver);
        holder.add(ob1);

        ob1=new Project_Leader_board_model();
        ob1.set_Gr_id("G2");
        ob1.set_task_status("S");
        ob1.set_Sr_no("4");
        ob1.set_St_id("18L-1185");
        ob1.set_t_id("D1");
        ob1.set_Badge_image(R.drawable.bronze);
        holder.add(ob1);

        ob1=new Project_Leader_board_model();
        ob1.set_Sr_no("5");
        ob1.set_Gr_id("G3");
        ob1.set_task_status("S");
        ob1.set_St_id("18L-1186");
        ob1.set_t_id("D1");
        ob1.set_Badge_image(R.drawable.diamond);
        holder.add(ob1);

        for(int i=1;i<20;i++)
        {
            ob1=new Project_Leader_board_model();
            ob1.set_Sr_no(String.valueOf(i+5));
            ob1.set_Gr_id("G3");
            ob1.set_task_status("S");
            ob1.set_St_id("18L-118"+String.valueOf(i%9));

            if(i%2==0)
            {
                ob1.set_t_id("D"+ String.valueOf(i%4+1));
                ob1.set_Badge_image(R.drawable.diamond);
            }
            else {

                ob1.set_Badge_image(R.drawable.gold);
            }
            holder.add(ob1);
        }

        return holder;
    }
}