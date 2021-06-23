package com.allam.carsapp.db.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CarData {
    @Expose
    @SerializedName("imageUrl")
    private String imageUrl;
    @Expose
    @SerializedName("isUsed")
    private boolean isUsed;
    @Expose
    @SerializedName("constractionYear")
    private String constractionYear;
    @Expose
    @SerializedName("brand")
    private String brand;
    @Expose
    @SerializedName("id")
    private int id;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public boolean getIsUsed() {
        return isUsed;
    }

    public void setIsUsed(boolean isUsed) {
        this.isUsed = isUsed;
    }

    public String getConstractionYear() {
        return constractionYear;
    }

    public void setConstractionYear(String constractionYear) {
        this.constractionYear = constractionYear;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
