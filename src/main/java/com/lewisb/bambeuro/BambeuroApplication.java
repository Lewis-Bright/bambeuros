package com.lewisb.bambeuro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class BambeuroApplication {

    public static void main(String[] args) {
        SpringApplication.run(BambeuroApplication.class, args);
    }

}
