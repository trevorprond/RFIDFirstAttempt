package com.example.rfidfirstattempt;
import com.hivemq.client.mqtt.mqtt5.Mqtt5Client;
import com.hivemq.client.mqtt.mqtt5.message.connect.connack.Mqtt5ConnAck;
import com.hivemq.client.mqtt.mqtt5.message.publish.Mqtt5Publish;
import com.hivemq.client.mqtt.mqtt5.message.publish.Mqtt5PublishResult;
import java.util.UUID;

public class MQTTHelper {
    public static Mqtt5Client client;
    public String connect() {
        try {
            // attempt a connect
             client = Mqtt5Client.builder()
                    .identifier(UUID.randomUUID().toString())
                    .serverHost("8ad553459c4e46fca3ecc98d6cc2c3fe.s1.eu.hivemq.cloud")
                    .serverPort(8883)
                    .sslWithDefaultConfig()
                    .simpleAuth()
                    .username("admin")
                    .password("password".getBytes())
                    .applySimpleAuth()
                    .build();

            Mqtt5ConnAck connAckMessage = client.toBlocking().connect();
            //success
            Mqtt5PublishResult publishMessage = client.toBlocking().publishWith()
                    .topic("test/topic")
                    .payload("This is the mobile app".getBytes())// here you can specify multiple properties which are described below
                    .send();

            Mqtt5PublishResult publishResult = client.toBlocking().publishWith().topic("test/topic").send();
            return connAckMessage.getReasonCode().toString();

        } catch (Exception e) {
            //failure
            return e.getMessage();
        }
    }

    public String SendMessage(String message)
    {
        try {
            Mqtt5PublishResult publishMessage = client.toBlocking().publishWith()
                    .topic("test/topic")
                    .payload(message.getBytes())// here you can specify multiple properties which are described below
                    .send();
            return "Success";
        }
        catch (Exception e) {
            //failure
            return e.getMessage();
        }
    }

}
