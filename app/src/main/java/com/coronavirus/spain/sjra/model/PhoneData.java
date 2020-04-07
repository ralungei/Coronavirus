package com.coronavirus.spain.sjra.model;

public class PhoneData {

    private String region;

    private String number;

    public PhoneData(String region, String number) {
        this.region = region;
        this.number = number;
    }

    public String getRegion() {
        return region;
    }

    public String getNumber() {
        return number;
    }
}