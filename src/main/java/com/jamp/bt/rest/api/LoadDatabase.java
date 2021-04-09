package com.jamp.bt.rest.api;

import com.jamp.bt.rest.api.dto.subscription.Subscription;
import com.jamp.bt.rest.api.dto.user.User;
import com.jamp.bt.rest.api.repositories.SubscriptionRepository;
import com.jamp.bt.rest.api.repositories.UserRepository;
import com.github.javafaker.Faker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.ZoneId;
import java.util.stream.IntStream;

@Configuration
public class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);
    private final Faker faker = new Faker();

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Bean
    CommandLineRunner initDatabase() {

        return args -> {
            IntStream.range(0, 3).forEach(i->userRepository.save(createNewUser()));

            IntStream.range(0, 3).forEach(i->{
                User user = createNewUser();
                userRepository.save(user);
                Subscription subscription = new Subscription(user,
                        faker.date().birthday(0,1).toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                subscriptionRepository.save(subscription);
            });

            userRepository.findAll().forEach(user -> log.info("Preloaded " + user));
            subscriptionRepository.findAll().forEach(s -> log.info("Preloaded " + s));
        };
    }

    private User createNewUser() {
        return new User(faker.name().firstName(), faker.name().lastName(),
                faker.date().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
    }
}
