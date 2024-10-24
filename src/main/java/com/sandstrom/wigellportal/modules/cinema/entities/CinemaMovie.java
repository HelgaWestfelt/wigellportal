package com.sandstrom.wigellportal.modules.cinema.entities;


import jakarta.persistence.*;

@Entity
@Table (name = "cinema_movie")
public class CinemaMovie {


    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name= "title")
    private String title;

    @Column(name = "length")
    private int length;

    @Enumerated(EnumType.STRING)
    @Column (name="genre")
    private CinemaMovieGenre genre;

    @Column(name="age_limit")
    private int ageLimit;

    public CinemaMovie(){

    }

    public CinemaMovie(String title, int length, CinemaMovieGenre genre, int ageLimit) {
        this.title = title;
        this.length = length;
        this.genre = genre;
        this.ageLimit = ageLimit;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public CinemaMovieGenre getGenre() {
        return genre;
    }

    public void setGenre(CinemaMovieGenre genre) {
        this.genre = genre;
    }

    public int getAgeLimit() {
        return ageLimit;
    }

    public void setAgeLimit(int ageLimit) {
        this.ageLimit = ageLimit;
    }


    @Override
    public String toString() {
        return "Film{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", length=" + length +
                ", genre=" + genre +
                ", ageLimit=" + ageLimit +
                '}';
    }
}
