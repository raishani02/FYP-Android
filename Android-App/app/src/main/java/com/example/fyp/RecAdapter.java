package com.example.fyp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecAdapter extends RecyclerView.Adapter<RecAdapter.RecViewHolder> implements Filterable {

    private List<Movie> list;

    List<Movie> data;
    List<Movie> backup;
    Context context;


    public RecAdapter(List<Movie> list,Context context) {
        this.list = list;
        this.data = list;
        this.context=context;
        backup=new ArrayList<>(data);
    }

    @Override
    public RecViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.single_row_dashboard, parent, false);

        return new RecViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecViewHolder holder, int position) {
        Movie movie = list.get(position);

        holder.bind(movie);

        holder.itemView.setOnClickListener(v -> {
            boolean expanded = movie.isExpanded();
            movie.setExpanded(!expanded);
            notifyItemChanged(position);
        });
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }



    public class RecViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView title;
        private TextView c_id;
        private TextView c_name;
        private TextView t_name;
        private TextView t_students;
        private View subItem;
        private Button leaderboard_button;

        public RecViewHolder(View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.c_name);
            c_id = itemView.findViewById(R.id.c_id);
            c_name = itemView.findViewById(R.id.course_name);
            t_name = itemView.findViewById(R.id.t_name);
            t_students = itemView.findViewById(R.id.t_students);
            subItem = itemView.findViewById(R.id.expanded_view);
            leaderboard_button = itemView.findViewById(R.id.leader_board);
            leaderboard_button.setOnClickListener(this);

//            leaderboard_button.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent intent = new Intent(v.getContext(), Course_leader_board.class);
//                    v.getContext().startActivity(intent);
//                }
//            });

        }


        @Override
        public void onClick(View v) {
            Intent intent = new Intent (v.getContext(), Leaderboard.class);
            v.getContext().startActivity(intent);
        }

        private void bind(Movie movie) {
            boolean expanded = movie.isExpanded();

            subItem.setVisibility(expanded ? View.VISIBLE : View.GONE);
            c_id.setText(movie.getCourseId());
            title.setText(movie.getTitle());
            c_name.setText(movie.getfullName());
            t_name.setText(movie.getTeacherName());
            t_students.setText(movie.getTotalStudents());

        }

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
            List<Movie> filtereddata=new ArrayList<>();

            if(keyword.toString().isEmpty())
                filtereddata.addAll(backup);
            else
            {
                for(Movie obj : backup)
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
            data.addAll((List<Movie>)results.values);
            notifyDataSetChanged();
        }
    };
}