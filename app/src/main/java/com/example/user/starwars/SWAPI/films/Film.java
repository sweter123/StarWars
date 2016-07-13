package com.example.user.starwars.SWAPI.films;

/**
 * Created by user on 13.07.2016.
 */
public class Film {
    String title;
    int episodeId;
    String director;
    String Producer;
    String openingCrawl;
    String releseDate;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getEpisodeId() {
        return episodeId;
    }

    public void setEpisodeId(int episodeId) {
        this.episodeId = episodeId;
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

    public String getReleseDate() {
        return releseDate;
    }

    public void setReleseDate(String releseDate) {
        this.releseDate = releseDate;
    }

    @Override
    public String toString() {
        return "Film{" +
                "title='" + title + '\'' +
                ", episodeId=" + episodeId +
                ", director='" + director + '\'' +
                ", Producer='" + Producer + '\'' +
                ", openingCrawl='" + openingCrawl + '\'' +
                ", releseDate='" + releseDate + '\'' +
                '}';
    }
}
