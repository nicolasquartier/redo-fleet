package com.realdolmen.fleet;

import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@ContextConfiguration(classes = TestConfig.class)
@TestPropertySource(locations = "/test.properties")
@Transactional
@ActiveProfiles("TST")
public @interface SpringBootTransactionalIntegrationTest {

}
