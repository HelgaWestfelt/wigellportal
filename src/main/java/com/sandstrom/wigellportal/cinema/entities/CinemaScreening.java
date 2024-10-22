package com.sandstrom.wigellportal.cinema.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table (name = "cinema_screening")
public class CinemaScreening  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name="id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "film_id")
    private CinemaMovie movie;

    @ManyToOne
    @JoinColumn(name = "cinema_venue_id", nullable = false)
    private CinemaVenue venue;

    @Column(name="screening_time")
    private LocalDateTime screeningTime;

    public CinemaScreening(CinemaMovie movie, CinemaVenue venue, LocalDateTime screeningTime) {
        this.movie = movie;
        this.venue = venue;
        this.screeningTime = screeningTime;
    }

    public CinemaScreening() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CinemaMovie getFilm() {
        return movie;
    }

    public void setFilm(CinemaMovie movie) {
        this.movie = movie;
    }

    public CinemaVenue getVenue() {
        return venue;
    }

    public void setVenue(CinemaVenue venue) {
        this.venue = venue;
    }

    public LocalDateTime getScreeningTime() {
        return screeningTime;
    }

    public void setScreeningTime(LocalDateTime screeningTime) {
        this.screeningTime = screeningTime;
    }

    @Override
    public String toString() {
        return "Screening{" +
                "id=" + id +
                ", film=" + movie +
                ", venue=" + venue +
                ", screeningTime=" + screeningTime +
                '}';
    }
}

