package com.example.fyp;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Project_Leader_board_view_holder extends RecyclerView.ViewHolder
{

    TextView Sr_id,St_id,T_id,Gr_id,Task_status;
    ImageView badge_image;
    public Project_Leader_board_view_holder(@NonNull View itemView, final Project_Leader_board_adapter.onItemClickListner listner)
    {
        super(itemView);
        Sr_id=(TextView)itemView.findViewById(R.id.sr);
        St_id=(TextView)itemView.findViewById(R.id.u_id);
        T_id=(TextView)itemView.findViewById(R.id.t_id);
        Task_status=(TextView) itemView.findViewById(R.id.task);
        Gr_id=(TextView) itemView.findViewById(R.id.gr_id);
        badge_image=(ImageView) itemView.findViewById(R.id.b_id);
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
    public Project_Leader_board_view_holder(@NonNull View itemView)
    {
        super(itemView);
        Sr_id=(TextView)itemView.findViewById(R.id.sr);
        St_id=(TextView)itemView.findViewById(R.id.u_id);
        T_id=(TextView)itemView.findViewById(R.id.t_id);
        Task_status=(TextView) itemView.findViewById(R.id.task);
        Gr_id=(TextView) itemView.findViewById(R.id.gr_id);
        badge_image=(ImageView) itemView.findViewById(R.id.b_id);

    }


}