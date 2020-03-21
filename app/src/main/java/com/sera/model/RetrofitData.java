package com.sera.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RetrofitData {
    @SerializedName("date")
    private DateUtil date;
    @SerializedName("states")
    private List<StateData> statesList;

    public RetrofitData(DateUtil date, List<StateData> statesList) {
        this.date = date;
        this.statesList = statesList;
    }

    public List<StateData> getStatesList() {
        return statesList;
    }

    public DateUtil getDate() {
        return date;
    }
}
