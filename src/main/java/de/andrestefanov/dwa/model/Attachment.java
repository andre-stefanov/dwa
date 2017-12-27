package de.andrestefanov.dwa.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Attachment {

    @SerializedName("fallback")
    @Expose
    public String fallback;

    @SerializedName("callback_id")
    @Expose
    public String callbackId;

    @SerializedName("actions")
    @Expose
    public List<Action> actions;

    @SerializedName("color")
    @Expose
    public String color;

    @SerializedName("pretext")
    @Expose
    public String pretext;

    @SerializedName("author_name")
    @Expose
    public String authorName;

    @SerializedName("author_link")
    @Expose
    public String authorLink;

    @SerializedName("author_icon")
    @Expose
    public String authorIcon;

    @SerializedName("title")
    @Expose
    public String title;

    @SerializedName("title_link")
    @Expose
    public String titleLink;

    @SerializedName("text")
    @Expose
    public String text;

    @SerializedName("fields")
    @Expose
    public List<Field> fields;

    @SerializedName("image_url")
    @Expose
    public String imageUrl;

    @SerializedName("thumb_url")
    @Expose
    public String thumbUrl;

    @SerializedName("footer")
    @Expose
    public String footer;

    @SerializedName("footer_icon")
    @Expose
    public String footerIcon;

    @SerializedName("ts")
    @Expose
    public Integer ts;

    @Override
    public String toString() {
        return "Attachment{" +
                "fallback='" + fallback + '\'' +
                ", callbackId='" + callbackId + '\'' +
                ", actions=" + actions +
                ", color='" + color + '\'' +
                ", pretext='" + pretext + '\'' +
                ", authorName='" + authorName + '\'' +
                ", authorLink='" + authorLink + '\'' +
                ", authorIcon='" + authorIcon + '\'' +
                ", title='" + title + '\'' +
                ", titleLink='" + titleLink + '\'' +
                ", text='" + text + '\'' +
                ", fields=" + fields +
                ", imageUrl='" + imageUrl + '\'' +
                ", thumbUrl='" + thumbUrl + '\'' +
                ", footer='" + footer + '\'' +
                ", footerIcon='" + footerIcon + '\'' +
                ", ts=" + ts +
                '}';
    }
}
