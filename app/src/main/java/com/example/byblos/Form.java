package com.example.byblos;

public class Form {

    private String service;
    private String fullname = "";
    private String licenseType = "";
    private String preferredType = "";
    private String pickupDate = "";
    private String pickupTime = "";
    private String returnDate = "";
    private String returnTime = "";
    private String kmdriven = "";
    private String area = "";
    private String email = "";
    private String movingstart = "";
    private String movingend = "";
    private String moversneeded = "";
    private String boxesneeded = "";

    public Form(){}

    public Form(String service, String fullname, String licenseType, String preferredType, String pickupDate, String pickupTime, String returnDate, String returnTime){
        this.service = service;
        this.fullname = fullname;
        this.licenseType = licenseType;
        this.preferredType = preferredType;
        this.pickupDate = pickupDate;
        this.pickupTime = pickupTime;
        this.returnDate = returnDate;
        this.returnTime = returnTime;
    }

    public Form(String service, String fullname,String email, String licenseType, String kmdriven, String area, String pickupDate, String pickupTime, String returnDate, String returnTime){
        this.service = service;
        this.fullname = fullname;
        this.licenseType = licenseType;
        this.email = email;
        this.kmdriven = kmdriven;
        this.area = area;
        this.pickupDate = pickupDate;
        this.pickupTime = pickupTime;
        this.returnDate = returnDate;
        this.returnTime = returnTime;
    }

    public Form(String service, String fullname, String email, String movingstart, String movingend, String moversneeded, String boxesneeded){
        this.service = service;
        this.fullname = fullname;
        this.email = email;
        this.movingstart = movingstart;
        this.movingend = movingend;
        this.moversneeded = moversneeded;
        this.boxesneeded = boxesneeded;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getLicenseType() {
        return licenseType;
    }

    public void setLicenseType(String licenseType) {
        this.licenseType = licenseType;
    }

    public String getPreferredType() {
        return preferredType;
    }

    public void setPreferredType(String preferredType) {
        this.preferredType = preferredType;
    }

    public String getPickupDate() {
        return pickupDate;
    }

    public void setPickupDate(String pickupDate) {
        this.pickupDate = pickupDate;
    }

    public String getPickupTime() {
        return pickupTime;
    }

    public void setPickupTime(String pickupTime) {
        this.pickupTime = pickupTime;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public String getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(String returnTime) {
        this.returnTime = returnTime;
    }

    public String getKmdriven() {
        return kmdriven;
    }

    public void setKmdriven(String kmdriven) {
        this.kmdriven = kmdriven;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMovingstart() {
        return movingstart;
    }

    public void setMovingstart(String movingstart) {
        this.movingstart = movingstart;
    }

    public String getMovingend() {
        return movingend;
    }

    public void setMovingend(String movingend) {
        this.movingend = movingend;
    }

    public String getMoversneeded() {
        return moversneeded;
    }

    public void setMoversneeded(String moversneeded) {
        this.moversneeded = moversneeded;
    }

    public String getBoxesneeded() {
        return boxesneeded;
    }

    public void setBoxesneeded(String boxesneeded) {
        this.boxesneeded = boxesneeded;
    }
}
