package com.gmail.fatihmergunqa.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Configs class to facilitate reading configuration properties from a specified properties file.
 * It provides methods to load properties and retrieve property values by key.
 */
public class Configs {
    public static Properties prop; // Holds the properties loaded from the file

    /**
     * Reads properties from the specified file path.
     *
     * @param filePath the path of the properties file to read
     */
    public static void readProperties(String filePath) {
        FileInputStream fis;

        try {
            fis = new FileInputStream(filePath);
            prop = new Properties();
            prop.load(fis);
            fis.close(); // Close the input stream after loading
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Set properties to the specified file path.
     *
     * @param filePath the path of the properties file to set
     * @param property property to set
     * @param value    value to set the property
     */
    public static void setProperty(String filePath, String property, String value) {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            // Load the existing properties file
            fis = new FileInputStream(filePath);
            prop = new Properties();
            prop.load(fis);
            fis.close(); // Close the input stream after loading

            // Set the new property value
            prop.setProperty(property, value);

            // Write the updated properties back to the file
            fos = new FileOutputStream(filePath);
            prop.store(fos, null); // Null comment to avoid additional text
        } catch (IOException e) {
            throw new RuntimeException("Error setting property", e);
        } finally {
            // Close resources
            try {
                if (fis != null) {
                    fis.close();
                }
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Retrieves the value associated with the specified key from the properties.
     *
     * @param key the key of the property to retrieve
     * @return the value of the property associated with the specified key
     */
    public static String getProperty(String key) {
        return prop.getProperty(key);
    }
}