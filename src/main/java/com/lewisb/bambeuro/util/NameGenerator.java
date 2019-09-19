package com.lewisb.bambeuro.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class NameGenerator {

    private static int COUNTER = 0; // This shouldn't shouldn't be so stateful, but it's just for testing purposes

    private static Properties nameProperties = new Properties();

    private static final int NUMBER_OF_NAMES;

    static {
        try {
            nameProperties.load(NameGenerator.class.getClassLoader().getResourceAsStream("database.properties"));
            NUMBER_OF_NAMES = nameProperties.getProperty("names").split(",").length;
        } catch (IOException e) {
            throw new IllegalStateException("Could not load names.properties", e);
        }
    }

    public static String generateName() {
        if (COUNTER >= NUMBER_OF_NAMES) {
            COUNTER = 0;
        }
        return nameProperties.getProperty("names").split(",")[COUNTER];
    }
}
