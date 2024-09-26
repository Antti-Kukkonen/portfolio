import static com.mongodb.client.model.Filters.eq;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;

public class Controller {
    private final MongoCollection<Document> collection;


    public Controller() {
        // Your MongoDB URI should be stored in an environment variable.
        String mongo_uri = System.getenv("MONGO_URI");
        String mongo_db = System.getenv("MONGO_DB");
        String mongo_collection = System.getenv("MONGO_COLLECTION");
        if (mongo_uri == null || mongo_db == null || mongo_collection == null) {
            throw new RuntimeException("MONGO_URI, MONGO_DB, and MONGO_COLLECTION environment variables must be set");
        }

        try  {
            MongoClient mongoClient = MongoClients.create(mongo_uri);
            MongoDatabase database = mongoClient.getDatabase(mongo_db);
            collection = database.getCollection(mongo_collection);
            System.out.println("Connected to MongoDB");
        } catch (Exception e) {
            throw new RuntimeException("Failed to connect to MongoDB", e);
        }
    }

    // Creating documents
    public boolean createDocument(String name, int age, String city) {
        if (collection != null) {
            Document document = new Document("name", name)
                    .append("age", age)
                    .append("city", city);
            return collection.insertOne(document).wasAcknowledged();
        } else {
            return false;
        }
    }

    // Reading documents
    public Document readDocumentById(String id) {
        return collection.find(eq("_id", id)).first();
    }

    public Document readDocumentByName(String name) {
        return collection.find(eq("name", name)).first();
    }

    // Updating documents
    public boolean updateDocument(String id, String name, int age, String city) {
        return collection.updateOne(eq("_id", id), new Document("$set", new Document("name", name).append("age", age).append("city", city))).wasAcknowledged();
    }

    public boolean updateDocument(String name, int age, String city) {
        return collection.updateOne(eq("name", name), new Document("$set", new Document("age", age).append("city", city))).wasAcknowledged();
    }

    // Deleting documents
    public boolean deleteDocumentById(String id) {
        return collection.deleteOne(eq("_id", id)).wasAcknowledged();
    }

    public boolean deleteDocumentByName(String name) {
        return collection.deleteOne(eq("name", name)).wasAcknowledged();
    }
}
