package com.example.fyp;

public class Movie {

    private String c_id;
    private String c_title;
    private String c_name;
    private String t_name;
    private String t_students;

    // State of the item
    private boolean expanded;

    public Movie(String c_id, String c_title, String c_name, String t_name, String t_students) {
        this.c_id=c_id;
        this.c_title=c_title;
        this.c_name=c_name;
        this.t_name=t_name;
        this.t_students=t_students;
    }

    public String getTitle() {
        return c_title;
    }

    public String getfullName() {
        return c_name;
    }

    public String getTeacherName() {
        return t_name;
    }

    public String getCourseId() {
        return c_id;
    }

    public String getTotalStudents() {
        return t_students;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }

    public boolean isExpanded() {
        return expanded;
    }
}