package de.leuphana.order;

import de.leuphana.order.behaviour.OrderService;
import de.leuphana.order.structure.database.entity.OrderEntity;
import de.leuphana.order.structure.database.entity.OrderPositionEntity;
import de.leuphana.order.structure.database.mapper.OrderMapper;
import de.leuphana.order.structure.OrderPosition;
import de.leuphana.order.structure.Order;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class OrderServiceTests {

    @Autowired
    OrderService orderService;

    @Autowired
    OrderMapper orderMapper;

    static Order order;

    @Test
    @org.junit.jupiter.api.Order(1)
    void canOrderBeAdded() {
        int articleId = 1;
        int articleQuantity = 2;
        order = orderService.createNewOrder();
        order = orderService.addNewOrderToDatabase(order.getOrderId(), articleId, articleQuantity);
        System.out.println("Added order with id: " + order.getOrderId());
        Assertions.assertNotNull(order);
    }

    // TODO: implement tests
    @Test
    @org.junit.jupiter.api.Order(2)
    void canOrderBeMapped() {
        OrderEntity mappedOrderEntity = orderMapper.mapToOrderEntity(order);
        Assertions.assertEquals(order.getOrderId(), mappedOrderEntity.getOrderId());
    }

    @Test
    @org.junit.jupiter.api.Order(3)
    void canOrderPositionBeMapped() {
        OrderPosition orderPosition = order.getOrderPositions().get(0);
        OrderPositionEntity mappedOrderPositionEntity = orderMapper.mapToOrderPositionEntity(orderPosition);
        Assertions.assertEquals(orderPosition.getArticleId(), mappedOrderPositionEntity.getArticleId());
    }


    @Test
    @org.junit.jupiter.api.Order(4)
    void canOrderBeFound() {
        Order foundOrder = orderService.findOrderById(order.getOrderId());
        System.out.println("Found order with id: " + foundOrder.getOrderId() + " and number of articles: " + foundOrder.getNumberOfArticles());
        List<OrderPosition> orderPositions = foundOrder.getOrderPositions();
        System.out.println("Articles: ");
        for (OrderPosition orderPosition : orderPositions) {
            System.out.println("id: " + orderPosition.getArticleId() + " quantity: " + orderPosition.getArticleQuantity());
        }
        Assertions.assertNotNull(foundOrder);
    }

    @Test
    @org.junit.jupiter.api.Order(5)
    void canAllOrdersBeFound() {
        List<Order> foundOrders = orderService.findAllOrders();
        System.out.println("Found orders: ");
        for (Order order : foundOrders) {
            System.out.println("id: " + order.getOrderId() + ", article quantity: " + order.getNumberOfArticles());
        }
    }

    @Test
    @org.junit.jupiter.api.Order(6)
    void canOrderBeDeleted() {
        boolean isDeleted = orderService.deleteOrderById(order.getOrderId());
        System.out.println("Deleted order with id: " + order.getOrderId() + " ? " + isDeleted);
        Assertions.assertTrue(isDeleted);
    }

}
