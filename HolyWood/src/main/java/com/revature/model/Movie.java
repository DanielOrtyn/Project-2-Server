package com.revature.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Movie")
public class Movie {
	@Id
	@Column(name = "movie_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int movieId;

	@Column(name = "title")
	private String title;

	@Column(name = "release_date")
	private Date releaseDate;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "plot_FK")
	private Plot plot;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "director_FK")
	private Director director;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "Actors_In_Movies", 
            joinColumns = { @JoinColumn(name = "movie_id") },
            inverseJoinColumns = { @JoinColumn(name = "actor_id") }
        )
	private List<Actor> actors;

	public Movie(String title, Date releaseDate, Plot plot, Director director,
			List<Actor> actors) {
		super();
		this.title = title;
		this.releaseDate = releaseDate;
		this.plot = plot;
		this.director = director;
		this.actors = actors;
	}

	public int getMovieId() {
		return movieId;
	}

	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public Plot getPlot() {
		return plot;
	}

	public void setPlot(Plot plot) {
		this.plot = plot;
	}

	public Director getDirector() {
		return director;
	}

	public void setDirector(Director director) {
		this.director = director;
	}

	public List<Actor> getActors() {
		return actors;
	}

	public void setActors(List<Actor> actors) {
		this.actors = actors;
	}

	@Override
	public String toString() {
		return "Movie \n\tmovieId=" + movieId + ", \n\ttitle=" + title
				+ ", \n\treleaseDate=" + releaseDate + ", \n\tplot=" + plot
				+ ", \n\tdirector=" + director + ", \n\tactors=" + actors + "]";
	}
	

}
