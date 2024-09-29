package com.example.cocoagh.models;

public class Inputs {
    private int id, farmerId;
    private String inputType, inputQty, inputNote, farmName, requestDate, status;

    public Inputs(int id, int farmerId, String farmName, String inputType, String inputQty, String inputNote, String status, String requestDate) {
        this.id = id;
        this.farmerId = farmerId;
        this.farmName = farmName;
        this.inputType = inputType;
        this.inputQty = inputQty;
        this.inputNote = inputNote;
        this.requestDate = requestDate;
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Inputs(){}

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

    public String getInputType() {
        return inputType;
    }

    public void setInputType(String inputType) {
        this.inputType = inputType;
    }

    public String getInputQty() {
        return inputQty;
    }

    public void setInputQty(String inputQty) {
        this.inputQty = inputQty;
    }

    public String getInputNote() {
        return inputNote;
    }

    public void setInputNote(String inputNote) {
        this.inputNote = inputNote;
    }

    public String getFarmName() {
        return farmName;
    }

    public void setFarmName(String farmName) {
        this.farmName = farmName;
    }

    public String getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(String requestDate) {
        this.requestDate = requestDate;
    }
}
