package de.leuphana.connector;

import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.TemporaryQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessagePostProcessor;
import org.springframework.stereotype.Component;
import de.leuphana.order.structure.Order;

@Component
public class OrderJMSConnectorSender {
    // TODO: Maybe the name needs to be changed!

    // This is the provider because it sends the message to the queue,
    // no matter what or who is attached to the destination
    @Autowired
    JmsTemplate jmsTemplate;

    public Order addOrderPosition(int articleId, int articleQuantity, String orderId) {
        Order respondedOrder = jmsTemplate.execute(session -> {
            TemporaryQueue tempQueue = session.createTemporaryQueue();
            MessagePostProcessor messagePostProcessor = new MessagePostProcessor() {
                @Override
                public Message postProcessMessage(Message message) throws JMSException {
                    message.setIntProperty(OrderJMSConnectorRequester.ARTICLE_QUANTITY, articleQuantity);
                    message.setStringProperty(OrderJMSConnectorRequester.ORDER_ID, orderId);
                    message.setJMSReplyTo(tempQueue);
                    return message;
                }
            };
            jmsTemplate.convertAndSend(OrderJMSConnectorRequester.ADD_ORDER, articleId, messagePostProcessor);
            return (Order) jmsTemplate.receiveAndConvert(tempQueue);
        });
        return respondedOrder;
    }

    public Order createOrder() {
        return jmsTemplate.execute(session -> {
            TemporaryQueue tempQueue = session.createTemporaryQueue();
            MessagePostProcessor messagePostProcessor = new MessagePostProcessor() {
                @Override
                public Message postProcessMessage(Message message) throws JMSException {
                    message.setJMSReplyTo(tempQueue);
                    return message;
                }
            };
            jmsTemplate.convertAndSend(OrderJMSConnectorRequester.CREATE_ORDER, 0, messagePostProcessor);
            return (Order) jmsTemplate.receiveAndConvert(tempQueue);
        });
    }

    public Order getOrder(String orderId) {
        Order foundOrder = jmsTemplate.execute(session -> {
            TemporaryQueue tempQueue = session.createTemporaryQueue();
            MessagePostProcessor messagePostProcessor = new MessagePostProcessor() {
                @Override
                public Message postProcessMessage(Message message) throws JMSException {
                    message.setJMSReplyTo(tempQueue);
                    return message;
                }
            };
            jmsTemplate.convertAndSend(OrderJMSConnectorRequester.GET_ORDER, orderId, messagePostProcessor);
            return (Order) jmsTemplate.receiveAndConvert(tempQueue);
        });
        return foundOrder;
    }

    public boolean deleteOrder(String orderId) {
        boolean isOrderDeleted = Boolean.TRUE.equals(jmsTemplate.execute(session -> {
            TemporaryQueue tempQueue = session.createTemporaryQueue();
            MessagePostProcessor messagePostProcessor = new MessagePostProcessor() {
                @Override
                public Message postProcessMessage(Message message) throws JMSException {
                    message.setJMSReplyTo(tempQueue);
                    return message;
                }
            };
            jmsTemplate.convertAndSend(OrderJMSConnectorRequester.DELETE_ORDER, orderId, messagePostProcessor);
            return (Boolean) jmsTemplate.receiveAndConvert(tempQueue);
        }));
        return isOrderDeleted;
    }
}
