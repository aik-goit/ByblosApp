package com.example.byblos;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class branch implements Parcelable{

    private String name;
    private String phonenumber;
    private String mondayopen;
    private ArrayList<String> services;
    private String mondayclose;



    public branch(){}

    public branch(String name, String phonenumber,String mondayopen, String mondayclose, ArrayList<String> services){
        this.name = name;
        this.phonenumber = phonenumber;
        this.services = services;
        this.mondayopen = mondayopen;
        this.mondayclose = mondayclose;

    }

    protected branch(Parcel in) {
        name = in.readString();
        phonenumber = in.readString();
        mondayopen = in.readString();
        services = in.createStringArrayList();
        mondayclose = in.readString();
    }

    public static final Creator<branch> CREATOR = new Creator<branch>() {
        @Override
        public branch createFromParcel(Parcel in) {
            return new branch(in);
        }

        @Override
        public branch[] newArray(int size) {
            return new branch[size];
        }
    };

    public ArrayList<String> getServices() {
        return services;
    }

    public void setServices(ArrayList<String> services) {
        this.services = services;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getMondayopen() {
        return mondayopen;
    }

    public void setMondayopen(String mondayopen) {
        this.mondayopen = mondayopen;
    }



    public String getMondayclose() {
        return mondayclose;
    }

    public void setMondayclose(String mondayclose) {
        this.mondayclose = mondayclose;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(phonenumber);
        parcel.writeString(mondayopen);
        parcel.writeStringList(services);
        parcel.writeString(mondayclose);
    }
}
