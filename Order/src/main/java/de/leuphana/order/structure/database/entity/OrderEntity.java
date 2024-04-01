package de.leuphana.order.structure.database.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document
public class OrderEntity {

    @Id
    private String orderId;
    private List<OrderPositionEntity> orderPositionEntities;

    public OrderEntity() {
        orderPositionEntities = new ArrayList<>();
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderId() {
        return orderId;
    }

    public List<OrderPositionEntity> getOrderPositionEntities() {
        return orderPositionEntities;
    }

    public void setOrderPositionEntities(List<OrderPositionEntity> orderPositionEntities) {
        this.orderPositionEntities = orderPositionEntities;
    }
}
