package za.co.kmotsepe.tasuku.util;

import java.io.IOException;
import java.util.Properties;
import org.apache.log4j.Logger;

/**
 *
 * @author Kingsley Motsepe
 */
public class PropertiesLoader {

    /**
     * Application logger
     */
    private static final Logger LOGGER
            = Logger.getLogger(PropertiesLoader.class);

    Properties properties = null;
    static PropertiesLoader propertiesLoader;

    /**
     *
     * @throws IOException
     */
    private PropertiesLoader() {
        properties = new Properties();
    }

    public static PropertiesLoader getInstance() {

        if (propertiesLoader == null) {
            return new PropertiesLoader();
        } else {
            return propertiesLoader;
        }
    }

    /**
     *
     * @return @throws IOException
     */
    public Properties loadProperties() throws IOException {
        //TODO should property names be hardcoded? maybe relative to app
        properties.load(getClass().getClassLoader()
                .getResourceAsStream("application.properties"));
        LOGGER.debug("Properties empty?: " + properties.isEmpty());

        return properties;
    }

    /**
     *
     * @param propertiesName
     * @return
     */
    public Properties loadProperties(String propertiesName) {
        //TODO load properties based on variable
        return null;
    }
}
