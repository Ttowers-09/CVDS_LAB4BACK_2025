package com.cvds.eci.laboratoryreservations.app_core.model;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class ConnectionDb {

    public static void connect() {
        // Asegúrate de que esta URI sea correcta (Ver en MongoDB Atlas -> Connect)
        String uri = "mongodb+srv://root:root1234@cvds.uwlh9.mongodb.net/reservations?retryWrites=true&w=majority";

        try (MongoClient mongoClient = MongoClients.create(uri)) {
            // Obtener la base de datos
            MongoDatabase database = mongoClient.getDatabase("reservations");

            // Obtener la colección correcta
            MongoCollection<Document> collection = database.getCollection("users");

            // Crear un documento y guardarlo en la colección
            Document users = new Document("nombre", "Juan Pérez")
                    .append("email", "juan.perez@email.com")
                    .append("telefono", "+57 312 345 6789");
            collection.insertOne(users);

            // Mostrar los documentos de la colección
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