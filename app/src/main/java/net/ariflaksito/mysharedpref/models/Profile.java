package net.ariflaksito.mysharedpref.models;

import com.google.gson.annotations.SerializedName;

public class Profile {
    private String fullname;
    private String email;
    private String dept;
    private String company;

    public Profile(String fullname, String email, String dept, String company) {
        this.fullname = fullname;
        this.email = email;
        this.dept = dept;
        this.company = company;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}

