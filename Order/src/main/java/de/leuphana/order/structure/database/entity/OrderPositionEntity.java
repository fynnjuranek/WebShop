package de.leuphana.order.structure.database.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class OrderPositionEntity {

    @Id
    private String positionId;
    private Integer articleId;
//    private Float articlePrice;
    private int articleQuantity;

    public void setPositionId(String positionId) {
        this.positionId = positionId;
    }

    public String getPositionId() {
        return positionId;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

//    public Float getArticlePrice() {
//        return articlePrice;
//    }

//    public void setArticlePrice(Float articlePrice) {
//        this.articlePrice = articlePrice;
//    }

    public int getArticleQuantity() {
        return articleQuantity;
    }

    public void setArticleQuantity(int articleQuantity) {
        this.articleQuantity = articleQuantity;
    }
}
