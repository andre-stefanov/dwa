package de.andrestefanov.dwa.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Message {

    @SerializedName("channel")
    @Expose
    public String channel;

    @SerializedName("text")
    @Expose
    public String text;

    @SerializedName("ts")
    @Expose
    public String ts;

    @SerializedName("username")
    @Expose
    public String username;

    @SerializedName("attachments")
    @Expose
    public List<Attachment> attachments;

    @Override
    public String toString() {
        return "Message{" +
                "channel='" + channel + '\'' +
                ", text='" + text + '\'' +
                ", ts='" + ts + '\'' +
                ", username='" + username + '\'' +
                ", attachments=" + attachments +
                '}';
    }
}
