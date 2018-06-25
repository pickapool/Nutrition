package com.example.lloyd.healthnutritiontool;

import android.support.annotation.NonNull;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Lloyd on 12/20/2017.
 */

public class list_stats{
    public String dorr;
    public String agee;
    public String hhh;
    public String www;
    public String mmm;
    public String ss1;
    public String ss2;
    public String ss3;
    public String ss4;
    public String representaive;


    public list_stats( String dorr, String agee, String hhh, String www, String mmm, String ss1, String ss2, String ss3, String ss4,String representaive) {
        this.dorr = dorr;
        this.agee = agee;
        this.hhh = hhh;
        this.www = www;
        this.mmm = mmm;
        this.ss1 = ss1;
        this.ss2 = ss2;
        this.ss3 = ss3;
        this.ss4 = ss4;
        this.representaive = representaive;

    }


    public String getDorr() {
        return dorr;
    }

    public void setDorr(String dorr) {
        this.dorr = dorr;
    }

    public String getAgee() {
        return agee;
    }

    public void setAgee(String agee) {
        this.agee = agee;
    }

    public String getHhh() {
        return hhh;
    }

    public void setHhh(String hhh) {
        this.hhh = hhh;
    }

    public String getWww() {
        return www;
    }

    public void setWww(String www) {
        this.www = www;
    }

    public String getMmm() {
        return mmm;
    }

    public void setMmm(String mmm) {
        this.mmm = mmm;
    }

    public String getSs1() {
        return ss1;
    }

    public void setSs1(String ss1) {
        this.ss1 = ss1;
    }

    public String getSs2() {
        return ss2;
    }

    public void setSs2(String ss2) {
        this.ss2 = ss2;
    }

    public String getSs3() {
        return ss3;
    }

    public void setSs3(String ss3) {
        this.ss3 = ss3;
    }

    public String getSs4() {
        return ss4;
    }

    public void setSs4(String ss4) {
        this.ss4 = ss4;
    }

    public String getRepresentaive() {
        return representaive;
    }

    public void setRepresentaive(String representaive) {
        this.representaive = representaive;
    }
}