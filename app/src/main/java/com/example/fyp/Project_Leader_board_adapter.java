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

public class Project_Leader_board_adapter extends RecyclerView.Adapter<Project_Leader_board_view_holder> implements Filterable
{
    ArrayList<Project_Leader_board_model> data;
    ArrayList<Project_Leader_board_model> backup;
    Context context;

    private Project_Leader_board_adapter.onItemClickListner mListner;

    public Project_Leader_board_adapter() {

    }

    public interface onItemClickListner
    {
        void onItemClick(int position);
    }

    public void setOnItemClickListner(Project_Leader_board_adapter.onItemClickListner listner)
    {
        mListner=listner;
    }

    public Project_Leader_board_adapter(ArrayList<Project_Leader_board_model> data, Context context)
    {
        this.data = data;
        this.context=context;
        backup=new ArrayList<>(data);
    }

    @NonNull
    @Override
    public Project_Leader_board_view_holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.project_leader_board,parent,false); ////// rehta hai
        return new Project_Leader_board_view_holder(view,mListner);
    }

    @Override
    public void onBindViewHolder(@NonNull final Project_Leader_board_view_holder holder, int position)
    {
        final Project_Leader_board_model temp=data.get(position);

        holder.St_id.setText(data.get(position).get_St_id());
        holder.T_id.setText(data.get(position).get_T_id());
        holder.badge_image.setImageResource(data.get(position).get_Badge_image());
        holder.Sr_id.setText(data.get(position).get_Sr_no());
        holder.Task_status.setText(data.get(position).getTask_status());
        holder.Gr_id.setText(data.get(position).get_Gr_no());

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
            ArrayList<Project_Leader_board_model> filtereddata=new ArrayList<>();

            if(keyword.toString().isEmpty())
                filtereddata.addAll(backup);
            else
            {
                for(Project_Leader_board_model obj : backup)
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
            data.addAll((ArrayList<Project_Leader_board_model>)results.values);
            notifyDataSetChanged();
        }
    };
}