package com.example.lloyd.healthnutritiontool;

/**
 * Created by Lloyd on 12/19/2017.
 */

public class sendTo {
    public String brgy;
    public  String uname;
    public  String pass;

    public sendTo(String brgy,String uname,String pass){
        this.brgy = brgy;
        this.uname = uname;
        this.pass = pass;

    }

    public String getBrgy() {
        return brgy;
    }

    public String getUname() {
        return uname;
    }

    public String getPass() {
        return pass;
    }
}
