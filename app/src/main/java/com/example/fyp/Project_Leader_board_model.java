package com.example.fyp;

import java.io.Serializable;

public class Project_Leader_board_model implements Serializable {
    private String Sr_no;
    private String Gr_id;
    private String St_id;
    private String Task_id;
    private String Task_status;
    private int badge_image;
    public Project_Leader_board_model(){

    }
    public void set_Sr_no(String c_id)
    {
        Sr_no=c_id;
    }
    public void set_St_id(String c_id)
    {
        St_id=c_id;
    }
    public void set_Gr_id(String g_id)
    {
        Gr_id=g_id;
    }
    public void set_task_status(String status)
    {
        Task_status=status;
    }
    public void set_t_id(String c_name)
    {
        Task_id=c_name;
    }
    public void set_Badge_image(int b)
    {
        badge_image=b;
    }
    public String get_Gr_no()
    {
        return Gr_id;
    }
    public String getTask_status()
    {
        return Task_status;
    }
    public String get_Sr_no()
    {
        return Sr_no;
    }
    public String get_St_id()
    {
        return St_id;
    }
    public String get_T_id()
    {
        return Task_id;
    }
    public int get_Badge_image()
    {
        return badge_image;
    }
}
