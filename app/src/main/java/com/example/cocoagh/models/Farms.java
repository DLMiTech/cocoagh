package com.example.cocoagh.models;

public class Farms {
    private int id, farmerId;
    private double size;
    private String stage, location, farmName;
    private byte[] image;

    public Farms(){}

    public Farms(int id, int farmerId, double size, String stage, String location, String farmName, byte[] image) {
        this.id = id;
        this.farmerId = farmerId;
        this.size = size;
        this.stage = stage;
        this.location = location;
        this.farmName = farmName;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFarmerId() {
        return farmerId;
    }

    public void setFarmerId(int farmerId) {
        this.farmerId = farmerId;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getFarmName() {
        return farmName;
    }

    public void setFarmName(String farmName) {
        this.farmName = farmName;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
