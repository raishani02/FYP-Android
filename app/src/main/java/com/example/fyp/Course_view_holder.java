package com.example.fyp;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

public class Course_view_holder extends RecyclerView.ViewHolder
{

    TextView c_id,c_name,t_name,course_name,t_students;
    View subview;
    public Course_view_holder(@NonNull View itemView, final Course_adapter.onItemClickListner listner)
    {
        super(itemView);
        c_id=(TextView)itemView.findViewById(R.id.c_id);
        c_name=(TextView)itemView.findViewById(R.id.c_name);
        t_name=(TextView)itemView.findViewById(R.id.t_name);
        course_name=(TextView)itemView.findViewById(R.id.course_name);
        t_students=(TextView)itemView.findViewById(R.id.t_students);
        subview = itemView.findViewById(R.id.expanded_view);


        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listner!=null)
                {
                    int position=getAdapterPosition();
                    if(position!=RecyclerView.NO_POSITION)
                    {
                        listner.onItemClick(position);
                    }
                }
            }
        });
    }

    public Course_view_holder(@NonNull View itemView)
    {
        super(itemView);
        c_id =(TextView)itemView.findViewById(R.id.c_id);
        c_name=(TextView)itemView.findViewById(R.id.c_name);
        t_name=(TextView)itemView.findViewById(R.id.t_name);
        course_name=(TextView)itemView.findViewById(R.id.course_name);
        t_students=(TextView)itemView.findViewById(R.id.t_students);
        subview = itemView.findViewById(R.id.expanded_view);

    }

//    public void onBind(@NonNull Course_details coursedetails, ExpandableGroup group) {
//        c_name.setText(coursedetails.getName());
////        if (group.getTitle().equals("Android")) {
////            phoneName.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
////        } else if (group.getTitle().equals("iOS")) {
////            phoneName.setCompoundDrawablesWithIntrinsicBounds(R.drawable.diamond, 0, 0, 0);
////        } else {
////            phoneName.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
////        }
//        c_name.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
//    }

}
