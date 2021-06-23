package com.allam.carsapp.db.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CarModel {
    @Expose
    @SerializedName("data")
    private List<CarData> data;
    @Expose
    @SerializedName("status")
    private int status;

    public List<CarData> getData() {
        return data;
    }

    public void setData(List<CarData> data) {
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
