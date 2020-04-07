package com.coronavirus.spain.sjra.model;

import com.google.gson.annotations.SerializedName;

public class StateData {
    @SerializedName("id")
    private String id;

    @SerializedName("new")
    private int newCases;

    @SerializedName("total")
    private int totalCases;

    @SerializedName("diff")
    private int diffCases;

    public StateData(String id, int newCases, int totalCases, int diffCases) {
        this.id = id;
        this.newCases = newCases;
        this.totalCases = totalCases;
        this.diffCases = diffCases;
    }

    public String getId() {
        return id;
    }

    public void setTitle(String title) {
        this.id = title;
    }

    public int getNewCases() {
        return newCases;
    }

    public void setNewCases(int newCases) {
        this.newCases = newCases;
    }

    public int getTotalCases() {
        return totalCases;
    }

    public void setTotalCases(int totalCases) {
        this.totalCases = totalCases;
    }

    public int getDiffCases() {
        return diffCases;
    }

    public void setDiffCases(int diffCases) {
        this.diffCases = diffCases;
    }
}
