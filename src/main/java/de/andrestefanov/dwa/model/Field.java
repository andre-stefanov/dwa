package de.andrestefanov.dwa.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Field {

    @SerializedName("title")
    @Expose
    public String title;

    @SerializedName("value")
    @Expose
    public String value;

    @SerializedName("short")
    @Expose
    public Boolean isShort;

    @Override
    public String toString() {
        return "Field{" +
                "title='" + title + '\'' +
                ", value='" + value + '\'' +
                ", isShort=" + isShort +
                '}';
    }
}
