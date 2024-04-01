package de.leuphana.connector;

import de.leuphana.shop.behaviour.ShopService;
import de.leuphana.order.structure.OrderPosition;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OrderJMSConnectorSenderTest {

    @Autowired
    ShopService shopService;

    @Autowired
    OrderJMSConnectorSender orderJMSConnectorSender;


    static int articleId;
    static int articleQuantity;
    static de.leuphana.order.structure.Order addedOrder;
    static de.leuphana.order.structure.Order createdOrder;

    @BeforeAll
    public static void setUp() {
        articleId = 1;
        articleQuantity = 2;
    }

    @Test
    @Order(1)
    void canOrderBeCreated() {
        createdOrder = orderJMSConnectorSender.createOrder();
        System.out.println("CreatedOrder with id: " + createdOrder.getOrderId());
        Assertions.assertNotNull(createdOrder);
    }

    @Test
    @Order(2)
    void canOrderBeAdded() {
        addedOrder = orderJMSConnectorSender.addOrderPosition(articleId, articleQuantity, createdOrder.getOrderId());
        System.out.println("Added article with id: " + articleId + " and quantity: " + articleQuantity + " to order with id: " + addedOrder.getOrderId());
        Assertions.assertNotNull(addedOrder);
    }

    @Test
    @Order(3)
    void canOrderBeFound() {
        de.leuphana.order.structure.Order foundOrder = orderJMSConnectorSender.getOrder(addedOrder.getOrderId());
        System.out.println("Found order with id: " + foundOrder.getOrderId() + " and number of unique articles: " + foundOrder.getOrderPositions().size());
        List<OrderPosition> orderPositions = foundOrder.getOrderPositions();
        System.out.println("Articles: ");
        for (OrderPosition orderPosition : orderPositions) {
            System.out.println("id: " + orderPosition.getArticleId() + " quantity: " + orderPosition.getArticleQuantity());
        }
        Assertions.assertNotNull(foundOrder);
    }

    @Test
    @Order(4)
    void canOrderBeDeleted() {
        boolean isDeleted = orderJMSConnectorSender.deleteOrder(addedOrder.getOrderId());
        System.out.println("Deleted order with id: " + addedOrder.getOrderId() + " ? " + isDeleted);
        Assertions.assertTrue(isDeleted);
    }

}
