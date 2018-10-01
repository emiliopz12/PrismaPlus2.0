package com.prismaplus.entities;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BillInfo {

    @SerializedName("Message")
    @Expose
    private String mSJ;

    public String getMSJ() {
        return mSJ;
    }

    public void setMSJ(String mSJ) {
        this.mSJ = mSJ;
    }

}