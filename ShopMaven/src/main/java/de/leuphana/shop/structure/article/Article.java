package de.leuphana.shop.structure.article;

// Java Bean
// POJO (Plain Old Java Object)

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

// otherwise Json can't serialize inheritance and won't be able to resolve for example cd.getArtist()...
@JsonTypeInfo(
		use = JsonTypeInfo.Id.NAME,
		property = "type")
@JsonSubTypes({
		@JsonSubTypes.Type(value = Book.class, name = "book"),
		@JsonSubTypes.Type(value = CD.class, name = "cd")
})
public class Article {
	// ehemals attribute
	// bei Java Bean ist dies ein property
	private Integer articleId;
	private String manufacturer;
	private String name;
	private float price;
	
	public Article() {
	}

	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}

	public Integer getArticleId() {
		return articleId;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

}