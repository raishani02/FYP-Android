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

public class Course_Leader_board_adapter extends RecyclerView.Adapter<Course_Leader_board_view_holder> implements Filterable
{
    ArrayList<Course_Leader_board_model> data;
    ArrayList<Course_Leader_board_model> backup;
    Context context;

    private Course_Leader_board_adapter.onItemClickListner mListner;

    public Course_Leader_board_adapter() {

    }

    public interface onItemClickListner
    {
        void onItemClick(int position);
    }

    public void setOnItemClickListner(Course_Leader_board_adapter.onItemClickListner listner)
    {
        mListner=listner;
    }

    public Course_Leader_board_adapter(ArrayList<Course_Leader_board_model> data, Context context)
    {
        this.data = data;
        this.context=context;
        backup=new ArrayList<>(data);
    }

    @NonNull
    @Override
    public Course_Leader_board_view_holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.course_leader_board,parent,false); ////// rehta hai

        return new Course_Leader_board_view_holder(view,mListner);
    }

    @Override
    public void onBindViewHolder(@NonNull final Course_Leader_board_view_holder holder, int position)
    {
        final Course_Leader_board_model temp=data.get(position);

        holder.St_id.setText(data.get(position).get_St_id());
        holder.T_id.setText(data.get(position).get_T_id());
        holder.badge_image.setImageResource(data.get(position).get_Badge_image());
        holder.Sr_id.setText(data.get(position).get_Sr_no());

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
            ArrayList<Course_Leader_board_model> filtereddata=new ArrayList<>();
            if(keyword.toString().isEmpty())
                filtereddata.addAll(backup);
            else
            {
                for(Course_Leader_board_model obj : backup)
                {
                    if(obj.get_St_id().toString().toLowerCase().contains(keyword.toString().toLowerCase()))
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
            data.addAll((ArrayList<Course_Leader_board_model>)results.values);
            notifyDataSetChanged();
        }
    };
}