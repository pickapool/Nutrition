package com.example.lloyd.healthnutritiontool;

/**
 * Created by Lloyd on 2/20/2018.
 */

public class bar {

    String ID;
    String date;
    String WFA;
    String HFA;
    String HFW;
    String MUAC;

    public bar(String ID, String date, String WFA, String HFA, String HFW, String MUAC) {
        this.ID = ID;
        this.date = date;
        this.WFA = WFA;
        this.HFA = HFA;
        this.HFW = HFW;
        this.MUAC = MUAC;
    }

    public String getID() {
        return ID;
    }

    public String getDate() {
        return date;
    }

    public String getWFA() {
        return WFA;
    }

    public String getHFA() {
        return HFA;
    }

    public String getHFW() {
        return HFW;
    }

    public String getMUAC() {
        return MUAC;
    }
}
