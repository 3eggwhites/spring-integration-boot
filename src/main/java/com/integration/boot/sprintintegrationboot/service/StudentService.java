package com.integration.boot.sprintintegrationboot.service;

import com.integration.boot.sprintintegrationboot.model.Student;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
public class StudentService {

    @ServiceActivator(
            inputChannel = "integration.student.objectToJson.channel",
            outputChannel = "integration.student.jsonToObject.channel"
    )
    public Message<?> receiveMessage(Message<?> message) throws MessagingException {
        System.out.println("####################");
        System.out.println(message);
        System.out.println("####################");
        System.out.println("Object to JSON: " + message.getPayload());
        return message;
    }

    @ServiceActivator(inputChannel = "integration.student.jsonToObject.fromtransformer.channel")
    public void processJsonToObject(Message<?> message) throws MessagingException {
        MessageChannel replyChannel = (MessageChannel) message.getHeaders().getReplyChannel();
        MessageBuilder.fromMessage(message);
        System.out.println("####################");
        System.out.println("JSON to Object: " + message.getPayload());
        Student student = (Student) message.getPayload();
        Message<?> newMessage = MessageBuilder.withPayload(student.toString()).build();
        replyChannel.send(newMessage);
    }

}
