package com.stackroute.chatservice.repository;



import com.stackroute.chatservice.model.MessageModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ChatRepository extends MongoRepository<MessageModel, String> {

    public List<MessageModel> findFirst10ByOrderByDateDesc();

}
