package com.realdolmen.fleet.config;


import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@Configuration
@Profile(value = {"PRD", "TST"})
public class ResourceConfig {

    @Bean
    public MessageSource messageSource() {

        String[] strBaseNames = {
                "classpath:locales/rdfmessages_global",
        };
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasenames(strBaseNames);
        messageSource.setUseCodeAsDefaultMessage(true);
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setCacheSeconds(10);
        return messageSource;
    }


}
