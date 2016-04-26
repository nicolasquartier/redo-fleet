package com.realdolmen.fleet;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.convert.Jsr310Converters;

@Configuration
@Profile("TST")
@EnableAutoConfiguration
@ComponentScan
@EntityScan(basePackageClasses = {Application.class, Jsr310Converters.class})
public class TestConfig {

}
