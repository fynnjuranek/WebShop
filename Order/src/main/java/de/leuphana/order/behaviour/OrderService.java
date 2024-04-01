package de.leuphana.order.behaviour;

import de.leuphana.order.structure.database.OrderDatabase;
import de.leuphana.order.structure.database.entity.OrderEntity;
import de.leuphana.order.structure.database.entity.OrderPositionEntity;
import de.leuphana.order.structure.database.mapper.OrderMapper;
import de.leuphana.order.structure.Order;
import de.leuphana.order.structure.OrderPosition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    OrderDatabase orderDatabase;

    @Autowired
    OrderMapper orderMapper;

    static final Logger LOGGER = LoggerFactory.getLogger(OrderService.class);

    public Order addNewOrderToDatabase(String orderId, Integer articleId, int articleQuantity) {
        OrderEntity orderEntity = orderDatabase.findOrderEntityByOrderId(orderId);
        // create new order if it doesn't already exist
        Order order;
        if (orderEntity == null) {
            order = new Order();
        } else {
            order = orderMapper.mapToOrder(orderEntity);
        }
        OrderPosition orderPosition = new OrderPosition();
        orderPosition.setArticleId(articleId);
//        orderPosition.setArticlePrice(articlePrice);
        orderPosition.setArticleQuantity(articleQuantity);
        order.addOrderPosition(orderPosition);
        LOGGER.info("Added an orderPosition with articleId {} to order with id {}", articleId, orderId);

        orderEntity = orderMapper.mapToOrderEntity(order);
        List<OrderPositionEntity> orderPositionEntities = new ArrayList<>();
        for (OrderPosition mappingOrderPosition : order.getOrderPositions()) {
            orderPositionEntities.add(orderMapper.mapToOrderPositionEntity(mappingOrderPosition));
        }
        orderEntity.setOrderPositionEntities(orderPositionEntities);

        // To guarantee that order got properly saved
        OrderEntity savedOrder = orderDatabase.save(orderEntity);
        LOGGER.info("Added order with  id {} to  orderDatabase", savedOrder.getOrderId());
        List<OrderPosition> savedOrderPositions = new ArrayList<>();
        Order returningOrder = orderMapper.mapToOrder(savedOrder);
        for (OrderPositionEntity orderPositionEntity : savedOrder.getOrderPositionEntities()) {
            savedOrderPositions.add(orderMapper.mapToOrderPosition(orderPositionEntity));
        }
        returningOrder.setOrderPositions(savedOrderPositions);

        return returningOrder;
    }

    public Order createNewOrder() {
        OrderEntity orderEntity = new OrderEntity();
        return orderMapper.mapToOrder(orderDatabase.save(orderEntity));
    }

    public Order findOrderById(String orderID) {
        OrderEntity foundOrderEntity = orderDatabase.findById(orderID).get();
        List<OrderPosition> orderPositions = new ArrayList<>();
        for (OrderPositionEntity orderPositionEntity : foundOrderEntity.getOrderPositionEntities()) {
            orderPositions.add(orderMapper.mapToOrderPosition(orderPositionEntity));
        }
        Order order = orderMapper.mapToOrder(foundOrderEntity);
        order.setOrderPositions(orderPositions);
        return order;
    }

    public List<Order> findAllOrders() {
        List<Order> orders = new ArrayList<>();
        for (OrderEntity orderEntity : orderDatabase.findAll()) {
            List<OrderPosition> orderPositions = new ArrayList<>();
            for (OrderPositionEntity orderPositionEntity : orderEntity.getOrderPositionEntities()) {
                orderPositions.add(orderMapper.mapToOrderPosition(orderPositionEntity));
            }
            Order order = orderMapper.mapToOrder(orderEntity);
            order.setOrderPositions(orderPositions);
            orders.add(order);
        }
        return orders;
    }

    public boolean deleteOrderById(String orderId) {
        boolean isDeleted = false;
        orderDatabase.deleteById(orderId);
        if (orderDatabase.findOrderEntityByOrderId(orderId) == null) {
            isDeleted = true;
            LOGGER.info("Deleted an order with id {}", orderId);
        }
        return isDeleted;
    }

}
