package za.co.kmotsepe.tasuku.util;

import com.google.gson.Gson;
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
    private PropertiesLoader(){
        properties = new Properties();
    }
    
    public static PropertiesLoader getInstance() throws IOException{
        
        if(propertiesLoader == null){
            return new PropertiesLoader();
        }else{
            return propertiesLoader;
        }
    }
    
    /**
     * 
     * @return
     * @throws IOException 
     */
    public Properties loadProperties() throws IOException {

        properties.load(getClass().getClassLoader().getResourceAsStream("application.properties"));
        System.out.println("Properties empty?: " + properties.isEmpty());
        
        return properties;
    }
}
