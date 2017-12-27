package de.andrestefanov.dwa.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MessageResponse {

    @SerializedName("message")
    @Expose
    public Message message;

    @Override
    public String toString() {
        return "MessageResponse{" +
                "message=" + message +
                '}';
    }
}
