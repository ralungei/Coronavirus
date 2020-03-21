package com.sera.model;

import com.google.gson.annotations.SerializedName;

public class DateUtil {

    @SerializedName("hour")
    private int hour;

    @SerializedName("day")
    private int day;

    @SerializedName("month")
    private int month;

    @SerializedName("year")
    private int year;

    public DateUtil(int hour, int day, int month, int year){
        this.hour = hour;
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public int getHour() {
        return hour;
    }
}
