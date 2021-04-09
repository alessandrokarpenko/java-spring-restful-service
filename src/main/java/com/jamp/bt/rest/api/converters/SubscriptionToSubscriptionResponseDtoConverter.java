package com.jamp.bt.rest.api.converters;

import com.jamp.bt.rest.api.dto.subscription.Subscription;
import com.jamp.bt.rest.api.dto.subscription.SubscriptionResponseDto;
import org.springframework.core.convert.converter.Converter;

public class SubscriptionToSubscriptionResponseDtoConverter implements Converter<Subscription, SubscriptionResponseDto> {

    @Override
    public SubscriptionResponseDto convert(Subscription subscription) {

        SubscriptionResponseDto subscriptionResponseDto = new SubscriptionResponseDto();
        subscriptionResponseDto.setId(subscription.getId());
        subscriptionResponseDto.setStartDate(String.valueOf(subscription.getStartDate()));
        subscriptionResponseDto.setUserId(subscription.getUser().getId());

        return subscriptionResponseDto;
    }
}