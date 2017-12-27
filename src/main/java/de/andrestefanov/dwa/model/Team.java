package de.andrestefanov.dwa.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Team {

    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("domain")
    @Expose
    public String domain;

    @Override
    public String toString() {
        return "Team{" +
                "id='" + id + '\'' +
                ", domain='" + domain + '\'' +
                '}';
    }
}
