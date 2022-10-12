package config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {

    private static final Properties PROPERTIES;
    private static final String ENVIRONMENT_PREFIX;
    private static final String EMAIL_ADDRESS;
    private static final String VALID_PASSWORD;

    /* initialises data,
    the env defaults to test when none is specified through the command line,
    an error is thrown if no password is provided */
    static {
        PROPERTIES = new Properties();
        initPropertiesFromFile();

        ENVIRONMENT_PREFIX = System.getProperty("env", "test") + ".";

        String emailProp = System.getProperty("email");
        String pwdProp = System.getProperty("pwd");
        if (null != pwdProp && null != emailProp) {
            VALID_PASSWORD = pwdProp;
            EMAIL_ADDRESS = emailProp;
        } else {
            throw new RuntimeException("Required credentials have not been provided. " +
                    "Provided credentials: email - " + emailProp + "; password - " + pwdProp);
        }
    }

    private static void initPropertiesFromFile() {
        String propsLocation = "properties/config.properties";
        InputStream inputStream = null;
        try {
            inputStream = Config.class.getClassLoader().getResourceAsStream(propsLocation);
            PROPERTIES.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException("Couldn't find properties file at location: " + propsLocation, e);
        } finally {
            if (null != inputStream) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static String getProperty(String s) {
        return PROPERTIES.getProperty(ENVIRONMENT_PREFIX + s);
    }

    public static String getEmailAddress() {
        return EMAIL_ADDRESS;
    }

    public static String getValidPassword() {
        return VALID_PASSWORD;
    }

    public static String getBaseURL() {
        return getProperty("baseURL");
    }
}
