package de.leuphana.shop.structure;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
public class Article {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int articleId;
	private String manufactor;
	private String name;
	private BigDecimal price;

	public Article() {}

	public Article(String manufactor, String name, BigDecimal price) {
		this.manufactor = manufactor;
		this.name = name;
		this.price = price;
	}

	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Article article = (Article) o;
		return articleId == article.articleId && Objects.equals(manufactor, article.manufactor) && Objects.equals(name, article.name) && Objects.equals(price, article.price);
	}

	@Override
	public int hashCode() {
		return Objects.hash(articleId, manufactor, name, price);
	}

	@Override
	public String toString() {
		return "Article{" +
				"articleId=" + articleId +
				", manufactor='" + manufactor + '\'' +
				", name='" + name + '\'' +
				", price=" + price +
				'}';
	}

	public int getArticleId() {
		return articleId;
	}

	public String getManufactor() {
		return manufactor;
	}

	public void setManufactor(String manufactor) {
		this.manufactor = manufactor;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

}