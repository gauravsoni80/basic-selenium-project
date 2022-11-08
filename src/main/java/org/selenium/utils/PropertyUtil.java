package org.selenium.utils;

import org.selenium.constants.IPath;
import org.yaml.snakeyaml.Yaml;
import java.io.File;
import java.io.FileInputStream;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

public class PropertyUtil {

    public static String getProperty(String property) {
        Properties properties = null;
        File file = null;
        FileInputStream fis = null;
        try {
            file = new File(IPath.RESOURCES_PATH+"application.properties");
            fis = new FileInputStream(file);
            properties = new Properties();
            properties.load(fis);
        } catch (Exception e) {
            System.out.println("Exception : " + e.getMessage());
            e.printStackTrace();
        }

        return properties.getProperty(property.trim()).replace('"', ' ').trim().toLowerCase(Locale.ROOT);
    }

    public static Map getProperty() {
        Yaml yaml = null;
        File file = null;
        FileInputStream fis = null;
        Map map = null;
        try {
            file = new File(IPath.RESOURCES_PATH+"application.yaml");
            fis = new FileInputStream(file);
            yaml = new Yaml();
            map = yaml.load(fis);
        } catch (Exception e) {
            System.out.println("Exception : " + e.getMessage());
            e.printStackTrace();
        }
        return map;
    }
}
