package com.jamp.bt.rest.api.converters;

import com.jamp.bt.rest.api.dto.subscription.Subscription;
import com.jamp.bt.rest.api.dto.subscription.SubscriptionRequestDto;
import com.jamp.bt.rest.api.repositories.SubscriptionRepository;
import com.jamp.bt.rest.api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.NoSuchElementException;

@Component
public class SubscriptionRequestDtoToSubscriptionConverter implements Converter<SubscriptionRequestDto, Subscription> {

    @Autowired()
    private UserRepository userRepository;

    @Autowired()
    private SubscriptionRepository subscriptionRepository;

    @Override
    public Subscription convert(SubscriptionRequestDto subscriptionRequestDto) {
        long sId = subscriptionRequestDto.getId();
        var sub = subscriptionRepository.findById(sId);
        if (sub.isPresent()) {
            return sub.get();
        } else {
            var user = userRepository.findById(subscriptionRequestDto.getUserId());
            if (user.isPresent()) {
                return new Subscription(user.get(), LocalDate.now());
            } else {
                throw new NoSuchElementException("No user with id " + subscriptionRequestDto.getUserId());
            }
        }
    }
}
