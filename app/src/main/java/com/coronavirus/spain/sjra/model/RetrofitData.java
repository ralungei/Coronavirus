package com.coronavirus.spain.sjra.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RetrofitData {
    @SerializedName("date")
    private DateUtil date;
    @SerializedName("quarantineEnd")
    private DateUtil quarantineDate;
    @SerializedName("states")
    private List<StateData> statesList;

    public RetrofitData(DateUtil date, DateUtil quarantineDate, List<StateData> statesList) {
        this.date = date;
        this.quarantineDate = quarantineDate;
        this.statesList = statesList;
    }

    public List<StateData> getStatesList() {
        return statesList;
    }

    public DateUtil getDate() {
        return date;
    }

    public DateUtil getQuarantineDate() {
        return quarantineDate;
    }
}
