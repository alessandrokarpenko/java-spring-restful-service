package com.jamp.bt.rest.api.rest;

import com.jamp.bt.rest.api.dto.subscription.Subscription;
import com.jamp.bt.rest.api.dto.subscription.SubscriptionRequestDto;
import com.jamp.bt.rest.api.dto.subscription.SubscriptionResponseDto;
import com.jamp.bt.rest.api.service.api.SubscriptionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.ConversionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/subscriptions")
@Tag(name = "Subscription service", description = "API for interaction with Subscriptions")
public class ServiceController {

    @Autowired()
    private SubscriptionService subscriptionService;

    @Autowired()
    @Qualifier("mvcConversionService")
    private ConversionService conversionService;

    public ServiceController() {
    }

    @PostMapping()
    @Operation(summary = "Create new subscription")
    public SubscriptionResponseDto createSubscription(@RequestBody SubscriptionRequestDto subscriptionRequestDto) {
        Subscription subscription = subscriptionService.convert(subscriptionRequestDto);
        subscriptionService.createSubscription(subscription);
        return conversionService.convert(subscription, SubscriptionResponseDto.class);
    }

    @PutMapping()
    @Operation(summary = "Update subscription")
    public SubscriptionResponseDto updateSubscription(@RequestBody SubscriptionRequestDto subscriptionRequestDto) {
        Subscription subscription = subscriptionService.convert(subscriptionRequestDto);
        return conversionService.convert(subscriptionService.updateSubscription(subscription), SubscriptionResponseDto.class);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete subscription")
    public void deleteSubscription(@PathVariable Long id) {
        subscriptionService.deleteSubscription(id);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get subscription")
    public SubscriptionResponseDto getSubscription(@PathVariable Long id) {
        return conversionService.convert(subscriptionService.findById(id), SubscriptionResponseDto.class);
    }

    @GetMapping()
    @Operation(summary = "Get all subscriptions")
    public List<SubscriptionResponseDto> getAllSubscription() {
        List<Subscription> subscriptions = subscriptionService.getAllSubscriptions();
        return subscriptions.stream().map(x -> conversionService.convert(x, SubscriptionResponseDto.class))
                .collect(Collectors.toList());
    }


}
