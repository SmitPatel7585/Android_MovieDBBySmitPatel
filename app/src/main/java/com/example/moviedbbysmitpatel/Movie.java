package com.example.moviedbbysmitpatel;

import java.io.Serializable;

public class Movie implements Serializable {
    private String Title;
    private String Year;
    private String imdbID;
    private String Type;
    private String Poster;
    private String RottenTomatoesRating;
    private String imdbRating;
    private String Released;
    private String Genre;
    private String Director;
    private String Writer;
    private String Actors;
    private String Plot;
    private String Language;
    private String Country;
    private String Awards;
    private String imdbVotes;
    private String TotalSeasons;

    public String getTitle() { return Title; }
    public void setTitle(String title) { Title = title; }

    public String getYear() { return Year; }
    public void setYear(String year) { Year = year; }

    public String getImdbID() { return imdbID; }
    public void setImdbID(String imdbID) { this.imdbID = imdbID; }

    public String getType() { return Type; }
    public void setType(String type) { Type = type; }

    public String getPoster() { return Poster; }
    public void setPoster(String poster) { Poster = poster; }

    public String getRottenTomatoesRating() { return RottenTomatoesRating; }
    public void setRottenTomatoesRating(String rating) { RottenTomatoesRating = rating; }

    public String getImdbRating() { return imdbRating; }
    public void setImdbRating(String rating) { imdbRating = rating; }

    public String getReleased() { return Released; }
    public void setReleased(String released) { Released = released; }

    public String getGenre() { return Genre; }
    public void setGenre(String genre) { Genre = genre; }

    public String getDirector() { return Director; }
    public void setDirector(String director) { Director = director; }

    public String getWriter() { return Writer; }
    public void setWriter(String writer) { Writer = writer; }

    public String getActors() { return Actors; }
    public void setActors(String actors) { Actors = actors; }

    public String getPlot() { return Plot; }
    public void setPlot(String plot) { Plot = plot; }

    public String getLanguage() { return Language; }
    public void setLanguage(String language) { Language = language; }

    public String getCountry() { return Country; }
    public void setCountry(String country) { Country = country; }

    public String getAwards() { return Awards; }
    public void setAwards(String awards) { Awards = awards; }

    public String getImdbVotes() { return imdbVotes; }
    public void setImdbVotes(String imdbVotes) { this.imdbVotes = imdbVotes; }

    public String getTotalSeasons() { return TotalSeasons; }
    public void setTotalSeasons(String totalSeasons) { TotalSeasons = totalSeasons; }
}
