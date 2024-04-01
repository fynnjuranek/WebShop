package de.leuphana.connector;

import de.leuphana.order.behaviour.OrderService;
import de.leuphana.order.structure.Order;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class OrderJMSConnectorRequester {

    // This is the requester because it attaches it's self to the queue (destination)
    // and reacts to a message which is provided by the shopSystem

    // Destinations
    public static final String ADD_ORDER = "addOrder";
    public static final String GET_ORDER = "getOrder";
    public static final String DELETE_ORDER = "deleteOrder";
    public static final String CREATE_ORDER = "createOrder";
    // Properties
    public static final String ARTICLE_QUANTITY = "articleQuantity";
    public static final String ORDER_ID= "orderId";

    @Autowired
    OrderService orderService;

    static final Logger LOGGER = LoggerFactory.getLogger(OrderJMSConnectorRequester.class);

    @JmsListener(destination = OrderJMSConnectorRequester.ADD_ORDER)
    public Order addOrder(int articleId, Message message) throws JMSException {
        int articleQuantity = message.getIntProperty(OrderJMSConnectorRequester.ARTICLE_QUANTITY);
        String orderId = message.getStringProperty(OrderJMSConnectorRequester.ORDER_ID);
        // TODO: debugging
        System.out.println("Order added: " + articleId);
        LOGGER.info("JMS 'addOrder' received in requester, with article id {}", articleId);
        Order order = orderService.addNewOrderToDatabase(orderId, articleId, articleQuantity);
        return order;
    }

    @JmsListener(destination = OrderJMSConnectorRequester.CREATE_ORDER)
    public Order createOrder() {
        LOGGER.info("JMS 'createOrder' received");
        return orderService.createNewOrder();
    }

    @JmsListener(destination = OrderJMSConnectorRequester.GET_ORDER)
    public Order getOrder(String orderId) throws JMSException {
        LOGGER.info("JMS 'getOrder' received with order id {}", orderId);
        return orderService.findOrderById(orderId);
    }

    @JmsListener(destination = OrderJMSConnectorRequester.DELETE_ORDER)
    public boolean deleteOrder(String orderId) throws JMSException {
        LOGGER.info("JMS 'deleteOrder' received with order id {}", orderId);
        return orderService.deleteOrderById(orderId);
    }
}
