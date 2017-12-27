package de.andrestefanov.dwa.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Action {

    @SerializedName("name")
    @Expose
    public String name;

    @SerializedName("value")
    @Expose
    public String value;

    @SerializedName("type")
    @Expose
    public String type;

    @SerializedName("text")
    @Expose
    public String text;

    @SerializedName("style")
    @Expose
    public String style;

    @Override
    public String toString() {
        return "Action{" +
                "name='" + name + '\'' +
                ", value='" + value + '\'' +
                ", type='" + type + '\'' +
                ", text='" + text + '\'' +
                ", style='" + style + '\'' +
                '}';
    }
}
