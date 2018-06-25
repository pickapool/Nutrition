package com.example.lloyd.healthnutritiontool;

/**
 * Created by Lloyd on 3/15/2018.
 */

public class signup_model {

    String brgy;
    String name;
    String uname;
    String pass;

    public signup_model(String brgy, String name, String uname, String pass) {
        this.brgy = brgy;
        this.name = name;
        this.uname = uname;
        this.pass = pass;
    }

    public String getBrgy() {
        return brgy;
    }

    public String getName() {
        return name;
    }

    public String getUname() {
        return uname;
    }

    public String getPass() {
        return pass;
    }
}
