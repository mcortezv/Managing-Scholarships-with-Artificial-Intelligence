/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datosGobierno.configMongoGobierno;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import solicitarBeca.config.MongoConfig;
import java.io.InputStream;
import java.util.Properties;

/**
 * The enum Mongo cliente provider.
 *
 * @author Cortez, Manuel;
 */
public enum MongoClienteProvider {
    /**
     * Instance mongo cliente provider.
     */
    INSTANCE;
    private final MongoClient client;
    private final String dbName;
    private String uri;


    MongoClienteProvider() {
        try {
            InputStream input = getClass().getClassLoader().getResourceAsStream("mongo.properties");
            Properties props = new Properties();
            props.load(input);
            this.uri = props.getProperty("mongo.uri");
            this.dbName = props.getProperty("mongo.dbnameGobierno");
            client = MongoClients.create(MongoConfig.buildSettings(this.uri));
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                try { client.close(); } catch (Exception ignored) {}
            }));
        } catch (Exception ignored) {
            throw new RuntimeException("Error cargando configuracion Mongo");
        }
    }

    /**
     * Client mongo client.
     *
     * @return the mongo client
     */
    public MongoClient client() {
        return client;
    }

    /**
     * Database mongo database.
     *
     * @return the mongo database
     */
    public MongoDatabase database() {
        CodecRegistry pojoCodecRegistry = CodecRegistries.fromRegistries(
                MongoClientSettings.getDefaultCodecRegistry(),
                CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build())
        );
        return client.getDatabase(dbName).withCodecRegistry(pojoCodecRegistry);
    }

    /**
     * Gets collection.
     *
     * @param <T>  the type parameter
     * @param name the name
     * @param type the type
     * @return the collection
     */
    public <T> MongoCollection<T> getCollection(String name, Class<T> type) {
        return database().getCollection(name, type);
    }
}
