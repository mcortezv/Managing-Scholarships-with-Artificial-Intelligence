package datosGobierno.configMongoGobierno;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

/**
 * The type Mongo config.
 *
 * @author Cortez, Manuel;
 */
public class MongoConfig {

    private MongoConfig() {}

    /**
     * Build settings mongo client settings.
     *
     * @param uri the uri
     * @return the mongo client settings
     */
    public static MongoClientSettings buildSettings(String uri) {
        ConnectionString connectionString = new ConnectionString(uri);

        CodecRegistry pojoCodecRegistry = fromRegistries(
                MongoClientSettings.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().automatic(true).build())
        );

        return MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .codecRegistry(pojoCodecRegistry)
                .build();
    }
}
