package com.example.sushma.vegetablemart.Pozos;

public class SignUpPozo {
    String fname,lname,email,mobile,pass,confirmpass,state,city,address;

    public SignUpPozo(String fname, String lname, String email, String mobile, String pass, String confirmpass, String state, String city, String address) {
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.mobile = mobile;
        this.pass = pass;
        this.confirmpass = confirmpass;
        this.state = state;
        this.city = city;
        this.address = address;
    }

    public SignUpPozo() {

    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getConfirmpass() {
        return confirmpass;
    }

    public void setConfirmpass(String confirmpass) {
        this.confirmpass = confirmpass;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
