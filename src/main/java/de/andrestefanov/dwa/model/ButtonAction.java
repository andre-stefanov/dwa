package de.andrestefanov.dwa.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ButtonAction {

    @SerializedName("actions")
    @Expose
    public List<Action> actions = null;

    @SerializedName("message_ts")
    @Expose
    public String messageTs;

    @Override
    public String toString() {
        return "ButtonAction{" +
                "actions=" + actions +
                ", messageTs='" + messageTs + '\'' +
                '}';
    }
}
