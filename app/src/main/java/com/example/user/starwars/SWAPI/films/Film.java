package com.example.user.starwars.SWAPI.films;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by user on 13.07.2016.
 */
public class Film implements Parcelable {
    String title;
    String director;
    String Producer;
    String openingCrawl;
    String releaseDate;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getProducer() {
        return Producer;
    }

    public void setProducer(String producer) {
        Producer = producer;
    }

    public String getOpeningCrawl() {
        return openingCrawl;
    }

    public void setOpeningCrawl(String openingCrawl) {
        this.openingCrawl = openingCrawl;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releseDate) {
        this.releaseDate = releseDate;
    }

    @Override
    public String toString() {
        return "Film{" +
                "title='" + title + '\'' +
                ", director='" + director + '\'' +
                ", Producer='" + Producer + '\'' +
                ", openingCrawl='" + openingCrawl + '\'' +
                ", releseDate='" + releaseDate + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.director);
        dest.writeString(this.Producer);
        dest.writeString(this.openingCrawl);
        dest.writeString(this.releaseDate);
    }

    public Film() {
    }

    protected Film(Parcel in) {
        this.title = in.readString();
        this.director = in.readString();
        this.Producer = in.readString();
        this.openingCrawl = in.readString();
        this.releaseDate = in.readString();
    }

    public static final Creator<Film> CREATOR = new Creator<Film>() {
        @Override
        public Film createFromParcel(Parcel source) {
            return new Film(source);
        }

        @Override
        public Film[] newArray(int size) {
            return new Film[size];
        }
    };
}
