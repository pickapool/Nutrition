package com.example.lloyd.healthnutritiontool;

/**
 * Created by Lloyd on 12/6/2017.
 */

public class Register {

public String firstname_last;
public String gender;
public String date_of_birth;
public String parent;
public String contact_number;
public String address;
public String house_number;
public int infant_ID;

    public Register(Integer infant_ID,String firstname_last, String gender, String date_of_birth, String parent, String contact_number, String address, String house_number) {
        this.infant_ID = infant_ID;
        this.firstname_last = firstname_last;
        this.gender = gender;
        this.date_of_birth = date_of_birth;
        this.parent = parent;
        this.contact_number = contact_number;
        this.address = address;
        this.house_number = house_number;
    }
    public Integer getInfant_ID(){return infant_ID;}

    public String getFirstname_last(){return firstname_last;}

    public String getGender() {
        return gender;
    }

    public String getDate_of_birth() {
        return date_of_birth;
    }

    public String getParent() {
        return parent;
    }

    public String getContact_number() {
        return contact_number;
    }

    public String getAddress() {
        return address;
    }

    public String getHouse_number() {
        return house_number;
    }
}
