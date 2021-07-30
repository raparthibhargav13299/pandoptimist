package com.stackroute.chatservice.controller;

import com.stackroute.chatservice.model.MessageModel;
import com.stackroute.chatservice.model.UserStorage;
import com.stackroute.chatservice.services.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Random;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MessageController {

	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;

	private String[] colors = { "red", "green", "blue", "yellow", "orange", "violet" };
	private ChatService chatService;

	public MessageController(ChatService chatService, SimpMessagingTemplate simpMessagingTemplate) {
		this.chatService = chatService;
		this.simpMessagingTemplate = simpMessagingTemplate;
	}

	@MessageMapping("/writing")
	@SendTo("/topic/writing")
	public String writing(String username) {
		return username + " is writing..";
	}

	@MessageMapping("/chat/{to}")
	public MessageModel  sendMessage(@DestinationVariable String to, MessageModel message) {
		message.setDate(new Date().getTime());
		if (message.getType().equals("CONNECTED")) {
			message.setColor(colors[new Random().nextInt(colors.length)]);
			message.setMessage(message.getUsername() + " has been connected.");
		}
		    this.chatService.save(message);
			System.out.println("handling send message: " + message + " to: " + to);
			simpMessagingTemplate.convertAndSend("/topic/messages/" + to, message);


		return message;
	}

	@MessageMapping("/history")
	public void history(String clientId) {

		this.simpMessagingTemplate.convertAndSend("/chat/history/" + clientId, this.chatService.getLastTenMessages());

	}
}