package datos.configMongoPaypal;


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

public enum MongoClienteProvider {
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
            this.dbName = props.getProperty("mongo.dbPaypal");
            client = MongoClients.create(MongoConfig.buildSettings(this.uri));
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                try { client.close(); } catch (Exception ignored) {}
            }));
        } catch (Exception ignored) {
            throw new RuntimeException("Error cargando configuracion Mongo");
        }
    }

    public MongoClient client() {
        return client;
    }

    public MongoDatabase database() {
        CodecRegistry pojoCodecRegistry = CodecRegistries.fromRegistries(
                MongoClientSettings.getDefaultCodecRegistry(),
                CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build())
        );
        return client.getDatabase(dbName).withCodecRegistry(pojoCodecRegistry);
    }

    public <T> MongoCollection<T> getCollection(String name, Class<T> type) {
        return database().getCollection(name, type);
    }
}