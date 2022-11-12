package config;

import java.io.*;
import java.util.Properties;

public class Config {

    private static String configFilePath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" +
            File.separator + "resources" + File.separator + "config.properties";

    public static Properties loadProperties() {
        Properties prop = new Properties();

        try(FileInputStream fis = new FileInputStream(configFilePath)) {
            prop.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return prop;
    }

}
