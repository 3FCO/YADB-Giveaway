package me.efco.data;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigLoader {
    private final static ConfigLoader INSTANCE = new ConfigLoader();

    private ConfigLoader(){}

    public static ConfigLoader getInstance() {
        return INSTANCE;
    }

    public String loadConfigByName(String configName) {
        try (InputStream inputStream = new FileInputStream("config.properties")) {
            Properties properties = new Properties();
            properties.load(inputStream);

            return properties.getProperty(configName);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }
}
