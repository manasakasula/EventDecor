package com.onestop.EventDecor.repository;

import com.onestop.EventDecor.model.ItemList;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class SearchRepositoryImpl implements SearchRepository {

    @Autowired
    private MongoClient client; // Inject MongoDB client

    @Autowired
    private MongoConverter converter; // Inject converter for document-to-object conversion

    @Override
    public Optional<ItemList> findById(String text) {
        // List to hold search results
        List<ItemList> itemList = new ArrayList<>();

        // Get the database and collection
        MongoDatabase database = client.getDatabase("OneStop");
        MongoCollection<Document> collection = database.getCollection("EventDecor");

        // Perform the MongoDB search with aggregation
        AggregateIterable<Document> result = collection.aggregate(Arrays.asList(
                new Document("$search", new Document("text",
                        new Document("query", text) // The search text
                                .append("path", "itemName"))), // Field to search
                new Document("$sort", new Document("_id", 1L)) // Sort by _id
        ));

        // Convert each document to ItemList object and add it to the list
        for (Document doc : result) {
            ItemList item = converter.read(ItemList.class, doc); // Convert document to ItemList
            itemList.add(item); // Add the item to the list
        }

        // Return the first item in the list if it exists, or an empty Optional
        return itemList.isEmpty() ? Optional.empty() : Optional.of(itemList.get(0));
    }
}
