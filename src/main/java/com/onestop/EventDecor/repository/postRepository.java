package com.onestop.EventDecor.repository;

import com.onestop.EventDecor.model.ItemList;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface postRepository extends MongoRepository<ItemList, String >{
}
