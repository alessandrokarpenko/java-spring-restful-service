package com.jamp.bt.rest.api.repositories;

import com.jamp.bt.rest.api.dto.subscription.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

}
