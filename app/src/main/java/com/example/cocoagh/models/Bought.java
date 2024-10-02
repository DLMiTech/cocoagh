package com.example.cocoagh.models;

public class Bought {
    int id, lbcId, farmerId;
    String lbcName, lbcPhone, farmerName, farmerPhone, farmerLocation, quantity, total, payerName, paymentType, dateBought;

    public Bought(){}

    public Bought(int id, int lbcId, int farmerId, String lbcName, String lbcPhone, String farmerName, String farmerPhone, String farmerLocation, String quantity, String total, String payerName, String paymentType, String dateBought) {
        this.id = id;
        this.lbcId = lbcId;
        this.farmerId = farmerId;
        this.lbcName = lbcName;
        this.lbcPhone = lbcPhone;
        this.farmerName = farmerName;
        this.farmerPhone = farmerPhone;
        this.farmerLocation = farmerLocation;
        this.quantity = quantity;
        this.total = total;
        this.payerName = payerName;
        this.paymentType = paymentType;
        this.dateBought = dateBought;
    }

    public int getFarmerId() {
        return farmerId;
    }

    public void setFarmerId(int farmerId) {
        this.farmerId = farmerId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLbcId() {
        return lbcId;
    }

    public void setLbcId(int lbcId) {
        this.lbcId = lbcId;
    }

    public String getLbcName() {
        return lbcName;
    }

    public void setLbcName(String lbcName) {
        this.lbcName = lbcName;
    }

    public String getLbcPhone() {
        return lbcPhone;
    }

    public void setLbcPhone(String lbcPhone) {
        this.lbcPhone = lbcPhone;
    }

    public String getFarmerName() {
        return farmerName;
    }

    public void setFarmerName(String farmerName) {
        this.farmerName = farmerName;
    }

    public String getFarmerPhone() {
        return farmerPhone;
    }

    public void setFarmerPhone(String farmerPhone) {
        this.farmerPhone = farmerPhone;
    }

    public String getFarmerLocation() {
        return farmerLocation;
    }

    public void setFarmerLocation(String farmerLocation) {
        this.farmerLocation = farmerLocation;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getPayerName() {
        return payerName;
    }

    public void setPayerName(String payerName) {
        this.payerName = payerName;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getDateBought() {
        return dateBought;
    }

    public void setDateBought(String dateBought) {
        this.dateBought = dateBought;
    }
}
