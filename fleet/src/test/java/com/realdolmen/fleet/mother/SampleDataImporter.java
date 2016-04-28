package com.realdolmen.fleet.mother;

import com.realdolmen.fleet.TestConfig;
import com.realdolmen.fleet.config.SecurityConfig;
import com.realdolmen.fleet.domain.*;
import com.realdolmen.fleet.domain.enums.Brand;
import com.realdolmen.fleet.domain.enums.CarType;
import com.realdolmen.fleet.domain.enums.FuelType;
import com.realdolmen.fleet.domain.enums.RimType;
import com.realdolmen.fleet.repository.*;
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
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

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
    private CarRepository carRepository;

    @Autowired
    private CompanyCarRepository companyCarRepository;

    @Autowired
    private OptionRepository optionRepository;

    @Autowired
    private FunctionalLevelRepository levelRepo;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthoritiesRepository authoritiesRepository;

    @Autowired
    private UserCarHistoryRepository userCarHistoryRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
   // @Ignore
    @Rollback(false)
    @Transactional
    public void generateSampleData() {
        generateFunctionalLevels();
        generateUsers();
        generateCars();
        generateOptions();
        generateCompanyCar();
        generateUserCarHistory();

    }

    @Transactional
    private void generateUserCarHistory() {
        // could loop this by replacing the id with the loop index, be carefull index should stay below #cars and #users
        for (Long i = 1L; i < 100; i++) {
            for (Long j = 1L; j < 5; j++) {
                Long carId = i;
                Long userId = j;
                UserCarHistory userCarHistory = UserCarHistoryMother.init().build();
                userCarHistory.setUser(userRepository.findOne(userId));
                userCarHistory.setCompanyCar(companyCarRepository.findOne(carId));
                userCarHistory.setEndDate(LocalDate.now().minusDays(j-1));
                userCarHistoryRepository.save(userCarHistory);
            }
        }
    }

    @Test
    @Ignore
    @Rollback(false)
    @Transactional
    public void generateSampleDataWithClearDatabase() {
        levelRepo.deleteAll();
        carRepository.deleteAll();
        optionRepository.deleteAll();
        companyCarRepository.deleteAll();
        userRepository.deleteAll();
        authoritiesRepository.deleteAll();
        generateSampleData();
    }

    private void generateUser(String firstname, String lastname, String username, String email, String business, int level, String role) {
        User user = UserMother.init().build();
        user.setVersion(1L);
        user.setFirstName(firstname);
        user.setLastName(lastname);
        user.setUsername(username);
        user.setEmail(email);
        user.setFunctionalLevel(getLevelForFLevel(level));
        user.setPassword(passwordEncoder.encode("123"));
        user.setBusinessUnit(business);
        user.setEnabled(true);
        entityManager.persist(user);

        Authorities normal = AuthoritiesMother.init().build();
        normal.setUsername(username);
        normal.setAuthority(role);
        entityManager.persist(normal);
    }

    private void generateUsers() {
        generateUser("Eddard", "Stark", "nedstark", "eddard@winterfell.com", "Hand of the King", 3, "ROLE_USER");
        generateUser("Catelyn", "Tully", "cattully", "catelyn@winterfell.com", "Lady of Winterfell", 2, "ROLE_USER");
        generateUser("Daenerys", "Targaryen ", "daenerys", "khaleesi@stormborn.com", "Dothraki khaleesi", 4, "ROLE_ADMIN");
        generateUser("Jon", "Snow", "bastard", "jon@winterfell.com", "Night's watch", 3, "ROLE_USER");
        generateUser("Tyrion", "Lannister", "imp", "Tyrion@lannister.com", "Hand of the king", 6, "ROLE_USER");
        generateUser("Iam", "Administrator", "admin", "admin@realdolmen.com", "Hand of the king", 3, "ROLE_ADMIN");
        generateUser("Iam", "user", "user", "user@realdolmen.com", "Java unit", 3, "ROLE_USER");
    }


    private void generateCompanyCar() {

        // could loop this by replacing the id with the loop index, be carefull index should stay below #cars
        for (Long i = 1L; i < 12; i++) {
            for (int j = 1; j < 10; j++) {

                Long carId = i;

                CompanyCar companyCar = CompanyCarMother.init().build();

                Car carToSet = carRepository.getOne(carId);
                companyCar.setCar(carToSet);
                if ((j % 2) == 0) {
                    companyCar.setApproved(true);
                }

                entityManager.persist(companyCar);
                entityManager.flush();

                List<Option> options = companyCar.getOptions();
                options.stream().forEach(companyCar::removeOption);

                List<Option> optionToSet = optionRepository.findByCar(carToSet);
                optionToSet.stream().forEach(companyCar::addOption);
                optionToSet.stream().forEach(option -> option.addCompanyCar(companyCar));

                entityManager.merge(companyCar);
                entityManager.flush();
            }
        }
    }

    private void generateOptions() {
        // there should be 4 cars in the database
        for (int i = 0; i < 4; i++) {
            Option option = OptionMother.init().build();
            option.setCar(carRepository.findAll().get(i));
            optionRepository.save(option);
        }
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
                             String imageUrl,
                             RimType rimType,
                             int idealKm,
                             int maxKm,
                             double listPrice,
                             double monthlyBenefit,
                             double upgradeAmount,
                             double downgradeAmount,
                             int hPower,
                             String engine,
                             CarType type

    ) {
        Car car = CarMother.init().build();
        DateTimeFormatter dTF = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate date = LocalDate.parse(dateString, dTF);
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
        car.setRimType(rimType);
        car.setIdealKm(idealKm);
        car.setMaxKm(maxKm);
        car.setListPrice(listPrice);
        car.setMonthlyBenefit(monthlyBenefit);
        car.setUpgradeAmount(upgradeAmount);
        car.setDowngradeAmount(downgradeAmount);
        car.setHPower(hPower);
        car.setEngine(engine);
        car.setType(type);

        entityManager.persist(car);
    }


    private void generateCars() {
        //car 1
        persistACar("30-08-2016",
                Brand.AUDI,
                true,
                getLevelForFLevel(2),
                89,
                9,
                FuelType.DIESEL,
                "A3 sportback ultra attraction",
                false,
                "Pack intuition Plus attraction",
                "A3sportback.jpg",
                RimType.STEEL,
                140000,
                180000,
                25048.99,
                104.17,
                0.0,
                -3924.43,
                110,
                "1,6 tdi",
                CarType.BERLINE
        );
        //car 2
        persistACar("30-08-2016",
                Brand.SEAT,
                true,
                getLevelForFLevel(2),
                109,
                7,
                FuelType.DIESEL,
                "Ibiza St Style ecomotive",
                false,
                "Pack electronic / accoustic parking system/ Seat portable system/ oberg module",
                "ibizaST.jpg",
                RimType.STEEL,
                140000,
                180000,
                19945.49,
                104.17,
                0.0,
               -2479.89,
                105,
                "1,6 crtdi",
                CarType.BREAK
        );
        //car 3
        persistACar("30-08-2016",
                Brand.SEAT,
                true,
                getLevelForFLevel(2),
                109,
                7,
                FuelType.DIESEL,
                "Leon Style ecomotive",
                false,
                "Pack Dynamic / Full led",
                "normal_leon_rosa.jpg",
                RimType.STEEL,
                140000,
                180000,
                25971.52,
                104.17,
                0.0,
                -5491.27,
                110,
                "1,6 tdi",
                CarType.BERLINE
        );

        //car 4
        persistACar("30-08-2016",
                Brand.SKODA,
                true,
                getLevelForFLevel(2),
                90,
                9,
                FuelType.DIESEL,
                "Octavia greenline",
                false,
                "Pack Ambition GPs",
                "octaviaberline.jpg",
                RimType.STEEL,
                140000,
                180000,
                24566.82,
                104.17,
                0.0,
                -4253.93,
                110,
                "1,6 tdi",
                CarType.BERLINE
        );
        //car 5
        persistACar("30-08-2016",
                Brand.SKODA,
                true,
                getLevelForFLevel(2),
                90,
                9,
                FuelType.DIESEL,
                "Octavia greenline",
                false,
                "Pack Ambition GPs",
                "octaviaberline.jpg",
                RimType.STEEL,
                140000,
                180000,
                25343.22,
                104.17,
                0.0,
                -4042.73,
                110,
                "1,6 tdi",
                CarType.BREAK
        );
        //car 6
        persistACar("30-08-2016",
                Brand.SKODA,
                true,
                getLevelForFLevel(2),
                109,
                7,
                FuelType.DIESEL,
                "Roomster",
                false,
                "Pack ambition Greenline - GPS TomTom Via 135 LTM",
                "roomster.jpg",
                RimType.ALUMINIUM,
                140000,
                200000,
                19876.19,
                104.17,
                0,
                -1907.41,
                75,
                "1,2 crtdi",
                CarType.MONOVOLUME
        );
        //car 7
        persistACar("30-08-2016",
                Brand.VOLKSWAGEN,
                true,
                getLevelForFLevel(2),
                99,
                9,
                FuelType.DIESEL,
                "Golf Highline",
                false,
                "Pack BUSINESS CLASS @ GPS MEDIA",
                "highline.jpg",
                RimType.STEEL,
                140000,
                180000,
                27476.95,
                123.65,
                0,
                -1369.33,
                110,
                "1,6 crtdi",
                CarType.BERLINE
        );

        //car 8
        persistACar("30-08-2016",
                Brand.VOLKSWAGEN,
                true,
                getLevelForFLevel(2),
                102,
                9,
                FuelType.DIESEL,
                "Golf Variant Trendl",
                false,
                "Pack Business Class Discover Media",
                "variantbreak.jpg",
                RimType.STEEL,
                140000,
                180000,
                26696.57,
                123.86,
                0,
                -1407.33,
                110,
                "1,6 crtdi",
                CarType.MONOVOLUME
        );
        //car 9
        persistACar("30-08-2016",
                Brand.VOLKSWAGEN,
                true,
                getLevelForFLevel(3),
                107,
                9,
                FuelType.DIESEL,
                "Beetle design",
                false,
                "Pack Business Class Discover Media",
                "volkswagen-beetle.jpg",
                RimType.STEEL,
                140000,
                180000,
                24240.15,
                122.93,
                2431.85,
                -2795.35,
                110,
                "2.0 tdi",
                CarType.BERLINE
        );

        //car 10
        persistACar("30-08-2016",
                Brand.AUDI,
                true,
                getLevelForFLevel(3),
                99,
                9,
                FuelType.DIESEL,
                "A3 sportback Ambiente",
                false,
                "Pack intuition Plus ambiente / Pack lounge",
                "A3sportback.jpg",
                RimType.STEEL,
                140000,
                180000,
                28668.01,
                129.01,
                2197.09,
                -3165.90,
                110,
                "1,6 tdi",
                CarType.BERLINE
        );
        //car 11
        persistACar("30-08-2016",
                Brand.VOLKSWAGEN,
                true,
                getLevelForFLevel(3),
                102,
                9,
                FuelType.DIESEL,
                "Golf Variant Highline",
                false,
                "Pack Business @ GPS Discover Media",
                "variantbreak.jpg",
                RimType.STEEL,
                180000,
                200000,
                28668.88,
                134.86,
                2061.39,
                -3165.90,
                110,
                "1,6 crtdi",
                CarType.BREAK
        );
        //car 12
        persistACar("30-08-2016",
                Brand.SKODA,
                true,
                getLevelForFLevel(3),
                105,
                9,
                FuelType.DIESEL,
                "Superb berline Ambition",
                false,
                "ack Ambition Comfort / Pack Ambition GPs/ lichtmetale velgen orion",
                "suberb.jpg",
                RimType.STEEL,
                180000,
                200000,
                28618,
                134,
                2061,
                -3165,
                120,
                " 1,6 tdi 6v",
                CarType.BERLINE
        );
    }

    private void generateFunctionalLevels() {
        for (Integer i = 1; i <= 7; i++) {
            FunctionalLevel level = FunctionalLevelMother.init().build();
            level.setFLevel(i);
            entityManager.persist(level);
        }
    }

/*    @Transactional
    private void generateDifferentRoleUsers() {
        User adminUser = UserMother.init().build();
        adminUser.setUsername("admin");
        adminUser.setPassword(passwordEncoder.encode("123"));
        adminUser.setEnabled(true);
        adminUser.setEmail("admin@realdolmen.com");
        adminUser.setFunctionalLevel(levelRepo.findOne(3L));
        entityManager.persist(adminUser);

        User normalUser = UserMother.init().build();
        normalUser.setUsername("user");
        normalUser.setPassword(passwordEncoder.encode("123"));
        normalUser.setEmail("user@realdolmen.com");
        normalUser.setFunctionalLevel(levelRepo.findOne(3L));
        entityManager.persist(normalUser);
    }*/


   /* @Transactional
    private void generateAuthoritiesForDiffrentRoles() {
        Authorities adminRole = AuthoritiesMother.init().build();
        adminRole.setUsername("admin");
        adminRole.setAuthority("ROLE_ADMIN");
        entityManager.persist(adminRole);

        Authorities normal = AuthoritiesMother.init().build();
        normal.setUsername("user");
        normal.setAuthority("ROLE_USER");
        entityManager.persist(normal);
    }*/

    // TODO: implement method using mother and persist entity

}
