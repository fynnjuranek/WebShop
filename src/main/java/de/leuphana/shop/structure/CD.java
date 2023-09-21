package de.leuphana.shop.structure;

import jakarta.persistence.Entity;

@Entity
public class CD extends Article {

	private String artist;

	public CD() {
		super();
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

}