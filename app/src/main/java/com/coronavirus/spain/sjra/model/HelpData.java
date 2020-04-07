package com.coronavirus.spain.sjra.model;

import com.google.gson.annotations.SerializedName;

public class HelpData {
    @SerializedName("title")
    private String title;

    @SerializedName("link")
    private String link;

    public HelpData(String title, String link) {
        this.title = title;
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }
}
