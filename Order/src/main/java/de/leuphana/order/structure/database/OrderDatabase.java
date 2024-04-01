package de.leuphana.order.structure.database;

import de.leuphana.order.structure.database.entity.OrderEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderDatabase extends MongoRepository<OrderEntity, String> {
    OrderEntity deleteOrderEntityByOrderId(String orderId);
    OrderEntity findOrderEntityByOrderId(String orderId);
}
