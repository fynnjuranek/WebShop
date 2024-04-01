package de.leuphana.article.structure.database.entity;

import jakarta.persistence.Entity;

@Entity
public class CdEntity extends ArticleEntity {
    private String artist;
    public CdEntity(String manufacturer, String name, float price, String artist) {
        super(manufacturer, name, price);
        this.artist = artist;
    }

    public CdEntity() {
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }
}
