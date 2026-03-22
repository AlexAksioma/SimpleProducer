import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class SimpleProducer {
    private final static String QUEUE_NAME = "hello";

    public static void main(String[] args) {
        ConnectionFactory factory = new ConnectionFactory();
        //factory.setHost("localhost:8080");
        factory.setHost("127.0.0.1");
        //factory.setPort(5672);
        factory.setPort(8585);
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()){
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            String message  = "hello, new message1111111";
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            System.out.println("Sent --> "+message);
        } catch (IOException | TimeoutException e) {
            throw new RuntimeException(e);
        }
    }
}
