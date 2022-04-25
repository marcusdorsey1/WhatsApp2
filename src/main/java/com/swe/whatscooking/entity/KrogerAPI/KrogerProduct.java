package com.swe.whatscooking.entity.KrogerAPI;

public class KrogerProduct {
    private String upc;
    private String description;
    private String brand;

    public KrogerProduct(String upc, String description, String brand) {
        this.upc = upc;
        this.description = description;
        this.brand = brand;
    }

    public String getUpc() {
        return upc;
    }

    public void setUpc(String upc) {
        this.upc = upc;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}
