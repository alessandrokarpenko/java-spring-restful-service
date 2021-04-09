package com.jamp.bt.rest.api.service.impl;

import com.jamp.bt.rest.api.dto.subscription.Subscription;
import com.jamp.bt.rest.api.dto.subscription.SubscriptionRequestDto;
import com.jamp.bt.rest.api.repositories.SubscriptionRepository;
import com.jamp.bt.rest.api.repositories.UserRepository;
import com.jamp.bt.rest.api.service.api.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {

    @Autowired()
    private SubscriptionRepository subscriptionRepository;

    @Autowired()
    private UserRepository userRepository;

    @Override
    public Subscription createSubscription(Subscription subscription) {
        if (subscription.getId() != null && subscriptionRepository.findById(subscription.getId()).isPresent()) {
            throw new RuntimeException(String.format("Subscription with id %s already exists", subscription.getId()));
        }
        subscriptionRepository.save(subscription);
        return subscription;
    }

    @Override
    public Subscription updateSubscription(Subscription targetSubscription) {
        Optional<Subscription> optSubscription = subscriptionRepository.findById(targetSubscription.getId());
        if (optSubscription.isPresent()) {
            Subscription subscription = optSubscription.get();
            subscription.setStartDate(targetSubscription.getStartDate());
            subscription.setUser(targetSubscription.getUser());
            subscriptionRepository.save(subscription);
            return subscription;
        } else {
            throw new NoSuchElementException("No subscription with ID " + targetSubscription.getId());
        }
    }

    @Override
    public void deleteSubscription(long id) {
        subscriptionRepository.deleteById(id);
    }

    @Override
    public Subscription findById(long id) {
        return subscriptionRepository.findById(id).orElseThrow(() -> new NoSuchElementException("No subscription with ID " + id));
    }

    @Override
    public List<Subscription> getAllSubscriptions() {
        return subscriptionRepository.findAll();
    }

    public Subscription convert(SubscriptionRequestDto subscriptionRequestDto) {
        long sId = subscriptionRequestDto.getId();
        var sub = subscriptionRepository.findById(sId);
        var user = userRepository.findById(subscriptionRequestDto.getUserId());

        if (user.isEmpty()) {
            throw new NoSuchElementException("No user with id " + subscriptionRequestDto.getUserId());
        }

        if (sub.isPresent()) {
            return new Subscription(subscriptionRequestDto.getId(), user.get(), LocalDate.now());
        } else {
            return new Subscription(user.get(), LocalDate.now());
        }
    }
}
