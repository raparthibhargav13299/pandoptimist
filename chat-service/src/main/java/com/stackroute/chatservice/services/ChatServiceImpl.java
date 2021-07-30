package com.stackroute.chatservice.services;


import com.stackroute.chatservice.model.MessageModel;
import com.stackroute.chatservice.repository.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatServiceImpl implements ChatService {

    private ChatRepository chatRepository;

    @Autowired
    public ChatServiceImpl(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }

    @Override
    public List<MessageModel> getLastTenMessages() {
        return this.chatRepository.findFirst10ByOrderByDateDesc();
    }

    @Override
    public MessageModel save(MessageModel message) {
        return this.chatRepository.save(message);
    }
}