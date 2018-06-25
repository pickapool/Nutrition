package com.example.lloyd.healthnutritiontool;

/**
 * Created by Lloyd on 1/6/2018.
 */

public class datas {
    String id;
    String names;

    public datas(String id, String names) {
        this.id = id;
        this.names = names;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }
}
