import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import org.json.JSONObject;

public class Send {

  private final static String QUEUE_NAME = "json-example4";
  private ConnectionFactory factory=null;

  public Send(){}

  public void run(JSONObject obj)throws Exception
  {
    factory = new ConnectionFactory();
    factory.setHost("localhost");
    Connection connection = factory.newConnection();
    Channel channel = connection.createChannel();

    channel.queueDeclare(QUEUE_NAME, false, false, false, null);

    channel.basicPublish("", QUEUE_NAME, null, obj.toString().getBytes()); 
    System.out.println(" [x] Sent '" + obj.toString() + "'");
    
     channel.close();
     connection.close();
 }

   
};
  