package com.hackathon.flowwatcher.model;

import com.mongodb.ConnectionString;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoDatabase;
import com.mongodb.reactivestreams.client.MongoClients;

public class MongoDBConnection {
    private static final String DATABASE_NAME = "FloWatcher";

    private MongoClient mongoClient;
    private MongoDatabase database;

    public MongoDBConnection() {
        // Setting up MongoDB
        try{
            ConnectionString connectionString = new ConnectionString("mongodb+srv://isaquehghackathon:KfFvapQzaoTx8W9i@cluster0.f5goe18.mongodb.net/?retryWrites=true&w=majority");
            this.mongoClient = MongoClients.create(connectionString);

            // Getting reference
            this.database = mongoClient.getDatabase(DATABASE_NAME);
        } catch (Exception e){
            System.out.println("MongoDB error: " + e.getMessage());
        }
    }

    public MongoDatabase getDatabase() {
        return database;
    }

    public void close() {
        mongoClient.close();
    }
}