package com.automation.api.utils;

import java.io.InputStream;
import java.util.Properties;

public class PropsManager {
    private final static String PROPS_FILE_NAME = "main.properties";

    public static Properties props() {
        Properties prop = new Properties();
        try (InputStream input = PropsManager.class.getClassLoader().getResourceAsStream(PROPS_FILE_NAME)) {
            if (input == null) {
                System.out.println("Sorry, unable to find config.properties");
                return null;
            }
            prop.load(input);

        } catch (Exception e) {
            System.out.println("Exception occurred: " + e.getMessage());
        }
        return prop;
    }
}
