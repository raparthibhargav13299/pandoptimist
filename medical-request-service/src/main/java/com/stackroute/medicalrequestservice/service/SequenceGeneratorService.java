package com.stackroute.medicalrequestservice.service;


import com.stackroute.medicalrequestservice.model.DBSequence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class SequenceGeneratorService {
    @Autowired
    private MongoOperations mongoOperations;

    public int getSequenceNumber(String sequenceName){
        //get seqNo
        Query query = new Query(Criteria.where("id").is(sequenceName));
        //update seqNo
        Update update= new Update().inc("seq",1);
        //modify in documnet
        DBSequence counter = mongoOperations
                .findAndModify(query,update, FindAndModifyOptions.options().returnNew(true).upsert(true),DBSequence.class);

        return !Objects.isNull(counter)?counter.getSeq():1;

    }
}
