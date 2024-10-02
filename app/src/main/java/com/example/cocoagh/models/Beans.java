package com.example.cocoagh.models;

import java.io.Serializable;

public class Beans implements Serializable {
    int id, farmerId;
    double quantity, price, total;
    String address, phone, farmerName, addedDate, status;

    public Beans(int id, int farmerId, double quantity, double price, double total, String address, String phone, String farmerName, String addedDate, String status) {
        this.id = id;
        this.farmerId = farmerId;
        this.quantity = quantity;
        this.price = price;
        this.total = total;
        this.address = address;
        this.phone = phone;
        this.farmerName = farmerName;
        this.addedDate = addedDate;
        this.status = status;
    }

    public Beans(){}

    public String getAddedDate() {
        return addedDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setAddedDate(String addedDate) {
        this.addedDate = addedDate;
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

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal() {
        this.total = this.price * this.quantity;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFarmerName() {
        return farmerName;
    }

    public void setFarmerName(String farmerName) {
        this.farmerName = farmerName;
    }
}
