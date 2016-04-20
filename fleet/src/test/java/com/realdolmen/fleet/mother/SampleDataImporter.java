package com.realdolmen.fleet.mother;

import com.realdolmen.fleet.Application;
import com.realdolmen.fleet.SpringBootTransactionalIntegrationTest;
import com.realdolmen.fleet.TestConfig;
import com.realdolmen.fleet.config.SecurityConfig;
import com.realdolmen.fleet.domain.Authorities;
import com.realdolmen.fleet.domain.Car;
import com.realdolmen.fleet.domain.FunctionalLevel;
import com.realdolmen.fleet.domain.User;
import com.realdolmen.fleet.domain.enums.Brand;
import com.realdolmen.fleet.domain.enums.FuelType;
import com.realdolmen.fleet.repositories.CarRepository;
import com.realdolmen.fleet.repositories.FunctionalLevelRepository;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

// To use this class :
//      - Create a method calling the mother object, set the data, persist
//      - Add the new method in generateSampleData()
//      - Run the generateSampleDate() method (place ignore in comment - don't forget to put it on again)
//      - Verify that the data was put in the db and close the application
//
//      NOTE: this runs against production DB!
@ContextConfiguration(classes = {TestConfig.class, SecurityConfig.class})
@TestPropertySource(locations = "/application.properties")
@Transactional
@ActiveProfiles("TST")
@RunWith(SpringJUnit4ClassRunner.class)
public class SampleDataImporter {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private CarRepository carRepo;
    @Autowired
    private FunctionalLevelRepository levelRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    @Ignore
    @Transactional
    public void generateSampleData() {
        levelRepo.deleteAll();
        generateFunctionalLevels();
        carRepo.deleteAll();
        generateCars();
        generateDifferentRoleUsers();
        generateAuthoritiesForDiffrentRoles();
        generateOneCar();
        // TODO: add new method here
    }

    @Transactional
    private FunctionalLevel getLevelForFLevel(Integer level) {
        return levelRepo.findByFLevel(level);
    }

    @Transactional
    private void persistACar(String dateString,
                             Brand brand,
                             boolean active,
                             FunctionalLevel category,
                             int emission,
                             int hp,
                             FuelType fuelType,
                             String model,
                             boolean hybrid,
                             String pack,
                             String imageUrl
    ) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy");
        Car car = CarMother.init().build();
        String dateInString;
        Date date = new Date();
        try {
            date = sdf.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        car.setBrand(brand);
        car.setActive(active);
        car.setCategory(category);
        car.setEmission(emission);
        car.setFiscalHorsePower(hp);
        car.setFuelType(fuelType);
        car.setModel(model);
        car.setHybrid(hybrid);
        car.setProductionDate(date);
        car.setPack(pack);
        car.setThumbnail(imageUrl);

        entityManager.persist(car);
    }


    private void generateCars() {

        persistACar("30-8-2016",
                Brand.SEAT,
                true,
                getLevelForFLevel(2),
                109,
                7,
                FuelType.DIESEL,
                "Ibiza St 1,6 crtdi 105 pk Style ecomotive",
                false,
                "Pack electronic / accoustic parking system/ Seat portable system/ oberg module",
                "image1.jpg"
        );
        persistACar("30-8-2016",
                Brand.SEAT,
                true,
                getLevelForFLevel(2),
                87,
                9,
                FuelType.DIESEL,
                "Leon style 1,6 Tdi 110 pk ecomotive",
                false,
                "Pack Dynamic / Full lede",
                "image1.jpg"
        );
        persistACar("30-8-2016",
                Brand.SEAT,
                true,
                getLevelForFLevel(6),
                130,
                11,
                FuelType.DIESEL,
                "Alhambra Style2,0 tdi 150 pk",
                false,
                "Pack technology / Pack executive/Alcantara zetels",
                "image1.jpg"
        );
        persistACar("30-8-2016",
                Brand.SEAT,
                true,
                getLevelForFLevel(6),
                130,
                11,
                FuelType.DIESEL,
                "Alhambra Style2,0 tdi 150 pk",
                false,
                "Pack technology / Pack executive",
                "image1.jpg"
        );
        persistACar("30-8-2016",
                Brand.SKODA,
                true,
                getLevelForFLevel(2),
                90,
                9,
                FuelType.DIESEL,
                "Octavia Berline 1,6 tdi 110 pk  greenline",
                false,
                "Pack Ambition GPs",
                "image1.jpg"
        );
        persistACar("30-8-2016",
                Brand.SKODA,
                true,
                getLevelForFLevel(5),
                106,
                11,
                FuelType.DIESEL,
                "Superb Combi Ambition 2,0CRTDI 150 pk 6v",
                false,
                "Pack Ambition Comfort / Pack Ambition GPs/ lichtmetale velgen helios / reserve wiel / bagageruimte afdekking autom/ elektrische opening en sluiting van de achterklep",
                "image1.jpg"
        );
        persistACar("30-8-2016",
                Brand.SKODA,
                true,
                getLevelForFLevel(4),
                106,
                9,
                FuelType.DIESEL,
                "Superb Combi Style 1,6CRTDI 120 pk 6v",
                false,
                "Pack Style Comfort / Pack style GPs / Pack style premium / leder interieur",
                "image1.jpg"
        );
        persistACar("30-8-2016",
                Brand.SKODA,
                true,
                getLevelForFLevel(4),
                106,
                9,
                FuelType.DIESEL,
                "Superb Berline Style 1,6CRTDI 120 pk 6v",
                false,
                "Pack Style Comfort / Pack style GPs / Pack style premium / leder interieur",
                "image1.jpg"
        );
        persistACar("30-8-2016",
                Brand.SKODA,
                true,
                getLevelForFLevel(2),
                90,
                9,
                FuelType.DIESEL,
                "Octavia combi 1,6 tdi 110 pk  greenline",
                false,
                "Pack Ambition GPs",
                "image1.jpg"
        );
        persistACar("30-8-2016",
                Brand.SKODA,
                true,
                getLevelForFLevel(2),
                109,
                7,
                FuelType.DIESEL,
                "Roomster 1,2 crtdi 75 pk",
                false,
                "Pack Ambition GPs",
                "image1.jpg"
        );
        persistACar("30-8-2016",
                Brand.SKODA,
                true,
                getLevelForFLevel(3),
                109,
                7,
                FuelType.DIESEL,
                "Superb berline Ambition 1,6 tdi 120 pk 6v",
                false,
                "ack Ambition Comfort / Pack Ambition GPs/ lichtmetale velgen orion",
                "image1.jpg"
        );
        persistACar("30-8-2016",
                Brand.SKODA,
                true,
                getLevelForFLevel(3),
                106,
                9,
                FuelType.DIESEL,
                "Superb Combi Ambition 1,6 tdi 120 pk 6v",
                false,
                "ack Ambition Comfort / Pack Ambition GPs/ lichtmetale velgen orion",
                "image1.jpg"
        );
    }

    @Transactional
    private void generateOneCar() {
        CarMother carMother = CarMother.init();

        FunctionalLevel category = new FunctionalLevel();
        category.setFLevel(1);
        entityManager.persist(category);
        carMother.setCategoryOrFunctionLevel(category);

        Car car = carMother.build();
        entityManager.persist(car);
    }

    private void generateFunctionalLevels() {
        for (Integer i = 1; i <= 7; i++) {
            FunctionalLevel level = FunctionalLevelMother.init().build();
            level.setFLevel(i);
            entityManager.persist(level);
        }
    }

    @Transactional
    private void generateDifferentRoleUsers() {
        User adminUser = UserMother.init().build();
        adminUser.setUsername("admin");
        adminUser.setPassword(passwordEncoder.encode("123"));
        adminUser.setEnabled(true);
        adminUser.setEmail("admin@realdolmen.com");
        entityManager.persist(adminUser);

        User normalUser = UserMother.init().build();
        normalUser.setUsername("user");
        normalUser.setPassword(passwordEncoder.encode("123"));
        normalUser.setEmail("user@realdolmen.com");
        entityManager.persist(normalUser);
    }


    @Transactional
    private void generateAuthoritiesForDiffrentRoles() {
        Authorities adminRole = AuthoritiesMother.init().build();
        adminRole.setUsername("admin");
        adminRole.setAuthority("ROLE_ADMIN");
        entityManager.persist(adminRole);

        Authorities normal = AuthoritiesMother.init().build();
        normal.setUsername("user");
        normal.setAuthority("ROLE_USER");
        entityManager.persist(normal);
    }

    // TODO: implement method using mother and persist entity

}
