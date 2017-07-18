import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

public class Recv 
{
 private final static String QUEUE_NAME = "json-apple";
 private ConnectionFactory factory = null;
 final private JSONParser parser;
 
 public Recv() 
 {
  parser = new JSONParser();
 }

 public void run () throws Exception
 {
  factory = new ConnectionFactory();
     factory.setHost("localhost");
     factory.setPort(5672);
      factory.setUsername("farheen"); 
      factory.setPassword("farheen786");
     Connection connection = factory.newConnection();
     Channel channel = connection.createChannel();

     channel.queueDeclare(QUEUE_NAME, true, false, false, null);
     System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
     
     QueueingConsumer consumer = new QueueingConsumer(channel);
     channel.basicConsume(QUEUE_NAME, true, consumer);
     
     while (true) 
     {
       QueueingConsumer.Delivery delivery = consumer.nextDelivery();
       String message = new String(delivery.getBody()); 
       JSONObject obj = (JSONObject) parser.parse(message);
       
       System.out.println(" [x] Received '" + obj.toJSONString() + "'");
     }  
 }
 
 /**
  * @param args
  * @throws Exception 
  */
 public static void main(String[] args) throws Exception 
 {
  // TODO Auto-generated method stub
  Recv test = new Recv();
  test.run();
 }

}  