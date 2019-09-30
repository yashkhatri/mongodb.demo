package com.khatri.mongodb.demo;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {

		//Mongo DB Client URI
		MongoClientURI uri = new MongoClientURI("mongodb+srv://user:user@c" +
				"luster0-j2pwu.mongodb.net/test?retryWrites=true&w=majority");

		//Creating a Mongo Client.
		MongoClient mongoClient = new MongoClient(uri);

		//Creating a Mongo DB. If the DB doesn't exist mongodb creates it.
		MongoDatabase database = mongoClient.getDatabase("myDB");

		//Creating Collection
		//database.createCollection("sampleCollection");

		// get a handle to the "sampleCollection" collection
		MongoCollection<Document> mongoCollection = database.getCollection("sampleCollection");

		Document document = new Document("title", "My first Document")
				.append("id", 1)
				.append("description", "Learning how to use Mongo DB")
				.append("likes", 100)
				.append("url", "https://sites.google.com/site/eryashkhatri/")
				.append("by", "Yash Khatri");

		mongoCollection.insertOne(document);

		LOGGER.info("Document inserted successfully");
		LOGGER.info("Collection has {} ", mongoCollection.countDocuments() + " documents" );

		// close resources
		mongoClient.close();

		LOGGER.info("======= Finish =======");
	}

}
