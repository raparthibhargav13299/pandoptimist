package com.stackroute.chatservice.services;

import com.stackroute.chatservice.model.MessageModel;

import java.util.List;

public interface ChatService {

    public List<MessageModel> getLastTenMessages();
    public MessageModel save(MessageModel message);
}
