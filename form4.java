package com.example.lloyd.healthnutritiontool;

/**
 * Created by Lloyd on 2/21/2018.
 */

public class form4 {
    String name;
    String sex;
    String dob;
    String aim;
    String wt;
    String wt_ns;
    String ht;
    String ht_ns;
    String muac_ns;

    public form4(String name, String sex, String dob, String aim, String wt, String wt_ns, String ht, String ht_ns, String muac_ns) {
        this.name = name;
        this.sex = sex;
        this.dob = dob;
        this.aim = aim;
        this.wt = wt;
        this.wt_ns = wt_ns;
        this.ht = ht;
        this.ht_ns = ht_ns;
        this.muac_ns = muac_ns;
    }

    public String getName() {
        return name;
    }

    public String getSex() {
        return sex;
    }

    public String getDob() {
        return dob;
    }

    public String getAim() {
        return aim;
    }

    public String getWt() {
        return wt;
    }

    public String getWt_ns() {
        return wt_ns;
    }

    public String getHt() {
        return ht;
    }

    public String getHt_ns() {
        return ht_ns;
    }

    public String getMuac_ns() {
        return muac_ns;
    }
}
