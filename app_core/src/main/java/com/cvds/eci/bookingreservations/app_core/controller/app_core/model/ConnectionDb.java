package com.cvds.eci.bookingreservations.app_core.controller.app_core.model;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class ConnectionDb {

    public static void connect() {
        String uri = "mongodb+srv://root:root1234@cvds.uwlh9.mongodb.net/reservations?retryWrites=true&w=majority";

        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase("reservations");

            MongoCollection<Document> collection = database.getCollection("users");

            for (Document doc : collection.find()) {
                System.out.println(doc.toJson());
            }

            System.out.println("Conexión exitosa a MongoDB Atlas");
        } catch (Exception e) {
            System.err.println("Error de conexión: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        connect();
    }
}