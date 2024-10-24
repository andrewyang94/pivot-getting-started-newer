/*
 * Copyright (C) ActiveViam 2024
 * ALL RIGHTS RESERVED. This material is the CONFIDENTIAL and PROPRIETARY
 * property of ActiveViam Limited. Any unauthorized use,
 * reproduction or transfer of this material is strictly prohibited
 */
package com.activeviam.apps;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class AtotiSpringBootApplication {
    public static void main(String[] args) {
        //        SpringApplication.run(AtotiSpringBootApplication.class, args);
        var application = new SpringApplication(AtotiSpringBootApplication.class);
        // this is useful if we want to display the data store schema in the awt window
        application.setHeadless(false);
        application.run(args);
    }
}
