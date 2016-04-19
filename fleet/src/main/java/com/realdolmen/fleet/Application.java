package com.realdolmen.fleet;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
public class Application {

    @Profile("PRD")
    public static void main(String[] args) {
        new SpringApplicationBuilder(Application.class).profiles("PRD").run();
    }
}
