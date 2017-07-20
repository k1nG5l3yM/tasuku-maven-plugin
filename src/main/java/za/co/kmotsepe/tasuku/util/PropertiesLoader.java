package za.co.kmotsepe.tasuku.util;

import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author Kingsley Motsepe
 */
public class PropertiesLoader {

    Properties properties = null;
    static PropertiesLoader propertiesLoader;

    /**
     *
     * @throws IOException
     */
    private PropertiesLoader() {
        properties = new Properties();
    }

    public static PropertiesLoader getInstance() throws IOException {

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
        System.out.println("Properties empty?: " + properties.isEmpty());

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
