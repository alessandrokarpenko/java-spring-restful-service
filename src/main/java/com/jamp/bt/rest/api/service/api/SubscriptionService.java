package com.jamp.bt.rest.api.service.api;

import com.jamp.bt.rest.api.dto.subscription.Subscription;
import com.jamp.bt.rest.api.dto.subscription.SubscriptionRequestDto;

import java.util.List;

public interface SubscriptionService {

    Subscription createSubscription(Subscription user);
    Subscription updateSubscription(Subscription user);
    void deleteSubscription(long id);
    Subscription findById(long id);
    List<Subscription> getAllSubscriptions();
    Subscription convert(SubscriptionRequestDto subscriptionRequestDto);

}
