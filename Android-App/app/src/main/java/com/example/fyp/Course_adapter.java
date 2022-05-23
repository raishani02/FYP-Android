package com.example.fyp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Course_adapter extends RecyclerView.Adapter<Course_view_holder> implements Filterable
{
    ArrayList<Course_model> data;
    ArrayList<Course_model> backup;
    Context context;

    private onItemClickListner mListner;

    public Course_adapter() {
    }

    public interface onItemClickListner
    {
        void onItemClick(int position);
    }

    public void setOnItemClickListner(onItemClickListner listner)
    {
        mListner=listner;
    }

    public Course_adapter(ArrayList<Course_model> data, Context context)
    {
        this.data = data;
        this.context=context;
        backup=new ArrayList<>(data);
    }

    @NonNull
    @Override
    public Course_view_holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.single_row_dashboard,parent,false);
        return new Course_view_holder(view,mListner);
    }

    @Override
    public void onBindViewHolder(@NonNull final Course_view_holder holder, int position)
    {
        final Course_model temp=data.get(position);

        holder.c_id.setText(data.get(position).getCourseId());
        holder.c_name.setText(data.get(position).getTitle());
        holder.course_name.setText(data.get(position).getfullName());
        holder.t_students.setText(data.get(position).getTotalStudents());
        holder.t_name.setText(data.get(position).getTeacherName());

        //  holder.img.setImageResource(data.get(position).getImgname());
    }

    @Override
    public int getItemCount()
    {
        return data.size();
    }


    @Override
    public Filter getFilter() {
        return filter;
    }

    Filter filter=new Filter() {
        @Override
        // background thread
        protected FilterResults performFiltering(CharSequence keyword)
        {
            ArrayList<Course_model> filtereddata=new ArrayList<>();

            if(keyword.toString().isEmpty())
                filtereddata.addAll(backup);
            else
            {
                for(Course_model obj : backup)
                {
                    //// base on course name
                    if(obj.getfullName().toString().toLowerCase().contains(keyword.toString().toLowerCase()))
                        filtereddata.add(obj);
                }
            }

            FilterResults results=new FilterResults();
            results.values=filtereddata;
            return results;
        }

        @Override  // main UI thread
        protected void publishResults(CharSequence constraint, FilterResults results)
        {
            data.clear();
            data.addAll((ArrayList<Course_model>)results.values);
            notifyDataSetChanged();
        }
    };
}

