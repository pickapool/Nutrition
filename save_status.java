package com.example.lloyd.healthnutritiontool;

/**
 * Created by Lloyd on 12/20/2017.
 */

public class save_status {
    public String child_IDs;
    public String date_of_recordings;
    public String child_age_now;
    public String child_weight;
    public String child_height;
    public String midUpArmCir;
    public String weight_for_age;
    public String height_for_age;
    public String height_fow_weight;
    public String midUpArmCir_stats;
    public String representative;

    public save_status(String child_IDs, String date_of_recordings, String child_age_now, String child_weight, String child_height, String midUpArmCir, String weight_for_age, String height_for_age, String height_fow_weight, String midUpArmCir_stats, String representative) {
        this.child_IDs = child_IDs;
        this.date_of_recordings = date_of_recordings;
        this.child_age_now = child_age_now;
        this.child_weight = child_weight;
        this.child_height = child_height;
        this.midUpArmCir = midUpArmCir;
        this.weight_for_age = weight_for_age;
        this.height_for_age = height_for_age;
        this.height_fow_weight = height_fow_weight;
        this.midUpArmCir_stats = midUpArmCir_stats;
        this.representative = representative;
    }

    public String getChild_IDs() {
        return child_IDs;
    }

    public String getDate_of_recordings() {
        return date_of_recordings;
    }

    public String getChild_age_now() {
        return child_age_now;
    }

    public String getChild_weight() {
        return child_weight;
    }

    public String getChild_height() {
        return child_height;
    }

    public String getMidUpArmCir() {
        return midUpArmCir;
    }

    public String getWeight_for_age() {
        return weight_for_age;
    }

    public String getHeight_for_age() {
        return height_for_age;
    }

    public String getHeight_fow_weight() {
        return height_fow_weight;
    }

    public String getMidUpArmCir_stats() {
        return midUpArmCir_stats;
    }

    public String getRepresentative() {
        return representative;
    }
}
