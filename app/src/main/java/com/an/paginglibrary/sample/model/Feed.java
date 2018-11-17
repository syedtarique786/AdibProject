package com.an.paginglibrary.sample.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.an.paginglibrary.sample.utils.AppUtils;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Feed implements Parcelable {

    public static final Creator<Feed> CREATOR = new Creator<Feed>() {
        @Override
        public Feed createFromParcel(Parcel in) {
            return new Feed(in);
        }

        @Override
        public Feed[] newArray(int size) {
            return new Feed[size];
        }
    };
    private transient long id;
    private String status;
    private String copyright;
    private long num_results;
    @SerializedName("results")
    private List<Results> articles;

    protected Feed(Parcel in) {
        id = AppUtils.getRandomNumber();
        status = in.readString();
        copyright = in.readString();
        num_results = in.readLong();
        articles = in.createTypedArrayList(Results.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(status);
        dest.writeString(copyright);
        dest.writeLong(num_results);
        dest.writeTypedList(articles);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getNum_results() {
        return num_results;
    }

    public void setNum_results(Long num_results) {
        this.num_results = num_results;
    }

   /* public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }*/

    public List<Results> getArticles() {
        return articles;
    }

    public void setArticles(List<Results> articles) {
        this.articles = articles;
    }
}
