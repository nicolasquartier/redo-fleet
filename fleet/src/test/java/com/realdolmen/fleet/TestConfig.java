package com.realdolmen.fleet;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

import javax.sql.DataSource;

@Configuration
@Profile("TST")
@EnableAutoConfiguration
@ComponentScan
public class TestConfig {

}
