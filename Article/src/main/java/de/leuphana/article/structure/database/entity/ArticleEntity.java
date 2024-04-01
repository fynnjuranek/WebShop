package de.leuphana.article.structure.database.entity;


import jakarta.persistence.*;


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class ArticleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer articleId;
    private String manufacturer;
    private String name;
    private float price;

    public ArticleEntity() {
    }

    public ArticleEntity(String manufacturer, String name, float price) {
        this.manufacturer = manufacturer;
        this.name = name;
        this.price = price;
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
