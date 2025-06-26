package coreUtils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

public class ConfigPropertiesUtil extends SeleniumCore{
    private static String defaultConfigFile = "./GlobalVariables.properties";
    public static Properties prop;
    
    /**
     * construtor of this class
     */
    public ConfigPropertiesUtil() {
    }

    /**
     *
     * This method will get the properties pulled from a file located relative to the base dir
     *
     * @param propFile complete or relative (to base dir) file location of the properties file
     * @param Property property name for which value has to be fetched
     * @return String value of the property
     */
    public static void setProperty(String propFile, String property, String value) {
        try {
			prop = new Properties();
            prop.setProperty(property, value);
            FileOutputStream out = new FileOutputStream(propFile, true);
            prop.store(out, "");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    //
    public static void setProperty(String property, String value){
       setProperty(defaultConfigFile, property, value);
    }
    
    //
    public static String getProperty(String propFile, String key) {
        try {
			prop = new Properties();
			FileInputStream fis = new FileInputStream(propFile);
			prop.load(fis);
			if (prop.containsKey(key)) {
				System.out.println("Key exists in properties file:- " + key);
			}else {
				System.out.println("Key doesn't exists in properties file:- " + key);
			}
			return prop.getProperty(key);
        } catch (Exception ex) {
        	ex.printStackTrace();
        }
		return null;
    }
    
    //Get property from property file using key
    public static String getProperty(String key){
       return getProperty(defaultConfigFile, key);
    }
}
