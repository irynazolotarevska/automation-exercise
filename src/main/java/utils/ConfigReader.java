package utils;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static Properties properties;

    static {
        try{
            properties = new Properties();
            FileInputStream fis = new FileInputStream("src/test/resources/config.properties");
            properties.load(fis);

        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public static String getBaseUrl(){
        return properties.getProperty("base.url");
    }

    public static int getNavigationTimeout(){
        return Integer.parseInt(properties.getProperty("navigation.timeout"));
    }

    public static int getActionTimeout(){
        return Integer.parseInt(properties.getProperty("action.timeout"));
    }

    public static String getExistingUserEmail(){
        return properties.getProperty("existing.user.email");
    }


}
