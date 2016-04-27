package com.realdolmen.fleet;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Profile;
import org.springframework.data.convert.Jsr310Converters;

@SpringBootApplication
@EntityScan(basePackageClasses = {Application.class, Jsr310Converters.class})
public class Application {

    public static final String ROOT = "C:\\java-projects\\fleet\\fleet\\src\\main\\resources\\static\\images\\cars";

    @Profile("PRD")
    public static void main(String[] args) {
        ConfigurableApplicationContext context = new SpringApplicationBuilder(Application.class).profiles("PRD").run();
    }
}
